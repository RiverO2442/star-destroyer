import {combineReducers} from "@reduxjs/toolkit";
import {default as cartReducer} from "./cartSlice.ts";

const rootReducers = combineReducers({
    cartReducer
})

export default rootReducers;