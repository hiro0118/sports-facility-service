import { IconButton, Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, TableSortLabel } from "@mui/material";
import { FC, memo, useState } from "react";
import StarIcon from '@mui/icons-material/Star';
import { CourtData } from "./TennisCourtsPage";
import { RaffleStatus } from "../../resources/RaffleStatusResource";

export const MAX_DISPLAY_NUM = 100;

interface Props {
  dataList: RaffleStatus[],
  favedItemSet: Set<string>,
  favedItemOnly: boolean,
  onFaved: any,
}

const getSortedData = (dataList: RaffleStatus[], asc: boolean): RaffleStatus[] => {
  const newDataList = [...dataList];
  return newDataList.sort(
    (a, b) => asc ? (b.ratio - a.ratio) : (a.ratio - b.ratio)
  );
}

export const SortableTable: FC<Props> = memo((props: Props) => {

  console.log("SortableTable updated!")

  const [asc, setAsc] = useState<boolean>(false);

  const onRatioSort = () => {
    setAsc(!asc);
    props.dataList.sort(
      (a, b) =>
        (asc ? (b.ratio - a.ratio) : (a.ratio - b.ratio)) ||
        a.date.localeCompare(b.date) ||
        ((a.time.length - b.time.length) || a.time.localeCompare(b.time)) ||
        a.parkId.localeCompare(b.parkId)
    );
  }

  return (
    <TableContainer component={Paper}>
      <Table sx={{ minWidth: 650 }} size="small" aria-label="simple table">
        <TableHead>
          <TableRow>
            <TableCell> </TableCell>
            <TableCell>Date</TableCell>
            <TableCell>Time</TableCell>
            <TableCell>Park</TableCell>
            <TableCell align="right">Courts</TableCell>
            <TableCell align="right">Applications</TableCell>
            <TableCell align="right">
              <TableSortLabel
                active={true}
                direction={asc ? "asc" : "desc"}
                onClick={onRatioSort}
              >
                Ratio
              </TableSortLabel>
            </TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {getSortedData(props.dataList, asc)
            .filter(data => props.favedItemOnly ? props.favedItemSet.has(keyOf(data)) : true)
            .filter((_value, index) => index < MAX_DISPLAY_NUM)
            .map((data) => {
              const dataKey = keyOf(data);
              return (
                <TableRow
                  key={dataKey}
                  sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                >
                  <TableCell align="center" sx={{ padding: 0, pl: 1, my: 0 }}>
                    <IconButton sx={{ padding: 0, margin: 0 }} size="small" onClick={(e) => props.onFaved(dataKey)}>
                      <StarIcon fontSize="small" color={props.favedItemSet.has(dataKey) ? "warning" : "disabled"} />
                    </IconButton>
                  </TableCell>
                  <TableCell component="th" scope="row">{data.date}</TableCell>
                  <TableCell>{data.time}</TableCell>
                  <TableCell>{data.parkId}</TableCell>
                  <TableCell align="right">{data.numOfCourts}</TableCell>
                  <TableCell align="right">{data.numOfApplications}</TableCell>
                  <TableCell align="right">{String(data.ratio).slice(0, 5)}</TableCell>
                </TableRow>
              )
            })}
        </TableBody>
      </Table>
    </TableContainer>
  );
});

const keyOf = (data: RaffleStatus): string => {
  return `${data.date}-${data.time}-${data.parkId}`
}