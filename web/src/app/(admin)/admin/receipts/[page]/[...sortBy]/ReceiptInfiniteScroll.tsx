import InfiniteScroll from "react-infinite-scroll-component";
import utilStyles from "@/styles/utils.module.scss";
import Link from "next/link";
import React, {useEffect, useState} from "react";
import {ReceiptSummary} from "@/types/receipt";
import {useCrudApi} from "@/app/(admin)/api/curd";

const ReceiptInfiniteScroll = () => {
    const [receipts, setReceipts] = useState<ReceiptSummary[]>([]);
    const {getPage} = useCrudApi<undefined, undefined, ReceiptSummary, undefined>("/api/v1/receipts");

    useEffect(() => {
        fetchMoreData();
    }, []);

    const fetchMoreData = () => {
        getPage()
            .then(value => {
                const fetchedItems = value.data;
                setReceipts(receipts.concat(fetchedItems.content));
            })
            .catch(reason => {
                console.error(reason);
            })
    }
    return <InfiniteScroll style={{}} next={fetchMoreData}
                           hasMore={true}
                           loader={<h4>...</h4>}
                           dataLength={receipts.length}>
        {
            receipts.map(({id, name, amount, createdDate}) => (
                <li className={utilStyles.listItem} key={id + (Math.random() + 1).toString(36).substring(7)}>
                    <Link href={`/receipts/${id}/edit`}> {name || "untitled receipt"} </Link>
                    <br/>
                    <small className={utilStyles.lightText}>
                        {createdDate.toString()}
                        {/*<Date dateString={lastModifiedDate}></Date>*/}
                    </small>
                </li>
            ))
        }
    </InfiniteScroll>
}
export default ReceiptInfiniteScroll;