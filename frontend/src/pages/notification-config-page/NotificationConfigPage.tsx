import { Box, Button, Checkbox, Chip, Container, Divider, FormControlLabel, Grid, Stack, Switch, Typography } from '@mui/material';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { useEffect, useState } from 'react';
import { CustomDate, compareDates, dateEquals, toDateId, toDateValue, useGetDates } from '../../resources/CustomDateResource';
import { Park, useGetParks } from '../../resources/ParkResource';
import { ConfigSection } from './ConfigSection';

const DAYS = ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"];

export const NotificationConfigPage = () => {
  const [allDates, setAllDates] = useState<CustomDate[]>([]);
  const [allParks, setAllParks] = useState<Park[]>([]);

  const [enabled, setEnabled] = useState<boolean>(true);
  const [selectedDays, setSelectedDays] = useState<string[]>([]);
  const [dateExclusions, setDateExclusions] = useState<CustomDate[]>([]);

  // API hooks
  const sendGetDatesRequest = useGetDates();
  const sendGetParksRequest = useGetParks();

  // Initialization
  useEffect(() => {
    sendGetDatesRequest({}, (dates: CustomDate[]) => setAllDates(dates));
    sendGetParksRequest({}, (parks: Park[]) => setAllParks(parks));
  }, []);

  const onEnabled = (event: React.ChangeEvent<HTMLInputElement>, checked: boolean) => {
    setEnabled(checked);
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
    console.log("onReset");
  }

  const onSave = () => {
    console.log("onSave");
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
                  control={<Checkbox />}
                  key={day}
                  label={day}
                  disabled={!enabled}
                  sx={{ m: 1 }}
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
                  control={<Checkbox />}
                  label={park.name}
                  disabled={!enabled}
                  sx={{ m: 1 }}
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
