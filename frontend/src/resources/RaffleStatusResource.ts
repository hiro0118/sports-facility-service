import { useGetApi } from "./useApi";

const URL: string = "http://localhost:8000/tennis-api/raffle-status";

export const useGetRaffleStatus = () => {
    return useGetApi<GetRafflestatusParams, RaffleStatus[]>(URL);
}

export type GetRafflestatusParams = {
    date?: string[],
    time?: string[],
    parkId?: string[],
}

export type RaffleStatus = {
    date: string,
    time: string,
    parkId: string,
    parkName: string,
    numOfCourts: number,
    numOfApplications: number,
    ratio: number,
}
