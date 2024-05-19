import {createContext} from 'react';

const LoadingContext = createContext('none');
const AuthenticationContext = createContext(null);
const CartContext = createContext(null);

const cartReducer = (currentState, action) => {
  switch (action.type) {
    case 'TOGGLE_CART': {
      return {
        ...currentState,
        show: !currentState['show'],
      };
    }
  }
};

export {LoadingContext, AuthenticationContext, CartContext, cartReducer};
