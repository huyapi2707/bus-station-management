import axios from 'axios';

const BASE_URL = 'http://localhost:8080/busstation';
const endpoints = {
  login: '/api/v1/auth/authenticate',
  register: '/api/v1/auth/register',
  company_list: '/api/v1/transportation_company/list',
  company_list_idName: '/api/v1/transportation_company/list/name-and-id',
  userInfor: '/api/v1/users/self',
  route_list: '/api/v1/route/list',
  cart_details: '/api/v1/ticket/cart/details',
  payment_method_list: '/api/v1/payment-method/list',
  add_route: 'api/v1/route/add',
  register_company: '/api/v1/transportation_company/add',
  create_route: '/api/v1/route/add',
  list_station: '/api/v1/transportation_company/list_station',
  available_cars: '/api/v1/cars/available-cars',
  creat_trip: '/api/v1/trip/add',
  statistics_ticket_month: (year) => `/api/v1/statistics/month/${year}`,
  statistics_ticket_quarterly: (year) => `/api/v1/statistics/quarterly/${year}`,
  statistics_ticket_day: (year, month, day) => `/api/v1/statistics/day/${year}/${month}/${day}`,
  register_cargo: (id) => `api/v1/transportation_company/cargo/${id}`,
  get_route_by_companyid: (id) => `api/v1/route/company/${id}`,
  get_company_managerid:(id) => `/api/v1/transportation_company/manager/${id}`,
  route_trip_list: (id) => `/api/v1/route/${id}/trip`,
  trip_seat_details: (id) => `/api/v1/trip/${id}/seat-details`,
  checkout: (paymentMethodId) => `/api/v1/ticket/checkout/${paymentMethodId}`,
  user: (id) => `/api/v1/users/${id}`,
  get_user_by_role: (id) => `/admin/users/role/${id}`,
};
const apis = (accessToken) => {
  if (accessToken) {
    return axios.create({
      baseURL: BASE_URL,
      headers: {
        Authorization: `Bearer ${accessToken}`,
      },
    });
  }
  return axios.create({
    baseURL: BASE_URL,
  });
};

export {apis, endpoints};
