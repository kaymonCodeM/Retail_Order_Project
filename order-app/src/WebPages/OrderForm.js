import React, { useState } from 'react'
import Nav from '../Component/Nav'
import AddressForm from '../Component/AddressForm'
import ContactForm from '../Component/ContactForm'
import PaymentForm from '../Component/PaymentForm'
import Service from '../Services/Service'

export default function OrderForm() {

  const cartNum = useState(Service.calculateItems);

  return (
    <div>
        <Nav cartNum={cartNum}/>
        <ContactForm />
        <AddressForm />
        <PaymentForm />
    </div>
  )
}
