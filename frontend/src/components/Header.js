import React, {useEffect, useState} from 'react';
import {Link} from "react-router-dom";
import "./../styles/layout.css"

const Header = () => {
    const [user, setUser] = useState();

    useEffect(() => {
        setUser(localStorage.getItem("user"))
    }, [localStorage]);
    return (
        <div id="header">
            {user && <Link to={"/logout"}>Logout</Link>}
            {!user && <Link to={"/login"}>Login</Link>}
        </div>
    );
};

export default Header;