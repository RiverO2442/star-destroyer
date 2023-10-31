import axios from "axios";

console.log(
    import.meta.env.TODO_API_URL
)
const url: string = process.env.TODO_API_URL ? process.env.TODO_API_URL : "http://localhost:8080";
export const todoApi = axios.create({baseURL: `${url}/api/`})