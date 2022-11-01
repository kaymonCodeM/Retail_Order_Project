import React from 'react'
import { BiErrorCircle } from "react-icons/bi";

export default function AddressForm(props) {



  return (
    <div className='mt-5'>
      <p className='h2 text-center col-11'>Address</p>
      <div className="form-group">
        <label htmlFor="streetAddress">Street Address</label>

        <div className='d-flex flex-row justify-content-between'>

          <div className='col-11'>
            <input type="text" className="form-control" id="streetAddress" aria-describedby="addressHelp" placeholder="Street Address" required />
          </div>
          <div>
            <BiErrorCircle style={{ height: "2.5rem", width: "2.5rem", visibility: props.streetError }} className='text-danger' />
          </div>

        </div>

        <small id="addressHelp" className="form-text text-danger" style={{ visibility: props.streetError }}>Please give street address</small>
      </div>

      <div className="form-group">
        <label htmlFor="country">Country</label>

        <div className='d-flex flex-row justify-content-between'>

          <div className='col-11'>
            <input type="text" className="form-control" id="country" aria-describedby="countryHelp" placeholder="Country" required />
          </div>

          <div>
            <BiErrorCircle style={{ height: "2.5rem", width: "2.5rem", visibility: props.countryError }} className='text-danger' />
          </div>
        </div>

        <small id="countryHelp" className="form-text text-danger" style={{ visibility: props.countryError }}>Please give country</small>
      </div>

      <div className="form-group">
        <label htmlFor="city">City</label>

        <div className='d-flex flex-row justify-content-between'>
          <div className='col-11'>
            <input type="text" className="form-control" id="city" aria-describedby="cityHelp" placeholder="City" required />
          </div>

          <div>
            <BiErrorCircle style={{ height: "2.5rem", width: "2.5rem", visibility: props.cityError }} className='text-danger' />
          </div>
        </div>

        <small id="cityHelp" className="form-text text-danger" style={{ visibility: props.cityError }}>Please give city</small>
      </div>

      <div className="form-group">
        <label htmlFor="state">State</label>

        <div className='d-flex flex-row justify-content-between'>
          <div className='col-11'>
            <input type="text" className="form-control" id="state" aria-describedby="stateHelp" placeholder="state" required />
          </div>

          <div>
            <BiErrorCircle style={{ height: "2.5rem", width: "2.5rem", visibility: props.stateError}} className='text-danger' />
          </div>
        </div>

        <small id="stateHelp" className="form-text text-danger" style={{ visibility: props.stateError}}>Please give State</small>
      </div>

      <div className="form-group">
        <label htmlFor="zip">Zip</label>

        <div className='d-flex flex-row justify-content-between'>
          <div className='col-11'>
            <input type="text" className="form-control" id="zip" aria-describedby="zipHelp" placeholder="Zip" required />
          </div>

          <div>
            <BiErrorCircle style={{ height: "2.5rem", width: "2.5rem", visibility: props.zipError }} className='text-danger' />
          </div>
        </div>
        <small id="zipHelp" className="form-text text-danger" style={{ visibility: props.zipError }}>Please give Zip</small>
      </div>
    </div>
  )
}
