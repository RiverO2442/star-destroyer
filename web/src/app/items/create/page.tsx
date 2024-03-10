import Link from "next/link";
import {FailureMessage, SuccessMessage} from "@/components/UserAlert";
import {CreateItemForm} from "@/components/item/create/CreateItemForm";

export interface ItemsListPageProps {
    status: string,
    searchParams: {
        error: string,
        message: string
    }
}

const ItemsListPage = async ({searchParams, status}: ItemsListPageProps) => {

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


export default ItemsListPage;