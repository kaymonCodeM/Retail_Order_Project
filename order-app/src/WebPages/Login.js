import React, { useState } from 'react'
import LoginForm from '../Component/LoginForm'
import Nav from '../Component/Nav'
import Service from '../Services/Service';

export default function Login() {
  const cartNum = useState(Service.calculateItems);

  return (
    <div>
        <Nav cartNum={cartNum}/>
        <LoginForm />
    </div>
  )
}
