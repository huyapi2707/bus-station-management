import {createContext} from 'react';

const LoadingContext = createContext('none');
const AuthenticationContext = createContext(null);
const CartContext = createContext(null);

const cartReducer = (currentState, action) => {
  switch (action.type) {
    case 'ADD_TO_CART': {
      const temp = [...currentState['data'], ...action.payload];
      let seen = {};
      const temp2 = temp.filter((item) => {
        let k = JSON.stringify(item);
        return seen.hasOwnProperty(k) ? false : (seen[k] = true);
      });
      return {
        key: JSON.stringify(temp2),
        data: temp2,
      };
    }
    case 'WITH_CARGO': {
      const temp = currentState['data'];
      temp.every((item) => {
        if (
          item['tripId'] == action['payload']['tripId'] &&
          item['seatId'] == action['payload']['seatId']
        ) {
          item['withCargo'] = action['payload']['withCargo'];

          return false;
        }
        return true;
      });
      return {
        key: JSON.stringify(temp),
        data: temp,
      };
    }
    case 'DELETE_ITEM': {
      let location = -1;
      const temp = currentState['data'];
      const temp2 = temp.filter((item, index) => {
        if (
          item['tripId'] == action['payload']['tripId'] &&
          item['seatId'] == action['payload']['seatId']
        ) {
          location = index;

          return false;
        }
        return true;
      });
      console.log(temp2);

      return {
        key: JSON.stringify(temp2),
        data: temp2,
      };
    }
  }
};

export {LoadingContext, AuthenticationContext, CartContext, cartReducer};
