"use client";

import InfiniteScroll from "react-infinite-scroll-component";
import Link from "next/link";
import React, {useEffect, useState} from "react";
import {ReceiptSummary} from "@/types/receipt";
import {useCrudApi} from "@/app/(admin)/api/curd";
import {useSession} from "next-auth/react";
import {PageResponse} from "@/types/pagination";

const ReceiptInfiniteScroll = () => {
    const [receipts, setReceipts] = useState<ReceiptSummary[]>([]);
    const [page, setPage] = useState<PageResponse<ReceiptSummary>>();
    const {getPage} = useCrudApi<undefined, undefined, ReceiptSummary, undefined>("/api/v1/receipts");
    const {data: session} = useSession();

    useEffect(() => {
        fetchMoreData();
    }, [session]);

    const fetchMoreData = () => {
        getPage()
            .then(value => {
                const page = value.data;
                setReceipts(receipts.concat(page.content));
                setPage(page)
            })
            .catch(reason => {
                console.error(reason);
            })
    }
    return <InfiniteScroll style={{}} next={fetchMoreData}
                           hasMore={!page?.last}
                           loader={<h4>...</h4>}
                           dataLength={receipts.length}>
        {
            receipts.map(({id, name, amount, createdDate}) => (
                <li key={id}>
                    <Link href={`/receipts/${id}/edit`}> {name || "untitled receipt"} </Link>
                    <br/>
                    <small>
                        {"" + createdDate + ""}
                        {/*<Date dateString={lastModifiedDate}></Date>*/}
                    </small>
                </li>
            ))
        }
    </InfiniteScroll>
}
export default ReceiptInfiniteScroll;