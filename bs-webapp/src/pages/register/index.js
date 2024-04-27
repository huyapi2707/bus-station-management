import './styles.css';
import '../login/styles.css';
import {useContext, useState} from 'react';
import * as validator from '../../config/validator';
import {LoadingContext, AuthenticationContext} from '../../config/context';
import {apis, endpoints} from '../../config/apis';
import {toast} from 'react-toastify';
import {Link} from 'react-router-dom';
const Register = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [rePassword, setRePassword] = useState('');
  const [email, setEmail] = useState('');
  const [isCompanyManager, setIsCompanyManager] = useState(false);
  const {setLoading} = useContext(LoadingContext);
  const {setUser} = useContext(AuthenticationContext);
  const validate = () => {
    if (password !== rePassword) {
      return 'Password and confirm password are mismatch';
    }
    const msgs = [];
    msgs.push(validator.validateEmail(email));
    msgs.push(validator.validateUsername(username));
    msgs.push(validator.validatePassword(password));
    for (let msg of msgs) {
      if (msg) return msg;
    }
  };
  const callRegister = async () => {
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
    } else {
      try {
        setLoading('flex');
        const response = await apis
          .post(endpoints.register, {
            username: username,
            password: password,
            email: email,
            role: isCompanyManager ? 'COMPANY_MANAGER' : 'CUSTOMER',
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
        if (response.status === 201) {
          localStorage.setItem('accessToken', response.data.accessToken);
          setUser(response.data);
        }
      } catch (error) {
        console.log(error);
      } finally {
        setLoading('none');
      }
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
                <h3>Register</h3>
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
                  <label htmlFor="email" className="form-label">
                    Email
                  </label>
                  <input
                    className="form-control form-control-lg"
                    id="email"
                    name="email"
                    type="email"
                    aria-describedby="email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                  ></input>
                </div>
                <div className="mb-3">
                  <label htmlFor="password" className="form-label">
                    Confirm password
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
                <div className="mb-3">
                  <label htmlFor="re-password" className="form-label">
                    Password
                  </label>
                  <input
                    className="form-control form-control-lg"
                    id="re-password"
                    name="re-password"
                    type="password"
                    aria-describedby="re-password"
                    value={rePassword}
                    onChange={(e) => setRePassword(e.target.value)}
                  ></input>
                </div>
                <div className="form-check mb-5">
                  <input
                    className="form-check-input"
                    type="checkbox"
                    checked={isCompanyManager}
                    onChange={(e) => {
                      setIsCompanyManager(
                        (isCompanyManager) => !isCompanyManager,
                      );
                    }}
                    id="isCompanyManager"
                  />
                  <label
                    className="form-check-label"
                    htmlFor="isCompanyManager"
                  >
                    Wanna open a company?
                  </label>
                  <p className="text-muted" style={{fontSize: '0.9rem'}}>
                    We will create your account as company manager role
                  </p>
                </div>
                <button
                  onClick={callRegister}
                  type="button"
                  className="btn btn-primary btn-lg"
                  style={{width: '100%'}}
                >
                  Go
                </button>
                <Link
                  to="/login"
                  className="d-block text-center my-4 text-muted btn"
                >
                  Already have an account?
                </Link>
              </form>
            </div>
          </div>
        </div>
      </div>
      <div className="col-md-6 rightImage"></div>
    </div>
  );
};
export default Register;
