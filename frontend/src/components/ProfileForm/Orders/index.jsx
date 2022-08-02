import { Avatar, Button, List, Skeleton } from 'antd';
import React, { useEffect, useState } from 'react';
import request from '../../../utils/request';
import './styles.css';


export default function Orders() {
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

  const refundOrder = (orderId) => {
    console.log('refund order id:', orderId);
    request(`/order/refundOrder?orderId=${orderId}`, {
      method: 'GET'
    }).then((response) => {
      console.log('response:', response);

    })
  }


  return (
    <List
      itemLayout="horizontal"
      dataSource={data}
      renderItem={(item) => (
        <List.Item
          key={item.orderId}
          actions={
            [<Button key="list-loadmore-edit" type="link">Details</Button>
          ]}
          extra={
            <div>
              <div>
                <b>Number of tickets:</b>{item.paymentAmount}
              </div>
              <div>
                <b>Refund:</b>
                {item.isRefund ? 
                <>
                  <span>Yes</span>
                  <Button disable type="link">Refund</Button>  
                </>
                : 
                <>
                  <span>No</span>
                  <Button type="link" onClick={() =>{refundOrder(item.orderId)}}>Refund</Button>
                </>
                }
              </div>
            </div>
          }
        >
          <List.Item.Meta
            title={<a href="#">title</a>}
          />
        </List.Item>
      )}
    />
  )
}
