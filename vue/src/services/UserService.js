import axios from "axios";
export default {
    getUsers() {
        return axios.get("/user");
    },
    getUserById(id) {
        return axios.get(`/user/id/${id}`)
    },
    getUserByUserName(username) {
        return axios.get(`/user/username/${username}`)
    },
    createUser(user) {
        return axios.post('/user', user);
    }
}