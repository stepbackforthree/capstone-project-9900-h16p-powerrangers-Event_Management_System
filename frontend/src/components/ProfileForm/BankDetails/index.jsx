import React, { useEffect } from 'react';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import { makeStyles } from '@material-ui/core/styles';
import { useForm, Form } from '../../useForm';
import BackupIcon from '@material-ui/icons/Backup';
import request from '../../../utils/request';

const initalFValues = {
  method: 'AliPay',
  accountNumber: '',
}

const useStyles = makeStyles((theme) => ({
  paper: {
    // marginTop: theme.spacing(8),
    display: 'flex',
    flexDirection: 'column',
    // alignItems: 'center',
    width: '50%',
  },
  submit: {
    margin: theme.spacing(1, 0, 2),
  },
}));

const method = [
  {
    value: 'AliPay',
    label: 'AliPay',
  },
  {
    value: 'Bpay',
    label: 'Bpay',
  },
  {
    value: 'Bank Account',
    label: 'Bank Account',
  },
  {
    value: 'Bill Pay',
    label: 'Bill Pay',
  },
];


export default function BankDetails() {
  const classes = useStyles();
  const [bankDetails, setBankDetails] = React.useState('');

  const { values, setValues, handleInputChange } = useForm(initalFValues);

  useEffect(() => {
    request(`/users/queryUser`,{
      method: 'GET'
    }).then((response) => {
      console.log(response);
    })
  },[])


  const handleSubmit = () => {
    console.log('submit success');
    setBankDetails(values.method+'+'+values.accountNumber);
    console.log(bankDetails);
    // request('/users/updateBankDetails',{
    //   method: 'POST',
    //   data: values
    // }).then(data => {
    //   console.log(data);
    // })
  }

  const handleNumberInput = (e) => {
    const { name, value } = e.target;
    console.log(name, value);
    const digitalValue = value.replace(/\D/g, '');
    console.log(name, value);
    setValues({
      ...values,
      [name]: digitalValue
    })
  }

  return (
    <div className={classes.paper}>
      <h1>
        Bank Detail
      </h1>
      <Form>
        <h5>Method:</h5>
        <TextField
          id="method"
          name="method"
          select
          // label="If Display"
          value={values.method}
          onChange={handleInputChange}
          SelectProps={{
            native: true,
          }}
          // helperText="If you want to show the event"
          variant="outlined"
        >
          {method.map((option) => (
            <option key={option.value} value={option.value}>
              {option.label}
            </option>
          ))}
        </TextField>

        <h5>Account Number:</h5>
        <TextField
          variant="outlined"
          margin="normal"
          required
          fullWidth
          name="accountNumber"
          label="Account Number(digit only)"
          id="accountNumber"
          autoComplete="accountNumber"
          value = {values.accountNumber}
          onChange = {handleNumberInput}
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

