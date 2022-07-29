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

const data = {
  eventName: `Event part 2`,
  eventType: 3,
  location: 'Sydney',
  description:
    'Ant Design, a design language for background applications, is refined by Ant UED Team.',
  startTime: 1659177000000,
  endTime: 1660818600000,
  isDisplayed: true,
  starLevel: 3,
  image: "https://gw.alipayobjects.com/zos/rmsportal/mqaQswcyDLcXyDKnZfES.png",
  isCancelled: false,
  tickets: [
		{
			"ticketAmount": 200,
			"ticketPrice": 100.00,
			"ticketType": "full price ticket"
		},
		{
			"ticketAmount": 100,
			"ticketPrice": 30.00,
			"ticketType": "student ticket"
		},
		{
			"ticketAmount": 100,
			"ticketPrice": 20.00,
			"ticketType": "infield ticket"
		}
	],
}

export default function OrderPage() {
  const [ticketChoise, SetTicketChoise] = useState('fullPriceTicket');

  const ticketChoiseChange = (e) => {
    SetTicketChoise(e.target.value);
  };

  useEffect(() => {
    console.log('ticketChoise:',ticketChoise)
  }, [ticketChoise]);

  // const [data, setData] = useState('');

  // useEffect(() => {
  //   request(`/events/queryEvent`,{
  //     method: 'POST',
  //     data: {
  //       'eventName': window.localStorage.getItem('eventName'),
  //       'hostName': window.localStorage.getItem('userName')
  //     }
  //   }).then((response) => {
  //     // console.log(response);
  //     setData(response);
  //   })
  // }, []);

  return (
    <div>
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
                <div>
                  <b>Ticket Amount: </b>
                  {data.ticketAmount}
                </div>
              </div>
              <div  className="icon-text-container">
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
                  <Radio.Button value="fullPriceTicket">FullPriceTicket</Radio.Button>
                  <Radio.Button value="studentTicket">StudentTicket</Radio.Button>
                  <Radio.Button value="infieldTicket">InfieldTicket</Radio.Button>
                  <Radio.Button value="standTicket">StandTicket</Radio.Button>
                  <Radio.Button value="earlyBirdTicket">EarlyBirdTicket</Radio.Button>
                </Radio.Group>
                <div className='pay-button'>
                  <Button 
                    type="primary"
                    shape="round"
                    size="large"
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
