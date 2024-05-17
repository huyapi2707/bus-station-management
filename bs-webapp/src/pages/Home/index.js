import './styles.css';
import Navbar from '../../components/navbar';
import Banner from '../../components/banner';
import {endpoints} from '../../config/apis';
import Grid from '../../components/Grid';
import Footer from '../../components/footer';
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
      <Footer />
    </>
  );
};

export default Home;
