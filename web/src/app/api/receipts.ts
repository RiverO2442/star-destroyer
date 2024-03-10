import {Receipt} from "@/types/receipt";

export const fetchReceipts = async (adminEmail: string):Promise<Receipt[]> => {
    const url = new URL(
        `${process.env.API_HOST}/api/v1/receipts/`
    )
    const res = await fetch(url, {
        method: "GET",
    })

    if (!res.ok) {
        throw new Error(`Error fetching receipts - (${res.status}) ${res.statusText}`);
    }
    return res.json().then(result => result.data);
}