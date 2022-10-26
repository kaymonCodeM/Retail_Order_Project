import React, { useState } from 'react'
import Nav from '../Component/Nav'
import Service from '../Services/Service';

export default function Home() {
    const [itemNumber,setItemNumber] = useState(0);

    localStorage.setItem("transactions", JSON.stringify([{"item":"ksk","quantity":8},{"item":"ksnfkmd","quantity":2}]));

  return (
    <div>
        <Nav quantity={itemNumber}/>
        Home
        <button type='button' id='newUser' className='btn btn-success' style={{ "width": "50px" }} onClick={() => setItemNumber(Service.calculateItems)}>add</button>
    </div>
  )
}
