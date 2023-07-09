import React, {useEffect, useState} from 'react';
import {Link} from "react-router-dom";
import "./../styles/layout.css"
const Sidebar = () => {
    const [login, setLogin] = useState()
    /*useEffect(() => {
        if (jwtO.jwt) {
            UserService.getUserLogin(jwtO.jwt).then((response: IResponse<string | null>) => {
                if (response.error === null) {
                    if (response.data) {
                        setLogin(response.data);
                        localStorage.setItem("login", response.data);
                    }
                }
            }).catch(console.log);
        }
    }, [])*/

    return (
        <div id="sidebar">
            <Link to={'/user/'+ (login ? login : "")} className="link"> Моя Страница</Link>
            <Link to={"/friends"} className="link"> Мои Друзья</Link>
            <Link to={"/people"} className="link"> Люди</Link>
            <Link to={"/"} className="link"> Мои Новости</Link>
        </div>
    );
};

export default Sidebar;