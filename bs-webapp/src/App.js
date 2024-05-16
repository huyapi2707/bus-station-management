import './App.css';
import Login from './pages/login';
import Register from './pages/register';
import {LoadingContext, AuthenticationContext} from './config/context';
import {useEffect, useState} from 'react';
import Loading from './components/loading';
import {ToastContainer} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import {BrowserRouter, Route, Routes} from 'react-router-dom';
import Home from './pages/Home';
import {apis, endpoints} from './config/apis';
function App() {
  const [loading, setLoading] = useState('none');
  const [user, setUser] = useState(null);

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
  return (
    <div className="container-fluid">
      <AuthenticationContext.Provider value={{user, setUser}}>
        <LoadingContext.Provider value={{loading, setLoading}}>
          {loading && <Loading />}
          <ToastContainer />
          <BrowserRouter>
            <Routes>
              <Route path="/" element={<Home />} />
              <Route path="/login" element={<Login />} />
              <Route path="/register" element={<Register />} />
            </Routes>
          </BrowserRouter>
        </LoadingContext.Provider>
      </AuthenticationContext.Provider>
    </div>
  );
}

export default App;
