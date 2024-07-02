import {  createContext, useContext, useState } from "react";

export const AuthContext=createContext();
export const useAuth=() =>useContext(AuthContext);
export default function AuthProvider({children})
{
    const [username,setUsername]=useState(10);
    const [isAuthenticated,setAuthentication]=useState(false);
    function login(username,password)
    {
        if(password==='2335')
            {
               setAuthentication(true);
                setUsername(username);

               return true
            }
            else
            {
                setAuthentication(false);
                return false
            }
    }
    function logout()
    {
        setAuthentication(false);
    }
    function signup()
    {

    }
    return (
        <AuthContext.Provider value={{username,isAuthenticated,logout,setUsername,login,signup}}>
            {children}
        </AuthContext.Provider>
    );
}