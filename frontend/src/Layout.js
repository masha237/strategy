import React from 'react';
import Logo from "./components/Logo";
import Header from "./components/Header";
import Sidebar from "./components/Sidebar";
import {Outlet} from "react-router-dom";
import "./styles/layout.css"
const Layout = () => {
    return <div id="main-layout">
        <Logo/>
        <Header/>
        <Sidebar/>
        <Outlet/>
    </div>;
}

export default Layout;