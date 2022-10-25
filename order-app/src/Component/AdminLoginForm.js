import React,{useState} from 'react'
import Service from '../Services/Service';

export default function AdminLoginForm() {

    const [adminName, setAdminName] = useState("");
    const [password, setPassword] = useState("");
    const [badLoginDisplay, setBadLoginDisplay] = useState("hidden");
    const [errorMessage, setErrorMessage] = useState("");



    const loginAdmin = async (e) => {
        e.preventDefault();

        setBadLoginDisplay("hidden");

        const res = await Service.loginAdmin(adminName,password);

        if (res === '') {
            setErrorMessage("Wrong Admin Name or password");
            setBadLoginDisplay("visible");
        } else {
            localStorage.setItem('token', 'Bearer ' + res);
            window.location.href = "http://localhost:3000/admin";
        }
        return false;

    }

    return (
            <div className='Container pt-5'>

                <form className='col-md-8 mx-auto' onSubmit={(e) => loginAdmin(e)}>
                    <div className='row justify-content-center pb-3'>
                        <div className='col-8'>
                            <label htmlFor='adminName' className='form-label'>Admin name</label>
                            <input type="text" className="form-control" id='adminName' placeholder='Admin name' required value={adminName} onChange={(event) => setAdminName(event.target.value)} />
                        </div>
                    </div>

                    <div className='row justify-content-center pb-3'>
                        <div className='col-8'>
                            <label htmlFor='password' className='form-label'>Password</label>
                            <input type="password" className="form-control" id='password' placeholder='password' required value={password} onChange={(event) => setPassword(event.target.value)} />
                        </div>
                    </div>

                    <div className='row justify-content-center pb-3'>
                        <div className='col-8'>
                            <button type='submit' className='btn btn-primary' style={{ "width": "100%" }}>submit</button>
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