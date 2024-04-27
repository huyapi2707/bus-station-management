import axios from 'axios';

const BASE_URL = 'http://localhost:8080/busstation';
const endpoints = {
  login: '/api/v1/auth/authenticate',
  register: '/api/v1/auth/register',
};

const apis = axios.create({
  baseURL: BASE_URL,
});

export {apis, endpoints};
