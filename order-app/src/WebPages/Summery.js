import React, { useEffect, useState } from 'react'
import Nav from '../Component/Nav';
import Service from '../Services/Service';
import jwt_decode from 'jwt-decode';
import OrderSummary from '../Component/OrderSummary';
import ContactSummary from '../Component/ContactSummary';
import AddressSummary from '../Component/AddressSummary';
import PaymentSummary from '../Component/PaymentSummary';
import Modal from 'react-bootstrap/Modal';
import Button from 'react-bootstrap/Button';


//https://react-bootstrap.github.io/components/alerts/

export default function Summery(props) {

  const [user, setUser] = useState(null);
  const [show, setShow] = useState(false);
  const [orderSuccess,setOrderSuccess] = useState(false);

  useEffect(() => {
    const id = jwt_decode(localStorage.getItem('token').replace('Bearer ', '')).jti;
    Service.getUserByUserId(id).then(data => setUser(data.data));
  }, []);

  const cancel = () => {
    window.location.href = "http://localhost:3000/orderForm"
  }

  const goHome = () =>{
    window.location.href = "http://localhost:3000/"
  }

  const submitOrder = () => {
    console.log(user);
    console.log(props.payment)
    console.log(props.contact)
    console.log(props.address)
    console.log(props.transactions)

    setOrderSuccess(true) 

  }


  return (
    <div>
      <Nav />
      <OrderSummary transactions={props.transactions} />
      <ContactSummary contact={props.contact} />
      <AddressSummary address={props.address} />
      <PaymentSummary payment={props.payment} />

      <div className='container mb-5'>
        <div className='d-flex flex-row justify-content-between col-lg-8 col-xl-6 mx-auto'>
          <div className='col-3'>
            <button type='button' className='btn btn-warning' style={{ width: "100%" }} onClick={() => cancel()}>Cancel</button>
          </div>
          <div className='col-3'>
            <button type='button' className='btn btn-primary' style={{ width: "100%" }} onClick={() => setShow(true)}>Gift Message</button>
          </div>
          <div className='col-3'>
            <button type='button' className='btn btn-success' style={{ width: "100%" }} onClick={() => submitOrder()}>Confirm</button>
          </div>
        </div>
      </div>

      <Modal show={show} onHide={() => setShow(false)}>
        <Modal.Header closeButton>
          <Modal.Title>Gift Message?</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <div className='form-group'>
            <input type="email" className="form-control mb-3" id="messageTo" placeholder="Enter email" />
            <input className="form-control mb-3" type="file" />
            <textarea class="form-control" id="textValue" style={{ width: "100%", height: "10rem" }} />
          </div>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={() => setShow(false)}>
            Close
          </Button>
          <Button variant="primary" onClick={() => setShow(false)}>
            Save Changes
          </Button>
        </Modal.Footer>
      </Modal>


      <Modal show={orderSuccess} onHide={() => setOrderSuccess(false)} backdrop="static">
        <Modal.Header>
          <Modal.Title className='text-success'>Order Successful</Modal.Title>
        </Modal.Header>
        <Modal.Body className='text-success'>
          Thank you for your order!
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={() => goHome()}>
            Close
          </Button>
        </Modal.Footer>
      </Modal>

    </div>
  )
}
