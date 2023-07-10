export class Util {
    static basicAuth() {
        console.log("Basic " + localStorage.getItem("auth"))
        return "Basic " + localStorage.getItem("auth");
    }
}