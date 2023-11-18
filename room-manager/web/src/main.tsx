import ReactDOM from "react-dom/client";
import {createBrowserRouter, RouterProvider} from "react-router-dom";
import App from "./App.tsx";
import AuthCodeCallback from "./components/AuthCodeCallback.tsx";

const router = createBrowserRouter([
    {
        path: '/',
        element: <App />
    },
    {
        path: '/login/oauth2/code/github',
        element: <AuthCodeCallback />
    }
])

ReactDOM.createRoot(document.getElementById("root") as HTMLElement).render(
    <RouterProvider router={router}/>
);
