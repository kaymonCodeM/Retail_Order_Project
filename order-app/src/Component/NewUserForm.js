import React, { useState } from 'react';
import Service from '../Services/Service';

export default function NewUserForm() {
    const [username, setUsername] = useState("");
    const [password1, setPassword1] = useState("");
    const [password2, setPassword2] = useState("");
    const [messageColor,setMessageColor] = useState("danger");
    const [messageToUserDisplay, setMessageToUserDisplay] = useState("hidden");
    const [messageToUser, setMessageToUser] = useState("");
    const [loginButtonDisplay,setLoginButtonDisplay] = useState("hidden");

    const validate = async() =>{
        setMessageToUserDisplay("hidden");
        setMessageToUser("");
        setLoginButtonDisplay("hidden");

        if(password1!==password2){
            setMessageToUser("Passwords do NOT match");
            setMessageToUserDisplay("visible");
            return false;
        }
        return true;
    }

    const newUserRequest = async(e) =>{
        e.preventDefault();
        setMessageColor("danger");
        const isValid = await validate();
        if(isValid){
           const data = await Service.createUser(username,password1);
           if(data.includes("success")){
               setMessageColor("success");
           }else{
                setMessageColor("danger");
           }
           setMessageToUser(data);
           setMessageToUserDisplay("visible"); 
           setLoginButtonDisplay("visible");
           clear();
        }
    }

    const clear = () =>{
        setUsername("");
        setPassword1("");
        setPassword2("");
    }

    const gotToLogin = () =>{
        window.location.href = "http://localhost:3000/login";
    }


    return (
        <div className='newuser'>
            <div className='Container mt-5'>
                <form className='col-md-8 mx-auto' onSubmit={(e) => newUserRequest(e)}>

                    <div className='row justify-content-center pb-3'>
                        <div className='col-8'>
                            <label htmlFor='username' className='form-label'>Username</label>
                            <input type="text" className="form-control" id='username' placeholder='Username' required value={username} onChange={(event) => setUsername(event.target.value)}/>
                        </div>
                    </div>

                    <div className='row justify-content-center pb-3'>
                        <div className='col-8'>
                            <label htmlFor='password1' className='form-label'>Password</label>
                            <input type="password" className="form-control" id='password1' placeholder='password' required value={password1} onChange={(event) => setPassword1(event.target.value)}/>
                        </div>
                    </div>

                    <div className='row justify-content-center pb-3'>
                        <div className='col-8'>
                            <label htmlFor='password2' className='form-label'>Re-Enter Password</label>
                            <input type="password" className="form-control" id='password2' placeholder='Re-Enter Password' required  value={password2} onChange={(event) => setPassword2(event.target.value)}/>
                        </div>
                    </div>

                    <div className='row justify-content-center pb-3'>
                        <div className='col-8'>
                            <button type='submit' id='submit' className='btn btn-primary' style={{ "width": "100%" }}>submit</button>
                        </div>
                    </div>

                    <div className='row justify-content-center'>
                        <div className='col-8'>
                            <div role="alert" className={'alert alert-'+messageColor+ ' alertMessage text-center'} style={{ visibility: messageToUserDisplay }}>{messageToUser}</div>
                        </div>
                    </div>

                    <div className='row justify-content-center'>
                        <div className='col-4'>
                            <button type='button' className='alert alert-success alertMessage text-center' style={{ "width": "100%", visibility: loginButtonDisplay }} onClick={() => gotToLogin()}>Please Login</button>
                        </div>
                    </div>


                </form>
            </div>
        </div>
    )
}