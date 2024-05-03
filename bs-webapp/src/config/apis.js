import axios from 'axios';

const BASE_URL = 'http://localhost:8080/busstation';
const endpoints = {
  login: '/api/v1/auth/authenticate',
  register: '/api/v1/auth/register',
  company_list: '/api/v1/transportation_company/list',
};

// transportationCompany_list has available request params: kw , page
// kw stand for company name query

const apis = axios.create({
  baseURL: BASE_URL,
});

export {apis, endpoints};
