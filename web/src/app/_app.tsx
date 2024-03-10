import {SessionProvider} from "next-auth/react"
import {FC} from "react";
import '@/styles/globals.scss'
import '@/styles/animations.scss'
import {Session} from "next-auth";

interface AppProps {
    Component: FC,
    session: Session | null,
    pageProps: object
}

export default function App({Component, session, pageProps}: AppProps) {
    return (
        <SessionProvider session={session}>
            <Component {...pageProps}/>
        </SessionProvider>
    )
}