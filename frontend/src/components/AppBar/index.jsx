import React from 'react';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
// import IconButton from '@material-ui/core/IconButton';
import Typography from '@material-ui/core/Typography';
// import InputBase from '@material-ui/core/InputBase';
import { alpha, makeStyles } from '@material-ui/core/styles';
// import MenuIcon from '@material-ui/icons/Menu';
// import SearchIcon from '@material-ui/icons/Search';
import {
  Link,
  useNavigate
} from 'react-router-dom';
import LeftBar from '../LeftBar';
import Button from '@material-ui/core/Button';
// import Avatar from '@material-ui/core/Avatar';
import Avatar from '../Avatar';
import PostAddIcon from '@material-ui/icons/PostAdd';


const useStyles = makeStyles((theme) => ({
  root: {
    flexGrow: 1,
  },
  menuButton: {
    marginRight: theme.spacing(2),
  },
  title: {
    flexGrow: 1,
    display: 'none',
    [theme.breakpoints.up('sm')]: {
      display: 'block',
    },
    fontWeight: 600,
  },
  navi: {
    fontWeight: 'bold', 
    textTransform: 'none',
    fontSize: 18,
  },
  search: {
    position: 'relative',
    borderRadius: theme.shape.borderRadius,
    backgroundColor: alpha(theme.palette.common.white, 0.15),
    '&:hover': {
      backgroundColor: alpha(theme.palette.common.white, 0.25),
    },
    marginLeft: 0,
    width: '100%',
    [theme.breakpoints.up('sm')]: {
      marginLeft: theme.spacing(1),
      width: 'auto',
    },
  },
  searchIcon: {
    padding: theme.spacing(0, 2),
    height: '100%',
    position: 'absolute',
    pointerEvents: 'none',
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
  },
  inputRoot: {
    color: 'inherit',
  },
  inputInput: {
    padding: theme.spacing(1, 1, 1, 0),
    // vertical padding + font size from searchIcon
    paddingLeft: `calc(1em + ${theme.spacing(4)}px)`,
    transition: theme.transitions.create('width'),
    width: '100%',
    [theme.breakpoints.up('sm')]: {
      width: '12ch',
      '&:focus': {
        width: '20ch',
      },
    },
  },
}));

export default function SearchAppBar() {
  const classes = useStyles();
  const navigate = useNavigate();


  const naviToDashboard = () => {
    navigate('/dashboard');
  }

  const naviToEventEdit = () => {
    navigate('/host/eventEdit');
  }

  const naviToEventAdd = () => {
    navigate('/host/eventAdd');
  }

  return (
    <div className={classes.root}>
      <AppBar position="static" color='inherit'>
        <Toolbar>
          {/* <IconButton
            edge="start"
            className={classes.menuButton}
            color="inherit"
            aria-label="open drawer"
          >
            <MenuIcon />
          </IconButton> */}
          <LeftBar />
          <Typography className={classes.title} variant="h6" noWrap >
            Event Management
          </Typography>
          
          {/* <Typography className={classes.title} variant="h6" noWrap>
            <Link to="/register">register</Link>
          </Typography>
          <Typography className={classes.title} variant="h6" noWrap>
            <Link to="/login">login</Link>
          </Typography> */}
          <Typography className={classes.title} variant="h6" noWrap >
            <Button size="large" onClick={naviToDashboard} className={classes.navi}>
              DashBoard
            </Button>
          </Typography>
          {/* <Typography className={classes.title} variant="h6" noWrap>
            <Link to="/profile">profile</Link>
          </Typography> */}
          <Typography className={classes.title} variant="h6" noWrap>
            <Button size="large" onClick={naviToEventEdit} className={classes.navi}>
              EventEdit
            </Button>
          </Typography>
          <Typography className={classes.title} variant="h6" noWrap>
            <Button 
              variant="contained" 
              color="primary" 
              onClick={naviToEventAdd}
              startIcon={<PostAddIcon />}
            >
              Event Add
            </Button>
          </Typography>
          
          {!localStorage.getItem('token') && 
          <>
            <Button variant="contained" color="primary" href="/register">
              register
            </Button>
            <Button variant="outlined" color="primary" href="/login">
              login
            </Button>
          </>
          }
          {/* <Button variant="contained" color="primary" href="/register">
            register
          </Button>
          <Button variant="outlined" color="primary" href="/login">
            login
          </Button> */}
          {localStorage.getItem('token') && 
          <Avatar />}
          {/* <div className={classes.search}>
            <div className={classes.searchIcon}>
              <SearchIcon />
            </div>
            <InputBase
              placeholder="Search event…"
              classes={{
                root: classes.inputRoot,
                input: classes.inputInput,
              }}
              inputProps={{ 'aria-label': 'search' }}
            />
          </div> */}
        </Toolbar>
      </AppBar>
    </div>
  );
}
