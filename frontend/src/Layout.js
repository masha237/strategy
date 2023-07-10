import React from 'react';
import Logo from "./components/Logo";
import Header from "./components/Header";
import Sidebar from "./components/Sidebar";
import {Outlet} from "react-router-dom";
import "./styles/layout.css"
const Layout = ({user}) => {
    return <div id="main-layout">
        <Logo/>
        <Header/>
        <Sidebar user={user}/>
        <Outlet/>
    </div>;
}

export default Layout;