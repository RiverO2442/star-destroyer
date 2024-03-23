export interface ReceiptSummary {
    id: string,
    name: string,
    amount: number,
    createdDate: Date
}

export type CreateReceiptRequest = {
    name: string,
    amount: number,
    consumers: string[],
    description: string,
}

export interface Receipt {
    name: string,
    moneyAmount: number,
    buyerId: string,
    consumers: string[],
    id: string,
    description: string,
    createDate: Date,
}
