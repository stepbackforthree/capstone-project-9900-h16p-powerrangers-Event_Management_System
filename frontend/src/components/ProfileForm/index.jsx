import React from 'react';
import './styles.css';
import PropTypes from 'prop-types';
import Tabs from '@material-ui/core/Tabs';
import Tab from '@material-ui/core/Tab';
import Typography from '@material-ui/core/Typography';
import Box from '@material-ui/core/Box';
import { makeStyles } from '@material-ui/core/styles';


function TabPanel(props) {
  const { children, value, index, ...other } = props;

  return (
    <div
      role="tabpanel"
      hidden={value !== index}
      id={`vertical-tabpanel-${index}`}
      aria-labelledby={`vertical-tab-${index}`}
      {...other}
    >
      {value === index && (
        <Box p={5}>
          <Typography>{children}</Typography>
        </Box>
      )}
    </div>
  );
}

TabPanel.propTypes = {
  children: PropTypes.node,
  index: PropTypes.any.isRequired,
  value: PropTypes.any.isRequired,
};

function a11yProps(index) {
  return {
    id: `vertical-tab-${index}`,
    'aria-controls': `vertical-tabpanel-${index}`,
  };
}

const useStyles = makeStyles((theme) => ({
  root: {
    flexGrow: 1,
    backgroundColor: theme.palette.background.paper,
    display: 'flex',
    height: '80vh',
  },
  tabs: {
    borderRight: `1px solid ${theme.palette.divider}`,
  },
}));


export default function ProfileForm() {
  const classes = useStyles();
  const [value, setValue] = React.useState(0);

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  return (
    <div className='profile-page'>
      <div className="title">
        <h1>Profile</h1>
      </div>
      <div className="profile-form">
        <div className={classes.root}>
          <Tabs
            orientation="vertical"
            variant="scrollable"
            value={value}
            onChange={handleChange}
            aria-label="Vertical tabs example"
            className={classes.tabs}
          >
            <Tab label="My details" {...a11yProps(0)} />
            <Tab label="Profile" {...a11yProps(1)} />
            <Tab label="Password" {...a11yProps(2)} />
            <Tab label="Email" {...a11yProps(3)} />
            <Tab label="Notification" {...a11yProps(4)} />
          </Tabs>
          <TabPanel value={value} index={0}>
            My details
          </TabPanel>
          <TabPanel value={value} index={1}>
            Profile
          </TabPanel>
          <TabPanel value={value} index={2}>
            Password
          </TabPanel>
          <TabPanel value={value} index={3}>
            Email
          </TabPanel>
          <TabPanel value={value} index={4}>
            Notification
          </TabPanel>
        </div>
      </div>
    </div>
  )
}
