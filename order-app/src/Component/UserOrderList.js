import React, { useEffect, useState } from 'react'
import Service from '../Services/Service';
import jwt_decode from 'jwt-decode';

export default function UserOrderList() {
    const [orders, setOrders] = useState([]);



    useEffect(() => {
        const id = jwt_decode(localStorage.getItem('token').replace('Bearer ', '')).jti;
        Service.getOrderByUserId(id).then(data => setOrders(data.data));
    }, [])


    return (
        <div>
            <p className='h2 p-3 border-bottom border-dark'>Orders</p>
            {
                orders.map(order =>
                    <div className="accordion accordion-flush mb-3" id="accordionFlushExample" key={order.orderId}>
                        <div className="accordion-item">
                            <h2 className="accordion-header" id={"flush-heading" + order.orderId}>
                                <button className="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target={"#flush-collapse" + order.orderId} aria-expanded="false" aria-controls={"flush-collapse" + order.orderId}>
                                    Order # {order.orderId}
                                </button>
                            </h2>

                            <div id={"flush-collapse" + order.orderId} className="accordion-collapse collapse mb-5" aria-labelledby={"flush-heading" + order.orderId} data-bs-parent="#accordionFlushExample">
                                <div className='d-flex flex-row'>
                                    <div style={{ width: "100%" }}>
                                        <div className='d-flex flex-row justify-content-around'>
                                            <div className='col-4 mt-3'>
                                                <p className='h4 p-1 ps-3'>Address</p>
                                                <ul className="list-group">
                                                    <li className="list-group-item">
                                                        <div className='d-flex flex-row justify-content-between'>
                                                            <div>Street Address:</div>
                                                            <div>{order.address.streetAddress}</div>
                                                        </div>
                                                    </li>
                                                    <li className="list-group-item">
                                                        <div className='d-flex flex-row justify-content-between'>
                                                            <div>Country:</div>
                                                            <div>{order.address.country}</div>
                                                        </div>
                                                    </li>
                                                    <li className="list-group-item">
                                                        <div className='d-flex flex-row justify-content-between'>
                                                            <div>City:</div>
                                                            <div>{order.address.city}</div>
                                                        </div>
                                                    </li>
                                                    <li className="list-group-item">
                                                        <div className='d-flex flex-row justify-content-between'>
                                                            <div>State:</div>
                                                            <div>{order.address.state}</div>
                                                        </div>
                                                    </li>
                                                    <li className="list-group-item">
                                                        <div className='d-flex flex-row justify-content-between'>
                                                            <div>Zip:</div>
                                                            <div>{order.address.zip}</div>
                                                        </div>
                                                    </li>
                                                </ul>
                                            </div>
                                            <div className='col-4 mt-3'>
                                                <p className='h4 p-1 ps-3'>Contact</p>
                                                <ul className="list-group">
                                                    <li className="list-group-item">
                                                        <div className='d-flex flex-row justify-content-between'>
                                                            <div>First Name:</div>
                                                            <div>{order.contact.firstname}</div>
                                                        </div>
                                                    </li>
                                                    <li className="list-group-item">
                                                        <div className='d-flex flex-row justify-content-between'>
                                                            <div>Last Name:</div>
                                                            <div>{order.contact.lastname}</div>
                                                        </div>
                                                    </li>
                                                    <li className="list-group-item">
                                                        <div className='d-flex flex-row justify-content-between'>
                                                            <div>E-mail:</div>
                                                            <div>{order.contact.email}</div>
                                                        </div>
                                                    </li>
                                                    <li className="list-group-item">
                                                        <div className='d-flex flex-row justify-content-between'>
                                                            <div>Phone Number:</div>
                                                            <div>{order.contact.phoneNumber}</div>
                                                        </div>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                        <div className='d-flex flex-row justify-content-around'>
                                            <div className='col-4 mt-3'>
                                                <p className='h4 p-1 ps-3'>Payment</p>
                                                <ul className="list-group">
                                                    <li className="list-group-item">
                                                        <div className='d-flex flex-row justify-content-between'>
                                                            <div>Card Holder:</div>
                                                            <div>{order.payment.cardHolder}</div>
                                                        </div>
                                                    </li>
                                                    <li className="list-group-item">
                                                        <div className='d-flex flex-row justify-content-between'>
                                                            <div>Card Number:</div>
                                                            <div>{order.payment.cardNumber}</div>
                                                        </div>
                                                    </li>
                                                    <li className="list-group-item">
                                                        <div className='d-flex flex-row justify-content-between'>
                                                            <div>Expiration Date</div>
                                                            <div>{order.payment.expirationDate}</div>
                                                        </div>
                                                    </li>
                                                    <li className="list-group-item">
                                                        <div className='d-flex flex-row justify-content-between'>
                                                            <div>Zip:</div>
                                                            <div>{order.payment.zip}</div>
                                                        </div>
                                                    </li>
                                                </ul>
                                            </div>

                                            <div className='col-4 mt-3'>
                                                <p className='h4 p-1 ps-3'>Order Details</p>
                                                <div className='d-flex flex-column'>
                                                    <ul className="list-group list-group-horizontal">
                                                        <li className="list-group-item fw-bold" style={{ width: "100%" }}>Item</li>
                                                        <li className="list-group-item text-center fw-bold" style={{ width: "100%" }}>Quantity</li>
                                                        <li className="list-group-item text-end fw-bold" style={{ width: "100%" }}>Price</li>
                                                    </ul>
                                                    {order.transactions.map(trans =>

                                                        <div key={trans.transactionId}>
                                                        <ul className="list-group list-group-horizontal">
                                                        <li className="list-group-item" style={{ width: "100%" }}>{trans.item.name}</li>
                                                        <li className="list-group-item text-center" style={{ width: "100%" }}>{trans.quantity}</li>
                                                        <li className="list-group-item text-end" style={{ width: "100%" }}>$ {parseFloat(trans.item.price).toFixed(2)}</li>
                                                        </ul>
                                                        </div>
                                                    
                                                    )}
                                                    <ul className="list-group list-group-horizontal">
                                                        <li className="list-group-item" style={{ width: "100%" }}>Delivery Charge</li>
                                                        <li className="list-group-item text-end" style={{ width: "100%" }}>$ {parseFloat(order.transactions.reduce((a, b) => a = a + b.item.weight * b.quantity * .15, 0.0)).toFixed(2)}</li>
                                                    </ul>
                                                    <ul className="list-group list-group-horizontal">
                                                        <li className="list-group-item" style={{ width: "100%" }}>Total</li>
                                                        <li className="list-group-item text-end" style={{ width: "100%" }}>$ {parseFloat(order.transactions.reduce((a, b) => a = a += b.item.price * b.quantity, 0.0) + order.transactions.reduce((a, b) => a = a + b.item.weight * b.quantity * .15, 0.0)).toFixed(2)}</li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div className='d-flex flex-column m-3 mt-5' style={{ width: "10rem" }}>
                                        <p className='h4'>Order Panel</p>
                                        <button type='button' className='btn btn-primary mt-2'>
                                            Update
                                        </button>
                                        <button type='button' className='btn btn-warning mt-2'>
                                            cancel
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                )
            }
        </div>
    )
}
