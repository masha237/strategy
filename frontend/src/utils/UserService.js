import axios from "axios";
import {API_URL, USERS, USER, SET_USER_ROLE} from "../const";

import {Util} from "./Util";



export class UserService {
    static getUsers() {
        return axios.get(API_URL + USER + USERS, {
            headers: {"Authorization": Util.basicAuth()}
        }).then(response => {
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

    static setRole(login, role) {
        return axios.post(API_URL + USER + SET_USER_ROLE, {
            "login": login, "role": role
        },{
            headers: {"Authorization": Util.basicAuth()}
        }).then(response => {
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
}
export default UserService