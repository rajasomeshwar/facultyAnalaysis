import React, { useState } from 'react';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import './EmailVerification.css';

const OtpInput = () => {
  const [otp, setOtp] = useState('');

  const handleChange = (e) => {
    setOtp(e.target.value);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (otp.trim() === '') {
      toast.error('OTP cannot be empty!');
    } else {
      toast.success(`Entered OTP: ${otp}`);
    }
  };

  return (
    <div className="Email-verification-App">
      <div className="Email-form-container">
        <form onSubmit={handleSubmit}>
          <h1> OTP sent to your email</h1>
          <input
            type="text"
            value={otp}
            onChange={handleChange}
            placeholder="Enter OTP"
          />
          <div className='Email-btns-container'>
          <button type="submit">Submit</button>
          <button >Resend</button>
          </div>
        </form>
      </div>
      <ToastContainer />
    </div>
  );
};

export default OtpInput;
