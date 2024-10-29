import axios from "axios";

const REST_API_BASE_URL = 'http://localhost:8080/api/user';

export const listOfUsers = () => axios.get(REST_API_BASE_URL);

export const listOfFriends = (userId) => axios.get(`http://localhost:8080/api/user/get-friends/${userId}`);

export const getUserById = (id) => axios.get(`http://localhost:8080/api/user/${id}`);

export const addUser = (user) => axios.post(REST_API_BASE_URL, user);

export const nicknameCheck = (nickname) => axios.get(`http://localhost:8080/api/user/check-nickname/${nickname}`);

export const loginCheck = (login) => axios.get(`http://localhost:8080/api/user/check-login/${login}`);

export const emailCheck = (email) => axios.get(`http://localhost:8080/api/user/check-email/${email}`);

export const getUser = (forAuthorization) =>  axios.get(`http://localhost:8080/api/user/user-authorization/${forAuthorization.login}/${forAuthorization.password}`);

export const updateNickname = (nickname, userId) => axios.patch(`http://localhost:8080/api/user/update-nickname/${userId}/${nickname}`);

export const updateAvatar = (formData, userId) => axios.patch(`http://localhost:8080/api/user/update-avatar/${userId}/avatar`, formData, {
    headers: {
        'Content-Type': 'multipart/form-data'
    }
});

export const updateEmail = (email, userId) => axios.patch(`http://localhost:8080/api/user/update-email/${userId}/${email}`);

export const getUserAvatar = (userId) => axios.get(`http://localhost:8080/api/user/user-avatar/${userId}`, {
    responseType: 'blob' 
});