import axios from "axios";

const REST_API_BASE_URL = 'http://localhost:8080/api/user';

export const listOfUsers = () => axios.get(REST_API_BASE_URL);

export const getUserById = (id) => axios.get(`http://localhost:8080/api/user/${id}`);

export const addUser = (user) => axios.post(REST_API_BASE_URL, user);

export const nicknameCheck = (nickname) => axios.get(`http://localhost:8080/api/user/check-nickname/${nickname}`);

export const loginCheck = (login) => axios.get(`http://localhost:8080/api/user/check-login/${login}`);

export const emailCheck = (email) => axios.get(`http://localhost:8080/api/user/check-email/${email}`);

export const getUser = (forAuthorization) =>  axios.get(`http://localhost:8080/api/user/user-authorization/${forAuthorization.login}/${forAuthorization.password}`);