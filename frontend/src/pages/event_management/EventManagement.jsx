import React from 'react';
import EventManagementForm from '../../components/EventManagementForm';

export default function eventManagement() {
  const event = {
    "eventName": "Kobe fan Meeting",
    "eventType": 2,
    "eventDescription": "Fan meeting, recall the past time",
    "location": "Melbourne",
    "siteDescription": "2 Macquarie Street, Sydney CBD NSW 2000",
    "numberOfTickets": 200,
    "startTime": "2022-07-30T17:30:00",
    "endTime": "2022-07-31T17:50:00",
    "isDisplayed": true,
    "starLevel": "4",
    "eventTag": "sports"
  }
  
  
  return (
    <div>
      <EventManagementForm event={event}/>
    </div>
  )
}
