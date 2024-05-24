import {useContext} from 'react';

import {Navigate, useLocation} from 'react-router-dom';
import {AuthenticationContext} from '../config/context';

const AuthenticatedRoute = ({children}) => {
  const {user} = useContext(AuthenticationContext);

  let {pathname} = useLocation();

  return user ? (
    children
  ) : (
    <Navigate to={{pathname: '/login'}} state={{from: pathname}} />
  );
};

export default AuthenticatedRoute;
