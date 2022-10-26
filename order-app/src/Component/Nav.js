import React, { useState } from 'react';
import '../App.css';
import { useEffect } from 'react';
import jwt_decode from 'jwt-decode';
import { TbShoppingCart } from "react-icons/tb";
import Service from '../Services/Service';

const baseUrl = "http://localhost:3000/";

export default function Nav(quantity) {
    const [username, setUsername] = useState("Login");
    const [account, setAccount] = useState('login');
    const [itemNumber, setItemNumber] = useState(0);

    useEffect(() => {
        if (localStorage.getItem('token') !== null) {
            setUsername(jwt_decode(localStorage.getItem('token').replace('Bearer ', '')).sub);
            if ({ username } !== 'login') {
                setAccount('UserAccount');
            }
        }

        setItemNumber(Service.calculateItems);

    }, [username]);

    const logoutUser = () => {
        localStorage.removeItem('token');
        localStorage.removeItem('transactions');
        window.location.href = baseUrl;
    }

    return (
        <nav className='navbar navbar-muted bg-light navbar-expand-sm'>
            <div className="container">


                <div className="collapse navbar-collapse d-grid justify-content-between h5" id="navbarNav">
                    <ul className="navbar-nav">
                        <li className="nav-item">
                            <a href={baseUrl} className="nav-link active">
                                Home
                            </a>
                        </li>
                        <li className="nav-item">
                            <a href={baseUrl + "about"} className="nav-link">
                                About
                            </a>
                        </li>
                        <li className="nav-item">
                            <a href={baseUrl + "shop"} className="nav-link">
                                Shop
                            </a>
                        </li>
                        <li className="nav-item dropdown">
                            <div className="nav-link 
                        dropdown-toggle" id="navbarDropDown" role="button" data-bs-toggle="dropdown"
                                aria-expanded="false">
                                {username}
                            </div>
                            <ul className="dropdown-menu" aria-labelledby="Login">
                                <li><a href={baseUrl + account} className="dropdown-item">
                                    {account}
                                </a>
                                </li>
                                <li><a href={baseUrl + "newUser"} className="dropdown-item">Create New Account</a></li>
                                <li><div className="dropdown-item" onClick={() => logoutUser()}>Logout</div></li>
                            </ul>
                        </li>
                        <li className="nav-item">
                            <a href={baseUrl + "contact"} className="nav-link">
                                Contact Us
                            </a>
                        </li>
                    </ul>
                    <ul className="navbar-nav">
                        <li className="nav-item">
                            <a href={baseUrl + "cart"} className="nav-link">
                                Items {quantity.itemNumber!==undefined?quantity.itemNumber:itemNumber}-<TbShoppingCart />
                            </a>
                        </li>
                    </ul>
                </div>

            </div>
        </nav>
    )
}