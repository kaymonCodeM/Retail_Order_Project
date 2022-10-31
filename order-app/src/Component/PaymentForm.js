import React from 'react'
import { BiErrorCircle } from "react-icons/bi";

export default function PaymentForm(props) {
  return (
    <div className='mt-5'>
    <p className='h2 text-center col-11'>Payment</p>
    <div className="form-group">
      <label htmlFor="cardHolder">Card Holder</label>

      <div className='d-flex flex-row justify-content-between'>

        <div className='col-11'>
          <input type="text" className="form-control" id="cardHolder" aria-describedby="cardHolderHelp" placeholder="Full Name" required />
        </div>
        <div>
          <BiErrorCircle style={{ height: "2.5rem", width: "2.5rem", visibility: props.cardHolderError }} className='text-danger' />
        </div>

      </div>

      <small id="cardHolderHelp" className="form-text text-danger" style={{ visibility: props.cardHolderError }}>Please give full name</small>
    </div>

    <div className="form-group">
      <label htmlFor="cardNumber">Card Number</label>

      <div className='d-flex flex-row justify-content-between'>

        <div className='col-11'>
          <input type="text" className="form-control" id="cardNumber" aria-describedby="cardNumberHelp" placeholder="Card Number" required />
        </div>

        <div>
          <BiErrorCircle style={{ height: "2.5rem", width: "2.5rem", visibility: props.cardNumberError }} className='text-danger' />
        </div>
      </div>

      <small id="cardNumberHelp" className="form-text text-danger" style={{ visibility: props.cardNumberError }}>Please give card number</small>
    </div>

    <div className="form-group">
      <label htmlFor="expirationDate">Expiration Date</label>

      <div className='d-flex flex-row justify-content-between'>
        <div className='col-11'>
          <input type="date" className="form-control" id="expirationDate" aria-describedby="expirationDateHelp" placeholder="Expiration Date" required />
        </div>

        <div>
          <BiErrorCircle style={{ height: "2.5rem", width: "2.5rem", visibility: props.expirationDateError }} className='text-danger' />
        </div>
      </div>

      <small id="expirationDateHelp" className="form-text text-danger" style={{ visibility: props.expirationDateError }}>Please give expiration date</small>
    </div>

    <div className="form-group">
      <label htmlFor="cvv">CVV</label>

      <div className='d-flex flex-row justify-content-between'>
        <div className='col-11'>
          <input type="number" className="form-control" id="cvv" aria-describedby="cvvHelp" placeholder="cvv" required />
        </div>

        <div>
          <BiErrorCircle style={{ height: "2.5rem", width: "2.5rem", visibility: props.cvvError}} className='text-danger' />
        </div>
      </div>

      <small id="cvvHelp" className="form-text text-danger" style={{ visibility: props.cvvError}}>Please give CVV number</small>
    </div>

    <div className="form-group">
      <label htmlFor="cardZip">Card Zip</label>

      <div className='d-flex flex-row justify-content-between'>
        <div className='col-11'>
          <input type="text" className="form-control" id="cardZip" aria-describedby="cardZipHelp" placeholder="Card Zip" required />
        </div>

        <div>
          <BiErrorCircle style={{ height: "2.5rem", width: "2.5rem", visibility: props.cardZipError }} className='text-danger' />
        </div>
      </div>
      <small id="cardZipHelp" className="form-text text-danger" style={{ visibility: props.cardZipError }}>Please give Zip</small>
    </div>
  </div>
  )
}
