import '../css/sideBar.css'
import { FaStream } from "react-icons/fa";
import { BiHome, BiMessageRoundedDetail } from 'react-icons/bi'
import { AiOutlineOrderedList } from 'react-icons/ai'
import { BsEnvelope } from 'react-icons/bs'
import { HiOutlineUsers } from 'react-icons/hi'
import { FiSettings } from 'react-icons/fi'
import { IoMdNotificationsOutline } from 'react-icons/io'
import {GrList} from 'react-icons/gr'



export default function SideNav() {

    const toggleActive = (e) => {
        let others = document.getElementsByClassName('active');
        Array.from(others).forEach((elm) =>
            elm.classList.remove('active')
        )
        e.currentTarget.className = 'active';

    }

    const smallActive = () =>{
        let sidebar = document.querySelector('.sidebar')
        sidebar.classList.add('active');
    }

    const closeSmallActive = () =>{
        let sidebar = document.querySelector('.sidebar')
        sidebar.classList.remove('active');
    }


    return (
        <div className='main-container d-flex'>
            <div className='sidebar' id='side_nav'>
                <div className='header-box px-3 pt-3 pb-4 d-flex justify-content-between'>
                    <h1 className='fs-4'>
                        <span className='bg-white text-dark rounded shadow px-2 me-2'>CL</span>
                        <span className='text-white'>Coding League</span>
                    </h1>
                    <button className='btn d-md-none close-btn px-1 py-0 text-white' onClick={()=>closeSmallActive()}><FaStream /></button>
                </div>
                <ul className='list-unstyled px-2'>
                    <li className='' onClick={(e) => toggleActive(e)}><div className='text-decoration-none d-block'><BiHome /> Dashboard</div></li>
                    <li className='' onClick={(e) => toggleActive(e)}><div className='text-decoration-none d-block'><AiOutlineOrderedList /> Projects</div></li>
                    <li className='' onClick={(e) => toggleActive(e)}>
                        <div className='text-decoration-none d-block d-flex justify-content-between'>
                            <span ><BiMessageRoundedDetail /> Messages</span>
                            <span className='bg-dark rounded-pill text-white py-0 px-2'>02</span>
                        </div>
                    </li>
                    <li className='' onClick={(e) => toggleActive(e)}><div className='text-decoration-none d-block'><BsEnvelope /> Services</div></li>
                    <li className='' onClick={(e) => toggleActive(e)}><div className='text-decoration-none d-block'><HiOutlineUsers /> Customers</div></li>
                </ul>
                <hr className='h-color mx-2' />

                <ul className='list-unstyled px-2'>
                    <li className='' onClick={(e) => toggleActive(e)}><div className='text-decoration-none d-block'><FiSettings /> Settings</div></li>
                    <li className='' onClick={(e) => toggleActive(e)}><div className='text-decoration-none d-block'><IoMdNotificationsOutline /> Notifications</div></li>

                </ul>
            </div>
            <div className='content'>
                <nav className="navbar navbar-expand-lg navbar-light bg-light">
                    <div className="container-fluid">
                        <div className='d-flex justify-content-between d-md-none d-block'>
                            <button className='btn px-1 py-0 open-btn me-2' onClick={()=>smallActive()}><FaStream /></button>
                            <div className="navbar-brand fs-4"><span className='bg-dark rounded px-2 py-0 text-white'>CL</span>Coding League</div>
                        </div>
                        <button className="navbar-toggler p-0 border-0" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                            <GrList/>
                        </button>
                        <div className="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
                            <ul className="navbar-nav mb-2 mb-lg-0">
                                <li className="nav-item">
                                    <div className="nav-link active" aria-current="page">Profile</div>
                                </li>

                            </ul>
                        </div>
                    </div>
                </nav>
                <div className='dashboard-content px-3 pt-4'>
                    <h2 className='fs-5'>Dashboard</h2>
                    <p>Incididunt exercitation ad occaecat incididunt dolor irure ullamco. Nostrud in eu et commodo in enim dolor occaecat velit officia nisi nisi. Ex et eu id irure sunt ex ipsum dolor cupidatat est sit 
                        cupidatat dolore. Ad deserunt exercitation veniam cupidatat reprehenderit sit ea exercitation. Laborum duis ipsum enim ex duis.</p>

                </div>
            </div>
        </div>
    )
}
