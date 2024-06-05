import Cart from '../components/Cart';
import {Routes, Route, BrowserRouter} from 'react-router-dom';
import Grid from '../components/Grid';
import RouteInfor from '../components/RouteInfor';
import AuthenticatedRoute from './AuthenticatedRoute';
import Checkout from '../components/Checkout';
import Login from '../pages/login';
import CreateRoute from '../pages/route';
import Register from '../pages/register';
import Home from '../pages/Home';
import {endpoints} from '../config/apis';
import PaymentResult from '../pages/PaymentResult';
import CreateCompany from '../pages/createCompany';
import ManageCompany from '../pages/manage';
import CreateTrip from '../pages/trip';
const AppRouter = () => {
  return (
    <BrowserRouter>
      <Cart />
      <Routes>
        <Route path="/" element={<Home />}>
          <Route
            index={true}
            element={
              <>
                <Grid
                  title="Công ty"
                  dataEndpoint={endpoints.company_list}
                  breadcrumb={['Trang chủ', 'Công ty']}
                />

                <Grid
                  title="Tuyến xe"
                  dataEndpoint={endpoints.route_list}
                  breadcrumb={['Trang chủ', 'Tuyến xe']}
                />
              </>
            }
          />
          <Route path="/route/:id" element={<RouteInfor />} />
          <Route
            path="/checkout"
            element={
              <AuthenticatedRoute>
                <Checkout />
              </AuthenticatedRoute>
            }
          />
        </Route>
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/payment-result" element={<PaymentResult />} />
        <Route path="/create-company" element={<CreateCompany/>} />
        <Route path="/manage-company" element={<ManageCompany/>} />
        <Route path="/create-route" element={<CreateRoute/>} />
        <Route path="/register-trip" element={<CreateTrip/>} />
      </Routes>
    </BrowserRouter>
  );
};

export default AppRouter;
