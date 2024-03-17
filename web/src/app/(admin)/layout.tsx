import {Lato} from "next/font/google";
import React, {ReactNode} from "react";
import {getServerSession} from "next-auth";
import {NotLoggedIn, SignOutButton} from "@/components/Global";
import Link from "next/link";
import Footer from "@/components/footer";
import "@/styles/globals.scss";
import {ClientItemSearch} from "@/app/(admin)/SearchComponent";

export const metadata = {
  title: 'Admin panel',
}

const lato = Lato({
  subsets: ['latin'],
  display: 'auto',
  weight: "400"
})

export default async function RootLayout(
    {children}: { children: ReactNode }
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

      flex flex-col justify-between
      items-center`}>
      <header className='w-full pb-10 pt-5'>
        <div className='text-center
            mb-6
            grid grid-cols-2 gap-x-1
            justify-items-center place-items-center'>
          {session ?
              <>
                <p className='text-center'>Logged in as {session.user?.name}  </p>
                <SignOutButton/>
              </>
              :
              <>
                {<p>Not logged in</p>}
                <Link
                    className='text-right hover:underline capitalize'
                    href="/signin">Log in</Link>
              </>}

        </div>

        {(session) && <div className='bg-opacity-5 bg-black
          dark:bg-opacity-5 dark:bg-white
          py-2 text-lg place-items-center
          xs:text-base
          mt-2 grid grid-cols-4 justify-items-center'>
          <ClientItemSearch/>
          <Link className='hover:underline' href={"/admin/items/1/default"}>Items</Link>
          <Link className='hover:underline' href={"/admin/categories"}>Categories</Link>
          <Link className='hover:underline' href={"/admin/orders"}>Orders</Link>
        </div>}
      </header>

      {/*{session ? children : <NotLoggedIn/>}*/}
      {children}
      <Footer>
        You are in the admin view.
        <br/>
        <div className='pt-2'>
          <Link
              className='hover:underline'
              href={"/"}>Store Page
          </Link>
        </div>
      </Footer>
      </body>
      </html>
  )
}