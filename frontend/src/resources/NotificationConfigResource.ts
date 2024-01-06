import { CustomDate } from "./CustomDateResource";
import { Empty, useGetByIdApi, userPutByIdApi } from "./useApi";

const URL: string = "http://localhost:8000/tennis-api/notification-configs";

export const useGetNotificationConfigById = () => {
    return useGetByIdApi<NotificationConfig>(URL);
}

export const usePutNotificationConfigsById = () => {
    return userPutByIdApi<NotificationConfig, Empty>(URL);
}

export type NotificationConfig = {
    userId: string
    enabled: boolean,
    dayList: string[],
    timeList: string[],
    parkList: string[],
    dateExclusionList: CustomDate[],
}
