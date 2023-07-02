import { useGetApi } from "./useApi";

const URL: string = "http://localhost:8000/tennis-api/dates";

export const useGetDates = () => {
    return useGetApi<GetDatesQueryParams, Date[]>(URL);
}

export type Date = {
    year: string;
    month: string;
    date: string;
}

export type GetDatesQueryParams = {
    year: string;
    month: string;
}

export const toDateString = (dateObj: Date) => {
    return `${dateObj.year}/${dateObj.month}/${dateObj.date}`;
}