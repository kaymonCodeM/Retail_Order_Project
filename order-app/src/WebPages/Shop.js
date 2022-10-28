import React, { useEffect, useState } from 'react'
import ItemsContent from '../Component/ItemsContent'
import Nav from '../Component/Nav'
import Service from '../Services/Service';

export default function Shop() {
  const [itemsList,setItemsList] = useState([]);

  useEffect(()=>{
    Service.getItems().then(data=>setItemsList(data.data))
  },[]);

  return (
    <div>
        <Nav/>
        {<ItemsContent items={itemsList}/>}
    </div>
  )
}
