import React, { useState } from 'react';
import Service from '../Services/Service';

//https://www.youtube.com/watch?v=rWfhwW9forg


export default function LoginForm() {

  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [badLoginDisplay, setBadLoginDisplay] = useState("hidden");
  const [errorMessage, setErrorMessage] = useState("");


  const sendLoginRequest = async(e) => {

    e.preventDefault();
    
    setBadLoginDisplay("hidden");

    const res = await Service.getToken(username,password);

    if (res==='') {
        setErrorMessage("Wrong username or password");
        setBadLoginDisplay("visible");
    } else {
      localStorage.setItem('token', 'Bearer ' + res);
      window.location.href = "http://localhost:3000/";
    }
    return false;
  }

  const createNewUser = () => {
    window.location.href = "http://localhost:3000/newUser";
  }

  return (
    <div className='Container mt-5'>

      <form className='col-md-8 mx-auto' onSubmit={(e)=> sendLoginRequest(e)}>
        <div className='row justify-content-center pb-3'>
          <div className='col-8'>
            <label htmlFor='username' className='form-label'>Username</label>
            <input type="text" className="form-control" id='username' placeholder='Username' required value={username} onChange={(event) => setUsername(event.target.value)} />
          </div>
        </div>

        <div className='row justify-content-center pb-3'>
          <div className='col-8'>
            <label htmlFor='password' className='form-label'>Password</label>
            <input type="password" className="form-control" id='password' placeholder='password' required value={password} onChange={(event) => setPassword(event.target.value)} />
          </div>
        </div>

        <div className='row justify-content-center pb-3'>
          <div className='col-6'>
            <button type='submit' className='btn btn-primary' style={{ "width": "100%" }}>submit</button>
          </div>
          <div className='col-2'>
            <button type='button' id='newUser' className='btn btn-success' style={{ "width": "100%" }} onClick={() => createNewUser()}>Create new user</button>
          </div>
        </div>

        <div className='row justify-content-center pb-3'>
          <div className='col-8'>
            <div role="alert" className='alert alert-danger alertMessage mt-3 text-center' style={{ visibility: badLoginDisplay }}>{errorMessage}</div>
          </div>
        </div>

      </form>
    </div>
  )
}