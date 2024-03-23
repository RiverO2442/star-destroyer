import Link from "next/link";
import {CreateItemForm} from "./CreateItemForm";
import React from "react";
import {FailureMessage, SuccessMessage} from "@/components/UserAlert";


const ReceiptList = async ({params, searchParams}: {
    params: { status: string },
    searchParams: { error: string, message: string }
}) => {

    const status = params.status;

    return (
        <main className="
            w-full
            overflow-x-hidden
            flex flex-col items-center 
            justify-start relative
            ">

            <h1 className="text-2xl my-2">Create new item</h1>


            {status == "success" &&
                <SuccessMessage message={searchParams.message}/>}

            {status == "fail" &&
                <FailureMessage error={searchParams.error}/>}


            <div className="
            bg-slate-200 py-4 rounded-md
            dark:bg-slate-800
            w-full
            xs:w-3/4
            flex flex-col items-center 
            justify-start">

                <CreateItemForm/>

            </div>

            <Link
                className="hover:underline"
                href="/admin">
                Back to Dashboard
            </Link>

        </main>

    )
}


export default ReceiptList;