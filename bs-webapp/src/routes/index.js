import Cart from '../components/Cart';
import {Routes, Route, BrowserRouter} from 'react-router-dom';
import Grid from '../components/Grid';
import RouteInfor from '../components/RouteInfor';
import AuthenticatedRoute from './AuthenticatedRoute';
import Checkout from '../components/Checkout';
import Login from '../pages/Login';
import Register from '../pages/Register';
import Home from '../pages/Home';
import {endpoints} from '../config/apis';
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
      </Routes>
    </BrowserRouter>
  );
};

export default AppRouter;
