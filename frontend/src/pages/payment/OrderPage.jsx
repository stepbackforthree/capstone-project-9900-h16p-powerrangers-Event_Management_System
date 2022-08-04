import styled from 'styled-components';
import React, { useState, useEffect }  from 'react';
import './styles.css';
import Paper from '@material-ui/core/Paper';
import { Image, InputNumber, Divider, Avatar, List, Space, Radio, Button, Input  } from 'antd';
import { LikeOutlined, MessageOutlined, StarOutlined } from '@ant-design/icons';
import QueryBuilderIcon from '@material-ui/icons/QueryBuilder';
import LocationCityIcon from '@material-ui/icons/LocationCity';
import GradeRoundedIcon from '@material-ui/icons/GradeRounded';
import moment from 'moment';
import request from '../../utils/request';
import { Modal } from 'antd';
import { useSearchParams } from 'react-router-dom';
import RecordVoiceOverIcon from '@material-ui/icons/RecordVoiceOver';



const TitleContainer = styled.div`
  /* position: relative; */
  display: flex;
  align-items: center;
  justify-content: space-between;
  /* padding: 10px; */
  border-bottom: 1px solid rgba(0,0,0,.26);
  padding: 1.5rem 1rem;
`;

const ContentContainer = styled.div`
  display: flex;
  flex-grow: 1;
  flex-direction: column;
  align-items: center;
  padding: 10px;
`;

const eventType = {
  1: 'Concert',
  2: 'Sports',
  3: 'Comic and Animation',
  4: 'Parents-child Campaign',
  5: 'Tourism Exhibition'
}

export default function OrderPage() {
  const [search,setSearch] = useSearchParams();
  const hostName = search.get("hostName");
  const [eventId, setEventId] = useState(0);
  const eventName = search.get("eventName");
  const userName = window.localStorage.getItem('userName');
  const [data, setData] = useState('');
  const [ticketChoise, SetTicketChoise] = useState('fullPriceTicket');
  const [isModalVisible, setIsModalVisible] = useState(false);
  const [ticketAmount, setTicketAmount] = useState(1);
  const [ticketPrice, setTicketPrice] = useState(1);
  const [balance, setBalance] = useState(1);
  const [commentsList, setCommentsList] = useState();
  const [couponsList, setCouponsList] = useState();
  const [couponCode, setCouponCode] = useState('');
  const [couponMoney, setCouponMoney] = useState(0);
  const [couponThreshold, setCouponThreshold] = useState(0);
  

  const ticketChoiseChange = (e) => {
    SetTicketChoise(e.target.value);
    setTicketPrice(e.target.price);
  };

  useEffect(() => {
    console.log('ticketChoise:',ticketChoise);
    console.log('ticketPrice:',ticketPrice);
  }, [ticketChoise, ticketPrice]);

  useEffect(() => {
    request(`/users/queryUser`,{
      method: 'GET',
      data: {}
    }).then((response) => {
      console.log(response);
      setBalance(response.balance);
    })
  }, []);

  const onTicketAmountChange = (value) => {
    console.log('ticketAmount changed', value);
    setTicketAmount(value);
  };

  useEffect(() => {
    request(`/events/queryEvent`,{
      method: 'POST',
      data: {
        // 'eventName': window.localStorage.getItem('queryEventName'),
        'eventName': eventName,
        // 'hostName': window.localStorage.getItem('queryHostName'),
        'hostName': hostName
      }
    }).then((response) => {
      // console.log(response);
      setData(response);
      setEventId(response.eventId);
    })
  }, []);

  useEffect(() => {
    request(`/comment/getComments?hostName=${hostName}&eventName=${eventName}`,{
      method: 'GET',
      data: {}
    }).then((response) => {
      console.log(response);
      setCommentsList(response);
    })
  }, []);

  useEffect(() => {
    request(`/coupon/getCoupons?eventId=${eventId}`,{
      method: 'GET',
      data: {}
    }).then((response) => {
      console.log(response);
      setCouponsList(response);
    })
  }, [eventId])

  const deleteComment = (customerId) => {
    console.log('deleteComment');
    // costomerId hostName eventName
    request(`/comment/deleteComment`, {
      method: 'POST',
      data: {
        "customerId": customerId,
        "hostName": hostName,
        "eventName": eventName
      }
    }).then((response) => {
      console.log(response);
      window.location.reload();
    })
  }

  // payment method modal
  const showModal = () => {
    setIsModalVisible(true);
  };
  const handleOk = () => {
    const insertOrderData = {
      "eventName": eventName,
      "hostName": hostName,
      "paymentType": 1,
      "ticketAmount": ticketAmount,
      "ticketPrice": ticketPrice,
      "ticketType": ticketChoise
    }
    const updateTicketAmountData = {
      "eventName": eventName,
      "hostName": hostName,
      "ticketAmount": ticketAmount,
      "ticketType": ticketChoise
    }
    console.log('insertOrderData:', insertOrderData);
    console.log('updateTicketAmountData:', updateTicketAmountData);
    // setIsModalVisible(false);
    request('/tickets/updateTicketAmount', {
      method: 'POST',
      data: updateTicketAmountData
    }).then((res) => {
      console.log(res);
    })
    
    request('/order/insertOrder', {
      method: 'POST',
      data: insertOrderData
    }).then((res) => {
      console.log(res);
    })

    window.location.reload();
    
  };
  const handleCancel = () => {
    setIsModalVisible(false);
  };




  const IconText = ({ icon, text }) => (
    <Space>
      {React.createElement(icon)}
      {text}
    </Space>
  );

  const showTime = (string) => {
    const time = string.split('.')[0];
    const timeStr = time.split('T')[0] + ' ' + time.split('T')[1];
    return timeStr;
  }

  const useCoupon = () => {
    console.log('using coupon');
    request(`/coupon/getCoupon?couponName=${couponCode}`, {
      method: 'GET',
    }).then((response) => {
      console.log(response);
      setCouponMoney(response.money);
      setCouponThreshold(response.threshold);
    })
  }


  return (
    <div>
      <Modal title="Text Input" visible={isModalVisible} onOk={handleOk} onCancel={handleCancel}>
        <p><b>Ticket you choose: </b>{ticketChoise}</p> 
        <p><b>Your Balance: </b>${balance}</p> 
        <p><b>Ticket Price: </b>${ticketPrice}</p>
        
        <p>
          <b>Ticket Amount:</b>
          <InputNumber min={1} max={10} value={ticketAmount} onChange={onTicketAmountChange} />
        </p>
        <p>[One Account Only Can Order 10 tickets Once at most]</p>
        <p><b>Total Price: </b>${ticketPrice*ticketAmount}</p>
        <p><b>Discount Price:</b></p> 
        {ticketPrice*ticketAmount >= couponThreshold ?
          <>${ticketPrice*ticketAmount-couponMoney}</>    :
          <>${ticketPrice*ticketAmount}</>
        }
        <p>
          <b>Input Coupon:</b>
          <Button type="link" onClick={() => {useCoupon()}}>Use</Button>
          <Input value={couponCode} onChange={(e)=> {setCouponCode(e.target.value)}} />
        </p>
        {/* <b>Choose Your Payment Method:</b> */}
        
      </Modal>
      
      <TitleContainer>
        <h2>Order Page</h2>
      </TitleContainer>
      <ContentContainer>
        <Paper elevation={5} className="event-container">
          <div className="eventName-container">
            <h2>[{data.location}]{data.eventName}</h2>
          </div>
          <div className="eventDetail-container">
            <div className="eventImage-container">
              <Image
                width={200}
                height={350}
                src={data.image}
              />
            </div>
            <div className="eventContent-container">
              <div className="eventContentTop-container">
                <div className="icon-text-container">
                  <QueryBuilderIcon/>
                  {moment(data.startTime).format("YYYY-MM-DD HH:mm:ss")}
                  -
                  {moment(data.endTime).format("YYYY-MM-DD HH:mm:ss")}
                </div>
              </div>
              <div className="icon-text-container">
                <RecordVoiceOverIcon/>
                <div>
                  <b>Host Name: </b>
                  {data.hostName}
                </div>
              </div>
              <div className="icon-text-container">
                <LocationCityIcon/>
                <div>
                  <b>City: </b>
                  {data.location}
                </div>
              </div>
              <div>
                <b>Event Type: </b>
                {eventType[data.eventType]}
              </div>
              <div  className="icon-text-container">
                <GradeRoundedIcon/>
                <div>
                  <b>Star Level: </b>
                  {data.starLevel}
                </div>
              </div>
              <div>
                <b>Description: </b>
                {data.description}
              </div>
              <div className="payment-container">
                <h2><b>Tickets: </b></h2>
                <Radio.Group value={ticketChoise} onChange={ticketChoiseChange}>
                  {data.tickets && data.tickets.map((item) => {
                    return (
                      <Radio.Button value={item.ticketType} price={item.ticketPrice}>{item.ticketType}</Radio.Button>
                    )
                  })}
                </Radio.Group>
                {data.tickets && data.tickets.map((item) => {
                  if (item.ticketType === ticketChoise) {
                    return (
                      <div className="details-row" key={item.ticketType}>
                        <div>
                          <b>{item.ticketType} Price:</b>
                          <i>${item.ticketPrice}</i>
                        </div>
                        <div className="amount-container">
                          <b>Remaining Amount:</b>
                          {item.ticketAmount}
                        </div>
                      </div>
                    )
                  }
                })}
                <div className='pay-button'>
                  <Button 
                    type="primary"
                    shape="round"
                    size="large"
                    onClick={() => showModal()}
                  >
                    Place An Order
                  </Button>
                </div>
              </div>
            </div>
          </div>
          <Divider orientation="left">Coupons</Divider>
          <List
            itemLayout="vertical"
            size="large"
            pagination={{
              onChange: (page) => {
                console.log(page);
              },
              pageSize: 2,
            }}
            dataSource={couponsList}
            renderItem={(item) => (
              <List.Item
                key={item.couponId}
                // actions={[
                //   <IconText icon={LikeOutlined} text="10" key="list-vertical-like-o" />,
                //   <IconText icon={MessageOutlined} text="2" key="list-vertical-message" />,
                // ]}
              >
                <List.Item.Meta
                  // avatar={<Avatar src={item.avatar}/>}
                  title={<h4><b>Coupon Code: </b>{item.couponName}</h4>}
                  description={`Amount:${item.amount}`}
                />
                <div>
                  <b>Valid Time: </b>  
                  <i>{showTime(item.startTime)} <b style={{"margin": "0 10px"}}>to</b> {showTime(item.endTime)}</i>
                </div>
              </List.Item>
            )}
          />
          <Divider orientation="left">Comments</Divider>
          <List
            itemLayout="vertical"
            size="large"
            pagination={{
              onChange: (page) => {
                console.log(page);
              },
              pageSize: 3,
            }}
            dataSource={commentsList}
            footer={
              <div>
                <b>9900-H16P-PowerRangers</b>
              </div>
            }
            renderItem={(item) => (
              <List.Item
                key={item.customerId}
                actions={[
                  <IconText icon={LikeOutlined} text="106" key="list-vertical-like-o" />,
                  <IconText icon={MessageOutlined} text="2" key="list-vertical-message" />,
                ]}
              >
                <List.Item.Meta
                  avatar={<Avatar src={item.avatar}/>}
                  title={<b>{item.customerName}</b>}
                  description={`⭐${item.starLevel}`}
                />
                <div className="flex-space-between">
                  {item.comment}
                  {item.customerName.toLowerCase() === userName ? 
                    <Button 
                      type="primary" 
                      danger 
                      size="small"
                      onClick={()=>deleteComment(item.customerId)}
                    >
                      Delete
                    </Button> : 
                    null
                  }
                </div>
              </List.Item>
            )}
          />
        </Paper>
      </ContentContainer>
    </div>
  )
}
