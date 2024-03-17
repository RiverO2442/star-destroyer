'use client'

import React, {useState} from "react"
import {LoginButtons} from "@/components/Global";

const SignInPage = () => {

    const [loading, setLoading] = useState(false);

    return (
        <main
            className="
                justify-start gap-y-8
                flex flex-col
                min-h-[60vh]">
            <div className="text-xl text-center">
                {loading ? "Loading.." : "Log in with"}
            </div>
            {!loading && <LoginButtons setLoading={setLoading}/>}
        </main>

    )
}


export default SignInPage;