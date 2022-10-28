import React from 'react'

export default function ItemsContent(items) {


  const viewMyItem = (itemId) => {
    localStorage.setItem('itemId', itemId);
    window.location.href = "http://localhost:3000/ItemView";
  }

  return (
    <div className='container'>
      <div className='row'>
        

        {items.items.map(item =>
          <div className="card m-5" style={{ width: "18rem" }} key={item.itemId}>
            <img className="card-image-top" src={require('../Images/' + item.type + '/' + item.imageUrl)} alt={item.name + " image card"} />
            <div className="card-body">
              <h5 className="card-title">{item.name}</h5>
              <p className="card-text">{item.description}</p>
              <button type='button' className='btn btn-primary' onClick={() => viewMyItem(item.itemId)}>View Item</button>
            </div>
          </div>
        )}
      </div>
    </div>
  )
}
