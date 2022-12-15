import React, { useState } from 'react'
import NewNav from '../Component/NewNav';
import SideNav from '../Component/SideNav';
import UserOrderList from '../Component/UserOrderList';
import '../css/sideBar.css'

export default function UserAccount() {

  //const settings = document.getElementById('settings')
  const [goToLink, setGoToLink] = useState('dashboard');

  const tabs = document.getElementsByClassName('side-tab');

  Array.from(tabs).map((elm) =>
    elm.addEventListener('click', function () {

      const actives = document.getElementsByClassName('active');
      Array.from(actives).map((e) =>
        e.classList.remove('active')
      )
      elm.classList.add('active')
      setGoToLink(elm.id);

    })
  )

  return (
    <div className='main-container d-flex content'>
      <SideNav />
      <div className='content'>

        <NewNav />
        {
          //console.log(goToLink)
        }

        {
          goToLink === 'settings' ? console.log(goToLink)
            :
            goToLink === 'dashboard' ?
              <div className='dashboard-content px-3 pt-4'>

                <h2 className='fs-5'>Dashboard</h2>
                <p>Incididunt exercitation ad occaecat incididunt dolor irure ullamco. Nostrud in eu et commodo in enim dolor occaecat velit officia nisi nisi. Ex et eu id irure sunt ex ipsum dolor cupidatat est sit
                  cupidatat dolore. Ad deserunt exercitation veniam cupidatat reprehenderit sit ea exercitation. Laborum duis ipsum enim ex duis.</p>

              </div>
              :
              goToLink === 'orders' ? <UserOrderList/>
                :
                goToLink === 'messages' ? console.log(goToLink)
                  :
                  goToLink === 'services' ? console.log(goToLink)
                    : null
        }



        {/* <div className='dashboard-content px-3 pt-4'>

          <h2 className='fs-5'>Dashboard</h2>
          <p>Incididunt exercitation ad occaecat incididunt dolor irure ullamco. Nostrud in eu et commodo in enim dolor occaecat velit officia nisi nisi. Ex et eu id irure sunt ex ipsum dolor cupidatat est sit
            cupidatat dolore. Ad deserunt exercitation veniam cupidatat reprehenderit sit ea exercitation. Laborum duis ipsum enim ex duis.</p>

        </div> */}
      </div>
    </div>
  )
}
