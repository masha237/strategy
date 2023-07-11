import axios, {AxiosError} from "axios";
import {API_URL, AUTH, LOGIN, RECIPE, REGISTER, SET_USER_ROLE, TAGS, USER, WRITE} from "../const";
import {Util} from "./Util";



export class RecipeService {
    static writeRecipe(array) {

        return axios.post(API_URL + RECIPE + WRITE, {
            array: array
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
    static getTags() {
        return axios.get(API_URL + RECIPE + TAGS, {
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
export default RecipeService