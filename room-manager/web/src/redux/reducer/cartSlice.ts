import {createSlice, PayloadAction} from "@reduxjs/toolkit";
import {Cart} from "../../types/cart.ts";
import {Product} from "../../types/product.ts";
import {RootState} from "../store.ts";


const initialState: Cart[] = []

const cartSlice = createSlice({
    name: "cart",
    initialState: initialState,
    reducers: {
        addItem: (state, action: PayloadAction<Product>) => {
            const product = action.payload;
            const exist = state.find((x) => x.id === product.id)
            if (exist) {
                // Increase the quantity
                return state.map((x) => x.id === product.id ? {...x, qty: x.qty + 1} : x)
            } else {
                return [...state, {...product, qty: 1}]
            }
        },
        delItem: (state, action: PayloadAction<Product>) => {
            const product = action.payload;
            const exist = state.find((x) => x.id === product.id)
            if (exist && exist.qty === 1) {
                return state.filter((x) => x.id !== exist.id)
            } else {
                return state.map((x) => x.id === product.id ? {...x, qty: x.qty - 1} : x)
            }
        }
    }
})

export const {addItem, delItem} = cartSlice.actions;

export const selectCart = (state: RootState) => state.cartReducer

export default cartSlice.reducer;