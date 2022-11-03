import React, { useState } from 'react'
import NewNav from '../Component/NewNav';
import SideNav from '../Component/SideNav';
import '../css/sideBar.css'

export default function UserAccount() {

  const settings = document.getElementById('settings')
  const [goToLink,setGoToLink] = useState('');

  if (settings !== null) {
    settings.onclick = () => {
      setGoToLink('settings');
    }
  }

  return (
    <div className='main-container d-flex content'>
      <SideNav />
      <div className='content'>

        <NewNav />
        <div className='dashboard-content px-3 pt-4'>

          <h2 className='fs-5'>Dashboard</h2>
          <p>Incididunt exercitation ad occaecat incididunt dolor irure ullamco. Nostrud in eu et commodo in enim dolor occaecat velit officia nisi nisi. Ex et eu id irure sunt ex ipsum dolor cupidatat est sit
            cupidatat dolore. Ad deserunt exercitation veniam cupidatat reprehenderit sit ea exercitation. Laborum duis ipsum enim ex duis.</p>

        </div>
      </div>
    </div>
  )
}
