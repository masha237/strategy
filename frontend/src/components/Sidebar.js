import React, {useEffect, useState} from 'react';
import {Link} from "react-router-dom";
import "./../styles/layout.css"
const Sidebar = ({user}) => {
    const [login, setLogin] = useState()


    return (
        <div id="sidebar">
            <Link to={'/user/'+ (login ? login : "")} className="link"> Моя Страница</Link>
            <Link to={"/friends"} className="link"> Мои Друзья</Link>
            {user && user.role === "ADMIN" && <Link to={"/users"} className="link"> Люди</Link>}
            <Link to={"/"} className="link"> Мои Новости</Link>
        </div>
    );
};

export default Sidebar;