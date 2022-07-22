import React, { useState, useEffect } from 'react';
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
import EmailIcon from '@material-ui/icons/Email';

import { useForm, Form } from '../useForm';

import request from '../../utils/request';

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

const initalFValues = {
  userName: '',
  email: '',
  verifyCode: '',
  password: '',
  confirmPassword: ''
}

const initalFError = {
  userName: '',
  email: '',
  verifyCode: '',
  password: '',
  confirmPassword: ''
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
    backgroundColor: theme.palette.secondary.main,
  },
  submit: {
    margin: theme.spacing(3, 0, 2),
  },
}));

export default function RegisterForm(props) {
  const classes = useStyles();

  const { values, setValues, handleInputChange } = useForm(initalFValues);

  const [error, setError] = useState(initalFError);

  const handleSubmit = () => {
    console.log(values);
    if (values.password !== values.confirmPassword) {
      alert('Password and Confirm Password does not match.');
    } else {
      console.log('submit success');
      props.submit(values);
    }
  }

  const verifyEmail = () => {
    // console.log(values.email);
    console.log({"email": values.email});

    request('/users/sendEmail',{
      method: 'POST',
      data: {"email": values.email}
    }).then(data => {
      console.log(data);
    })
  }

  const validateEmail = (email) => {
    return String(email)
    .toLowerCase()
    .match(
      /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
    );
  };

  const strongPassword = (password) => {
    let testPassword = /^(?=.*?[a-z])(?=.*?[A-Z])(?=.*?\d)(?=.*?[!#@*&.])[a-zA-Z\d!#@*&.]*$/
    if (testPassword.test(password) === false) {
      console.log('not strong password');
      return false;
    }
    return true;
  }

  const validateInput = e => {
    let { name, value } = e.target;
    setError(prev => {
      const stateObj = { ...prev, [name]: "" };
 
      switch (name) {
        case "userName":
          if (!value) {
            stateObj[name] = "Please enter Username.";
          }
          break;

        case "email":
          if (!value) {
            stateObj[name] = "Please enter email.";
          }
          if (!validateEmail(value)) {
            stateObj[name] = "Please enter email in correct format.";
          }
          break;

        case "verifyCode":
          if (!value) {
            stateObj[name] = "Please enter verifyCode.";
          }
          else if (value.length !== 4) {
            stateObj[name] = "Please enter the verification code for the correct digits.";
          }
          break;
 
        case "password":
          if (!value) {
            stateObj[name] = "Please enter Password.";
          } else if (!strongPassword(value)) {
            stateObj[name] = "Password isn't strong enough.";
          } else if (values.confirmPassword && value !== values.confirmPassword) {
            stateObj["confirmPassword"] = "Password and Confirm Password does not match.";
          } else {
            stateObj["confirmPassword"] = values.confirmPassword ? "" : error.confirmPassword;
          }
          break;
 
        case "confirmPassword":
          if (!value) {
            stateObj[name] = "Please enter Confirm Password.";
          } else if (values.password && value !== values.password) {
            stateObj[name] = "Password and Confirm Password does not match.";
          }
          break;
 
        default:
          break;
      }
 
      return stateObj;
    });
  }

  return (
    <Container component="main" maxWidth="xs">
      <CssBaseline />
      <div className={classes.paper}>
        <Avatar className={classes.avatar}>
          <LockOutlinedIcon />
        </Avatar>
        <Typography component="h1" variant="h5">
          Register
        </Typography>
        <Form>
          <Grid container spacing={2}>
            <Grid item xs={12}>
              <TextField
                autoComplete="fname"
                name="userName"
                variant="outlined"
                required
                fullWidth
                id="userName"
                label="User Name"
                autoFocus
                value = {values.userName}
                onChange = {handleInputChange}
                onBlur={validateInput}
              />
              {error.userName && <span className='err'>{error.userName}</span>}
            </Grid>
            <Grid item xs={8}>
              <TextField
                variant="outlined"
                required
                fullWidth
                id="email"
                label="Email Address"
                name="email"
                autoComplete="email"
                value = {values.email}
                onChange = {handleInputChange}
                onBlur={validateInput}
              />
              {error.email && <span className='err'>{error.email}</span>}
            </Grid>
            <Grid item xs={4}>
              {!error.email && <Button 
                variant="outlined"
                fullWidth
                color="primary"
                endIcon={<EmailIcon/>}
                onClick={verifyEmail}
                style={{textTransform: "none", fontSize: "7px", height:"100%", fontWeight: "bold"}}
                >Verify Code</Button>}
            </Grid>
            <Grid item xs={12}>
              <TextField
                variant="outlined"
                required
                fullWidth
                id="verifyCode"
                label="Verify Code"
                name="verifyCode"
                autoComplete="verifyCode"
                value = {values.verifyCode}
                onChange = {handleInputChange}
                onBlur={validateInput}
              />
              {error.verifyCode && <span className='err'>{error.verifyCode}</span>}
            </Grid>
            <Grid item xs={12}>
              <TextField
                variant="outlined"
                required
                fullWidth
                name="password"
                label="Password"
                type="password"
                id="password"
                autoComplete="current-password"
                value = {values.password}
                onChange = {handleInputChange}
                onBlur={validateInput}
              />
              <p style={{fontSize: '12px', color: '#666'}}>Password must contain lowercase letters, numbers, and symbols.</p>
              {error.password && <span className='err'>{error.password}</span>}
            </Grid>
            <Grid item xs={12}>
              <TextField
                variant="outlined"
                required
                fullWidth
                name="confirmPassword"
                label="confirmPassword"
                type="password"
                id="confirmPassword"
                autoComplete="current-confirmPassword"
                value = {values.confirmPassword}
                onChange = {handleInputChange}
                onBlur={validateInput}
              />
              {error.confirmPassword && <span className='err'>{error.confirmPassword}</span>}
            </Grid>
            <Grid item xs={12}>
              <FormControlLabel
                control={<Checkbox value="allowExtraEmails" color="primary" />}
                label="I want to receive info via email."
              />
            </Grid>
          </Grid>
          <Button
            // type="submit"
            fullWidth
            variant="contained"
            color="primary"
            className={classes.submit}
            onClick={handleSubmit}
          >
            Sign Up
          </Button>
          <Grid container justifyContent="flex-end">
            <Grid item>
              <Link href="/login" variant="body2">
                Already have an account? Log in
              </Link>
            </Grid>
          </Grid>
        </Form>
      </div>
      <Box mt={5}>
        <Copyright />
      </Box>
    </Container>
  );
}