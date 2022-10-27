import React, { useEffect, useState} from 'react'
import ItemsContent from '../Component/ItemsContent'
import Nav from '../Component/Nav'
import Service from '../Services/Service';

export default function Shop() {
  const [itemsList,setItemsList] = useState([]);

  const getListOfItems = async() =>{
    setItemsList(await Service.getItems());
  }

  useEffect( () => {
    getListOfItems();

}, []);
  return (
    <div>
        <Nav/>
        {<ItemsContent items={itemsList}/>}
    </div>
  )
}
