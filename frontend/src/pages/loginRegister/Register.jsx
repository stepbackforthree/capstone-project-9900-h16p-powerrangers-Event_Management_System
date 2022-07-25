import React from 'react'
import RegisterForm from '../../components/RegisterForm'
// import { useNavigate } from 'react-router-dom';
import request from '../../utils/request';
import message from '../../utils/message';


export default function Register() {
  // const navigate = useNavigate();

  return (
    <div>
      <RegisterForm submit={(values) => {
        console.log('get values:', values);
        request('/users/signUp', {
          method: 'POST',
          data: {
            ...values,
            "isReceived": true
          }
        })
        .then(data => {
          localStorage.setItem('token', data.token);
          localStorage.setItem('userName', values.userName);
          // navigate('/dashboard');
          window.location.href = '/dashboard';
          message.success({content: ('log in successfully!'),  duration: 2500})
        })
      }}/>
    </div>
  )
}
