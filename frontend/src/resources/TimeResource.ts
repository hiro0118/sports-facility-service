import { useGetApi } from "./useApi";

const URL: string = "http://localhost:8000/tennis-api/times";

export const useGetTimes = () => {
    return useGetApi<void, Time[]>(URL);
}

export type Time = {
    time: string;
}