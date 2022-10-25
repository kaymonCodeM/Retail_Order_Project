
//npm install react-router-dom
//npm install npm
//npm install bootstrap
//npm install buffer
//npm install jwt-decode

import { useState,useEffect } from "react";
import About from "./WebPages/About";
import Admin from "./WebPages/Admin";
import AdminLogin from "./WebPages/AdminLogin";
import Cart from "./WebPages/Cart";
import Home from "./WebPages/Home";
import Login from "./WebPages/Login";
import NewUser from "./WebPages/NewUser";
import OrderForm from "./WebPages/OrderForm";
import OrderSummery from "./WebPages/OrderSummery";
import Shop from "./WebPages/Shop";
import UserAccount from "./WebPages/UserAccount";
import { Route, Routes } from "react-router-dom";
import jwt_decode from 'jwt-decode';
import Contact from "./WebPages/Contact";

function App() {

  const [role,setRole] = useState([]);
  const [transactions,setTransactions] = useState([]);

  const getTransactions = () =>{
    if(localStorage.getItem('transactions')!==null){
      setTransactions(localStorage.getItem("transactions"));
    }
  }

  const getUserRoles = () =>{
    if(localStorage.getItem('token')!==null){
      setRole(jwt_decode(localStorage.getItem('token').replace('Bearer ','')).scope);
    }
  }

  useEffect(()=>{
    getTransactions();
    getUserRoles();
},[role,transactions]);

  return (
    <Routes>
          <Route path="/" element={<Home />}></Route>
          <Route path="/login" element={<Login />}></Route>
          <Route path="/newUser" element={<NewUser />}></Route>
          <Route path="/shop" element={<Shop />}></Route>
          <Route path="/about" element={<About />}></Route>
          <Route path="/cart" element={<Cart />}></Route>
          <Route path="/orderForm" element={ transactions.length>0 ?
          <OrderForm /> : <Shop />
          }></Route>
          <Route path="/orderSummery" element={<OrderSummery />}></Route>
          <Route path="/contact" element={<Contact />}></Route>
          <Route path="/userAccount" element={<UserAccount />}></Route>
          <Route path="/admin" element={role.includes('ROLE_ADMIN') ?(
          <Admin />
          ):(
            <AdminLogin />
          )}/>
        </Routes>
  );
}

export default App;
