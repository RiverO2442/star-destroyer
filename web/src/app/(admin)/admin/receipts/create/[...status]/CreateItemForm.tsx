"use client";
import {useCrudApi} from "@/app/(admin)/api/curd";
import {CreateReceiptRequest, Receipt, ReceiptSummary} from "@/types/receipt";
import React, {useEffect, useState} from "react";
import {MetadataItem} from "@/types/pagination";
import {useSession} from "next-auth/react";
import {CoolInput} from "@/components/CoolInput";
import {ImageInput} from "@/app/(admin)/admin/receipts/create/[...status]/imageInputField";
import {SubmitButton} from "@/components/Global";

export const CreateItemForm = () => {
    const {create} = useCrudApi<CreateReceiptRequest, Receipt, ReceiptSummary, Receipt>("/api/v1/receipts");
    const {getList: getMembers} = useCrudApi<undefined, undefined, MetadataItem, undefined>("/api/v1/members");

    const [members, setMembers] = useState<MetadataItem[]>([]);
    const {data: session} = useSession();

    async function handleSubmit(formData: FormData) {
        console.log(formData)
        await create({
            name: formData.get("name") as string,
            amount: parseFloat(formData.get("amount") as string),
            consumers: [],
            description: formData.get("description") as string,
        })
        await new Promise(r => setTimeout(r, 1000));
    }

    useEffect(() => {
        console.log(session)
        getMembers().then(resp => {
            setMembers(resp.data)
        })
    }, [session])

    return (
        <form
            id="itemForm"
            action={handleSubmit}
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
                Description:&ensp;
                <CoolInput>
                    <input
                        minLength={10}
                        name="description"/>
                </CoolInput>

            </div>
            <div
                className="flex flex-wrap">
                Amount:&ensp;
                <CoolInput>

                    <input
                        required

                        type="number"
                        className="w-[80px]"
                        step={0.01}
                        min={0.5}
                        name="amount"/>
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
                    className="hidden peer-checked:flex">
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
                Consumers:&ensp;
                <ul className="grid grid-cols-2 gap-x-2">
                    {members.map(
                        member => (
                            <li key={member.id}>
                                <input
                                    name={`category.${member.id}`}
                                    type="checkbox"/>{member.name}
                            </li>
                        )
                    )}
                </ul>

                {/* {item.categories.map((category,index)=><span key={index}>{index>0&&","}{category.name}</span>)} */}

            </div>

            <div
                className="flex flex-wrap justify-center">
                <SubmitButton variant="contained" type="submit"/>
            </div>


        </form>
    )

}




