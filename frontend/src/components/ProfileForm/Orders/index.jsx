import { Avatar, Button, List, Skeleton, message, Divider, Popconfirm, Modal,Rate,Input   } from 'antd';
import React, { useEffect, useState } from 'react';
import request from '../../../utils/request';
import './styles.css';
import { useNavigate } from 'react-router-dom';


export default function Orders() {
  const navigate = useNavigate();
  const [data, setData] = useState();
  const [starLevel, setStarLevel] = useState(2.5);
  const [comment, setComment] = useState('');
  const [isModalVisible, setIsModalVisible] = useState(false);
  const { TextArea } = Input;
  const [customerId, setCustomerId] = useState();
  const [eventName, setEventName] = useState();
  const [hostName, setHostName] = useState();



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


  const showCommentModal = (customerId,eventName,hostName) => {
    console.log('show comment modal');
    console.log(customerId,eventName,hostName);
    setCustomerId(customerId);
    setEventName(eventName);
    setHostName(hostName);
    setIsModalVisible(true);
  };

  const handleSubmitComment = () => {
    const addCommentData = {
      'customerId': customerId,
      'hostName': hostName,
      'eventName': eventName,
      'starLevel': starLevel,
      'comment': comment
    }
    console.log('addCommentData:',addCommentData);
    request('/comment/addComment', {
      method: 'POST',
      data: addCommentData
    }).then((response) => {
      console.log(response);
      message.success(response.msg, 5);
    })
    setIsModalVisible(false);
  };

  const handleCancel = () => {
    setIsModalVisible(false);
  };


  return (
    <>
    
    <Modal 
      title="Comment Submission" 
      visible={isModalVisible} 
      onOk={()=>handleSubmitComment()} 
      onCancel={handleCancel}
      okText="submit comment"
    >
      <div><b>Star:</b></div>
      <Rate 
        allowHalf 
        value={starLevel}
        onChange={(value)=>{setStarLevel(value)}}
      />
      <div><b>Comment Description:</b></div>
      <TextArea 
        rows={4}
        value={comment}
        onChange = {(e)=>{
          setComment(e.target.value)}
        }
      />
    </Modal>
    <List
      itemLayout="horizontal"
      dataSource={data}
      renderItem={(item) => (
        <List.Item
          key={item.orderId}
          actions={[
            <Button 
              key="list-details" 
              type="link"
              onClick={()=>gotoDetails(item.hostName,item.eventName)}
            >
              details
            </Button>,
            <Button
              key="list-comment" 
              type="link"
              onClick={()=>showCommentModal(item.customerId,item.eventName,item.hostName)}
            >
              comment
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
    </>
  )
}
