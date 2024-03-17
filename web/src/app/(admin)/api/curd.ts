import axios from "axios";
import {PageResponse} from "@/types/pagination";

export const useCrudApi = <CT, RT, LT, UT>(path: string) => {
    const url = `${process.env.API_HOST}/${path}`;
    const getOne = async (id: string) => {
        return axios.get<RT>(`${url}/${id}`);
    }
    const getList = async () => {
        return axios.get<PageResponse<LT>>(url);
    }
    const create = async (body: CT) => {
        return axios.post(url, body)
    }
    const update = async (id: string, body: UT) => {
        return axios.put(`${url}/${id}`, body)
    }
    const deleteOne = async (id: string) => {
        return axios.delete(`${url}/${id}`)
    }
    return {create, getOne, getList, update, deleteOne};
}
