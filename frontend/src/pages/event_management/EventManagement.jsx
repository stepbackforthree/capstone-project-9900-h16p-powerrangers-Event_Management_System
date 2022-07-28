import React, { useState, useEffect } from 'react';
import EventManagementForm from '../../components/EventManagementForm';
import request from '../../utils/request';


export default function EventManagement() {
  // const event = {
  //   "eventName": "Kobe fan Meeting",
  //   "eventType": 2,
  //   "eventDescription": "Fan meeting, recall the past time",
  //   "location": "Melbourne",
  //   "siteDescription": "2 Macquarie Street, Sydney CBD NSW 2000",
  //   "numberOfTickets": 200,
  //   "startTime": "2022-07-30T17:30:00",
  //   "endTime": "2022-07-31T17:50:00",
  //   "isDisplayed": true,
  //   "starLevel": "4",
  //   "eventTag": "sports"
  // }

  const [event, setEvent] = useState('');

  useEffect(() => {
    request(`/events/queryEvent`,{
      method: 'POST',
      data: {
        'eventName': window.localStorage.getItem('eventName'),
        'userName': window.localStorage.getItem('userName')
      }
    }).then((response) => {
      // console.log(response);
      setEvent(response);
    })
  }, []);

  
  return (
    <div>
      <EventManagementForm event={event}/>
    </div>
  )
}
