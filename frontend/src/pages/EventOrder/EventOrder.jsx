import React, { useEffect } from 'react';
import { useSearchParams } from 'react-router-dom';
import request from '../../utils/request';


export default function EventOrder() {
  const [search,setSearch] = useSearchParams();
  const eventId = search.get("eventId");

  useEffect(() => {
    request(`/order/queryEventOrdersByHost?eventId=${eventId}`,{
      method: 'GET',
    }).then((response) => {
      console.log(response);
      // setEventList(response);
    })
  }, []);

  return (
    <div>
      EventOrder-{eventId}
    </div>
  )
}
