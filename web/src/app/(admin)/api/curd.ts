"use client";

import axios from "axios";
import {DysonSession} from "@/types/auth";
import {useSession} from "next-auth/react";
import {PageResponse} from "@/types/pagination";

export const useCrudApi = <CT, RT, LT, UT>(path: string) => {
    const url = `${path}`;
    const {data: session} = useSession();

    const axiosInstance = axios.create({baseURL: process.env.NEXT_PUBLIC_API_HOST});

    // request interceptor for adding token
    axiosInstance.interceptors.request.use(config => {
        // add token to request headers
        console.log("Current session:", session)
        let accessToken = session != null ? (session as DysonSession).accessToken : null;
        config.headers['Authorization'] = `Bearer ${accessToken}`;
        return config;
    }, async error => {
        console.log("Axios error: ", error);
        return Promise.reject(error)
    });

    const getOne = async (id: string) => {
        return axiosInstance.get<RT>(`${url}/${id}`);
    }
    const getList = async (queries?: { [key: string]: string | string[] | number | boolean }) => {
        return axiosInstance.get<LT[]>(url, {params: queries});
    }
    const getPage = async () => {
        return axiosInstance.get<PageResponse<LT>>(url);
    }
    const create = async (body: CT) => {
        return axiosInstance.post(url, body)
    }
    const update = async (id: string, body: UT) => {
        return axiosInstance.put(`${url} /${id}`, body)
    }
    const deleteOne = async (id: string) => {
        return axiosInstance.delete(`${url} /${id}`)
    }
    return {create, getOne, getList, getPage, update, deleteOne};
}
