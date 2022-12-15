import { FaStream } from "react-icons/fa";
import { BiHome, BiMessageRoundedDetail } from 'react-icons/bi'
import { AiOutlineOrderedList } from 'react-icons/ai'
import { BsEnvelope } from 'react-icons/bs'
import { HiOutlineUsers } from 'react-icons/hi'
import { FiSettings } from 'react-icons/fi'
import { IoMdNotificationsOutline } from 'react-icons/io'



export default function SideNav() {

    const closeSmallActive = () =>{
        let sidebar = document.querySelector('.sidebar')
        sidebar.classList.remove('active');
    }


    return (
            <div className='sidebar' id='side_nav'>
                <div className='header-box px-3 pt-3 pb-4 d-flex justify-content-between'>
                    <h1 className='fs-4'>
                        <span className='bg-white text-dark rounded shadow px-2 me-2'>MK</span>
                        <span className='text-white'>Make My Order</span>
                    </h1>
                    <button className='btn d-md-none close-btn px-1 py-0 text-white' onClick={()=>closeSmallActive()}><FaStream /></button>
                </div>
                <ul className='list-unstyled px-2'>
                    <li className='side-tab active' id="dashboard" ><div className='text-decoration-none d-block'><BiHome /> Dashboard</div></li>
                    <li className='side-tab' id="orders" ><div className='text-decoration-none d-block'><AiOutlineOrderedList /> Orders</div></li>
                    <li className='side-tab' id="messages" >
                        <div className='text-decoration-none d-block d-flex justify-content-between'>
                            <span ><BiMessageRoundedDetail /> Messages</span>
                            <span className='bg-dark rounded-pill text-white py-0 px-2'>02</span>
                        </div>
                    </li>
                    <li className='side-tab' id="services" ><div className='text-decoration-none d-block'><BsEnvelope /> Services</div></li>
                    <li className='side-tab' id="customers" ><div className='text-decoration-none d-block'><HiOutlineUsers /> Customers</div></li>
                </ul>
                <hr className='h-color mx-2' />

                <ul className='list-unstyled px-2'>
                    <li className='side-tab' id="settings" ><div className='text-decoration-none d-block'><FiSettings /> Settings</div></li>
                    <li className='side-tab' id="notifications" ><div className='text-decoration-none d-block'><IoMdNotificationsOutline /> Notifications</div></li>
                </ul>
            </div>
    )
}
