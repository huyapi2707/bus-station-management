import {createContext} from 'react';

const LoadingContext = createContext('none');
const AuthenticationContext = createContext(null);

export {LoadingContext, AuthenticationContext};
