import React, { useState } from 'react'
import { makeStyles } from '@material-ui/core/styles';

export function useForm(initalFValues) {

  const [values, setValues] = useState(initalFValues);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    console.log(name, value);
    setValues({
      ...values,
      [name]: value
    })
  }
  
  return {
    values,
    setValues,
    handleInputChange,
  }
}

const useStyles = makeStyles((theme) => ({
  form: {
    width: '100%', // Fix IE 11 issue.
    marginTop: theme.spacing(3),
  },
}));

export function Form(props) {

  const classes = useStyles();

  return (
    <form className={classes.form}>
      {props.children}
    </form>
  )
}
