// import Package
import React from 'react';
import './App.css';
import {
  Routes,
  Route,
  // Link,
  // useNavigate,
  // useLocation,
} from 'react-router-dom';
import { AppContainer } from './style'

// import Pages 
import DashBoard from './pages/host/DashBoard'
import EventAdd from './pages/host/EventAdd'
import Login from './pages/loginRegister/Login'
import Register from './pages/loginRegister/Register'
import ProfilePage from './pages/profile/ProfilePage'
import EventManagement from './pages/event_management/EventManagement';


// import Components
import AppBar from './components/AppBar'


function App() {
  return (
    <AppContainer>
      <AppBar />
      {/* <Link to="/register">register</Link>
      <Link to="/login">login</Link>
      <Link to="/dashboard">DashBoard</Link>
      <Link to="/profile">profile</Link> */}
      <Routes>
        <Route path="/register" element={<Register />} />
        <Route path="/login" element={<Login />} />
        <Route path="/dashboard" element={<DashBoard />} />
        <Route path="/profile" element={<ProfilePage />} />
        <Route path="/host/eventEdit" element={<EventManagement />} />
        <Route path="/host/eventAdd" element={<EventAdd />} />
        {/* <Route path="/quiz/question/edit" element={<QuizQuestion />} /> */}
      </Routes>
    </AppContainer>
  );
} 

export default App;
