import axios from 'axios';

const BASE_URL = 'http://localhost:8080/busstation';
const endpoints = {
  login: '/api/v1/auth/authenticate',
  register: '/api/v1/auth/register',
  company_list: '/api/v1/transportation_company/list',
  company_list_idName: '/api/v1/transportation_company/list/name-and-id',
  userInfor: '/api/v1/users/self',
  route_list: '/api/v1/route/list',
  route_trip_list: (id) => `/api/v1/route/${id}/trip`,
  trip_seat_details: (id) => `/api/v1/trip/${id}/seat-details`,
};

// transportationCompany_list has available request params: kw , page
// kw stand for company name query

const apis = (accessToken) => {
  if (accessToken) {
    return axios.create({
      baseURL: BASE_URL,
      headers: {
        Authorization: `Bearer ${accessToken}`,
        'Content-Type': 'application/x-www-form-urlencoded',
      },
    });
  }
  return axios.create({
    baseURL: BASE_URL,
  });
};

export {apis, endpoints};
