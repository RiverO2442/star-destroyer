import { redirect } from "next/navigation"

const AdminPage= ()=>{

    // redirect('/admin/items/1/default')
    //
    return(
        <main className="w-full sm:px-4">
            Welcome back!
        </main>

    )
}

const Search = () => {

}



// export default AdminPage;
export default function Page() {
    return <h1>Hello, Next.js!</h1>
}