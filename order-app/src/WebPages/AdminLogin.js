import React, { useState } from 'react'
import AdminLoginForm from '../Component/AdminLoginForm'
import Nav from '../Component/Nav'
import Service from '../Services/Service'

export default function AdminLogin() {
  const cartNum = useState(Service.addTransaction);
  return (
    <div>
        <Nav cartNum={cartNum}/>
        <AdminLoginForm />
    </div>
  )
}
