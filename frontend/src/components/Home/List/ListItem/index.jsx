import './styles.css';
import React from 'react';
import Button from '@material-ui/core/Button';
import { useNavigate } from 'react-router-dom';


export default function ListItem( {item:{coverSrc, title, price, deliveryFee, serviceTime, rating}} ) {
  const navigate = useNavigate();
  const goToOrder = () => {
    navigate('/event/eventOrder');
  }
  return (
    <div className="listItem-wrap">
      <img src={coverSrc} alt="item" />
      <header>
        <h4>{title}</h4>
        <span>⭐{rating}</span>
      </header>      
      <footer>
        <p>
          <b>Start Time: {serviceTime}</b> 
          {/* <span>Fee ${deliveryFee}</span> */}
          <Button variant="contained" color="secondary" size="small" onClick={goToOrder}>Join!</Button>
        </p>
        <p>
          <b>${price}</b>
        </p>
      </footer>
    </div>
  )
}
