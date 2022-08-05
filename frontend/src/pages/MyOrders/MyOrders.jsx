import React from 'react';
import styled from 'styled-components';
import Orders from '../../components/ProfileForm/Orders';


const TitleContainer = styled.div`
  position: relative;
  display: flex;
  align-items: center;
  justify-content: space-between;
  border-bottom: 1px solid rgba(0,0,0,.26);
  padding: 1.5rem 1rem;
`;

const Container = styled.div`
  position: relative;
  max-width: 1200px;
  padding: 10px 20px;
  margin: 0 auto;
`;


export default function EventList() {

  return (
    <div>
      <TitleContainer>
        <h2>My Orders</h2>
      </TitleContainer>
      <Container>
        <Orders/>
      </Container>
    </div>
  )
}
