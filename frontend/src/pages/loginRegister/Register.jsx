import React from 'react'
import RegisterForm from '../../components/RegisterForm'
import { useNavigate } from 'react-router-dom';

export default function Register() {
  const navigate = useNavigate();

  return (
    <div>
      <RegisterForm submit={(values) => {
        console.log('get values', values);
        navigate('/dashboard');
      }}/>
    </div>
  )
}
