import React from 'react';
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
  // const event = {
    // "eventName": "Kobe fan Meeting",
    // "eventType": "sports",
    // "description": "Fan meeting, recall the past time",
    // "siteName": "Sydney",
    // "siteDescription": "2 Macquarie Street, Sydney CBD NSW 2000",
    // "startTime": "2022-07-30T17:30:00",
    // "endTime": "2022-07-31T17:50:00",
    // "isDisplayed": true,
    // "starLevel": "4",
    // "eventTag": "sports"
  // }
  const classes = useStyles();

  const handleSubmit = () => {
    console.log('submit success');
    props.submit({
      ...values,
    });
  }

  // upload img
  const uploadIamge = async (e) => {
    const file = e.target.files[0];
    const base64 = await convertBase64(file);
    setValues({
      ...values,
      image: base64,
    })
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

  const { values, setValues, handleInputChange } = useForm(props.event);
  return (
    <Container component="main" maxWidth="xs">
      <CssBaseline />
      <div className={classes.paper}>
        <Avatar className={classes.avatar}>
          <EditIcon />
        </Avatar>
        <Typography component="h1" variant="h5">
          Event Management
        </Typography>
        <Form>
          <h2>Event Name:</h2>
          <TextField
            variant="outlined"
            margin="normal"
            required
            fullWidth
            id="Event Name"
            label="Event Name"
            name="eventName"
            // autoComplete="userName"
            autoFocus
            value = {values.eventName}
            onChange = {handleInputChange}
          />
          <div>
          <h2>Event Type:</h2>
            <TextField
              id="eventType"
              name="eventType"
              select
              // label="If Display"
              value={values.eventType}
              onChange={handleInputChange}
              SelectProps={{
                native: true,
              }}
              // helperText="If you want to show the event"
              variant="outlined"
            >
              {eventType.map((option) => (
                <option key={option.value} value={option.value}>
                  {option.label}
                </option>
              ))}
            </TextField>
          </div>
          <div>
            <h3>Location:</h3>
            <TextField
              id="location"
              name="location"
              select
              // label="If Display"
              value={values.location}
              onChange={handleInputChange}
              SelectProps={{
                native: true,
              }}
              // helperText="If you want to show the event"
              variant="outlined"
            >
              {location.map((option) => (
                <option key={option.value} value={option.value}>
                  {option.label}
                </option>
              ))}
            </TextField>
          </div>
          <div>
            <h3>Site Description:</h3>
            <TextField
              variant="outlined"
              margin="normal"
              required
              fullWidth
              id="siteDescription"
              label="Site Description"
              name="siteDescription"
              autoFocus
              value = {values.siteDescription}
              onChange = {handleInputChange}
            />
          </div>
          <div>
            <h2>Number of tickets:</h2>
            <TextField
            variant="outlined"
            margin="normal"
            required
            fullWidth
            id="numberOfTickets"
            label="Number of tickets(Only numbers are allowed)"
            name="numberOfTickets"
            autoComplete="numberOfTickets"
            autoFocus
            value = {values.numberOfTickets}
            onChange = {handleInputChange}
          />
          </div>
          <div>
            <h3>Start Time:</h3>
            <TextField
              id="startTime"
              name="startTime"
              label="Start Time"
              type="datetime-local"
              value={values.startTime}
              // className={classes.textField}
              InputLabelProps={{
                shrink: true,
              }}
              onChange = {handleInputChange}
            />
          </div>
          <div>
            <h3>End Time:</h3>
            <TextField
              id="endTime"
              name="endTime"
              label="End Time"
              type="datetime-local"
              value={values.endTime}
              InputLabelProps={{
                shrink: true,
              }}
              onChange = {handleInputChange}
            />
          </div>
          <div>
            <h4>Event If Display:</h4>
            <TextField
              id="ifDisplay"
              name="ifDisplay"
              select
              // label="If Display"
              value={values.ifDisplay}
              onChange={handleInputChange}
              SelectProps={{
                native: true,
              }}
              helperText="If you want to show the event"
              variant="outlined"
            >
              {ifDisplay.map((option) => (
                <option key={option.value} value={option.value}>
                  {option.label}
                </option>
              ))}
            </TextField>
          </div>
          <div>
            <h4>Title Image:</h4>
            <input type="file" onChange={(e) => uploadIamge(e)} />
            <img alt="TitleImage" src={values.image} height="200px"/>
          </div>
          <div>
            <h4>Event Description:</h4>
            {/* <TextField
              variant="outlined"
              margin="normal"
              required
              fullWidth
              name="password"
              label="Password"
              type="password"
              id="password"
              autoComplete="current-password"
              value = {values.password}
              onChange = {handleInputChange}
            /> */}
            <TextField
              id="eventDescription"
              name="eventDescription"
              label="Event Description"
              multiline
              fullWidth
              rows={4}
              variant="outlined"
              value = {values.eventDescription}
              onChange = {handleInputChange}
            />
          </div>
          <Button
            // type="submit"
            fullWidth
            variant="contained"
            color="primary"
            className={classes.submit}
            onClick={handleSubmit}
          >
            Change Event details
          </Button>
          
        </Form>
      </div>
      <Box mt={8}>
        <Copyright />
      </Box>
    </Container>
  )
}