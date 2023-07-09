import React from 'react';
import "./../styles/layout.css"
const Logo = () => {
    return (
        <img id="logo" src={process.env.PUBLIC_URL + "/owl.jpg"}/>
    );
};

export default Logo;