import {Link} from 'react-router-dom';

const CompanyItem = ({value}) => {
  return (
    <Link className="nav-link grid-item border">
      <div className="image-container">
        <img src={value['avatar']}></img>
      </div>
      <div className="mt-3">
        <h6>{value['name']}</h6>
        <p className="text-muted">
          Has cargo transport:{' '}
          {value['isCargoTransport'] ? (
            <span className="text-success fw-bold">Yes</span>
          ) : (
            <span className="text-dark fw-bold">No</span>
          )}
        </p>
      </div>
    </Link>
  );
};

export default CompanyItem;
