import React, { useState } from 'react'
import CartContent from '../Component/CartContent'
import Nav from '../Component/Nav'
import Service from '../Services/Service';

export default function Cart() {
  const cartNum = useState(Service.calculateItems);

  return (
    <div>
        <Nav cartNum={cartNum}/>
        <CartContent />
    </div>
  )
}
