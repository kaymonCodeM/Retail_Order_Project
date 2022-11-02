import React from 'react'

export default function AddressSummary(props) {
    return (
        <section className="h-100 h-custom">
            <div className="container py-5 h-100">
                <div className="row d-flex justify-content-center align-items-center h-100">
                    <div className="col-lg-8 col-xl-6">
                        <div className="card border-top border-bottom border-3" style={{ borderColor: "#f37a27 !important" }}>
                            <div className="card-body p-5">

                                <div className="mb-3">
                                    <p className="lead fw-bold mb-5 text-primary">Address Summery</p>
                                </div>

                                <div>
                                    <ul className="list-group">
                                        <li className="list-group-item d-flex flex-row justify-content-between list-group-item-light">
                                            <div>Street Address:</div>
                                            <div>{props.address.streetAddress}</div>
                                        </li>
                                        <li className="list-group-item d-flex flex-row justify-content-between list-group-item-light">
                                            <div>Country:</div>
                                            <div>{props.address.country}</div>
                                        </li>
                                        <li className="list-group-item d-flex flex-row justify-content-between list-group-item-light">
                                            <div>City:</div>
                                            <div>{props.address.city}</div>
                                        </li>
                                        <li className="list-group-item d-flex flex-row justify-content-between list-group-item-light">
                                            <div>State:</div>
                                            <div>{props.address.state}</div>
                                        </li>
                                        <li className="list-group-item d-flex flex-row justify-content-between list-group-item-light">
                                            <div>Zip:</div>
                                            <div>{props.address.zip}</div>
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
