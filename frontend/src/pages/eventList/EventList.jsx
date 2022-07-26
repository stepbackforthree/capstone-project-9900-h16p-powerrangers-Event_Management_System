import React, { useEffect, useState } from 'react';
import request from '../../utils/request';
import { LikeOutlined, MessageOutlined, StarOutlined } from '@ant-design/icons';
import styled from 'styled-components';
import { Avatar, List, Space } from 'antd';

const TitleContainer = styled.div`
  position: relative;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 10px;
`;

const Container = styled.div`
  position: relative;
  max-width: 1200px;
  padding: 10px 20px;
  margin: 0 auto;
  /* background-color: #a1be95; */
`;

const IconText = ({ icon, text }) => (
  <Space>
    {React.createElement(icon)}
    {text}
  </Space>
);

const data = Array.from({
  length: 23,
}).map((_, i) => ({
  href: 'https://ant.design',
  title: `ant design part ${i}`,
  avatar: 'https://joeschmoe.io/api/v1/random',
  description:
    'Ant Design, a design language for background applications, is refined by Ant UED Team.',
  content:
    'We supply a series of design principles, practical patterns and high quality design resources (Sketch and Axure), to help people create their product prototypes beautifully and efficiently.',
}));

export default function EventList() {
  const [eventList, setEventList] = useState('');

  useEffect(() => {
    request(`/events/getEvents?userName=${window.localStorage.getItem('userName')}`,{
      method: 'GET',
    }).then((response) => {
      // console.log(response);
      setEventList(response);
    })
  }, []);


  return (
    <div>
      <TitleContainer>
        <h2>eventList</h2>
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
          dataSource={data}
          footer={
            <div>
              <b>ant design</b> footer part
            </div>
          }
          renderItem={(item) => (
            <List.Item
              key={item.title}
              actions={[
                <IconText icon={StarOutlined} text="156" key="list-vertical-star-o" />,
                <IconText icon={LikeOutlined} text="156" key="list-vertical-like-o" />,
                <IconText icon={MessageOutlined} text="2" key="list-vertical-message" />,
              ]}
              extra={
                <img
                  width={272}
                  alt="logo"
                  src="https://gw.alipayobjects.com/zos/rmsportal/mqaQswcyDLcXyDKnZfES.png"
                />
              }
            >
              <List.Item.Meta
                // avatar={<Avatar src={item.avatar} />}
                title={<a href={item.href}>{item.title}</a>}
                description={item.description}
              />
              {item.content}
            </List.Item>
          )}
        />
      </Container>
    </div>
  )
}
