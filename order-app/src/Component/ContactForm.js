import React from 'react'
import { BiErrorCircle } from "react-icons/bi";

export default function ContactForm(props) {
  return (
    <div className='mt-5'>
      <p className='h2 text-center col-11'>Contact</p>
      <div className="form-group">
        <label htmlFor="firstName">First Name</label>

        <div className='d-flex flex-row justify-content-between'>

          <div className='col-11'>
            <input type="text" className="form-control" id="firstName" aria-describedby="firstNameHelp" placeholder="First Name" required />
          </div>
          <div>
            <BiErrorCircle style={{ height: "2.5rem", width: "2.5rem", visibility: props.firstNameError }} className='text-danger' />
          </div>

        </div>

        <small id="firstNameHelp" className="form-text text-danger" style={{ visibility: props.firstNameError }}>Please give First Name</small>
      </div>

      <div className="form-group">
        <label htmlFor="lastName">Last Name</label>

        <div className='d-flex flex-row justify-content-between'>

          <div className='col-11'>
            <input type="text" className="form-control" id="lastName" aria-describedby="lastNameHelp" placeholder="Last Name" required />
          </div>

          <div>
            <BiErrorCircle style={{ height: "2.5rem", width: "2.5rem", visibility: props.lastNameError }} className='text-danger' />
          </div>
        </div>

        <small id="lastNameHelp" className="form-text text-danger" style={{ visibility: props.lastNameError }}>Please give Last Name</small>
      </div>

      <div className="form-group">
        <label htmlFor="email">E-Mail</label>

        <div className='d-flex flex-row justify-content-between'>
          <div className='col-11'>
            <input type="email" className="form-control" id="email" aria-describedby="emailHelp" placeholder="email@email.com" required />
          </div>

          <div>
            <BiErrorCircle style={{ height: "2.5rem", width: "2.5rem", visibility: props.emailError }} className='text-danger' />
          </div>
        </div>

        <small id="emailHelp" className="form-text text-danger" style={{ visibility: props.emailError }}>Please give email</small>
      </div>

      <div className="form-group">
        <label htmlFor="phoneNumber">Phone Number</label>

        <div className='d-flex flex-row justify-content-between'>
          <div className='col-11'>
            <input type="tel" className="form-control" id="phoneNumber" aria-describedby="phoneNumberHelp" placeholder='Phone Number'  required />
          </div>

          <div>
            <BiErrorCircle style={{ height: "2.5rem", width: "2.5rem", visibility: props.phoneError}} className='text-danger' />
          </div>
        </div>

        <small id="phoneNumberHelp" className="form-text text-danger" style={{ visibility: props.phoneError}}>Please give a phone number</small>
      </div>
    </div>
  )
}
