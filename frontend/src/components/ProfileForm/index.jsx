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
import Orders from './Orders';
import styled from 'styled-components';


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
          {children}
        </Box>
      )}
    </div>
  );
}

const TitleContainer = styled.div`
  position: relative;
  display: flex;
  align-items: center;
  justify-content: space-between;
  /* padding: 10px; */
  border-bottom: 1px solid rgba(0,0,0,.26);
  padding: 1.5rem 1rem;
`;

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
    borderRight: `2px solid ${theme.palette.divider}`,
  },
  panel: {
    flexGrow: 1,
  }
}));


export default function ProfileForm(props) {
  const userDetail = props.userDetail;
  const classes = useStyles();
  const [value, setValue] = React.useState(0);

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  return (
    <div className='profile-page'>
      <TitleContainer>
        <h2>Profile</h2>
      </TitleContainer>
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
            <Tab label="Orders" {...a11yProps(3)} />
          </Tabs>
          <TabPanel value={value} index={0} className={classes.panel}>
            <MyDetails/>
          </TabPanel>
          <TabPanel value={value} index={1} className={classes.panel}>
            <BankDetails/>
          </TabPanel>
          <TabPanel value={value} index={2} className={classes.panel}>
            <ResetPassword/>
          </TabPanel>
          <TabPanel value={value} index={3} className={classes.panel}>
            <Orders/>
          </TabPanel>
        </div>
      </div>
    </div>
  )
}
