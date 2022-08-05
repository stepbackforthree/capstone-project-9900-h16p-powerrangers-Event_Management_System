import React from 'react';
import clsx from 'clsx';
import { makeStyles } from '@material-ui/core/styles';
import Drawer from '@material-ui/core/Drawer';
// import Button from '@material-ui/core/Button';
import List from '@material-ui/core/List';
import Divider from '@material-ui/core/Divider';
import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
import InboxIcon from '@material-ui/icons/MoveToInbox';
import MailIcon from '@material-ui/icons/Mail';
// import MenuIcon from '@material-ui/icons/Menu';
import IconButton from '@material-ui/core/IconButton';
import Logo from '../../img/Logo.png';
import { HomeOutlined, UnorderedListOutlined, OrderedListOutlined, WalletOutlined, ProfileOutlined } from '@ant-design/icons';


const useStyles = makeStyles({
  list: {
    width: 200,
  },
  fullList: {
    width: 'auto',
  },
});

const LogoText = {
  height: '60px',
};

export default function TemporaryDrawer() {
  const classes = useStyles();
  const [state, setState] = React.useState({
    top: false,
    left: false,
    bottom: false,
    right: false
  });

  const toggleDrawer = (anchor, open) => (event) => {
    if (event.type === 'keydown' && (event.key === 'Tab' || event.key === 'Shift')) {
      return;
    }
    setState({ ...state, [anchor]: open });
  };

  const eventRelative = [
    {
      'icon': <HomeOutlined />,
      'name': 'Dashboard',
      'link': '/dashboard'
    },
    {
      'icon': <UnorderedListOutlined />,
      'name': 'My Orders',
      'link': '/orders/myOrders'
    },
    {
      'icon': <OrderedListOutlined />,
      'name': 'My Event List',
      'link': '/host/eventList'
    }
  ]

  const userRelative = [
    {
      'icon': <WalletOutlined />,
      'name': 'My Wallet',
      'link': '/profile/accountRecharge'
    },
    {
      'icon': <ProfileOutlined />,
      'name': 'My Profile',
      'link': '/profile/info'
    }
  ]

  const list = (anchor) => (
    <div
      className={clsx(classes.list, {
        [classes.fullList]: anchor === 'top' || anchor === 'bottom',
      })}
      role="presentation"
      onClick={toggleDrawer(anchor, false)}
      onKeyDown={toggleDrawer(anchor, false)}
    >
      <List>
        {eventRelative.map((item) => (
          <ListItem button key={item.name}>
            <ListItemIcon>
              {item.icon}
            </ListItemIcon>
            <ListItemText primary={item.name} />
          </ListItem>
        ))}
      </List>
      <Divider />
      <List>
        {userRelative.map((item) => (
          <ListItem button key={item.name}>
            <ListItemIcon>
              {item.icon}
            </ListItemIcon>
            <ListItemText primary={item.name} />
          </ListItem>
        ))}
      </List>
    </div>
  );

  return (
    <div>
      <React.Fragment key={'left'}>
        <IconButton
          // edge="start"
          // className={classes.menuButton}
          color="inherit"
          aria-label="open drawer"
          onClick={toggleDrawer('left', true)}
        >
          {/* <MenuIcon /> */}
          <img style={LogoText} src={Logo} alt="PPLogo"></img>
        </IconButton>
        {/* <Button onClick={toggleDrawer('left', true)}></Button> */}
        <Drawer anchor={'left'} open={state['left']} onClose={toggleDrawer('left', false)}>
          {list('left')}
        </Drawer>
      </React.Fragment>
    </div>
  );
}
