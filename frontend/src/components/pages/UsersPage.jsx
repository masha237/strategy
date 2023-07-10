import React, {useEffect, useState} from 'react';
import {useParams} from "react-router-dom";
import UserService from "../../utils/UserService";
import {Form} from "antd";
import { RadioChangeEvent } from 'antd';
import { Radio } from 'antd';
import UserPageRow from "./molecules/UserPageRow";

const UsersPage = ({user}) => {
    const [people, setPeople] = useState(null)

    useEffect(() => {


        UserService.getUsers().then((response) => {
            if (response.error === null) {
                setPeople(response.data)
                console.log(response.data);
            }
        }).catch(console.log);
    }, [])





    return (
        <div>
            {people?.map((person) => <UserPageRow key={person.login + "Radio"} user={person}/>)}
        </div>
    );
};

export default UsersPage;