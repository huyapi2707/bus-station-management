import {useContext, useState} from 'react';
import '../../styles/login.css';
import {FaGoogle} from 'react-icons/fa';
import {LoadingContext, AuthenticationContext} from '../../config/context';
import {apis, endpoints} from '../../config/apis';
import {toast} from 'react-toastify';
import {Link} from 'react-router-dom';
import * as validator from '../../config/validator';
const Login = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const {setLoading} = useContext(LoadingContext);
  const {setUser} = useContext(AuthenticationContext);

  const validate = () => {
    const msgs = [];
    msgs.push(validator.validateUsername(username));
    msgs.push(validator.validatePassword(password));
    for (let msg of msgs) {
      if (msg) return msg;
    }
  };
  const callLogin = async () => {
    const validateMsg = validate();
    if (validateMsg) {
      toast.error(validateMsg, {
        position: 'top-center',
        autoClose: 4000,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
        theme: 'colored',
      });
      return;
    }

    try {
      setLoading('flex');
      const response = await apis
        .post(endpoints.login, {
          username: username,
          password: password,
        })
        .catch((error) => {
          if (error.response.status === 400) {
            toast.error(error.response.data, {
              position: 'top-center',
              autoClose: 4000,
              closeOnClick: true,
              pauseOnHover: true,
              draggable: true,
              progress: undefined,
              theme: 'colored',
            });
          }
        });
      const data = response.data;

      localStorage.setItem('accessToken', data.accessToken);
      setUser(data);
    } catch (error) {
      console.info(error);
    } finally {
      setLoading('none');
    }
  };
  return (
    <div className="row" style={{height: '100vh'}}>
      <div className="col-md-6">
        <div className="container" style={{height: '100%'}}>
          <div
            className="row align-items-center justify-content-center"
            style={{height: '100%'}}
          >
            <div className="col-md-7">
              <div>
                <h3>Login</h3>
                <p className="text-muted">Welcome to Bus Station</p>
              </div>

              <form>
                <div className="mb-3">
                  <label htmlFor="username" className="form-label">
                    Username
                  </label>
                  <input
                    className="form-control form-control-lg"
                    id="username"
                    name="username"
                    type="text"
                    aria-describedby="username"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                  ></input>
                </div>
                <div className="mb-3">
                  <label htmlFor="password" className="form-label">
                    Password
                  </label>
                  <input
                    className="form-control form-control-lg"
                    id="password"
                    name="password"
                    type="password"
                    aria-describedby="password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                  ></input>
                </div>
                <div className="d-flex justify-content-between mb-3">
                  <p className="text-decoration-underline text-muted btn">
                    Forgot password?
                  </p>
                  <Link
                    to="/register"
                    className="text-decoration-underline text-muted btn"
                  >
                    Dont't have an account?
                  </Link>
                </div>
                <button
                  onClick={callLogin}
                  type="button"
                  className="btn btn-primary btn-lg"
                  style={{width: '100%'}}
                >
                  Go
                </button>
                <span className="d-block text-center my-4 text-muted">
                  -- or --
                </span>
                <button
                  type="button"
                  className="btn btn-danger btn-lg"
                  style={{width: '100%'}}
                >
                  <FaGoogle className="me-2" />
                  Login with Google
                </button>
              </form>
            </div>
          </div>
        </div>
      </div>
      <div className="col-md-6 rightImage"></div>
    </div>
  );
};

export default Login;
