import React from 'react'

export default function ContactSummary(props) {
    return (
        <section className="h-100 h-custom">
            <div className="container py-5 h-100">
                <div className="row d-flex justify-content-center align-items-center h-100">
                    <div className="col-lg-8 col-xl-6">
                        <div className="card border-top border-bottom border-3" style={{ borderColor: "#f37a27 !important" }}>
                            <div className="card-body p-5">

                                <div className="mb-3">
                                    <p className="lead fw-bold mb-5 text-primary">Contact Summery</p>
                                </div>

                                <div>
                                    <ul className="list-group">
                                        <li className="list-group-item d-flex flex-row justify-content-between list-group-item-light">
                                            <div>First Name:</div>
                                            <div>{props.contact.firstname}</div>
                                        </li>
                                        <li className="list-group-item d-flex flex-row justify-content-between list-group-item-light">
                                            <div>Last Name:</div>
                                            <div>{props.contact.lastname}</div>
                                        </li>
                                        <li className="list-group-item d-flex flex-row justify-content-between list-group-item-light">
                                            <div>E-mail:</div>
                                            <div>{props.contact.email}</div>
                                        </li>
                                        <li className="list-group-item d-flex flex-row justify-content-between list-group-item-light">
                                            <div>Pone Number:</div>
                                            <div>{props.contact.phoneNumber}</div>
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
