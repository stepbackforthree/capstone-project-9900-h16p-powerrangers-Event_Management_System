import styled from 'styled-components';
import React, { useState, useEffect }  from 'react';
import './styles.css';
import Paper from '@material-ui/core/Paper';
import { Image } from 'antd';
import QueryBuilderIcon from '@material-ui/icons/QueryBuilder';
import LocationCityIcon from '@material-ui/icons/LocationCity';
import GradeRoundedIcon from '@material-ui/icons/GradeRounded';
import { Radio, Button } from 'antd';
import moment from 'moment';
import request from '../../utils/request';
import { Modal } from 'antd';



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
  const [ticketChoise, SetTicketChoise] = useState('fullPriceTicket');
  const [isModalVisible, setIsModalVisible] = useState(false);


  const ticketChoiseChange = (e) => {
    SetTicketChoise(e.target.value);
  };

  useEffect(() => {
    console.log('ticketChoise:',ticketChoise)
  }, [ticketChoise]);

  const [data, setData] = useState('');

  useEffect(() => {
    request(`/events/queryEvent`,{
      method: 'POST',
      data: {
        'eventName': window.localStorage.getItem('queryEventName'),
        'hostName': window.localStorage.getItem('queryHostName')
      }
    }).then((response) => {
      // console.log(response);
      setData(response);
    })
  }, []);

  // payment method modal
  const showModal = () => {
    setIsModalVisible(true);
  };
  const handleOk = () => {
    setIsModalVisible(false);
  };
  const handleCancel = () => {
    setIsModalVisible(false);
  };

  return (
    <div>
      <Modal title="Text Input" visible={isModalVisible} onOk={handleOk} onCancel={handleCancel}>
        <b>Choose Your Payment Method:</b>
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
                      <Radio.Button value={item.ticketType}>{item.ticketType}</Radio.Button>
                    )
                  })}
                </Radio.Group>
                {data.tickets && data.tickets.map((item) => {
                  return (
                    <div className="details-row" key={item.ticketType}>
                      <div>
                        <b>{item.ticketType}:</b>
                        <i>${item.ticketPrice}</i>
                      </div>
                      <div className="amount-container">
                        <b>Amount:</b>
                        {item.ticketAmount}
                      </div>
                    </div>
                  )
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
        </Paper>
      </ContentContainer>

    </div>
  )
}
