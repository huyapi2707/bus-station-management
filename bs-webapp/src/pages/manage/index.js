import Navbar from '../../components/Navbar';
import {Link} from 'react-router-dom';

const ManageCompany = () => {
  return (
    <>
      <Navbar />
      <h1>Nguyen Quoc Hung</h1>
      <Link to="/create-route">
        <button>Create Route</button>
      </Link>
    </>
  );
};
export default ManageCompany;
