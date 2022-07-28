import React, { useState, useEffect} from 'react';
// import Button from '@material-ui/core/Button';
import Menu from '@material-ui/core/Menu';
import MenuItem from '@material-ui/core/MenuItem';
import Avatar from '@material-ui/core/Avatar';
import {
  useNavigate
} from 'react-router-dom';
import request from '../../utils/request';

export default function SimpleMenu() {
  const navigate = useNavigate();

  const [anchorEl, setAnchorEl] = useState(null);
  const [avatar, setAvatar] = useState('');
  const [userName, setUserName] = useState('');


  const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleClose = () => {
    setAnchorEl(null);
  };

  const naviToProfile = () => {
    navigate('/profile');
  }

  const naviToEventList = () => {
    navigate('/host/eventList');
  }

  useEffect(() => {
    request('/users/queryUser',{
      method: 'GET'
    }).then(data => {
      setAvatar(data.avatar);
      setUserName(data.userName);
    })
  }, []);


  const handleLogOut = () => {
    console.log(window.localStorage.getItem('token'));
    window.localStorage.removeItem('token');
    window.localStorage.removeItem('userName');
    window.localStorage.removeItem('role');
    window.location.href = '/login';
  }

  return (
    <div>
      {/* <Button aria-controls="simple-menu" aria-haspopup="true" onClick={handleClick}>
        Open Menu
      </Button> */}
      <Avatar 
        alt="Remy Sharp" 
        src={avatar}
        aria-controls="simple-menu" 
        aria-haspopup="true" 
        onClick={handleClick} 
      />
      <Menu
        id="simple-menu"
        anchorEl={anchorEl}
        keepMounted
        open={Boolean(anchorEl)}
        onClose={handleClose}
      >
        <MenuItem>
          <b>User:</b>
          <i>{userName}</i>
        </MenuItem>
        <MenuItem onClick={() => { 
          handleClose();
          naviToProfile()
        }}>
          Profile
        </MenuItem>
        <MenuItem onClick={() => { 
          handleClose();
          naviToEventList()
        }}>
          {/* <Button onClick={naviToAccount}> */}
          My Event List
        </MenuItem>
        <MenuItem onClick={() => { 
          handleClose();
          handleLogOut()
        }}>
          Logout
        </MenuItem>
      </Menu>
    </div>
  );
}