import axios from "axios";

export const todoApi = axios.create({baseURL: `${import.meta.env.VITE_TODO_API_URL}`})