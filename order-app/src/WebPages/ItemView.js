import React, { useState } from 'react'
import Nav from '../Component/Nav';
import Service from '../Services/Service';

export default function ItemView(item) {
  const cartNum= useState(Service.calculateItems);
  const [quantity, setQuantity] = useState(1);

  const addToCart = (e) => {
    e.preventDefault();
    Service.addTransaction(item, quantity);
    localStorage.removeItem('itemId');
    window.location.href = "http://localhost:3000/shop";
  }


  return (
    <div>
      <Nav cartNum={cartNum}/>
      <div className='container'>

        <div className='row m-5 justify-content-between'>

          <div className="card col-3">
            <img className="card-image-top" src={require('../Images/' + item.item.type + '/' + item.item.imageUrl)} alt={item.item.name + " image card"} />
            <div className="card-body">
              <h5 className="card-title">{item.item.name}</h5>
              <p className="card-text">{item.item.description}</p>
            </div>
          </div>

          <div className="card col-5 p-0">
            <div className="card-header">
              {item.item.name}
            </div>
            <ul className="list-group list-group-flush">
              <li className="list-group-item">Item Type: {item.item.type}</li>
              <li className="list-group-item">Item size: {item.item.size}</li>
              <li className="list-group-item">Item weight: {item.item.weight}</li>
              <li className="list-group-item">Item quantity: {item.item.quantity}</li>
              <li className="list-group-item">Item price: ${parseFloat(item.item.price).toFixed(2)}</li>
            </ul>

            <form className='pt-3 mx-3' onSubmit={(e) => addToCart(e)}>
                  <label htmlFor='quantity' className='form-label'>Quantity</label>
              <div className="form-group row justify-content-between">
                <div className='col-2'>
                  <input type="number" className="form-control" min={1} max={item.item.quantity} id='quantity' placeholder='1' required value={quantity} onChange={(event) => setQuantity(event.target.valueAsNumber)} />
                </div>

                <div className='col-6'>
                  <button type='submit' className='btn btn-primary' style={{ "width": "100%" }}>Add to cart</button>
                </div>

              </div>
            </form>
          </div>

        </div>
      </div>
    </div>
  )
}

