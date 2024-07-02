import React, { useState } from 'react';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import './ForgotPassword.css';

const ForgotPassword = () => {
  const [password, setPassword] = useState('');
  const [cpassword, setCPassword] = useState('');
  const handleChange = (e) => {
   setPassword(e.target.value);
  };
  const handleChangere =(e) =>{
    setCPassword(e.target.value);
  }
  const handleSubmit = (e) => {
    e.preventDefault();
    if (password.trim() === '') {
      toast.error('password cannot be empty!');
    } 
   else if (password !==cpassword ) {
        toast.error('both passwords should be same!');
      } 
    else
     {
      toast.success(`password changed!`);
    }
  };

  return (
    <div className="ForgotPassword-App">
      <div className="ForgotPassword-container">
        <form onSubmit={handleSubmit}>
          <h1>Reset Your Password</h1>
          <input
            type="text"
            onChange={handleChange}
            placeholder="Enter new password"
          />
          <input
            type="text"
            onChange={handleChangere}
            placeholder="Re-Enter new password"
          />
          <button type="submit">Submit</button>
        </form>
      </div>
      <ToastContainer />
    </div>
  );
};

export default ForgotPassword;
