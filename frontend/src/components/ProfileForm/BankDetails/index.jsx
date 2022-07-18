import React from 'react';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import { makeStyles } from '@material-ui/core/styles';
import { useForm, Form } from '../../useForm';
import BackupIcon from '@material-ui/icons/Backup';
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
    // request('/users/updateBankDetails',{
    //   method: 'POST',
    //   data: values
    // }).then(data => {
    //   console.log(data);
    // })
  }

  return (
    <div className={classes.paper}>
      <Form>
        <h5>Method:</h5>
        <TextField
          variant="outlined"
          margin="normal"
          required
          // fullWidth
          id="method"
          label="method"
          name="method"
          autoComplete="method"
          autoFocus
          value = {values.method}
          onChange = {handleInputChange}
        />
        <h5>Account Number:</h5>
        <TextField
          variant="outlined"
          margin="normal"
          required
          fullWidth
          name="accountNumber"
          label="accountNumber"
          id="accountNumber"
          autoComplete="accountNumber"
          value = {values.accountNumber}
          onChange = {handleInputChange}
        />
        <Button
          // type="submit"
          fullWidth
          variant="contained"
          // color="primary"
          className={classes.submit}
          onClick={handleSubmit}
          startIcon={<BackupIcon/>}
        >
          submit change
        </Button>
      </Form>
    </div>
  )
}

