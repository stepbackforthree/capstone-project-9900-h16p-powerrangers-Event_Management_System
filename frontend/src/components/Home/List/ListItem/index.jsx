import './styles.css';
import React from 'react';
import Button from '@material-ui/core/Button';
import { useNavigate } from 'react-router-dom';
import LocationOnIcon from '@material-ui/icons/LocationOn';

const eventTypeMap = {
  1: 'Concert',
  2: 'Sports',
  3: 'Comic and Animation',
  4: 'Parents-child Campaign',
  5: 'Tourism Exhibition'
}

export default function ListItem( {item:{hostName, image, eventName, eventType, location, description, ticketPrice, ticketAmount, startTime, endTime, starLevel}} ) {
  const navigate = useNavigate();
  const goToOrder = (hostName, eventName) => {
    // window.localStorage.setItem('queryHostName', hostName);
    // window.localStorage.setItem('queryEventName', eventName);
    navigate(`/event/eventOrder?hostName=${hostName}&eventName=${eventName}`);
  }

  const showTime = (string) => {
    const time = string.split('.')[0];
    const timeStr = time.split('T')[0] + ' ' + time.split('T')[1];
    return timeStr;
  }

  return (
    <div className="listItem-wrap">
      <img src={image} alt="item" />
      <header>
        <h4>{eventName}[{eventTypeMap[eventType]}]</h4>
        <span>⭐{starLevel}</span>
      </header>      
      <footer>
        <p>
          <b>Start Time: {showTime(startTime)}</b> 
        </p>
        <p>
          <b>${ticketPrice}</b>
        </p>
        
      </footer>
      <div>
        <div>
          <LocationOnIcon/>{location}
        </div>
        <Button variant="contained" color="secondary" size="small" onClick={() => goToOrder(hostName, eventName)}>Join!</Button>
      </div>
    </div>
  )
}

