import {useContext, useEffect, useState} from 'react';
import './styles.css';
import {CartContext} from '../../config/context';
import {apis, endpoints} from '../../config/apis';
import {Link} from 'react-router-dom';
import moment from 'moment';
import * as ultils from '../../config/utils';

const Cart = () => {
  const {cart, cartDispatcher} = useContext(CartContext);
  const [tickets, setTickets] = useState([]);

  const handleDeleteCart = (tripId, seatId) => {
    cartDispatcher({
      type: 'DELETE_ITEM',
      payload: {
        tripId: tripId,
        seatId: seatId,
      },
    });
  };

  const handleToggleWithCargo = (event, tripId, seatId) => {
    cartDispatcher({
      type: 'WITH_CARGO',
      payload: {
        tripId: tripId,
        seatId: seatId,
        withCargo: event.target.checked,
      },
    });
  };
  const fetchTickets = async () => {
    const response = await apis(null).post(
      endpoints.cart_details,
      cart['data'],
    );

    setTickets(response['data']);
  };
  useEffect(() => {
    fetchTickets();
  }, [cart['key']]);
  return (
    <div
      className="offcanvas offcanvas-end w-50"
      data-bs-scroll="true"
      tabIndex="-1"
      id="offcanvasWithBothOptions"
      aria-labelledby="offcanvasWithBothOptionsLabel"
    >
      <div className="offcanvas-header">
        <h5
          className="offcanvas-title mt-5  p-3"
          id="offcanvasWithBothOptionsLabel"
        >
          Your cart
        </h5>
        <button
          type="button"
          className="btn-close text-reset"
          data-bs-dismiss="offcanvas"
          aria-label="Close"
        ></button>
      </div>
      <div className="offcanvas-body p-4">
        <table className="table">
          <thead>
            <tr>
              <th>Ticket details</th>
              <th>With cargo</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            {tickets.map((ticket, index) => {
              return (
                <tr key={index}>
                  <td>
                    <ul className="list-unstyled">
                      <li>
                        <Link className="nav-link">
                          Route name:{' '}
                          <span className="fw-bold">
                            {ticket['routeInfo']['name']}
                          </span>
                        </Link>
                      </li>
                      <li>
                        <Link className="nav-link">
                          Company name:{' '}
                          <span className="fw-bold">
                            {ticket['routeInfo']['company']['name']}
                          </span>
                        </Link>
                      </li>
                      <li>
                        From: {ticket['routeInfo']['fromStation']['address']} to{' '}
                        {ticket['routeInfo']['toStation']['address']}
                      </li>
                      <li>
                        Depart at:{' '}
                        {moment(ticket['tripInfo']['departAt']).format(
                          'MMMM Do YYYY, h:mm:ss a',
                        )}
                      </li>
                      <li>Seat code: {ticket['seat']['code']}</li>
                      <li>
                        Seat price:{' '}
                        {ultils.formatToVND(ticket['routeInfo']['seatPrice'])}
                      </li>
                      <li>
                        Cargo price:{' '}
                        {ultils.formatToVND(ticket['routeInfo']['cargoPrice'])}
                      </li>
                      <li>
                        Total:{' '}
                        {ultils.formatToVND(
                          ticket['routeInfo']['seatPrice'] +
                            ticket['routeInfo']['cargoPrice'],
                        )}
                      </li>
                    </ul>
                  </td>
                  <td>
                    <input
                      className="form-check-input"
                      type="checkbox"
                      checked={ticket['routeInfo']['cargoPrice'] > 0}
                      onChange={(event) =>
                        handleToggleWithCargo(
                          event,
                          ticket['tripInfo']['id'],
                          ticket['seat']['id'],
                        )
                      }
                    />
                  </td>
                  <td>
                    <button
                      onClick={() =>
                        handleDeleteCart(
                          ticket['tripInfo']['id'],
                          ticket['seat']['id'],
                        )
                      }
                      className="btn btn-danger"
                    >
                      Delete
                    </button>
                  </td>
                </tr>
              );
            })}
          </tbody>
        </table>
        <div className="d-flex justify-content-end">
          <Link to={'/checkout'} className="btn btn-primary">
            Check out
          </Link>
        </div>
      </div>
    </div>
  );
};

export default Cart;
