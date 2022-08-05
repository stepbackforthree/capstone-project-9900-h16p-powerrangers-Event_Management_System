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
  const [balance, setBalance] = useState();


  const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleClose = () => {
    setAnchorEl(null);
  };

  const naviToProfile = () => {
    navigate('/profile/info');
  }

  const naviToEventList = () => {
    navigate('/host/eventList');
  }

  const naviToMyOrders = () => {
    navigate('/orders/myOrders');
  }

  useEffect(() => {
    request('/users/queryUser',{
      method: 'GET'
    }).then(data => {
      setBalance(data.balance);
      setAvatar(data.avatar);
      setUserName(data.userName);
    })
  }, []);


  const handleLogOut = () => {
    window.localStorage.clear();
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
          window.location.href = '/profile/accountRecharge';
        }}>
          <b>Balance:</b>
          <i>${balance}</i>
        </MenuItem>
        <MenuItem onClick={() => { 
          handleClose();
          naviToProfile()
        }}>
          Profile
        </MenuItem>
        <MenuItem onClick={() => { 
          handleClose();
          naviToEventList();
        }}>
          My Event List
        </MenuItem>
        <MenuItem onClick={() => { 
          handleClose();
          naviToMyOrders();
        }}>
          My Orders
        </MenuItem>
        <MenuItem onClick={() => { 
          handleClose();
          handleLogOut()
        }}>
          <b style={{"color": "red"}}>Logout</b>
        </MenuItem>
      </Menu>
    </div>
  );
}