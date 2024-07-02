import React, { useEffect, useState } from "react";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import "./Verify.css";
import { sendEmailVerification } from "../ApiClinent/BasicAuthenicationSerivce";
import { useAuth } from "../security/AuthContext";
import { useNavigate } from "react-router-dom";
const OtpInput = () => {
  const [otp, setOtp] = useState(1);
  const authContext = useAuth();
  const navigate = useNavigate();
  const [isLoading, setIsLoading] = useState(false);
  useEffect(() => {
    if (authContext.verficationMessageSucces) {
      toast.success(authContext.verficationMessageSucces);
    }
  }, [authContext.verficationMessageSucces]);
  useEffect(() => {
    if (authContext.verficationMessageError) {
      toast.error(authContext.verficationMessageError);
    }
  }, [authContext.verficationMessageError]);
  const handleChange = (e) => {
    setOtp(e.target.value);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (otp == 0) {
      toast.error("OTP cannot be empty!");
    } else {
      console.log("x dl f" + otp);
      const v = await authContext.validateCode(otp);
      if (v) {
        navigate(`/login`);
      }
    }
  };
  const handleResend = async (e) => {
    // send to mail;
    setIsLoading(true);

    await authContext.sendVerification();
    setIsLoading(false);
  };
  return (
    <div className="Verify-App">
      <div className="verify-form-container">
        <form onSubmit={handleSubmit}>
          <h1>Enter OTP sent to your email</h1>
          <input
            type="text"
            value={otp}
            onChange={handleChange}
            placeholder="Enter OTP"
          />
          <button type="submit">Submit</button>
        </form>
        {isLoading ? (
          <div className="loading-indicator">Loading...</div>
        ) : (
          <button onClick={handleResend} className="resend-button">
            Resend Code
          </button>
        )}
      </div>
      <ToastContainer />
    </div>
  );
};

export default OtpInput;
