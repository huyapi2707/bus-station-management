import {useContext} from 'react';
import './styles.css';
import {CartContext} from '../../config/context';

const Cart = () => {
  const {cart, cartDispatcher} = useContext(CartContext);
  return (
    <div
      className="offcanvas offcanvas-end"
      data-bs-scroll="true"
      tabIndex="-1"
      id="offcanvasWithBothOptions"
      aria-labelledby="offcanvasWithBothOptionsLabel"
    >
      <div className="offcanvas-header">
        <h5
          className="offcanvas-title mt-5  p-3"
          id="offcanvasWithBothOptionsLabel"
        >
          Your cart
        </h5>
        <button
          type="button"
          className="btn-close text-reset"
          data-bs-dismiss="offcanvas"
          aria-label="Close"
        ></button>
      </div>
      <div className="offcanvas-body p-4">
        <p>Try scrolling the rest of the page to see this option in action.</p>
      </div>
    </div>
  );
};

export default Cart;
