import React from 'react'

export default function OrderSummary(props) {
    const current = new Date();
    const date = `${current.getMonth() + 1}/${current.getDate()}/${current.getFullYear()}`;
  
    const deliveryCharge = props.transactions.reduce((a, b) => a = a + b.item.weight * b.quantity * .15, 0.0)
    const total = props.transactions.reduce((a, b) => a = a += b.item.price * b.quantity, 0.0) + deliveryCharge;


    return (
        <section className="h-100 h-custom">
            <div className="container py-5 h-100">
                <div className="row d-flex justify-content-center align-items-center h-100">
                    <div className="col-lg-8 col-xl-6">
                        <div className="card border-top border-bottom border-3" style={{ borderColor: "#f37a27 !important" }}>
                            <div className="card-body p-5">

                                <div className="d-flex flex-row justify-content-between">
                                    <div className="mb-3">
                                        <p className="lead fw-bold mb-5 text-primary">Order Summery</p>
                                    </div>

                                    <div className="mb-3">
                                        <p className="small text-muted mb-1">Date</p>
                                        <p>{date}</p>
                                    </div>
                                </div>

                                <table className="mx-n5 p-5 bg-light" style={{width: "100%" }}>
                                    <thead className='text-center'>
                                        <tr>
                                            <th scope="col"></th>
                                            <th scope="col">Name</th>
                                            <th scope="col">Quantity</th>
                                            <th scope="col">Price</th>
                                        </tr>
                                    </thead>
                                    <tbody className='text-center'>
                                        {props.transactions.map(trans =>

                                            <tr key={trans.item.itemId}>
                                                <td>
                                                    <img className='my-3' style={{ height: "4rem", marginLeft: "-1.5rem" }} src={require('../Images/' + trans.item.type + '/' + trans.item.imageUrl)} alt={trans.item.name + " image card"} />
                                                </td>
                                                <td>
                                                    <p>{trans.item.name}</p>
                                                </td>
                                                <td>
                                                    <p>{trans.quantity}</p>
                                                </td>
                                                <td>
                                                    <p>{parseFloat(trans.item.price * trans.quantity).toFixed(2)}</p>
                                                </td>
                                            </tr>
                                        )}
                                    </tbody>
                                </table>
                                <div>

                                    <div className="d-flex flex-row justify-content-between mt-5">
                                        <div>
                                            <p>Delivery Charge</p>
                                        </div>
                                        <div >
                                            <p>{"$ " + parseFloat(deliveryCharge).toFixed(2)}</p>
                                        </div>
                                    </div>

                                    <div className="d-flex flex-row justify-content-between">
                                        <div>
                                            <p>Total</p>
                                        </div>
                                        <div>
                                            <p>{"$ " + parseFloat(total).toFixed(2)}</p>
                                        </div>
                                    </div>

                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    )
}
