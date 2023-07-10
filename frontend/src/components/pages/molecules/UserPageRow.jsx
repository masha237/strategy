import React, {useState} from 'react';
import {Radio} from "antd";
import UserService from "../../../utils/UserService";

const UserPageRow = ({user}) => {
    const [role, setRole] = useState(user.communityRole?.type)

    const onChange = ({target}) => {
        console.log(target)
        setRole(target.value);
        UserService.setRole(user.login, target.value);
        console.log('radio1 checked', target.value);
    };
    const options = [
        { label: 'Admin', value: 'ADMIN' },
        { label: 'Moderator', value: 'MODERATOR' },
        { label: 'Verified user', value: 'VERIFIED_USER' },
        { label: 'User', value: 'USER' },
    ];

    return (
        <div className="user-row" style={{display:"inline"}}>
            <div>{user.login}</div>
            <Radio.Group options={options} onChange={onChange} value={role} name={user.login} optionType="button"
                     buttonStyle="solid"/>
        </div>
    );
};

export default UserPageRow;