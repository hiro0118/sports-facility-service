import { Empty, useGetApi } from "./useApi";

const URL: string = "http://localhost:8000/tennis-api/times";

export const useGetTimes = () => {
    return useGetApi<Empty, Time[]>(URL);
}

export type Time = {
    time: string;
}