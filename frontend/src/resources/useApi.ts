import axios from "axios";

type Handler<U> = (res: U) => void;

export type Empty = {};

export const useGetApi = <T, U>(
    baseUrl: string
): ((queries: T, handler: Handler<U>) => void) => {
    const sendRequest = (queries: T, handler: Handler<U>) => {
        const queryParams = queries ? `?${encodeQueryParams(queries)}` : "";
        const url = baseUrl + queryParams;
        axios.get(url).then((response) => {
            console.log("Received: " + JSON.stringify(response.data));
            handler(response.data);
        });
    }
    return sendRequest;
}

export const useGetByIdApi = <U>(
    baseUrl: string
): ((id: string, handler: Handler<U>) => void) => {
    const sendRequest = (id: string, handler: Handler<U>) => {
        const url = `${baseUrl}/${id}`;
        axios.get(url).then((response) => {
            console.log("Received: " + JSON.stringify(response.data));
            handler(response.data);
        });
    }
    return sendRequest;
}

type PutParams<T> = {
    id?: string,
    subResource?: string,
    body?: T,
}

export const userPutByIdApi = <T, U>(
    baseUrl: string
): ((id: string, body: T, handler?: Handler<U>) => void) => {
    const sendRequest = (id: string, body: T, handler?: Handler<U>) => {
        const url = `${baseUrl}/${id}`;
        axios.put(url, body).then((response) => {
            console.log("Received: " + JSON.stringify(response.data));
            if (handler) {
                handler(response.data);
            }
        });
    }
    return sendRequest;
}

const encodeQueryParams = (params: any) => {
    if (!params) return "";
    const encodedParams = [];
    for (const key in params) {
        if (Array.isArray(params[key])) {
            params[key].forEach((value: string | number | boolean) => {
                encodedParams.push(`${encodeURIComponent(key)}=${encodeURIComponent(value)}`);
            });
        } else {
            encodedParams.push(`${encodeURIComponent(key)}=${encodeURIComponent(params[key])}`);
        }
    }
    return encodedParams.join('&');
}

