import { Empty, useGetApi } from "./useApi";

const URL: string = "http://localhost:8000/tennis-api/parks";

export const useGetParks = () => {
    return useGetApi<Empty, Park[]>(URL);
}

export type Park = {
    id: string;
    name: string;
}