import axios from "axios";

const url: string = process.env.TODO_API_URL;
export const todoApi = axios.create({baseURL: `${url}/api/`})