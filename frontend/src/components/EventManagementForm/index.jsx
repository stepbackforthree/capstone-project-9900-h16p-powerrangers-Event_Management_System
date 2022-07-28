import React, { useEffect } from 'react';
import Avatar from '@material-ui/core/Avatar';
import Button from '@material-ui/core/Button';
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
import { Image } from 'antd';
import moment from 'moment';


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


export default function EventManagementForm(props) {

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


  return (
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
            <b>Event Description:</b>
              {event.description}
          </div>
          
        </Paper>
      </div>
      <Box mt={8}>
        <Copyright />
      </Box>
    </Container>
  )
}