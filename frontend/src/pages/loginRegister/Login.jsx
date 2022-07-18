import React from 'react'
import LogInForm from '../../components/LoginForm'
// import { useNavigate } from 'react-router-dom';
import message from '../../utils/message';
import request from '../../utils/request';

export default function Login() {
  // const navigate = useNavigate();

  return (
    <div>
      <LogInForm submit={(values) => {
        console.log('get values:', values);
        request('/users/logIn', {
          method: 'POST',
          data: values
        }).then(data => {
          localStorage.setItem('token', data.token);
          localStorage.setItem('userName', values.userName);
          localStorage.setItem('role', values.role);
          // navigate('/dashboard');
          window.location.href = '/dashboard';
          message.success({content: ('log in successfully!'),  duration: 2500})
        })
      }}/>
    </div>
  )
}
