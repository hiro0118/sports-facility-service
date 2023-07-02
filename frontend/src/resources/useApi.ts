import axios from "axios";

type Params<T>  ={
    id?: string,
    subResource?: string,
    queries?: T,
}

type Handler<T> = (res: T) => void;

export const useGetApi = <T, U>(
    baseUrl: string
): ((parms: Params<T>, handler: Handler<U>) => void) => {
    const sendRequest = (params: Params<T>, handler: Handler<U>) => {
        const id = params.id ? `/${params.id}` : "";
        const subResource = params.subResource ? `/${params.subResource}` : "";
        const queryParams = params.queries ? `?${encodeQueryParams(params.queries)}` : "";
        const url = baseUrl + id + subResource + queryParams;
        axios.get(url).then((response) => {
            console.log(response.data);
            handler(response.data);
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

