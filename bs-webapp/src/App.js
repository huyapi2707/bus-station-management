import './App.css';
import Login from './pages/login';
import Register from './pages/register';
import {LoadingContext, AuthenticationContext} from './config/context';
import {useState} from 'react';
import Loading from './components/loading';
import {ToastContainer} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import {BrowserRouter, Route, Routes} from 'react-router-dom';
function App() {
  const [loading, setLoading] = useState('none');
  const [user, setUser] = useState(null);
  return (
    <div className="container-fluid">
      <AuthenticationContext.Provider value={{user, setUser}}>
        <LoadingContext.Provider value={{loading, setLoading}}>
          {loading && <Loading />}
          <ToastContainer />
          <BrowserRouter>
            <Routes>
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
