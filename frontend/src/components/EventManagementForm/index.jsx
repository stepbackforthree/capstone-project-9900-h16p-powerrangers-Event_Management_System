import React, { useEffect, useState } from 'react';
import Avatar from '@material-ui/core/Avatar';
import CssBaseline from '@material-ui/core/CssBaseline';
import TextField from '@material-ui/core/TextField';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import Checkbox from '@material-ui/core/Checkbox';
import Link from '@material-ui/core/Link';
import Grid from '@material-ui/core/Grid';
import Box from '@material-ui/core/Box';
import LockOutlinedIcon from '@material-ui/icons/LockOutlined';
import Typography from '@material-ui/core/Typography';
import { makeStyles } from '@material-ui/core/styles';
import Container from '@material-ui/core/Container';
import { useForm, Form } from '../useForm';
import EditIcon from '@material-ui/icons/Edit';
import Paper from '@material-ui/core/Paper';
import EditLocationIcon from '@material-ui/icons/EditLocation';
import './styles.css';
import moment from 'moment';
import { Button, Modal, Select,Image } from 'antd';
import request from '../../utils/request';


const { Option } = Select;

function Copyright() {
  return (
    <Typography variant="body2" color="textSecondary" align="center">
      {'Copyright © '}
      <Link color="inherit" href="#">
        9900-H16P-PowerRangers
      </Link>{' '}
      {new Date().getFullYear()}
      {'.'}
    </Typography>
  );
}



const useStyles = makeStyles((theme) => ({
  paper: {
    marginTop: theme.spacing(8),
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
  },
  avatar: {
    margin: theme.spacing(1),
    // backgroundColor: theme.palette.secondary.main,
    backgroundColor: theme.palette.primary.light,
  },
  submit: {
    margin: theme.spacing(1, 0, 2),
  },
}));

const ifDisplay = [
  {
    value: true,
    label: 'online',
  },
  {
    value: false,
    label: 'offline',
  },
];


const eventType = [
  {
    value: 1,
    label: 'concert',
  },
  {
    value: 2,
    label: 'sports',
  },
  {
    value: 3,
    label: 'Comic and Animation',
  },
  {
    value: 4,
    label: 'parents-child campaign',
  },
  {
    value: 5,
    label: 'Tourism exhibition',
  },
];

const location = [
  {
    value: 'Sydney',
    label: 'Sydney',
  },
  {
    value: 'Melbourne',
    label: 'Melbourne',
  },
  {
    value: 'Queensland',
    label: 'Queensland',
  },
];

const eventTypeMap = {
  1: 'Concert',
  2: 'Sports',
  3: 'Comic and Animation',
  4: 'Parents-child Campaign',
  5: 'Tourism Exhibition'
}


export default function EventManagementForm(props) {
  const [isModal1Visible, setIsModal1Visible] = useState(false);
  const [isModal2Visible, setIsModal2Visible] = useState(false);
  const [editUrl, setEditUrl] = useState('');
  const [inputType, setInputType] = useState('text');
  const [inputKey, setInputKey] = useState('');
  const [inputValue, setInputValue] = useState('');
  const [locationValue, setLocationValue] = useState('');

  const eventName = window.localStorage.getItem('eventName');


  const showModal1 = () => {
    setIsModal1Visible(true);
  };
  const handleOk1 = () => {
    console.log('submit.....');
    console.log(editUrl);
    console.log(inputValue);
    console.log('/'+editUrl);
    console.log(inputKey);
    const data = {
      'eventName': eventName,
      [inputKey]: inputValue
    }
    console.log('data:', data);
    request('/events/'+editUrl, {
      method: 'POST',
      data: data
    }).then(data => {
      console.log('data:', data);
    })
    setIsModal1Visible(false);
    window.location.href = '/host/eventList';
  };
  const handleCancel1 = () => {
    setIsModal1Visible(false);
  };


  const showModal2 = () => {
    setIsModal2Visible(true);
  };
  const handleOk2 = () => {
    console.log('submit.....');
    console.log(editUrl);
    console.log(locationValue);
    console.log('/'+editUrl);
    const data = {
      'eventName': eventName,
      'newString': locationValue
    }
    console.log('data:', data);
    request('/events/'+editUrl, {
      method: 'POST',
      data: data
    }).then(data => {
      console.log('data:', data);
    })
    setIsModal2Visible(false);
    window.location.href = '/host/eventList';
  };
  const handleCancel2 = () => {
    setIsModal2Visible(false);
  };

  useEffect(() => {
    console.log('editUrl:',editUrl)
  },[editUrl])

  useEffect(() => {
    console.log('inputType:',inputType)
  },[inputType])

  useEffect(() => {
    console.log('inputValue:',inputValue)
  },[inputValue])

  const classes = useStyles();
  // const { values, setValues, handleInputChange } = useForm(event);

  const event = props.event;
  const handleSubmit = () => {
    console.log('submit success');
    // props.submit({
    //   ...values,
    // });
  }

  // upload img
  const uploadIamge = async (e) => {
    const file = e.target.files[0];
    const base64 = await convertBase64(file);
    // setValues({
    //   ...values,
    //   image: base64,
    // })
  }

  const convertBase64 = (file) => {
    return new Promise((resolve, reject) => {
      const fileReader = new FileReader();
      fileReader.readAsDataURL(file);

      fileReader.onload = () => {
        resolve(fileReader.result);
      };

      fileReader.onerror = (error) => {
        reject(error);
      }
    })
  }

  const handleInputChange = (e) => {
    console.log('input change:', e.target.value);
    setInputValue(e.target.value);
  }

  const handleLocationChange = (value) => {
    setLocationValue(value);
    console.log(`selected ${value}`);
  };


  return (
    <>
      <Modal title="Text Input" visible={isModal1Visible} onOk={handleOk1} onCancel={handleCancel1}>
        <b>{editUrl}:</b>
        <input type={inputType} value={inputValue} onChange={handleInputChange}/>
      </Modal>
      <Modal title="Selection" visible={isModal2Visible} onOk={handleOk2} onCancel={handleCancel2}>
        <b>{editUrl}:</b>
        <Select
          value={locationValue}
          style={{
            width: 150,
          }}
          onChange={handleLocationChange}
        >
          <Option value="Sydney">Sydney</Option>
          <Option value="Melbourne">Melbourne</Option>
          <Option value="Queensland ">Queensland </Option>
        </Select>
      </Modal>


      <Container component="main" maxWidth="xs">
        <CssBaseline />
        <div className={classes.paper}>
          <Avatar className={classes.avatar}>
            <EditIcon />
          </Avatar>
          <Typography component="h1" variant="h5">
            Event Edit Management
          </Typography>


          <Paper elevation={3} className="details">
            <div className="details-row">
              <div>
                <b>Event Name:</b>
                {event.eventName}
              </div>
              <div>
                <EditLocationIcon/>{event.location}
              </div>
            </div>

            <div className="details-row">
              <Button type="link" onClick={() => {
                showModal1();
                setEditUrl('updateEventName');
                setInputType('text');
                setInputKey('newString');
                setInputValue(event.eventName);
              }}>
                Edit Event Name
              </Button>
              <Button type="link" onClick={() => {
                showModal2();
                setEditUrl('updateEventAddress');
                setLocationValue(event.location);
              }}>
                Edit Location
              </Button>
            </div>
            <div className="details-row">
              <div>
                <div>
                  <b>Start Time:</b>
                </div>
                <div>
                  {moment(event.startTime).format("YYYY-MM-DD HH:mm:ss")}
                </div>
              </div>
              <i style={{"padding": "0 20px"}}>To</i>
              <div>
                <div>
                  <b>End Time:</b>
                </div>
                <div>
                  {moment(event.endTime).format("YYYY-MM-DD HH:mm:ss")}
                </div>
              </div>
            </div>
            <div className="details-one-center">
              <b>Image:</b>
              <Image
                width={200}
                src={event.image}
              />
            </div>
            <div className="details-one">
              <b>Event Type:</b>
              {eventTypeMap[event.eventType]}
            </div>
            <div className="details-row">
              <div>
                <b>Event Description:</b>
                {event.description}
              </div>
              <Button type="link" onClick={() => {
                showModal1();
                setEditUrl('updateEventDescription');
                setInputType('text');
                setInputKey('newString');
                setInputValue(event.description);
              }}>
                Edit Description
              </Button>
            </div>
            <div className="details-one">
              <b>If Display this Event:</b>
              {event.isDisplayed}
            </div>
            <div className="details-one">
              <b>Event Cancelled:</b>
              {event.isCancelled}
            </div>
            <div className="details-one">
              <h3><b>Tickets Detail:</b></h3>
            </div>

            {event.tickets && event.tickets.map((item) => {
              return (
                <div className="details-row" key={item.ticketType}>
                  <div>{item.ticketType}:<i>${item.ticketPrice}</i></div>
                  <div><b>Amount:</b>{item.ticketAmount}</div>
                </div>
              )
            })}

            

            
          </Paper>
        </div>
        <Box mt={8}>
          <Copyright />
        </Box>
      </Container>
    </>
  )
}