import React from 'react';
import {useParams} from "react-router-dom";

const UserPage = () => {
    const {login} = useParams();
    return (
        <div>
            {login}
        </div>
    );
};

export default UserPage;