import React, { useState } from 'react'
import Nav from '../Component/Nav'
import Service from '../Services/Service'

export default function About() {
  const cartNum = useState(Service.calculateItems);
  return (
    <div>
        <Nav cartNum={cartNum}/>
        About
    </div>
  )
}
