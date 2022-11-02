import React from 'react'

export default function PaymentSummary(props) {
    return (
        <section className="h-100 h-custom">
            <div className="container py-5 h-100">
                <div className="row d-flex justify-content-center align-items-center h-100">
                    <div className="col-lg-8 col-xl-6">
                        <div className="card border-top border-bottom border-3" style={{ borderColor: "#f37a27 !important" }}>
                            <div className="card-body p-5">

                                <div className="mb-3">
                                    <p className="lead fw-bold mb-5 text-primary">Payment Summery</p>
                                </div>

                                <div>
                                    <ul className="list-group">
                                        <li className="list-group-item d-flex flex-row justify-content-between list-group-item-light">
                                            <div>Card Holder:</div>
                                            <div>{props.payment.cardHolder}</div>
                                        </li>
                                        <li className="list-group-item d-flex flex-row justify-content-between list-group-item-light">
                                            <div>Card Number:</div>
                                            <div>{props.payment.cardNumber}</div>
                                        </li>
                                        <li className="list-group-item d-flex flex-row justify-content-between list-group-item-light">
                                            <div>Expiration Date:</div>
                                            <div>{props.payment.expirationDate}</div>
                                        </li>
                                        <li className="list-group-item d-flex flex-row justify-content-between list-group-item-light">
                                            <div>Zip:</div>
                                            <div>{props.payment.zip}</div>
                                        </li>
                                    </ul>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    )
}
