import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import Checkbox from '@material-ui/core/Checkbox';

const useStyles = makeStyles({
  root: {
    '&$checked': {
      color: '#000',
    },
  },
  checked: {},
  wrap: {
    width: '100%',
    display: 'flex',
    flexDirection: 'row-reverse',
    justifyContent: 'space-between',
    alignItems: 'center',
    marginLeft: 0,
  },
  label: {
    fontSize: '.8rem',
  },
});

export default function CheckboxProton({city, changeChecked}) {
  const classes = useStyles();


  const { checked, label, id } = city;

  return (
    <div>
      <FormControlLabel
        classes={{
          label: classes.label,
          root: classes.wrap
        }}
        control={
          <Checkbox classes={{
            checked: classes.checked,
            root: classes.root
          }}
          size="small"
          check={checked}
          onChange={()=>changeChecked(id)}
          />
        }

        label={label}
      />
    </div>
  )
}
