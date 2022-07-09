import React from 'react';
// import Button from '@material-ui/core/Button';
import Menu from '@material-ui/core/Menu';
import MenuItem from '@material-ui/core/MenuItem';
import Avatar from '@material-ui/core/Avatar';
import {
  useNavigate
} from 'react-router-dom';

export default function SimpleMenu() {
  const navigate = useNavigate();

  const [anchorEl, setAnchorEl] = React.useState(null);

  const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleClose = () => {
    setAnchorEl(null);
  };

  const naviToProfile = () => {
    navigate('/profile');
  }

  const handleLogOut = () => {
    console.log(window.localStorage.getItem('token'));
    window.localStorage.removeItem('token');
    window.localStorage.removeItem('userName');
    window.location.href = '/login';
  }

  return (
    <div>
      {/* <Button aria-controls="simple-menu" aria-haspopup="true" onClick={handleClick}>
        Open Menu
      </Button> */}
      <Avatar 
        alt="Remy Sharp" 
        src="/img/userAvatar.jpg" 
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
        <MenuItem onClick={() => { 
          handleClose();
          naviToProfile()
        }}>
          Profile
        </MenuItem>
        <MenuItem onClick={() => { 
          handleClose();
          naviToProfile()
        }}>
          {/* <Button onClick={naviToAccount}> */}
          My account
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