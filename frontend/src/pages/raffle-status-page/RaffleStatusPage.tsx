import Typography from '@mui/material/Typography';
import { Box, Button, Container, Tab, Tabs } from '@mui/material';
import Grid from '@mui/material/Unstable_Grid2';
import { FilterAccordion, SELECT_ALL } from './FilterAccordion';
import React, { useCallback, useEffect, useState } from 'react';
import { MAX_DISPLAY_NUM, SortableTable } from './SortableTable';
import { Park, useGetParks } from '../../resources/ParkResource';
import { Time, useGetTimes } from '../../resources/TimeResource';
import { RaffleStatus, useGetRaffleStatus } from '../../resources/RaffleStatusResource';
import { Date, toDateString, useGetDates } from '../../resources/DateResource';

interface TabPanelProps {
  children?: React.ReactNode;
  index: number;
  value: number;
}

export interface CourtData {
  date: string;
  park: string;
  time: string;
  courts: number;
  applications: number;
  ratio: number;
}

const TabPanel = (props: TabPanelProps) => {
  const { children, value, index, ...other } = props;

  return (
    <div
      role="tabpanel"
      hidden={value !== index}
      id={`simple-tabpanel-${index}`}
      aria-labelledby={`simple-tab-${index}`}
      {...other}
    >
      {value === index && (
        <Box sx={{ p: 3 }}>
          <Typography component="span">{children}</Typography>
        </Box>
      )}
    </div>
  );
}

const tabProps = (index: number) => {
  return {
    id: `simple-tab-${index}`,
    'aria-controls': `simple-tabpanel-${index}`,
  };
}

const removeDuplicate = (value: string, index: number, array: string[]) => {
  return array.indexOf(value) === index;
}

const sortTimes = (a: string, b: string) => {
  return (a.length - b.length) || a.localeCompare(b);
}

export const TennisCourtsPage = () => {

  const [allDates, setAllDates] = useState<Date[]>([]);
  const [allTimes, setAllTimes] = useState<Time[]>([]);
  const [allParks, setAllParks] = useState<Park[]>([]);

  const [selectedDateIdSet, setSelectedDateIdSet] = useState<Set<string>>(new Set());
  const [selectedTimeIdSet, setSelectedTimeIdSet] = useState<Set<string>>(new Set());
  const [selectedParkIdSet, setSelectedParkIdSet] = useState<Set<string>>(new Set());

  const [raffleStatus, setRaffleStatus] = useState<RaffleStatus[]>([]);
  const [favedItems, setFavedItems] = useState<Set<string>>(new Set());

  const [tabId, setTabId] = useState(0);

  // API hooks
  const sendGetDatesRequest = useGetDates();
  const sendGetTimesRequest = useGetTimes();
  const sendGetParksRequest = useGetParks();
  const sendGetRaffleStatusRequest = useGetRaffleStatus();

  // Initialization
  useEffect(() => {
    sendGetDatesRequest({}, (dates: Date[]) => setAllDates(dates));
    sendGetTimesRequest({}, (times: Time[]) => setAllTimes(times));
    sendGetParksRequest({}, (parks: Park[]) => setAllParks(parks));
  }, []);

  const onDateChecked = (event: React.ChangeEvent<HTMLInputElement>) => {
    const updatedList = getUpdatedSelections(allDates.map(d => toDateString(d)), selectedDateIdSet, event);
    setSelectedDateIdSet(updatedList);
  }

  const onTimeChecked = (event: React.ChangeEvent<HTMLInputElement>) => {
    const updatedList = getUpdatedSelections(allTimes.map(t => t.id), selectedTimeIdSet, event);
    setSelectedTimeIdSet(updatedList);
  }

  const onParkChecked = (event: React.ChangeEvent<HTMLInputElement>) => {
    const updatedList = getUpdatedSelections(allParks.map(p => p.id), selectedParkIdSet, event);
    setSelectedParkIdSet(updatedList);
  }

  const onFaved = useCallback((key: string) => {
    const newFavedItemSet: Set<string> = new Set(favedItems);
    if (newFavedItemSet.has(key)) {
      newFavedItemSet.delete(key);
    } else {
      newFavedItemSet.add(key);
    }
    setFavedItems(newFavedItemSet);
  }, [favedItems]);

  const onApply = () => {
    sendGetRaffleStatusRequest(
      {
        queries: {
          date: Array.from(selectedDateIdSet),
          time: Array.from(selectedTimeIdSet),
          parkId: Array.from(selectedParkIdSet),
        }
      },
      (raffleStatus) => setRaffleStatus(raffleStatus));
  }

  const handleTabChange = (event: React.SyntheticEvent, newValue: number) => {
    setTabId(newValue);
  };

  return (
    <>
      <Typography variant="h3" padding={3} align="center">Raffle Status</Typography>
      <Container sx={{ mb: 2 }}>
        <Box sx={{ borderBottom: 1, borderColor: 'divider' }}>
          <Tabs value={tabId} onChange={handleTabChange} aria-label="basic tabs example">
            <Tab label="Search" {...tabProps(0)} />
            <Tab label="Favorites" {...tabProps(1)} />
          </Tabs>
        </Box>
        <TabPanel value={tabId} index={0}>
          <Typography padding={3} align="right">*Showing the first {MAX_DISPLAY_NUM} records.</Typography>
          <Grid container spacing={3} justifyContent="center">
            <Grid xs={12} md={2.8}>
              <FilterAccordion
                id='Dates'
                onSelect={onDateChecked}
                items={allDates.map(d => {
                  const dateStr = toDateString(d);
                  return { id: dateStr, value: dateStr, }
                })}
                selections={selectedDateIdSet}
              />
              <FilterAccordion
                id='Times'
                onSelect={onTimeChecked}
                items={allTimes.map(t => {
                  return { id: t.id, value: t.time, }
                })}
                selections={selectedTimeIdSet}
              />
              <FilterAccordion
                id='Parks'
                onSelect={onParkChecked}
                items={allParks.map(p => {
                  return { id: p.id, value: p.name, }
                })}
                selections={selectedParkIdSet}
              />
              <Button
                variant="contained"
                fullWidth={true}
                onClick={onApply}
                sx={{ mt: 0.8 }}
              >
                Apply
              </Button>
            </Grid>

            <Grid xs={12} md={9.2}>
              <SortableTable
                dataList={raffleStatus}
                favedItemSet={favedItems}
                favedItemOnly={false}
                onFaved={onFaved} />
            </Grid>

          </Grid>
        </TabPanel>
        <TabPanel value={tabId} index={1}>
          <Grid xs={12} md={9.2}>
            <SortableTable
              dataList={raffleStatus}
              favedItemSet={favedItems}
              favedItemOnly={true}
              onFaved={onFaved} />
          </Grid>
        </TabPanel>
      </Container>
    </>
  );
}

const getUpdatedSelections = (
  allItems: string[],
  selections: Set<string>,
  event: React.ChangeEvent<HTMLInputElement>
): Set<string> => {

  const value = event.target.defaultValue;
  const checked = event.target.checked;

  if (value === SELECT_ALL) {
    return (allItems.length === selections.size) ?
      new Set<string>() : new Set<string>(allItems);
  } else {
    const newSelections = new Set<string>(selections);
    checked ? newSelections.add(value) : newSelections.delete(value);
    return newSelections;
  }
}

const getUpdatedSelections2 = (
  allItems: string[],
  selections: Set<string>,
  event: React.ChangeEvent<HTMLInputElement>
): Set<string> => {

  const value = event.target.defaultValue;
  const checked = event.target.checked;

  if (value === SELECT_ALL) {
    const allSelectedAlready = (allItems.length === selections.size);
    return allSelectedAlready ?
      new Set<string>() : new Set<string>(allItems);
  } else {
    const newSelections = new Set<string>(selections);
    checked ? newSelections.add(value) : newSelections.delete(value);
    return newSelections;
  }
}