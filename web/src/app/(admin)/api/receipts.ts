import {PageResponse} from "@/types/pagination";
import {Receipt} from "@/types/receipt";

const url = new URL(`${process.env.API_HOST}/api/v1/receipts`)
export const fetchReceipts = async (): Promise<PageResponse<Receipt>> => {
    const res = await fetch(url, {
        method: "GET",
    })

    if (!res.ok) {
        throw new Error(`Fetch failed - (${res.status}) ${res.statusText}`);
    }
    return res.json().then(result => result.data);
}

export const createReceipt = async (body: Receipt) => {
    const res = await fetch(url, {
        method: "POST",
        body: JSON.stringify(body)
    })

    const resJSON = await res.json();

    if (!res.ok) {
        throw (JSON.stringify(resJSON.error.message) + JSON.stringify(resJSON.error.errors))
    }

    return resJSON;
}
