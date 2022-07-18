import React from 'react';
import './styles.css';
import PropTypes from 'prop-types';
import Tabs from '@material-ui/core/Tabs';
import Tab from '@material-ui/core/Tab';
import Typography from '@material-ui/core/Typography';
import Box from '@material-ui/core/Box';
import { makeStyles } from '@material-ui/core/styles';
import MyDetails from './MyDetails';
import BankDetails from './BankDetails';
import ResetPassword from './ResetPassword';


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
    width: '100%',
  },
  tabs: {
    borderRight: `1px solid ${theme.palette.divider}`,
  },
  panel: {
    flexGrow: 1,
  }
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
            <Tab label="Bank Detail" {...a11yProps(1)} />
            <Tab label="Password" {...a11yProps(2)} />
            <Tab label="Notification" {...a11yProps(3)} />
          </Tabs>
          <TabPanel value={value} index={0} className={classes.panel}>
            <h1>
              My details
            </h1>
            <MyDetails/>
          </TabPanel>
          <TabPanel value={value} index={1} className={classes.panel}>
            <h1>
              Bank Detail
            </h1>
            <BankDetails/>
          </TabPanel>
          <TabPanel value={value} index={2} className={classes.panel}>
            <h1>
              Password
            </h1>
            <ResetPassword/>
          </TabPanel>
          <TabPanel value={value} index={3} className={classes.panel}>
            Notification
          </TabPanel>
        </div>
      </div>
    </div>
  )
}
