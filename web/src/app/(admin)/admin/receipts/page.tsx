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


                <h1 className="text-2xl mb-6">Receipts</h1>
                <ReceiptInfiniteScroll/>
                <ul
                    className="
                        gap-y-2
                        w-full 
                        flex flex-row flex-wrap 
                        justify-around">


                    {
                        categories.map(category => (
                            <li
                                className="
                                w-[20%]
                                group h-[80px]
                                xs:h-[50px]
                                relative
                                "
                                key={category.id}>
                                <div className="
                                bg-opacity-5 bg-black
                                dark:bg-opacity-5 dark:bg-white
                                justify-items-center place-items-center
                                border border-slate-300 dark:border-slate-700
                                grid  w-full h-full overflow-hidden
                                ">

                                    <div>{category.name} | {category.products}</div>

                                </div>

                                <Link

                                    href={`/admin/categories/edit/${category.id}`}
                                    className="
                                backdrop-blur-sm
                                bg-slate-200 bg-opacity-20
                                dark:bg-slate-800 dark:bg-opacity-20
                                w-full h-full
                                group-hover:justify-center group-hover:items-center
                                hidden group-hover:flex absolute bottom-0 ">
                                    Press to edit
                                </Link>
                            </li>
                        ))
                    }


                </ul>

                <div className="flex flex-row-reverse justify-between w-full">

                    <div className="flex flex-col gap-y-4 mt-2">
                        <Button href="/admin/receipts/create/new"
                                variant="contained"
                                color="primary"
                                component={Link}>
                            Create new
                        </Button>
                    </div>

                </div>

            </div>

        </main>

    )
}

export default CategoryList;