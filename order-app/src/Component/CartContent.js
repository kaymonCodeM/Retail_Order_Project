import React, { useEffect, useState } from 'react'
import { BiErrorCircle } from "react-icons/bi";
import { AiFillPlusCircle, AiFillMinusCircle } from "react-icons/ai";

export default function CartContent() {
  const [transactions, setTransactions] = useState([]);
  const [alertSymbol, SetAlertSymbol] = useState('hidden');
  const [errorMessageDisplay, setErrorMessageDisplay] = useState('hidden');
  const [errorMessage, setErrorMessage] = useState('Quantity is too high');


  const deliveryCharge = transactions.reduce((a, b) => a = a + b.item.item.weight * b.quantity * .15, 0.0)
  const total = transactions.reduce((a, b) => a = a += b.item.item.price * b.quantity, 0.0) + deliveryCharge;


  const getTransactions = () => {
    if (localStorage.getItem('transactions') !== null) {
      setTransactions(JSON.parse(localStorage.getItem('transactions')));
    }
  }

  const minus = (id) =>{
    console.log(id);
  }

  const plus = (id) =>{
    console.log(id);
  }




  useEffect(() => {

    getTransactions();

  }, [])

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
            <th scope="col"></th>
          </tr>
        </thead>
        <tbody>
          {
            transactions.map(transaction =>
              <tr key={transaction.item.item.itemId} style={{ verticalAlign: "middle"}}>
                <th scope='row'>
                  <img style={{ width: "5rem", height: "5rem" }} src={require('../Images/' + transaction.item.item.type + '/' + transaction.item.item.imageUrl)} alt={transaction.item.item.name + " image"} />
                </th>
                <td>{transaction.item.item.name}</td>
                <td>{transaction.item.item.description}</td>
                <td><i onClick={() =>minus(transaction.item.item.itemId)} ><AiFillMinusCircle className='text-success' style={{  width: "1.5rem", height: "1.5rem",cursor:"pointer"}}/></i>      -{transaction.quantity}-      <i onClick={() =>plus(transaction.item.item.itemId)}><AiFillPlusCircle className='text-primary' style={{  width: "1.5rem", height: "1.5rem",cursor:"pointer"}}/></i></td>
                <td>${parseFloat(transaction.item.item.price * transaction.quantity).toFixed(2)}</td>
                <td className='text-center'>
                  <BiErrorCircle className='text-danger' style={{  width: "2.5rem", height: "2.5rem", visibility: alertSymbol }} />
                </td>
              </tr>
            )
          }
          <tr style={{ verticalAlign: "middle" }}>
            <th scope='row' style={{ height: "5rem" }}>Delivery Charge</th>
            <td></td>
            <td></td>
            <td></td>
            <td>$ {parseFloat(deliveryCharge).toFixed(2)}</td>
            <td></td>
          </tr>
          <tr style={{ verticalAlign: "middle" }}>
            <th scope='row' style={{ height: "5rem" }}>Total</th>
            <td></td>
            <td></td>
            <td></td>
            <td>$ {parseFloat(total).toFixed(2)}</td>
            <td className='text-center'>
              <button type='button' className='btn btn-primary'>Make Order</button>
            </td>
          </tr>
        </tbody>
      </table>
      <div className='d-flex flex-row justify-content-end'>
            <div role="alert" className='alert alert-danger alertMessage text-center col-2' style={{ visibility: errorMessageDisplay }}>{errorMessage}</div>
      </div>
    </div>
  )
}
