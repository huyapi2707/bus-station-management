import './App.css';
import Login from './pages/Login';
import Register from './pages/Register';
import {
  LoadingContext,
  AuthenticationContext,
  cartReducer,
  CartContext,
} from './config/context';
import {useEffect, useReducer, useState} from 'react';
import Loading from './components/Loading';
import {ToastContainer} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import {BrowserRouter, Route, Routes, json} from 'react-router-dom';
import Home from './pages/Home';
import Cart from './components/Cart';
import {apis, endpoints} from './config/apis';
import CartIcon from './components/CartIcon';
import Grid from './components/Grid';
import RouteInfor from './components/RouteInfor';
import {type} from '@testing-library/user-event/dist/type';
function App() {
  const [loading, setLoading] = useState('none');
  const [user, setUser] = useState(null);
  const [cart, cartDispatcher] = useReducer(cartReducer, {
    key: '',
    data: [],
  });

  const fetchUserInfor = async (accessToken) => {
    try {
      setLoading('flex');
      const response = await apis(accessToken).get(endpoints.userInfor);
      if (response) {
        setUser(response['data']);
      } else {
        localStorage.removeItem('accessToken');
      }
    } catch (ex) {
      localStorage.removeItem('accessToken');
      console.log(ex);
    } finally {
      setLoading('none');
    }
  };
  useEffect(() => {
    const accessToken = localStorage.getItem('accessToken');
    if (accessToken && !user) {
      fetchUserInfor(accessToken);
    }
  }, [user]);

  useEffect(() => {
    const localStorageItem = localStorage.getItem('cart');
    if (cart['key'] === '' && localStorageItem !== '') {
      try {
        const temp = JSON.parse(localStorageItem);
        if (temp.length > 0) {
          cartDispatcher({
            type: 'ADD_TO_CART',
            payload: temp,
          });
        }
      } catch (ex) {
        console.error(ex);
      }
    } else {
      localStorage.setItem('cart', cart['key']);
    }
  }, [cart['key']]);

  return (
    <div className="container-fluid">
      <AuthenticationContext.Provider value={{user, setUser}}>
        <LoadingContext.Provider value={{loading, setLoading}}>
          <CartContext.Provider value={{cart, cartDispatcher}}>
            {loading && <Loading />}
            <ToastContainer />
            <CartIcon />
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
                </Route>
                <Route path="/login" element={<Login />} />
                <Route path="/register" element={<Register />} />
              </Routes>
            </BrowserRouter>
          </CartContext.Provider>
        </LoadingContext.Provider>
      </AuthenticationContext.Provider>
    </div>
  );
}

export default App;
