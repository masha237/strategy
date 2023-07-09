import axios, {AxiosError} from "axios";
import {API_URL, AUTH, LOGIN, REGISTER} from "../const";



export class AuthService {
    static login(user) {

        return axios.post(API_URL + AUTH + LOGIN, {login: user.login, password: user.password})
            .then(response => {
                console.log(response)
                return {
                    data: response.data,
                    error: null
                }
            }).catch((err) => {
                if (err.response) {
                    return {
                        data: null,
                        error: err.response.data
                    }
                }
                return {
                    data: null,
                    error: "fail"
                }
            });
    }


    static register(user) {
        console.log({
            login: user.login,
            password: user.password,
            username: user.username,

        })
        return axios.post(API_URL + AUTH + REGISTER, {
            login: user.login,
            password: user.password,
            username: user.username,

        })
            .then(response => {
                console.log(response);
                return {
                    data: response.data,
                    error: null
                }
            }).catch((err) => {
                console.log(err)
                if (err.response) {
                    return {
                        data: null,
                        error: err.response.data
                    }
                }
                return {
                    data: null,
                    error: "fail"
                }
            }).catch(() => {
                console.log("kek")
                return {
                    data: null,
                    error: "fail"
                }
            });
    }

    static logout() {
        console.log(localStorage)
        localStorage.removeItem("user");
        console.log(localStorage)
    }
}
export default AuthService