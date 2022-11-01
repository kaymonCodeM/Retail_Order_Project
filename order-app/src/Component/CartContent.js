import React, { useEffect, useState } from 'react'
import { BiErrorCircle } from "react-icons/bi";
import { AiFillPlusCircle, AiFillMinusCircle } from "react-icons/ai";
import { MdDelete } from "react-icons/md";
import Service from '../Services/Service';

export default function CartContent() {
  const transactions = JSON.parse(localStorage.getItem('transactions'));
  const [errorMessageDisplay, setErrorMessageDisplay] = useState('hidden');
  const [errorMessage, setErrorMessage] = useState('Quantity is too high');


  const deliveryCharge = transactions !== null ? transactions.reduce((a, b) => a = a + b.item.weight * b.quantity * .15, 0.0) : null
  const total = transactions !== null ? transactions.reduce((a, b) => a = a += b.item.price * b.quantity, 0.0) + deliveryCharge : null


  const minus = (id) => {
    let t;
    transactions.map(trans => (
      trans.item.itemId === id ?
        t = trans : trans
    )
    )
    t.quantity = t.quantity - 1;
    Service.updateTransaction(t);
    window.location.href = "http://localhost:3000/cart";
  }

  const plus = (id) => {
    let t;
    transactions.map(trans => (
      trans.item.itemId === id ?
        t = trans : trans
    )
    )
    t.quantity = t.quantity + 1;
    Service.updateTransaction(t);
    window.location.href = "http://localhost:3000/cart";

  }

  const deleteTransaction = (id) => {
    Service.removeTransaction(id);
    window.location.href = "http://localhost:3000/cart";
  }

  const setAllError = (trans) => {
    setErrorMessageDisplay('visible')
    setErrorMessage(trans.item.name + ' only has ' + trans.item.quantity + ' in stock')
  }

  const makeOrder = () => {
    if (errorMessageDisplay !== 'visible') {
      if (localStorage.getItem('token') !== null) {
        window.location.href = "http://localhost:3000/orderForm";
      } else {
        window.location.href = "http://localhost:3000/login";
      }
    }
  }

  useEffect(() => {

    if (transactions !== null) {
      transactions.map(trans => (
        trans.item.quantity < trans.quantity ?
          setAllError(trans) : trans
      ))
    }

  }, [transactions]);


  return (
    <div className='container mt-5'>
      <table className='table'>
        <thead>
          <tr>
            <th scope="col" style={{ visibility: "hidden" }}>Item</th>
            <th scope="col">Name</th>
            <th scope="col">Description</th>
            <th scope="col">Quantity</th>
            <th scope="col">Price</th>
            <th scope="col" className='text-center'>Delete</th>
            <th scope="col"></th>
          </tr>
        </thead>
        <tbody>
          {
            transactions !== null ?
              transactions.map(transaction =>
                <tr key={transaction.item.itemId} style={{ verticalAlign: "middle" }}>
                  <th scope='row'>
                    <img style={{ width: "5rem", height: "5rem" }} src={require('../Images/' + transaction.item.type + '/' + transaction.item.imageUrl)} alt={transaction.item.name + " image"} />
                  </th>
                  <td>{transaction.item.name}</td>
                  <td>{transaction.item.description}</td>
                  <td><i onClick={() => minus(transaction.item.itemId)} ><AiFillMinusCircle className='text-success' style={{ width: "1.5rem", height: "1.5rem", cursor: "pointer" }} /></i>      -{transaction.quantity}-      <i onClick={() => plus(transaction.item.itemId)}><AiFillPlusCircle className='text-primary' style={{ width: "1.5rem", height: "1.5rem", cursor: "pointer" }} /></i></td>
                  <td>${parseFloat(transaction.item.price * transaction.quantity).toFixed(2)}</td>
                  <td className='text-center'><MdDelete style={{ width: "1.5rem", height: "1.5rem", cursor: "pointer" }} onClick={() => deleteTransaction(transaction.item.itemId)} /></td>
                  <td className='text-center'>
                    {transaction.quantity > transaction.item.quantity ?
                      <BiErrorCircle className='text-danger' style={{ width: "2.5rem", height: "2.5rem" }} />
                      : <BiErrorCircle className='text-danger' style={{ width: "2.5rem", height: "2.5rem",visibility:"hidden" }} />
                    }
                  </td>
                </tr>
              )
              :
              <tr><td className="border-0">No Items in List</td></tr>
          }
          <tr style={{ verticalAlign: "middle" }}>
            <th scope='row' style={{ height: "5rem" }}>Delivery Charge</th>
            <td></td>
            <td></td>
            <td></td>
            <td>$ {parseFloat(deliveryCharge).toFixed(2)}</td>
            <td></td>
            <td></td>
          </tr>
          <tr style={{ verticalAlign: "middle" }}>
            <th scope='row' style={{ height: "5rem" }}>Total</th>
            <td></td>
            <td></td>
            <td></td>
            <td>$ {parseFloat(total).toFixed(2)}</td>
            <td className='text-center'>
              <button type='button' className='btn btn-primary' onClick={() => makeOrder()}>Make Order</button>
            </td>
            <td></td>
          </tr>
        </tbody>
      </table>
      <div className='d-flex flex-row justify-content-end'>
        <div role="alert" className='alert alert-danger alertMessage text-center col-4' style={{ visibility: errorMessageDisplay }}>{errorMessage}</div>
      </div>
    </div>
  )
}
