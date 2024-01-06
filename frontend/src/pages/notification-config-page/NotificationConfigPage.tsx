import { Box, Button, Checkbox, Chip, Container, Divider, FormControlLabel, Grid, Stack, Switch, Typography } from '@mui/material';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { useEffect, useState } from 'react';
import { CustomDate, compareDates, dateEquals, toDateId, toDateValue, useGetDates } from '../../resources/CustomDateResource';
import { Park, useGetParks } from '../../resources/ParkResource';
import { ConfigSection } from './ConfigSection';
import { Time, useGetTimes } from '../../resources/TimeResource';
import { NotificationConfig, useGetNotificationConfigById, usePutNotificationConfigsById } from '../../resources/NotificationConfigResource';

const DAYS = ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"];

export const NotificationConfigPage = () => {
  // States
  const [userId, setUserId] = useState<string>('');
  const [allTimes, setAllTimes] = useState<Time[]>([]);
  const [allParks, setAllParks] = useState<Park[]>([]);

  const [notificationConfig, setNotificationConfig] = useState<NotificationConfig>();

  const [enabled, setEnabled] = useState<boolean>(true);
  const [selectedDays, setSelectedDays] = useState<string[]>([]);
  const [selectedTimes, setSelectedTimes] = useState<string[]>([]);
  const [selectedParks, setSelectedParks] = useState<string[]>([]);
  const [dateExclusions, setDateExclusions] = useState<CustomDate[]>([]);

  // API hooks
  const sendGetTimesRequest = useGetTimes();
  const sendGetParksRequest = useGetParks();
  const sendGetNotificationConfigById = useGetNotificationConfigById();
  const sendPutNotificationConfigById = usePutNotificationConfigsById();

  // Initialization
  useEffect(() => {
    const user = "UserA";
    setUserId(user);
    sendGetTimesRequest({}, (times: Time[]) => setAllTimes(times));
    sendGetParksRequest({}, (parks: Park[]) => setAllParks(parks));
    sendGetNotificationConfigById(
      user,
      (config: NotificationConfig) => {
        setNotificationConfig(config);
        if (config) setConfig(config);
      }
    );
  }, []);

  const setConfig = (config: NotificationConfig | undefined) => {
    setEnabled(config?.enabled ?? false);
    setSelectedDays(config?.dayList ?? []);
    setSelectedTimes(config?.timeList ?? []);
    setSelectedParks(config?.parkList ?? []);
    setDateExclusions(config?.dateExclusionList ?? []);
  }

  // Event Handlers
  const onEnabled = (event: React.ChangeEvent<HTMLInputElement>, checked: boolean) => {
    setEnabled(checked);
  }

  const onDayChecked = (checked: string) => {
    const newSelections = getNewSelections(selectedDays, checked);
    setSelectedDays(newSelections);
  }

  const onTimeChecked = (checked: string) => {
    const newSelections = getNewSelections(selectedTimes, checked);
    setSelectedTimes(newSelections);
  }

  const onParkChecked = (checked: string) => {
    const newSelections = getNewSelections(selectedParks, checked);
    setSelectedParks(newSelections);
  }

  const getNewSelections = (currentSelections: string[], checked: string): string[] => {
    const wasSelected = currentSelections.includes(checked);
    return wasSelected ?
      currentSelections.filter(s => (s !== checked)) :
      [...currentSelections, checked];
  }

  const onExclusionSelected = (dayjs: any) => {
    const added: CustomDate = {
      year: dayjs.year().toString(),
      month: (dayjs.month() + 1).toString(),
      date: dayjs.date().toString(),
    }

    const mergedDates = [...dateExclusions, added];
    const uniqueDates: CustomDate[] = [];
    mergedDates.forEach((date) => {
      if (!uniqueDates.some((uniqueDate) => dateEquals(date, uniqueDate))) {
        uniqueDates.push(date);
      }
    });

    const sortedUniqueDates = uniqueDates.slice().sort(compareDates);
    setDateExclusions(sortedUniqueDates);
  }

  const onExclusionDeleted = (deleted: CustomDate) => {
    const newExclusions = [...dateExclusions]
      .filter(date => !dateEquals(date, deleted));
    setDateExclusions(newExclusions);
  }

  const onRemoveOld = () => {
    const date = new Date();
    const currentYear = date.getFullYear();
    const currentMonth = date.getMonth();
    const newExclusions = [...dateExclusions]
      .filter(date => {
        if (Number(date.year) === currentYear) {
          return Number(date.month) > currentMonth;
        } else {
          return Number(date.year) > currentYear;
        }
      });
    setDateExclusions(newExclusions);
  }

  const onReset = () => {
    setConfig(notificationConfig);
  }

  const onSave = () => {
    const newConfig: NotificationConfig = {
      userId: userId,
      enabled: enabled,
      dayList: selectedDays,
      timeList: selectedTimes,
      parkList: selectedParks,
      dateExclusionList: dateExclusions,
    }
    sendPutNotificationConfigById(userId, newConfig);
  }

  return (
    <>
      <Typography variant="h3" padding={3} align="center">Notification Configuration</Typography>
      <Container sx={{ mb: 2 }}>

        <ConfigSection title="Notifications">
          <FormControlLabel
            control={
              <Switch
                checked={enabled}
                onChange={onEnabled}
                sx={{ m: 1 }}
              />}
            label={enabled ? "ON" : "OFF"} />
        </ConfigSection>

        <ConfigSection title="Days">
          <Stack direction="row">
            {DAYS.map(day => {
              return (
                <FormControlLabel
                  control={
                    <Checkbox
                      checked={selectedDays.includes(day)}
                      onChange={() => onDayChecked(day)}
                    />
                  }
                  key={day}
                  label={day}
                  disabled={!enabled}
                  sx={{ m: 0.5 }}
                />
              )
            })}
          </Stack>
        </ConfigSection>

        <ConfigSection title="Times">
          <Stack direction="row">
            {allTimes.map(time => {
              return (
                <FormControlLabel
                  control={
                    <Checkbox
                      checked={selectedTimes.includes(time.time)}
                      onChange={() => onTimeChecked(time.time)}
                    />
                  }
                  key={time.time}
                  label={time.time}
                  disabled={!enabled}
                  sx={{ m: 0.5 }}
                />
              )
            })}
          </Stack>
        </ConfigSection>

        <ConfigSection title="Parks">
          <Stack direction="row">
            {allParks.map(park => {
              return (
                <FormControlLabel
                  key={park.id}
                  control={
                    <Checkbox
                      checked={selectedParks.includes(park.id)}
                      onChange={() => onParkChecked(park.id)}
                    />
                  }
                  label={park.name}
                  disabled={!enabled}
                  sx={{ m: 0.5 }}
                />
              );
            })}
          </Stack>
        </ConfigSection>

        <ConfigSection title="Date Exclusions">
          <Grid
            container
            direction="row"
            alignItems="center"
          >
            <LocalizationProvider dateAdapter={AdapterDayjs}>
              <DatePicker
                onAccept={(dayjs) => onExclusionSelected(dayjs)}
                format="YYYY/MM/DD"
                disabled={!enabled}
                sx={{ m: 1 }}
              />
            </LocalizationProvider>
            <Button
              size="large"
              onClick={onRemoveOld}
              disabled={!enabled}
            >
              Remove Old
            </Button>
          </Grid>
          <Stack direction="row" sx={{ m: 1 }}>
            {dateExclusions.map(ex => {
              const id = toDateId(ex);
              const value = toDateValue(ex);
              return (
                <Chip
                  key={id}
                  id={id}
                  label={value}
                  onDelete={() => onExclusionDeleted(ex)}
                  disabled={!enabled}
                  sx={{ m: 0.5 }}
                />
              );
            })
            }
          </Stack>
        </ConfigSection>

        <Grid
          container
          direction="row"
          justifyContent="flex-end"
        >
          <Button
            variant="outlined"
            size="large"
            onClick={onReset}
            sx={{ mx: 1 }}
          >
            Reset
          </Button>
          <Button
            variant="contained"
            size="large"
            onClick={onSave}
            sx={{ mx: 1 }}
          >
            Save
          </Button>
        </Grid>

      </Container>
    </>
  );
}
