import React from 'react'
import {GrList} from 'react-icons/gr'
import { FaStream } from "react-icons/fa";

export default function NewNav() {
    const smallActive = () =>{
        let sidebar = document.querySelector('.sidebar')
        sidebar.classList.add('active');
    }

    return (
        <div>
            <nav className="navbar navbar-expand-lg navbar-light bg-light">
                <div className="container-fluid">
                    <div className='d-flex justify-content-between d-md-none d-block'>
                        <button className='btn px-1 py-0 open-btn me-2' onClick={() => smallActive()}><FaStream /></button>
                        <div className="navbar-brand fs-4"><span className='bg-dark rounded px-2 py-0 text-white'>CL</span>Coding League</div>
                    </div>
                    <button className="navbar-toggler p-0 border-0" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <GrList />
                    </button>
                    <div className="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
                        <ul className="navbar-nav mb-2 mb-lg-0">
                            <li className="nav-item">
                                <div className="nav-link active" aria-current="page" >Profile</div>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </div>
    )
}
