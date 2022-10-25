import React from 'react'
import Nav from '../Component/Nav'
import AddressForm from '../Component/AddressForm'
import ContactForm from '../Component/ContactForm'
import PaymentForm from '../Component/PaymentForm'

export default function OrderForm() {
  return (
    <div>
        <Nav />
        <ContactForm />
        <AddressForm />
        <PaymentForm />
    </div>
  )
}
