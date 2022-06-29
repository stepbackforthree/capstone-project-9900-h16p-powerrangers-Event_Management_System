import React from 'react'
import RegisterForm from '../../components/RegisterForm'
import { useNavigate } from 'react-router-dom';
import message from '../../utils/message';


export default function Register() {
  const navigate = useNavigate();

  return (
    <div>
      <RegisterForm submit={(values) => {
        console.log('get values', values);
        message.success({content: ('Success Register~ Auto Log in!'),  duration: 2500})
        navigate('/dashboard');
      }}/>
    </div>
  )
}
