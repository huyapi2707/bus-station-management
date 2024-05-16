import './styles.css';
import Navbar from '../../components/navbar';
import Banner from '../../components/banner';
import Grid from '../../components/Grid';
import {endpoints} from '../../config/apis';
const Home = () => {
  return (
    <>
      <Navbar />
      <Banner />
      <Grid
        title="Companies"
        dataEndpoint={endpoints.company_list}
        breadcrumb={['Home', 'Companies']}
      />
      <Grid
        title="Routes"
        dataEndpoint={endpoints.route_list}
        breadcrumb={['Home', 'Route']}
      />
    </>
  );
};

export default Home;
