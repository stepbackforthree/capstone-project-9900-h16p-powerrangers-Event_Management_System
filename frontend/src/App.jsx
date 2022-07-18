// import Package
import React, { useEffect } from 'react';
import './App.css';
import {
  Routes,
  Route,
  // Link,
  useNavigate,
  useLocation,
} from 'react-router-dom';
import { AppContainer } from './style'

// import Pages ;
import DashBoard from './pages/host/DashBoard';
import EventAdd from './pages/host/EventAdd';
import Login from './pages/loginRegister/Login';
import Register from './pages/loginRegister/Register';
import ForgotPassword from './pages/loginRegister/ForgotPassword';
import ProfilePage from './pages/profile/ProfilePage'
import EventManagement from './pages/event_management/EventManagement';
import message from './utils/message';


// import Components
import AppBar from './components/AppBar';
// import { createTheme, ThemeProvider } from '@material-ui/core/styles';

// const THEME = createTheme({
//    typography: {
//     "fontFamily": `"Roboto", "Helvetica", "Arial", sans-serif`,
//     "fontSize": 14,
//     "fontWeightLight": 300,
//     "fontWeightRegular": 400,
//     "fontWeightMedium": 500
//    }
// });

function App() {
  const NotLoggedInPath = ['/login', '/forgotPassword', '/register','/dashboard'];
  const navigate = useNavigate();
  const location = useLocation();
  useEffect(() => {
    const token = localStorage.token;
    if (!token && !NotLoggedInPath.includes(location.pathname)) {
      message.warning({content: ('You are not logged in, please log in and try again'),  duration: 2500});
      navigate('/login');
    }
  }, []);

  return (
    // <ThemeProvider theme={THEME}>
      <AppContainer >
        <AppBar />
        {/* <Link to="/register">register</Link>
        <Link to="/login">login</Link>
        <Link to="/dashboard">DashBoard</Link>
        <Link to="/profile">profile</Link> */}
        <Routes>
          <Route path="/register" element={<Register />} />
          <Route path="/login" element={<Login />} />
          <Route path="/forgotPassword" element={<ForgotPassword />} />
          <Route path="/dashboard" element={<DashBoard />} />
          <Route path="/profile" element={<ProfilePage />} />
          <Route path="/host/eventEdit" element={<EventManagement />} />
          <Route path="/host/eventAdd" element={<EventAdd />} />
          {/* <Route path="/quiz/question/edit" element={<QuizQuestion />} /> */}
        </Routes>
      </AppContainer>
    // </ThemeProvider>  
  );
} 

export default App;
