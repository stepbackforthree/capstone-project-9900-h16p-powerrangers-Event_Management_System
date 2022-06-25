import React from 'react'
import LogInForm from '../../components/LogInForm'
import { useNavigate } from 'react-router-dom';


export default function Login() {
  const navigate = useNavigate();

  
  return (
    <div>
      <LogInForm submit={(values) => {
        console.log('get values', values);
        navigate('/dashboard');
      }}/>
    </div>
  )
}
