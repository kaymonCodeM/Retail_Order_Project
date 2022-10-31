import React, { useState } from 'react'
import Nav from '../Component/Nav'
import AddressForm from '../Component/AddressForm'
import ContactForm from '../Component/ContactForm'
import PaymentForm from '../Component/PaymentForm'

export default function OrderForm() {
  const [firstNameError,setFirstNameError] = useState('hidden');
  const [lastNameError,setLastNameError] = useState('hidden');
  const [emailError,setEmailError] = useState('hidden');
  const [phoneError,setPhoneError] = useState('hidden');
  const [streetError,setStreetError] = useState('hidden');
  const [countryError,setCountryError] = useState('hidden');
  const [cityError,setCityError] = useState('hidden');
  const [stateError,setStateError] = useState('hidden');
  const [zipError,setZipError] = useState('hidden');
  const [cardHolderError,setCardHolderError] = useState('hidden');
  const [cardNumberError,setCardNumberError] = useState('hidden');
  const [expirationDateError,setExpirationDateError] = useState('hidden');
  const [cvvError,setCvvError] = useState('hidden');
  const [cardZipError,setCardZipError] = useState('hidden');

  const submitOrder = (e) =>{
    e.preventDefault();

    const firstName = document.getElementById('firstName');
    const lastName = document.getElementById('lastName');
    const email = document.getElementById('email');
    const phoneNumber = document.getElementById('phoneNumber');
    const streetAddress = document.getElementById('streetAddress');

    


  }


  return (
    <div>
      <Nav />
      <div className='container mb-5'>

        <form className='col-6 mx-auto' onSubmit={(e)=>submitOrder(e)}>

          <ContactForm firstNameError={firstNameError} lastNameError={lastNameError} emailError={emailError} phoneError={phoneError}/>
          <AddressForm streetError={streetError} countryError={countryError} cityError={cityError} stateError={stateError} zipError={zipError} />
          <PaymentForm cardHolderError={cardHolderError} cardNumberError={cardNumberError} expirationDateError={expirationDateError} cvvError={cvvError} cardZipError={cardZipError}/>
          <div className='form-group pt-2 col-11'>
            <button type="submit" className="btn btn-primary" style={{ width: "100%" }}>Submit</button>
          </div>
        </form>
      </div>
    </div>
  )
}
