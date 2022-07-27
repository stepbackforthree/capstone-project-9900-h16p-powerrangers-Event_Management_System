import './styles.css';
import React from 'react';
import Button from '@material-ui/core/Button';
import { useNavigate } from 'react-router-dom';


export default function ListItem( {item:{image, eventName, eventType, location, description, tickerPrice, tickerAmount, startTime, endTime, starLevel}} ) {
  const navigate = useNavigate();
  const goToOrder = () => {
    navigate('/event/eventOrder');
  }

  const showTime = (string) => {
    const time = startTime.split('.')[0];
    const timeStr = time.split('T')[0] + ' ' + time.split('T')[1];
    return timeStr;
  }

  return (
    <div className="listItem-wrap">
      <img src={image} alt="item" />
      <header>
        <h4>{eventName}</h4>
        <span>⭐{starLevel}</span>
      </header>      
      <footer>
        <p>
          <b>Start Time: {showTime(startTime)}</b> 
          {/* <span>Fee ${deliveryFee}</span> */}
          <Button variant="contained" color="secondary" size="small" onClick={goToOrder}>Join!</Button>
        </p>
        <p>
          <b>${tickerPrice}</b>
        </p>
      </footer>
    </div>
  )
}

