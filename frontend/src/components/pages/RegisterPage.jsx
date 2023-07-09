import React, {useState} from 'react';
import {useNavigate} from "react-router-dom";
import AuthService from "../../utils/AuthService";
import {Button, Form, Input} from "antd";
import Password from "antd/es/input/Password";
import "./../../styles/form.css"

const RegisterPage = () => {
    const [error, setError] = useState(null);
    const navigate = useNavigate();
    function register(data) {
        setError(null);
        if (data.username.length > 100) {
            setError("Name too long");return
        }
        if (data.login.length > 100) {
            setError("Login too long");return
        }
        if (data.password.length > 256) {
            setError("Password too long");return
        }

        AuthService.register(data).then((response) => {
            if (response.error === null) {
                navigate("../login");
            } else {
                setError(response.error);
            }
        }).catch(console.log);

    }
    return (
        <Form className="form" name="login-page" initialValues={{remember: false}} onFinish={register} autoComplete="off"
              labelCol={{span: 2}} wrapperCol={{span: 8}}>
            <Form.Item className="form-item" name="username" rules={[
                {required: true, message: "Please, input name!"}
            ]} wrapperCol={{ sm: 24 }}>
                <Input placeholder="name"/>
            </Form.Item>
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
                    Register
                </Button>
                <div>{error}</div>
            </Form.Item>

        </Form>

    );
};

export default RegisterPage;