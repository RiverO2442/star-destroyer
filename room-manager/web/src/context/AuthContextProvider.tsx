import {createContext, HtmlHTMLAttributes, useCallback, useEffect, useState} from "react";
import axios from "axios";

interface AuthContext {
}

const AuthContext = createContext<AuthContext>({
});

export const AuthContextProvider = ({children}: HtmlHTMLAttributes<Element>) => {
    const [loggedIn, setLoggedIn] = useState(null);
    const [user, setUser] = useState(null);

    const checkLoginState = useCallback(async () => {
        try {
            const {
                data: {
                    loggedIn: logged_in,
                    user
                }
            } = await axios.get(`${import.meta.env.VITE_ROOM_MANAGER_API_URL}/auth/logged_in`);
            setLoggedIn(logged_in);
            user && setUser(user);
        } catch (err) {
            console.error(err);
        }
    }, []);

    useEffect(() => {
        checkLoginState();
    }, [checkLoginState]);

    return (
        <AuthContext.Provider value={{loggedIn, checkLoginState, user}}>
            {children}
        </AuthContext.Provider>
    );
}