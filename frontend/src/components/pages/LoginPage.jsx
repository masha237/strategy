import React, {useState} from 'react';
import {Button, Form, Input} from "antd";
import Password from "antd/es/input/Password";
import {Link, useNavigate} from "react-router-dom";
import AuthService from "./../../utils/AuthService";
import "./../../styles/form.css"

const LoginPage = ({setUser}) => {
    const [error, setError] = useState(null);
    const navigate = useNavigate();
    function login(data) {

        AuthService.login(data).then((response) => {
            if (response.error === null) {
                if (response.data) {
                    localStorage.setItem("auth", window.btoa(data.login + ":" + data.password));
                    localStorage.setItem("user", response.data);
                }
                setUser(response.data);
                navigate("/");
            } else {
                setError(response.error);
            }
        }).catch(console.log);

    }
    return (
        <div className="login-page content">
            <Form className="form" name="login-form" initialValues={{remember: false}} onFinish={login} autoComplete="off"
                  labelCol={{span: 2}} wrapperCol={{span: 8}}>
                <Form.Item className="form-item" name="login" rules={[
                    {required: true, message: "Please, input login!"}
                ]} wrapperCol={{ sm: 24 }}>
                    <Input placeholder="login"/>
                </Form.Item>
                <Form.Item className="form-item" name="password" rules={[
                    {required: true, message: "Please, input password!"}
                ]} wrapperCol={{ sm: 24 }}>
                    <Password placeholder="password"/>
                </Form.Item>
                <Form.Item wrapperCol={{offset: 2}} >
                    <Button type="primary" htmlType="submit" className="form-button">
                        Login
                    </Button>
                    <div>{error}</div>
                </Form.Item>
                <Link to="../register">Зарегистрироваться</Link>

            </Form>
        </div>
    );
};

export default LoginPage;