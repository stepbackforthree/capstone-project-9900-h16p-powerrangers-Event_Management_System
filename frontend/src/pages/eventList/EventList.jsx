import React, { useEffect, useState } from 'react';
import request from '../../utils/request';
import { LikeOutlined, MessageOutlined, StarOutlined } from '@ant-design/icons';
import styled from 'styled-components';
import { Button, List, Space, Modal } from 'antd';
import LocationCityIcon from '@material-ui/icons/LocationCity';import ScheduleIcon from '@material-ui/icons/Schedule';
import AttachMoneyIcon from '@material-ui/icons/AttachMoney';
import StarBorderRoundedIcon from '@material-ui/icons/StarBorderRounded';
import EventAvailableRoundedIcon from '@material-ui/icons/EventAvailableRounded';
import { useNavigate } from 'react-router-dom';


const TitleContainer = styled.div`
  position: relative;
  display: flex;
  align-items: center;
  justify-content: space-between;
  /* padding: 10px; */
  border-bottom: 1px solid rgba(0,0,0,.26);
  padding: 1.5rem 1rem;
`;

const Container = styled.div`
  position: relative;
  max-width: 1200px;
  padding: 10px 20px;
  margin: 0 auto;
  /* background-color: #a1be95; */
`;

const InfoContainer = styled.div`
  display: flex;
  flex-direction: column;
  /* align-items: center; */
  justify-content: center;
  /* position: relative;
  max-width: 1200px; */
  padding: 0px 20px;
  /* margin: 0 auto; */
`;

const IconText = ({ icon, text }) => (
  <Space>
    {React.createElement(icon)}
    {text}
  </Space>
);

const eventType = {
  1: 'Concert',
  2: 'Sports',
  3: 'Comic and Animation',
  4: 'Parents-child Campaign',
  5: 'Tourism Exhibition'
}

const data = Array.from({
  length: 15,
}).map((_, i) => ({
  eventName: `Event part ${i+1}`,
  eventType: 3,
  location: 'Sydney',
  description:
    'Ant Design, a design language for background applications, is refined by Ant UED Team.',
  startTime: '2022-07-07T10:00:00',
  endTime: '2022-07-08T10:00:00',
  isDisplayed: true,
  starLevel: 3,
  image: "https://gw.alipayobjects.com/zos/rmsportal/mqaQswcyDLcXyDKnZfES.png",
  isCancelled: false,
  ticketPrice: 100,
  ticketAmount: 500
}));


export default function EventList() {
  const navigate = useNavigate();
  const [eventList, setEventList] = useState('');

  useEffect(() => {
    request(`/events/getEvents?userName=${window.localStorage.getItem('userName')}`,{
      method: 'GET',
    }).then((response) => {
      // console.log(response);
      setEventList(response);
    })
  }, []);

  

  const showTime = (string) => {
    const time = string.split('.')[0];
    const timeStr = time.split('T')[0] + ' ' + time.split('T')[1];
    return timeStr;
  }

  const goToEdit = (eventName) => {
    window.localStorage.setItem('eventName', eventName);
    navigate('/host/eventEdit');
  }

  return (
    <div>
      <TitleContainer>
        <h2>Your Event List</h2>
      </TitleContainer>
      <Container>
        <List
          // itemLayout="vertical"
          size="large"
          pagination={{
            onChange: (page) => {
              console.log('page: ', page);
            },
            pageSize: 5,
          }}
          dataSource={eventList}
          footer={
            <div>
              <b>9900-H16P-PowerRangers</b>
            </div>
          }
          renderItem={(item) => (
            <List.Item
              key={item.eventName}
              actions={[
                <IconText icon={StarOutlined} text="156" key="list-vertical-star-o" />,
                <IconText icon={LikeOutlined} text="112" key="list-vertical-like-o" />,
                <IconText icon={MessageOutlined} text="3" key="list-vertical-message" />,
              ]}
              extra={
                <img
                  width={200}
                  alt="logo"
                  src={item.image}
                />
              }
            >
              <List.Item.Meta
                // avatar={<Avatar src={item.avatar} />}
                title={
                  <>
                    <b style={{fontSize: '25px'}}>{item.eventName}</b>
                    <Button type="link" onClick={()=>goToEdit(item.eventName)}>Edit</Button>
                  </>
                }
                description={item.description}
              />
              <InfoContainer>
                <p><EventAvailableRoundedIcon/><b>Event Type: </b>{eventType[item.eventType]}</p>
                <p><LocationCityIcon/><b>Location: </b>{item.location}</p>
                <p><StarBorderRoundedIcon/><b>Star Level: </b>{item.starLevel}</p>
                <p><ScheduleIcon/><b>Start Time: </b>{showTime(item.startTime)}</p>
                <p><AttachMoneyIcon/><b>Ticket Price: </b>{item.ticketPrice}</p>
              </InfoContainer>
            </List.Item>
          )}
        />
      </Container>
    </div>
  )
}
