import React from 'react';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import { makeStyles } from '@material-ui/core/styles';
import { useForm, Form } from '../../useForm';
import VpnKeyIcon from '@material-ui/icons/VpnKey';
import request from '../../../utils/request';

const initalFValues = {
  method: '',
  accountNumber: '',
}

const useStyles = makeStyles((theme) => ({
  paper: {
    marginTop: theme.spacing(8),
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    width: '50%',
  },
  submit: {
    margin: theme.spacing(1, 0, 2),
  },
}));


export default function BankDetails() {
  const classes = useStyles();

  const { values, setValues, handleInputChange } = useForm(initalFValues);

  const handleSubmit = () => {
    console.log('submit success');
    console.log(values);
    request('/users/resetPassword',{
      method: 'POST',
      data: values
    }).then(data => {
      console.log(data);
    })
  }

  return (
    <div className={classes.paper}>
      <Form>
        <h5>Email:</h5>
        <TextField
          variant="outlined"
          margin="normal"
          required
          fullWidth
          id="email"
          label="email"
          name="email"
          autoComplete="email"
          autoFocus
          value = {values.email}
          onChange = {handleInputChange}
        />
        <h5>New Password:</h5>
        <TextField
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
        />
        <Button
          // type="submit"
          fullWidth
          variant="contained"
          // color="primary"
          className={classes.submit}
          onClick={handleSubmit}
          startIcon={<VpnKeyIcon/>}
        >
          change password
        </Button>
      </Form>
    </div>
  )
}

