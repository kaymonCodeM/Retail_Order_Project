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

  

  let contact = {};
  let address = {};
  let payment = {};



  const submitOrder = (e) =>{
    e.preventDefault();

    const firstName = document.getElementById('firstName');
    const lastName = document.getElementById('lastName');
    const email = document.getElementById('email');
    const phoneNumber = document.getElementById('phoneNumber');

    const streetAddress = document.getElementById('streetAddress');
    const country = document.getElementById('country');
    const city = document.getElementById('city');
    const state = document.getElementById('state');
    const zip = document.getElementById('zip');

    const cardHolder = document.getElementById('cardHolder');
    const cardNumber = document.getElementById('cardNumber');
    const expirationDate = document.getElementById('expirationDate');
    const cvv = document.getElementById('cvv');
    const cardZip = document.getElementById('cardZip');

    const contactIn = setupContact(firstName.value,lastName.value,email.value,phoneNumber.value);
    const addressIn = setupAddress(streetAddress.value,country.value,city.value,state.value,zip.value);
    const paymentIn = setupPayment(cardHolder.value,cardNumber.value,expirationDate.value,cvv.value,cardZip.value);

    if(contactIn && addressIn && paymentIn){
      localStorage.setItem('payment',JSON.stringify(payment));
      localStorage.setItem('contact',JSON.stringify(contact));
      localStorage.setItem('address',JSON.stringify(address));

      window.location.href = "http://localhost:3000/summery"

    }
  }

  const setupContact = (firstName,lastName,email,phoneNumber) =>{

    setFirstNameError('hidden');
    setLastNameError('hidden');
    setEmailError('hidden');
    setPhoneError('hidden');


    if(firstName !== ''){
      contact["firstname"] = firstName;
    }else{
      setFirstNameError('visible');
      return false;
    }

    if(lastName !== ''){
      contact["lastname"] = lastName;
    }else{
      setLastNameError('visible');
      return false;
    }

    if(email !== ''){
      contact["email"] = email
    }else{
      setEmailError('visible');
      return false;
    }

    if(phoneNumber!==''){
      contact["phoneNumber"] = phoneNumber;
    }else{
      setPhoneError('visible');
      return false;
    }
    return true;
  }

  const setupAddress = (streetAddress,country,city,state,zip) =>{
    setStreetError('hidden');
    setCountryError('hidden');
    setCityError('hidden');
    setStateError('hidden');
    setZipError('hidden');


    if(streetAddress !== ''){

      address["streetAddress"] = streetAddress;
    }else{
      setStreetError('visible');
      return false;
    }

    if(country !== ''){
      address["country"] = country;
    }else{
      setCountryError('visible');
      return false;
    }

    if(city !== ''){
      address["city"] = city;
    }else{
      setCityError('visible');
      return false;
    }

    if(state!==''){
      address["state"] = state;
    }else{
      setStateError('visible');
      return false;
    }

    if(zip!==''){
      address["zip"] = zip;
    }else{
      setZipError('visible');
      return false;
    }
    return true;

  }

  const setupPayment = (cardHolder,cardNumber,expirationDate,cvv,cardZip) =>{

    setCardHolderError('hidden');
    setCardNumberError('hidden');
    setExpirationDateError('hidden');
    setCvvError('hidden');
    setCardZipError('hidden');

    if(cardHolder !== ''){
      payment["cardHolder"] = cardHolder;
    }else{
      setCardHolderError('visible');
      return false;
    }

    if(cardNumber !== ''){
      payment["cardNumber"] = cardNumber;
    }else{
      setCardNumberError('visible');
      return false;
    }

    if(expirationDate !== ''){
      payment["expirationDate"] = expirationDate;
    }else{
      setExpirationDateError('visible');
      return false;
    }

    if(cvv !== ''){
      payment["cvv"] = parseInt(cvv);
    }else{
      setCvvError('visible');
      return false;
    }

    if(cardZip !== ''){
      payment["zip"] = cardZip;
    }else{
      setCardZipError('visible');
      return false;
    }

    return true;

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
