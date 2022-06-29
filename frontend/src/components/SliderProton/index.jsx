import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import { Slider } from '@material-ui/core';

const useStyles = makeStyles({
  root: {
    width: '100%',
  },
  thumb: {
    color: '#000',
  },
  rail: {
    color: 'rgba(0,0,0,.26',
  },
  track: {
    color: '#000',
  }
});


export default function SliderProton({ value, changedPrice}) {
  const classes = useStyles();

  return (
    <div className = { classes.root }>
      <Slider 
        value={value}
        onChange={changedPrice}
        valueLabelDisplay='on'
        min={100}
        max={1000}
        classes={
          {
            thumb: classes.thumb,
            rail: classes.rail,
            track: classes.track
          }
      }
      />
    </div>
  )
}
