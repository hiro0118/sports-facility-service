import { Empty, useGetApi } from "./useApi";

const URL: string = "http://localhost:8000/tennis-api/days";

export const useGetDays = () => {
    return useGetApi<Empty, Day[]>(URL);
}

export type Day = {
    id: string;
    name: string;
    shortName: string;
}