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

export const toDateId = (dateObj: Date) => {
    const paddedMonth = dateObj.month.toString().padStart(2, '0');
    const paddedDate = dateObj.date.toString().padStart(2, '0');
    return `${dateObj.year}${paddedMonth}${paddedDate}`;
}

export const toDateValue = (dateObj: Date) => {
    return `${dateObj.year}/${dateObj.month}/${dateObj.date}`;
}