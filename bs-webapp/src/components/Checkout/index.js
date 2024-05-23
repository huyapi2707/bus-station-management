import {useContext, useEffect, useState} from 'react';
import './styles.css';
import moment from 'moment';
import {
  AuthenticationContext,
  CartContext,
  LoadingContext,
} from '../../config/context';
import {apis, endpoints} from '../../config/apis';
import * as ultils from '../../config/utils';
const Checkout = () => {
  const {user} = useContext(AuthenticationContext);
  const {setLoading} = useContext(LoadingContext);
  const {cart} = useContext(CartContext);
  const [paymentMethods, setPaymentMethods] = useState([]);
  const [selectedPaymentMethod, setSelectedPaymentMethod] = useState(0);
  const [tickets, setTickets] = useState([]);
  const fetchPaymentMethod = async () => {
    try {
      setLoading('flex');
      const response = await apis(null).get(endpoints.payment_method_list);
      const data = response['data'];
      setPaymentMethods(data);
      setSelectedPaymentMethod(data[0]['id']);
    } catch (ex) {
      console.error(ex);
    } finally {
      setLoading('none');
    }
  };

  const fetchCartDetails = async () => {
    try {
      setLoading('flex');
      const response = await apis(null).post(
        endpoints.cart_details,
        cart['data'],
      );
      setTickets(response['data']);
    } catch (ex) {
      console.log(ex);
    } finally {
      setLoading('none');
    }
  };

  useEffect(() => {
    fetchPaymentMethod();
  }, []);

  useEffect(() => {
    fetchCartDetails();
  }, [cart['key']]);

  return (
    <div className="container-fluid mt-5 ">
      <div className="row">
        <div className="col-md-6">
          <div className="shadow-sm p-3 mb-5 bg-body rounded">
            <h4>Your information</h4>
            <div className="form mt-4">
              <div className="mb-3">
                <label htmlFor="email" className="form-label">
                  First name
                </label>
                <input
                  type="email"
                  className="form-control"
                  id="email"
                  value={user['firstname']}
                  disabled
                  readOnly
                />
              </div>
              <div className="mb-3">
                <label htmlFor="lastname" className="form-label">
                  Last name
                </label>
                <input
                  type="text"
                  className="form-control"
                  id="lastname"
                  value={user['lastname']}
                  disabled
                  readOnly
                />
              </div>
              <div className="mb-3">
                <label htmlFor="email" className="form-label">
                  Email address
                </label>
                <input
                  type="email"
                  className="form-control"
                  id="email"
                  value={user['email']}
                  disabled
                  readOnly
                />
              </div>
              <div className="mb-3">
                <label htmlFor="phone" className="form-label">
                  Phone
                </label>
                <input
                  type="email"
                  className="form-control"
                  id="phone"
                  value={user['phone']}
                  disabled
                  readOnly
                />
              </div>
              <div className="mb-3">
                <label htmlFor="paymentMethod" className="form-label">
                  Payment Method
                </label>
                <select
                  onChange={(e) => setSelectedPaymentMethod(e.target.value)}
                  value={selectedPaymentMethod}
                  className="form-select"
                  id="paymentMethod"
                >
                  {paymentMethods.map((method) => (
                    <option value={method['id']} key={method['id']}>
                      {method['name']}
                    </option>
                  ))}
                </select>
              </div>
              <div className="mt-3 d-flex justify-content-center align-items-center">
                <button className=" btn btn-primary">Checkout</button>
              </div>
            </div>
          </div>
        </div>
        <div className="col-md-6">
          <div className="shadow-sm p-3 mb-5 bg-body rounded">
            <h4>Your orders</h4>
            <table className="table">
              <thead>
                <tr>
                  <th>General</th>
                  <th>Route</th>
                  <th>Depart at</th>
                  <th>Seat</th>
                  <th>Seat price</th>
                  <th>Cargo price</th>
                  <th>Total</th>
                </tr>
              </thead>
              <tbody>
                {tickets.map((ticket) => {
                  return (
                    <tr key={ticket['id']}>
                      <td>
                        <p>{ticket['routeInfo']['company']['name']}</p>
                        <p>{ticket['routeInfo']['name']}</p>
                      </td>
                      <td>
                        <p>
                          {ticket['routeInfo']['fromStation']['address']} -{' '}
                          {ticket['routeInfo']['toStation']['address']}
                        </p>
                      </td>
                      <td>
                        {moment(ticket['tripInfo']['departAt']).format(
                          'MMMM Do YYYY, h:mm:ss a',
                        )}
                      </td>
                      <td>{ticket['seat']['code']}</td>
                      <td>
                        {ultils.formatToVND(ticket['routeInfo']['seatPrice'])}
                      </td>
                      <td>
                        {ultils.formatToVND(ticket['routeInfo']['cargoPrice'])}
                      </td>
                      <td>
                        {ultils.formatToVND(
                          ticket['routeInfo']['seatPrice'] +
                            ticket['routeInfo']['cargoPrice'],
                        )}
                      </td>
                    </tr>
                  );
                })}
              </tbody>
            </table>
            <div className="d-flex justify-content-between align-items-center">
              <h5 className="ps-2 fw-bold">Total</h5>
              <p className="fw-bold fs-3 text-danger">
                {ultils.formatToVND(
                  tickets.reduce((total, cuurent) => {
                    const cuurentTotal =
                      cuurent['routeInfo']['seatPrice'] +
                      cuurent['routeInfo']['cargoPrice'];
                    return total + cuurentTotal;
                  }, 0),
                )}
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Checkout;
