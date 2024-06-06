import {useContext} from 'react';

import {Navigate, useLocation} from 'react-router-dom';
import {AuthenticationContext} from '../config/context';

const AuthenticatedRoute = ({children}) => {
  const {user} = useContext(AuthenticationContext);

  let {pathname} = useLocation();

  if (user) {
    if (user['role'] === 'COMPANY_MANAGER') {
      return children;
    } else {
      return <Navigate to={'/create-company'} />;
    }
  } else {
    return <Navigate to={{pathname: '/login'}} state={{from: pathname}} />;
  }
};

export default AuthenticatedRoute;
