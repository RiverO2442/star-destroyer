import {getServerSession} from 'next-auth/next';
import Link from 'next/link';
import {Lato} from 'next/font/google'
import React from "react";
import Footer from "@/components/footer";
import "@/styles/globals.scss";

export const metadata = {
    title: 'Authentication',
}


const lato = Lato({
    subsets: ['latin'],
    display: 'auto',
    weight: "400"
})


export default async function RootLayout(
    {children}:
        { children: React.ReactNode }
) {


    const session = await getServerSession();


    return (
        <html lang="en" suppressHydrationWarning>

        <body className={`

      ${lato.className}

      min-h-screen
      bg-gradient-to-b
      text-slate-900
      
      dark:text-slate-200
      from-slate-100
      to-slate-300
      dark:from-slate-900
      from-90%
      dark:to-black
      flex flex-col
      justify-between
      items-center mt-5`}>

        <header className='
          w-full py-10

          flex gap-x-1
          justify-around items-center'>
            <div>
                <p> Log in to &apos;The Cool Webstore&apos; </p>
            </div>
        </header>

        {session
            ? <main className='flex flex-col items-center justify-center'>
                <p>You are already signed in</p>
                <Link href="/" className='hover:underline'>Homepage</Link>
            </main>
            : children
        }

        <Footer>
            You are viewing the Authentication Page.
            <br/>
            <div className='pt-2'>

                <Link
                    className='mt-10 hover:underline'
                    href={"/"}>Store Page
                </Link>
            </div>

        </Footer>


        </body>
        </html>
    )
}
