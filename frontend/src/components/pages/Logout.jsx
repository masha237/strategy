import React, {useEffect} from 'react';
import AuthService from "../../utils/AuthService";
import {Navigate} from "react-router-dom";
const Logout = ({setUser}) => {

    useEffect(() => {
        AuthService.logout();
        setUser(null);
        localStorage.removeItem("communityRole");
        localStorage.removeItem("auth");
    }, []);

    return (
        <Navigate to="/login" replace/>
    );
};

export default Logout;