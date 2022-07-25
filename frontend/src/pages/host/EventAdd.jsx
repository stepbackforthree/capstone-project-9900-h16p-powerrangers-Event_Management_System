import React from 'react';
import Paper from '@material-ui/core/Paper';
import { makeStyles } from '@material-ui/core/styles';
import EventAddForm from '../../components/EventAddForm';
import request from '../../utils/request';
import message from '../../utils/message';
import { useNavigate } from 'react-router-dom';


const useStyles = makeStyles((theme) => ({
  root: {
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
  },
  paper: {
    margin: theme.spacing(1),
    padding: theme.spacing(2),
    width: theme.spacing(100),

    // height: theme.spacing(100),
  },
  header: {
    borderBottom: '1px solid #666',
    width: '100%',
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
  }
}));


export default function EventAdd() {
  const classes = useStyles()
  const navigate = useNavigate();


  return (
    <div className={classes.root} >
      <div className={classes.header} style={{'borderRadius': '35px'}}>
        <h1>EventAdd</h1>
      </div>
      <Paper elevation={20} className={classes.paper} style={{'borderRadius': '35px'}}>
        <EventAddForm submit={(values) => { 
          console.log('get values:', values);
          request('/events/createEvent', {
            method: 'POST',
            data: values
          }).then(data => {
            console.log('get data:', data);
            // localStorage.setItem('token', data.token);
            // localStorage.setItem('userName', values.userName);
            // localStorage.setItem('role', values.role);
            navigate('/dashboard');
            // window.location.href = '/dashboard';
            message.success({content: ('Create event successfully!'),  duration: 2500});
          })

          // window.location.href = '/dashboard';
        }}/>
      </Paper>
    </div>
  )
}
