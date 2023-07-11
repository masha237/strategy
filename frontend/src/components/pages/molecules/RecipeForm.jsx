import React, {useEffect, useState} from 'react';
import {Button, Form, Input, InputNumber, Space} from "antd";
import {MinusCircleOutlined, PlusOutlined} from "@ant-design/icons";
import Autocomplete from '@mui/material/Autocomplete';
import TextField from '@mui/material/TextField';
import {RecipeService} from "../../../utils/RecipeService";
import {Chip} from "@mui/material";
import {useNavigate} from "react-router-dom";
import "../../../styles/form.css"
import TextArea from "antd/es/input/TextArea";

const RecipeForm = () => {
    const [tags, setTags] = useState([])
    const [error, setError] = useState(null);
    const navigate = useNavigate();
    const [options, setOptions] = useState([]);

    useEffect(() => {
        RecipeService.getTags().then((response) => {
            if (response.error === null) {
                setTags(response.data.map(type => type.name));
            } else {
                setError(response.error);
            }
        }).catch(console.log);
    }, [])

    function submit(values) {
        console.log(values);

        console.log(options);
        setOptions([]);
        // validation
        // navigate("/");
    }

    return (
        <Form name="login-form" initialValues={{remember: false}} onFinish={submit} autoComplete="off"
              labelCol={{span: 2}} wrapperCol={{span: 8}}>
            <Form.Item
                name={["name"]}
            >
                <Input placeholder="Название"/>
            </Form.Item>
            <Form.List name="ingradients"
                       initialValue={[
                           { ingredient: "", amount: "" }
                       ]}>
                {(fields, {add, remove}) => (
                    <>
                        {fields.map((field) => (
                            <Space
                                key={field.key}
                                style={{display: "flex", marginBottom: 8}}
                                align="baseline"
                            >
                                <Form.Item
                                    {...field}
                                    style={{width: "80%"}}
                                    name={[field.name, "ingredient"]}
                                    key={field.key + "ingredient"}
                                >
                                    <Input placeholder="Инградиент"/>
                                </Form.Item>
                                <Form.Item
                                    {...field}
                                    key={field.key + "amount"}
                                    name={[field.name, "amount"]}
                                >
                                    <InputNumber placeholder="мл"/>
                                </Form.Item>
                                <MinusCircleOutlined onClick={() => remove(field.name)}/>
                            </Space>
                        ))}
                        <Form.Item>
                            <Button
                                type="dashed"
                                onClick={() => add()}
                                block
                                icon={<PlusOutlined/>}
                            >
                                Добавить инградиент
                            </Button>
                        </Form.Item>
                    </>
                )}
            </Form.List>

            <Form.Item name="description">
                <TextArea placeholder="Используйте шейкер, добаьте лед"/>
            </Form.Item>
            <Autocomplete
                multiple
                id="tags-filled"
                options={tags}
                freeSolo
                onChange={(e, value) => setOptions(value)}
                renderTags={(value, getTagProps) =>
                    value.map((option, index) => (
                        <Chip variant="outlined" label={option} {...getTagProps({index})} />
                    ))
                }
                renderInput={(params) => (
                    <TextField
                        {...params}
                        variant="filled"
                        label="Теги"
                        placeholder="Шот/Лонг"
                    />
                )}
            />

            <Form.Item>
                <Button type="primary" htmlType="submit">
                    {" "}
                    Написать{" "}
                </Button>
            </Form.Item>
        </Form>
    );
};

export default RecipeForm;