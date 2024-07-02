import React, { useState, useEffect } from "react";
import { ToastContainer, toast } from "react-toastify";
import { useAuth } from "../security/AuthContext";
import { useLocation, useNavigate } from "react-router-dom";
import "react-toastify/dist/ReactToastify.css";
import "./ForgotPassword.css";

const ForgetPasswordDataComp = () => {
  const [password, setPassword] = useState("");
  const [cpassword, setCPassword] = useState("");
  const [isPasswordOn, setIsPasswordOn] = useState(false);
  const [loading, setLoading] = useState(false);
  const authContext = useAuth();
  const location = useLocation();
  const navigate = useNavigate();
  const queryParams = new URLSearchParams(location.search);
  const token = queryParams.get("token");
  useEffect(() => {
    if (authContext.updatePasswordTokenError) {
      toast.error(authContext.updatePasswordTokenError);
    }
  }, [authContext.updatePasswordTokenError]);
  const handleChange = (e) => {
    setPassword(e.target.value);
  };

  const handleChangere = (e) => {
    setCPassword(e.target.value);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    if (password.trim() === "") {
      toast.error("password cannot be empty!");
    } else if (password !== cpassword) {
      toast.error("both passwords should be same!");
    } else {
      const v = await authContext.updatePasswordToken(token, password); // Assuming there's an updatePassword function
      //toast.success(`password changed!`);
      if (v) {
        navigate(`/login`);
      }
    }
    setLoading(false);
  };

  return (
    <>
      <div className="ForgotPassword-App">
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
      </div>
      <>
        <ToastContainer />
      </>
    </>
  );
};

export default ForgetPasswordDataComp;
