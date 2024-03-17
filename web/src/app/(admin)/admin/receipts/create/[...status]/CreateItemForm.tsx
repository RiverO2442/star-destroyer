import {getServerSession} from "next-auth/next";
import {ImageInput} from "./imageInputField";
import {useCrudApi} from "@/app/(admin)/api/curd";
import {Receipt, ReceiptSummary} from "@/types/receipt";
import React from "react";
import {CoolInput} from "@/components/CoolInput";
import {CoolButton} from "@/components/Global";

export const CreateItemForm = async () => {

    // const categories = await fetchCategoriesADMIN();
    const {getList} = useCrudApi<Receipt, Receipt, ReceiptSummary, Receipt>("/api/v1/receipts");
    const receiptPage = await getList();
    const session = await getServerSession();


    return (
        <form

            id="itemForm"
            action={session ? '/admin/items/create/loading' : "/admin/unauthorized"}
            className="
                    px-2
                    flex flex-col
                    text-lg 
                    gap-y-2">


            <div className="flex justify-center">

                <ImageInput/>

            </div>

            <div
                className="flex flex-wrap">
                Item Name:&ensp;
                <CoolInput>
                    <input
                        required
                        minLength={4}
                        maxLength={20}
                        name="name"/>
                </CoolInput>
            </div>
            <div
                className="flex flex-wrap">
                Permalink:&ensp;
                <CoolInput>
                    <input
                        pattern="([^ \W \s ]|-)+"
                        title="Permalink shouldn't contain any whitespaces. You can use '_' or '-' e.g. 'cool_item'"
                        minLength={4}
                        name="permalink"/>
                </CoolInput>
            </div>
            <div
                className="flex flex-wrap">
                Active:&ensp;
                <input
                    type="checkbox"
                    name="active"
                    className="text-inherit bg-inherit hover:bg-slate-50 dark:hover:bg-slate-950"/>
            </div>

            <div
                className="flex flex-wrap">
                Item SKU:&ensp;
                {/* better 8-12 */}
                <CoolInput>
                    <input
                        minLength={8}
                        maxLength={12}
                        name="sku"/>
                </CoolInput>

            </div>
            <div
                className="flex flex-wrap">
                Description:&ensp;
                <CoolInput>
                    <input
                        minLength={10}
                        name="description"/>
                </CoolInput>

            </div>
            <div
                className="flex flex-wrap">
                Price:&ensp;
                <CoolInput>

                    <input
                        required

                        type="number"
                        className="w-[80px]"
                        step={0.01}
                        min={0.5}
                        name="price"/>
                </CoolInput>
                Euro
            </div>
            <div
                className="flex flex-wrap">
                Managed Stock:&ensp;
                <input
                    className="peer"
                    name="managed"
                    type="checkbox"/>

                <div
                    className=
                        " hidden peer-checked:flex">

                    <CoolInput>
                        <input
                            className="w-14"
                            type="number"
                            name="available"
                            min={0}
                        />
                    </CoolInput>
                    Left

                </div>

            </div>
            <div
                className="flex flex-wrap">

                Categories:&ensp;
                <ul className="grid grid-cols-2 gap-x-2">
                    {receiptPage.data.items.map(
                        receipt => (
                            <li key={receipt.slug}>
                                <input
                                    name={`category.${receipt.id}`}
                                    type="checkbox"/>{receipt.name}
                            </li>
                        )
                    )}
                </ul>

                {/* {item.categories.map((category,index)=><span key={index}>{index>0&&","}{category.name}</span>)} */}

            </div>

            <div
                className="flex flex-wrap justify-center">

                <CoolButton>
                    <input
                        defaultValue={"Submit"}
                        type="submit"
                        className="
                            cursor-pointer
                            w-44 h-10 
                            rounded-md
                            dark:bg-transparent
                            capitalize"/>
                    {/* {loading?"Loading":"Make Changes"} */}
                </CoolButton>
            </div>


        </form>
    )

}




