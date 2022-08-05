import React from 'react'
import ForgotPasswordForm from '../../components/ForgotPasswordForm';
import message from '../../utils/message';
import request from '../../utils/request';

export default function ForgotPassword() {

  return (
    <div>
      <ForgotPasswordForm submit={(values) => {
        console.log('get values:', values);
        request('/users/logIn', {
          method: 'POST',
          data: values
        }).then(data => {
          localStorage.setItem('token', data.token);
          localStorage.setItem('userName', values.userName);
          localStorage.setItem('role', values.role);
          window.location.href = '/dashboard';
          message.success({content: ('log in successfully!'),  duration: 2500})
        })
      }}/>
    </div>
  )
}