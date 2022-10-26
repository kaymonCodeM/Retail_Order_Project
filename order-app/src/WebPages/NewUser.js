import React, { useState } from 'react'
import Nav from '../Component/Nav'
import NewUserForm from '../Component/NewUserForm'
import Service from '../Services/Service';

export default function NewUser() {
  const cartNum = useState(Service.calculateItems);
  return (
    <div>
        <Nav cartNum={cartNum}/>
        <NewUserForm />
    </div>
  )
}
