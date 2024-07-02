import React, { useState, useEffect } from "react";
import { ToastContainer, toast } from "react-toastify";
import { useAuth } from "../security/AuthContext";
import { useLocation, useNavigate } from "react-router-dom";
import "react-toastify/dist/ReactToastify.css";
import "./ForgotPassword.css";

const ForgotPassword = () => {
  const [password, setPassword] = useState("");
  const [cpassword, setCPassword] = useState("");
  const [email, setEmail] = useState("");
  const [isPasswordOn, setIsPasswordOn] = useState(false);
  const [loading, setLoading] = useState(false);
  const authContext = useAuth();
  const location = useLocation();
  const navigate = useNavigate();

  useEffect(() => {
    if (authContext.sentMailMessageError) {
      toast.error(authContext.sentMailMessageError);
    }
  }, [authContext.sentMailMessageError]);

  useEffect(() => {
    if (authContext.sentMailMessage) {
      toast.success(authContext.sentMailMessage);
    }
  }, [authContext.sentMailMessage]);

  const handleChange = (e) => {
    setPassword(e.target.value);
  };

  const handleChangeEmail = (e) => {
    setEmail(e.target.value);
  };

  const handleChangere = (e) => {
    setCPassword(e.target.value);
  };

  const handleSubmitVerify = async (e) => {
    e.preventDefault();
    setLoading(true);
    console.log("entered " + email);
    const val = await authContext.SentMailTokeToForgetPassword(email);
    if (val) {
      setIsPasswordOn(true);
    }
    setLoading(false);
    console.log("Back to email");
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    if (password.trim() === "") {
      toast.error("password cannot be empty!");
    } else if (password !== cpassword) {
      toast.error("both passwords should be same!");
    } else {
      await authContext.updatePassword(password); // Assuming there's an updatePassword function
      toast.success(`password changed!`);
      setIsPasswordOn(false);
    }
    setLoading(false);
  };

  return (
    <div className="ForgotPassword-App">
      {isPasswordOn ? (
        <div className="ForgotPassword-container">
          <form onSubmit={handleSubmit}>
            <h1>Reset Your Password</h1>
            <input
              type="password"
              onChange={handleChange}
              placeholder="Enter new password"
              value={password}
            />
            <input
              type="password"
              onChange={handleChangere}
              placeholder="Re-Enter new password"
              value={cpassword}
            />
            <button type="submit" disabled={loading}>
              {loading ? "Loading..." : "Submit"}
            </button>
          </form>
        </div>
      ) : (
        <div className="ForgotPassword-container">
          <form onSubmit={handleSubmitVerify}>
            <h1>Enter Email</h1>
            <input
              type="email"
              onChange={handleChangeEmail}
              placeholder="Enter Email"
              value={email}
            />
            <button type="submit" disabled={loading}>
              {loading ? "Loading..." : "Submit"}
            </button>
          </form>
        </div>
      )}
      <ToastContainer />
    </div>
  );
};

export default ForgotPassword;
