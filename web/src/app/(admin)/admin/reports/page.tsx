import Link from "next/link";
import {Category} from "@/types/category";
import ReceiptInfiniteScroll from "@/app/(admin)/admin/receipts/ReceiptInfiniteScroll";
import {Button} from "@mui/material";

const CategoryList = async () => {

    const categories: Category[] = [];

    // const categories:Category[]= await fetchCategoriesADMIN()
    //
    // if (!categories || categories.length<1){
    //     redirect('/admin')
    // }


    return (
        <main className="
            w-full
            flex flex-col items-center 
            justify-start
            text-center
            overflow-x-hidden

            ">
            <div className="xs:w-3/4 w-full
            min-h-[60vh] 
            flex flex-col items-center 
            justify-start">


                <div className="flex flex-row-reverse justify-between w-full">

                    <div className="flex flex-col gap-y-4 mt-2">
                        <Button href="/admin/receipts/create/new"
                                variant="contained"
                                color="primary"
                                component={Link}>
                            Make report
                        </Button>
                    </div>

                </div>

                <h1 className="text-2xl mb-6">Receipts</h1>
                <ReceiptInfiniteScroll/>

            </div>

        </main>

    )
}

export default CategoryList;