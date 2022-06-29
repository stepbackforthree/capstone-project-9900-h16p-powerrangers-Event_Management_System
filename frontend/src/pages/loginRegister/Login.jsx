import React from 'react'
import LogInForm from '../../components/LogInForm'
import { useNavigate } from 'react-router-dom';
import message from '../../utils/message';

export default function Login() {
  const navigate = useNavigate();

  return (
    <div>
      <LogInForm submit={(values) => {
        console.log('get values', values);
        message.success({content: ('log in successfully!'),  duration: 2500})
        navigate('/dashboard');
      }}/>
    </div>
  )
}
