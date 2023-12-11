import {Navigate, RouteProps} from "react-router-dom";
import {useContext} from "react";
import {AuthContext} from "./context/AuthContextProvider.tsx";

function PrivateRoute(_props: RouteProps) {
    const {user} = useContext(AuthContext);
    return (user ? _props.children : <Navigate to={{pathname: "/login"}}/>);
}

export default PrivateRoute;