export interface ReceiptSummary {
   id: string,
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
