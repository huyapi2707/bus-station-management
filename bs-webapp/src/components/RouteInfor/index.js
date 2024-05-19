import {useLocation, useParams} from 'react-router-dom';
import './styles.css';
import {useContext, useEffect, useState} from 'react';
import * as utils from '../../config/utils';
import {apis, endpoints} from '../../config/apis';
import {LoadingContext} from '../../config/context';
import moment from 'moment';

const RouteInfor = () => {
  let {id} = useParams();
  let {state} = useLocation();
  let route = state['route'];
  const {setLoading} = useContext(LoadingContext);
  const [trips, setTrips] = useState([]);
  const [tripId, setTripId] = useState(null);
  const [seatDetails, setSeatDetails] = useState([]);
  const [selectedSeats, setSelectedSeats] = useState([]);

  const handleToggleSeat = (event) => {
    if (!event.target.checked) {
      setSelectedSeats((selectedSeats) => {
        selectedSeats.push(event.target.value);
        return selectedSeats;
      });
    } else {
      const id = event.target.value;
      const index = selectedSeats.indexOf(id);
      setSelectedSeats((selectedSeats) => {
        selectedSeats.slice(index, 1);
        return selectedSeats;
      });
    }
  };

  const fetchAllTrips = async () => {
    setLoading('flex');
    try {
      const response = await apis(null).get(endpoints.route_trip_list(id));
      if (response) {
        setTrips(response['data']);
        setTripId(response['data'][0]['id']);
      }
    } catch (ex) {
      console.error(ex);
    } finally {
      setLoading('none');
    }
  };

  const fetchTripSeatDetails = async () => {
    setLoading('flex');
    try {
      const response = await apis(null).get(
        endpoints.trip_seat_details(tripId),
      );
      const availableSeat = response['data']['availableSeat'];
      const unAvailableSeat = response['data']['unAvailableSeat'];
      let seats = availableSeat.map((a) => {
        a['available'] = true;
        return a;
      });
      seats = seats.concat(
        unAvailableSeat.map((a) => {
          a['available'] = false;
          return a;
        }),
      );
      seats.sort((a, b) => {
        return a['id'] - b['id'];
      });
      setSeatDetails(seats);
      console.log(seats);
    } catch (ex) {
      console.error(ex);
    } finally {
      setLoading('none');
    }
  };

  useEffect(() => {
    fetchAllTrips();
  }, [id]);

  useEffect(() => {
    if (tripId !== null) {
      fetchTripSeatDetails();
    }
  }, [tripId, id]);
  return (
    <div className="container py-5">
      <div className="row">
        <div className="col-md-6 p-3">
          <div className="border-bottom mb-4">
            <h3>{route['company']['name']}</h3>
          </div>
          <div>
            <ul className="list-unstyled">
              <li className="mb-2">
                Route code: <span className="fw-bold">{route['name']}</span>
              </li>
              <li className="mb-2">
                Seat price:{' '}
                <span className="text-primary">
                  {utils.formatToVND(route['seatPrice'])}
                </span>
              </li>
              <li className="mb-2">
                Cargo price:{' '}
                <span className="text-info">
                  {utils.formatToVND(route['cargoPrice'])}
                </span>
              </li>

              <li className="row mb-2">
                <div className="col-md-6">
                  <p>
                    From: <span>{route['fromStation']['address']}</span>
                  </p>
                  <iframe
                    src={route['fromStation']['mapUrl']}
                    width="300"
                    height="300"
                    allowFullScreen={true}
                    loading="lazy"
                    referrerPolicy="no-referrer-when-downgrade"
                  ></iframe>
                </div>
                <div className="col-md-6">
                  <p>
                    To: <span>{route['toStation']['address']}</span>
                  </p>
                  <iframe
                    src={route['toStation']['mapUrl']}
                    width="300"
                    height="300"
                    allowFullScreen={true}
                    loading="lazy"
                    referrerPolicy="no-referrer-when-downgrade"
                  ></iframe>
                </div>
              </li>
            </ul>
          </div>
        </div>
        <div className="col-md-6 p-3">
          <div>
            <h5 className="text-center">Trips</h5>
            <div className="mt-4 border-bottom pb-3 d-flex flex-direction-column">
              {trips.map((trip) => {
                return (
                  <div key={trip['id']} className="form-check my-2">
                    <input
                      className="form-check-input"
                      type="radio"
                      name="tripId"
                      id={trip['id']}
                      onChange={() => {
                        setTripId(trip['id']);
                      }}
                      checked={trip['id'] === tripId}
                    />
                    <label
                      className="form-check-label"
                      htmlFor="flexRadioDefault2"
                    >
                      Depart at:{' '}
                      <span className="fw-bold">
                        {moment(trip['departAt']).format(
                          'MMMM Do YYYY, h:mm:ss a',
                        )}
                      </span>
                    </label>
                  </div>
                );
              })}
            </div>
            <div className="mt-4 d-flex align-items-center flex-column">
              <h5>Seat</h5>
              <div className="mt-3 seat-grid">
                {seatDetails.map((seat) => {
                  return (
                    <div key={seat['id']} className="form-check w-100 p-0">
                      <input
                        value={seat['id']}
                        type="checkbox"
                        className="btn-check"
                        id={seat['id']}
                        autocomplete="off"
                        onClick={(event) => handleToggleSeat(event)}
                        checked={selectedSeats.includes(seat['id'])}
                      />
                      <label
                        className={[
                          'btn',
                          seat['available']
                            ? 'btn-outline-primary'
                            : 'btn-danger',
                        ].join(' ')}
                        htmlFor={seat['id']}
                      >
                        {seat['code']}
                      </label>
                    </div>
                  );
                })}
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default RouteInfor;
