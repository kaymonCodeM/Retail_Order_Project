import React, { useState } from 'react'
import Nav from '../Component/Nav'

export default function Home() {
    const [itemNumber,setItemNumber] = useState(0);
    const incrementStorage = () => {
        let num = localStorage.getItem('transactions');
        if(num!=null){
            setItemNumber(num.length);
        }else{
            setItemNumber(0);
        }
    }

  return (
    <div>
        <Nav itemNumber={itemNumber}/>
        Home
        <button type='button' id='newUser' className='btn btn-success' style={{ "width": "50px" }} onClick={() => incrementStorage()}>add</button>
    </div>
  )
}
