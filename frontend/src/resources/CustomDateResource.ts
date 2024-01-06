import { Empty, useGetApi } from "./useApi";

const URL: string = "http://localhost:8000/tennis-api/dates";

export const useGetDates = () => {
    return useGetApi<GetDatesQueryParams | Empty, CustomDate[]>(URL);
}

export type CustomDate = {
    year: string;
    month: string;
    date: string;
}

export type GetDatesQueryParams = {
    year: string;
    month: string;
}

export const toDateId = (dateObj: CustomDate): string => {
    const paddedMonth = dateObj.month.toString().padStart(2, '0');
    const paddedDate = dateObj.date.toString().padStart(2, '0');
    return `${dateObj.year}${paddedMonth}${paddedDate}`;
}

export const toDateValue = (dateObj: CustomDate): string => {
    return `${dateObj.year}/${dateObj.month}/${dateObj.date}`;
}

export const dateEquals = (date1: CustomDate, date2: CustomDate): boolean => {
    return date1.year === date2.year &&
        date1.month === date2.month &&
        date1.date === date2.date;
}

export const compareDates = (date1: CustomDate, date2: CustomDate): number => {
    if (date1.year !== date2.year) {
        return Number(date1.year) - Number(date2.year);
    }
    if (date1.month !== date2.month) {
        return Number(date1.month) - Number(date2.month);
    }
    return Number(date1.date) - Number(date2.date);
};
