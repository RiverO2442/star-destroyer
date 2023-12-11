import {createContext, HtmlHTMLAttributes, useEffect, useReducer} from "react";
import {User} from "../types/user..ts";

interface AuthContext {
    user: User | null
}

const AuthContext = createContext<AuthContext>({user: null});


const AuthContextProvider = ({children}: HtmlHTMLAttributes<Element>) => {
    // const [loggedIn, setLoggedIn] = useReducer((_: boolean, newState: boolean) => newState, false);
    const [user, setUser] = useReducer((_: User | null, newState: User | null) => newState, null);

    // const checkLoginState = useCallback(async () => {
    //     console.log("check login state")
    //     try {
    //         const {
    //             data: {
    //                 loggedIn: logged_in,
    //                 user
    //             }
    //         } = await axios.get(`${import.meta.env.VITE_ROOM_MANAGER_API_URL}/auth/logged_in`);
    //         console.log(logged_in, user)
    //         setLoggedIn(logged_in);
    //         user && setUser(user);
    //     } catch (err) {
    //
    //         console.error(err);
    //     }
    // }, []);

    // useEffect(() => {
    //     checkLoginState();
    // }, [checkLoginState]);

    return (
        <AuthContext.Provider value={{user}}>
            {children}
        </AuthContext.Provider>
    );
}

// async function doLogin(dispatch, user) {
//     try {
//         dispatch({status: "pending"});
//
//         const result = await getUser(user);
//         dispatch({
//             status: "resolved",
//             user: result,
//             error: null
//         });
//     } catch (error) {
//         dispatch({status: "rejected", error});
//     }
// }

export {AuthContext, AuthContextProvider}