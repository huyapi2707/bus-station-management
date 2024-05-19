import {Link} from 'react-router-dom';
import * as utils from '../../config/utils';
const RouteItem = ({value}) => {
  return (
    <Link
      to={`/route/${value['id']}`}
      state={{route: value}}
      className="nav-link grid-item border"
    >
      <div className="image-container">
        <h1>{value['name']}</h1>
      </div>
      <div className="mt-3">
        <ul className="nav d-flex flex-column">
          <li className="nav-item">
            Route: {value['fromStation']['address']} -{' '}
            {value['toStation']['address']}
          </li>
          <li className="nav-item">Company: {value['company']['name']}</li>
          <li className="nav-item">
            Seat price:{' '}
            <span className="text-primary">
              {utils.formatToVND(value['seatPrice'])}
            </span>
          </li>
          <li className="nav-item">
            Cargo price:{' '}
            <span className="text-primary">
              {utils.formatToVND(value['cargoPrice'])}
            </span>
          </li>
        </ul>
      </div>
    </Link>
  );
};

export default RouteItem;
