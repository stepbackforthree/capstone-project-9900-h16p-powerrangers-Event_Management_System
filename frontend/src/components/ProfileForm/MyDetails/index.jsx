import React, { useState } from 'react';
import TextField from '@material-ui/core/TextField';
import './styles.css';
import Button from '@material-ui/core/Button';
import CloudUploadIcon from '@material-ui/icons/CloudUpload';
import Avatar from '@material-ui/core/Avatar';
import { makeStyles } from '@material-ui/core/styles';
import { deepPurple } from '@material-ui/core/colors';

const useStyles = makeStyles((theme) => ({
  purple: {
    color: theme.palette.getContrastText(deepPurple[500]),
    backgroundColor: deepPurple[500],
  },
  button: {
    margin: '10px 0 10px',
  }
}));


export default function Mydetails() {
  const [baseImage, setBaseImage] = useState("");
  const classes = useStyles();

  // upload img
  const uploadIamge = async (e) => {
    const file = e.target.files[0];
    const base64 = await convertBase64(file);
    setBaseImage(base64);
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
    <>
      <div className="detail-container">
        <h1>
          My details
        </h1>
        <h4>Avatar:</h4>
          <input type="file" onChange={(e) => uploadIamge(e)} />
        <Avatar>
          <img alt="avatar" src={baseImage} height="100%"/>
        </Avatar>
        <Button
          // fullWidth
          variant="contained"
          color="default"
          startIcon={<CloudUploadIcon />}
          className={classes.button}
          // onClick={handleSubmitHost}
        >
          update
        </Button>

        <h4>Phone Number:</h4>
        <TextField
          variant="outlined"
          margin="normal"
          fullWidth
          id="phoneNumber"
          label="Phone Number"
          name="phoneNumber"
          autoComplete="phoneNumber"
          autoFocus
          // value = {}
          // onChange = {}
        />
        <Button
          // fullWidth
          variant="contained"
          color="default"
          className={classes.button}
          startIcon={<CloudUploadIcon />}
          // className={classes.submit}
          // onClick={handleSubmitHost}
        >
          update
        </Button>

        <h4>Email:</h4>
        <TextField
          variant="outlined"
          margin="normal"
          fullWidth
          id="email"
          label="email"
          name="email"
          autoComplete="email"
          // autoFocus
          // value = {}
          // onChange = {}
        />
        <Button
          // fullWidth
          variant="contained"
          color="default"
          className={classes.button}
          startIcon={<CloudUploadIcon />}
          // className={classes.submit}
          // onClick={handleSubmitHost}
        >
          update
        </Button>

        <h4>Description:</h4>
        <TextField
          variant="outlined"
          margin="normal"
          fullWidth
          id="description"
          label="Description"
          name="description"
          autoComplete="description"
          autoFocus
          // value = {}
          // onChange = {}
        />
        <Button
          // fullWidth
          variant="contained"
          color="default"
          className={classes.button}
          startIcon={<CloudUploadIcon />}
          // className={classes.submit}
          // onClick={handleSubmitHost}
        >
          update
        </Button>

      </div>
    </>
  )
}
