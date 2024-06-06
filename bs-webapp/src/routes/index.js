import Cart from '../components/Cart';
import {Routes, Route, BrowserRouter} from 'react-router-dom';
import Grid from '../components/Grid';
import RouteInfor from '../components/RouteInfor';
import AuthenticatedRoute from './AuthenticatedRoute';
import Checkout from '../components/Checkout';
import Login from '../pages/Login';
import Register from '../pages/Register';
import CreateCompany from '../pages/CreateCompany';
import ManageCompany from '../pages/Manage';
import CreateRoute from '../pages/Route';
import CreateTrip from '../pages/Trip';
import Home from '../pages/Home';
import {endpoints} from '../config/apis';
import PaymentResult from '../pages/PaymentResult';
import ManagerRoute from './ManagerRoute';
import Profile from '../components/Profile';
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
          <Route
            path="/profile"
            element={
              <AuthenticatedRoute>
                <Profile />
              </AuthenticatedRoute>
            }
          />
          <Route
            path="/create-company"
            element={
              <AuthenticatedRoute>
                <CreateCompany />
              </AuthenticatedRoute>
            }
          />
          <Route
            path="/manage-company"
            element={
              <ManagerRoute>
                <ManageCompany />
              </ManagerRoute>
            }
          />
          <Route
            path="/create-route"
            element={
              <ManagerRoute>
                <CreateRoute />
              </ManagerRoute>
            }
          />
          <Route
            path="/register-trip"
            element={
              <ManagerRoute>
                <CreateTrip />
              </ManagerRoute>
            }
          />
        </Route>
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/payment-result" element={<PaymentResult />} />
      </Routes>
    </BrowserRouter>
  );
};

export default AppRouter;
