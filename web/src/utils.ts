import {cache} from "react";

export const getItem = cache(async (id: string) => {
    let db = [{}];
    return db[0]
})
