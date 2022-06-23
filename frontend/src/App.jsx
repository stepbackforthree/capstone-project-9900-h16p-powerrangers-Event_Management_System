import React from 'react';
import './App.css';
// import {
//   Routes,
//   Route,
//   Link,
//   useNavigate,
//   useLocation,
// } from 'react-router-dom';
import { AppContainer } from './style'
import Dashboard from './pages/host/DashBoard'
import MailIcon from '@material-ui/icons/Mail';
import Button from '@material-ui/core/Button';


function App() {
  return (
    <AppContainer>
      <Dashboard/>
      events
      <MailIcon />
      <Button variant="contained" color="primary">hello</Button>
    </AppContainer>
  );
} 

export default App;
