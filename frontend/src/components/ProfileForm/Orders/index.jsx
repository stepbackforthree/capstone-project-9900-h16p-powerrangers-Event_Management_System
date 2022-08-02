import { Avatar, Button, List, Skeleton, message, Divider, Popconfirm   } from 'antd';
import React, { useEffect, useState } from 'react';
import request from '../../../utils/request';
import './styles.css';
import { useNavigate } from 'react-router-dom';


export default function Orders() {
  const navigate = useNavigate();
  const [data, setData] = useState();

  useEffect(() => {
    request('/order/queryOrdersByCustomer', {
      method: 'GET',
      data: {}
    }).then((response) => {
      console.log('data:', response);
      setData(response);
    })
  }, [])

  const refundOrder = (orderId, eventName, hostName, ticketAmount, ticketType) => {
    console.log('refund order id:', orderId);
    request(`/order/refundOrder?orderId=${orderId}`, {
      method: 'GET'
    }).then((response) => {
      console.log('response:', response);
    })
    request(`/tickets/updateTicketAmount`, {
      method: 'POST',
      data: {
        "eventName": eventName,
        "hostName": hostName,
        "ticketAmount": ticketAmount,
        "ticketType": ticketType
      }
    }).then((response) => {
      console.log('response:', response);
    })
    message.success('Refund Successfully!');
    setTimeout(() => {
      window.location.reload();
    }, 2000)
  }

  const gotoDetails = (hostName, eventName) => {
    console.log('goto details');
    navigate(`/event/eventOrder?hostName=${hostName}&eventName=${eventName}`);
  }


  return (
    <List
      itemLayout="horizontal"
      dataSource={data}
      renderItem={(item) => (
        <List.Item
          key={item.orderId}
          actions={[
            <Button 
              key="list-loadmore-edit" 
              type="link"
              onClick={()=>gotoDetails(item.hostName,item.eventName)}
            >
              Details
            </Button>
          ]}
          extra={
            <div>
              <div>
                <b>Number of tickets:</b>{item.paymentAmount}
              </div>
              <Divider style={{"margin": "10px 0"}}/>
              <div>
                <b>Refund:</b>
                {item.isRefund ? 
                <>
                  <span>Yes</span>
                  <Button disabled type="link">Refund</Button>  
                </>
                : 
                <>
                  <span>No</span>
                  <Popconfirm
                    title="Are you sure to refund this event?"
                    onConfirm={() =>{refundOrder(item.orderId, item.eventName, item.hostName, item.paymentAmount, item.ticketType)}}
                    okText="Yes"
                    cancelText="No"
                  >
                    <Button type="link">Refund</Button>
                  </Popconfirm>
                </>
                }
              </div>
            </div>
          }
        >
          <List.Item.Meta
            title={<a onClick={()=>gotoDetails(item.hostName,item.eventName)}><b>Event Name:</b> {item.eventName}</a>}
            description={`Host: ${item.hostName}`}
          />
        </List.Item>
      )}
    />
  )
}
