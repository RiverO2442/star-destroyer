import {redirect} from "next/navigation";
import LoadingScreen from "@/components/loader";
import {setAsset} from "@/app/(admin)/api/assets";
import {CreateReceiptRequest, Receipt, ReceiptSummary} from "@/types/receipt";
import {useCrudApi} from "@/app/(admin)/api/curd";
import {useSession} from "next-auth/react";
import {useEffect} from "react";

const FormLoader = ({searchParams}: { searchParams: FormData }) => {
    const {data: session} = useSession();
    const email = session?.user?.email || "";
    // const data: CreateReceiptRequest = formDataProcessor(searchParams);
    const {create} = useCrudApi<Receipt, CreateReceiptRequest, ReceiptSummary, Receipt>("/api/v1/receipts");
    let success = false;

    // try {
    //     await create(data)
    //         .then(res => {
    //             data.product_id = res.id
    //         })
    //     success = true;
    //     if (data.assetID) {
    //         await setAsset(data.product_id, data.assetID, email);
    //     }
    //
    // } catch (error) {
    //     redirect(`/admin/items/create/fail?error=${error}`)
    // } finally {
    //     const message = `Successfully created ${data.properties.product.name}.`;
    //     if (success) {
    //         redirect(`/admin/items/edit/${data.product_id}/success?message=${message}`)
    //     }
    // }

    return (
        <>
            <LoadingScreen/>
        </>
    )


}

export default FormLoader;


// export const formDataProcessor = (formData: FormData): CreateReceiptRequest => {
//     const data: CreateReceiptRequest = {
//         product_id: "",
//         assetID: undefined,
//         properties: {
//             product: {
//                 name: "",
//                 permalink: "",
//                 active: false,
//                 sku: null,
//                 description: "",
//                 price: 0,
//                 inventory: {
//                     managed: false,
//                     available: 0,
//                 }
//             },
//             categories: {}
//         }
//
//     };
//
//     let categoryIndex = 0;
//
//     const BooleanFields = ["managed", "active"] as const;
//     const NumberFields = ["price", "available"] as const;
//     const StringFields = ["description", "permalink", "product_id", "name", "sku"] as const;
//     // const NotCategoryKey = [...BooleanFields,...NumberFields,...StringFields];
//
//     for (const entry of Object.entries(formData)) {
//         const [key, value]
//             : [key: string, value: FormDataEntryValue]
//             = entry;
//
//         if (key == "product_id") {
//             data.product_id = String(value);
//         } else if (key == "assetID") {
//             data.assetID = String(value);
//         } else if (key.includes("category.cat")) {
//
//             data.properties.categories = {
//
//                 ...data.properties.categories,
//                 [categoryIndex]: {
//
//                     id: String(key.slice(9))
//
//                 }
//
//             }
//
//             categoryIndex++;
//         } else {
//
//             // @ts-ignore
//             if (BooleanFields.includes(key)) {
//
//                 const fixedValue = value == "on" ? true : value;
//
//
//                 if (key == "managed") {
//                     data.properties.product.inventory.managed = Boolean(fixedValue);
//                 } else {
//
//                     // @ts-ignore
//                     data.properties.product[key] = Boolean(fixedValue)
//                 }
//
//             }
//
//             // @ts-ignore
//             else if (NumberFields.includes(key)) {
//
//                 if (key == "available") {
//                     data.properties.product.inventory.available = +value;
//                 } else {
//
//                     // @ts-ignore
//                     data.properties.product[key] = +value
//                 }
//             }
//
//             // @ts-ignore
//             else if (StringFields.includes(key)) {
//
//                 // @ts-ignore
//                 data.properties.product[key] = String(value)
//             }
//         }
//     }
//
//     return data;
// }