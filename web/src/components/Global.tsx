'use client'

import {ReactNode} from "react"
import {signIn, signOut} from "next-auth/react"
import Link from "next/link"
import {useFormStatus} from "react-dom";
import {Button, ButtonProps} from "@mui/material";


export const CoolButton = ({children}: { children: ReactNode }) => {
    return <>
        <div
            className="
    flex items-center justify-center
    w-fit
    text-slate-700
    bg-slate-300 hover:bg-slate-100
    rounded-md">
            {children}
        </div>
    </>
}

export const SubmitButton = (props: ButtonProps) => {
    const {pending} = useFormStatus();
    return <Button {...props} disabled={pending} className="
                            cursor-pointer
                            w-44 h-10
                            rounded-md
                            dark:bg-transparent
                            capitalize">
        {pending ? "Submitting..." : "Subbmit"}
    </Button>
}


export const SignOutButton = () => {
    return (
        <button

            className="
            capitalize hover:underline
            group-hover:underline"
            onClick={() => {
                signOut()
            }}>
            log out
        </button>
    )
}


export const NotLoggedIn = () => {
    return (
        <main className="min-h-[70vh] flex flex-col items-center">
            You must login to view this content
            {/*<form*/}
            {/*    action={setGuestMode}*/}
            {/*    className="mt-1">*/}
            {/*    <CoolButton>*/}

            {/*        <input*/}
            {/*            className="cursor-pointer px-2 py-1"*/}
            {/*            value={"Guest Mode"}*/}
            {/*            type="submit"/>*/}
            {/*    </CoolButton>*/}
            {/*</form>*/}
        </main>
    )
}

export const LoginButtons = ({setLoading}: { setLoading: (value: boolean) => void }) => {
    const providers = ["github", "google"];

    const LoginButton = ({provider}: { provider: string }) => (
        <button
            onClick={() => {
                setLoading(true);
                signIn(provider);
            }}
            className="
            w-44 h-10
            capitalize">
            {provider}
        </button>
    )

    return (
        <aside
            className="
            gap-y-2
            flex flex-col">
            {providers
                .map(provider => (
                    <CoolButton key={provider}>
                        <LoginButton provider={provider}/>
                    </CoolButton>
                ))}
        </aside>
    )
}
export const CoolLink = ({href, children}: { href: string, children: ReactNode }) => {
    return (
        <Link
            href={href}
            className="
        text-blue-600
        hover:text-blue-500
        hover:underline
        dark:text-blue-400
        dark:hover:text-blue-300
        ">
            {children}

        </Link>
    )
}