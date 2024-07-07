import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

import { useAuth } from "../security/AuthContext";
import { ToastContainer, toast } from "react-toastify";
import { useEffect } from "react";
import "./login.css";
import "react-toastify/dist/ReactToastify.css";

function LoginComponent() {
  const authContext = useAuth();
  const navigate = useNavigate();

  const [password, setPassword] = useState("");
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [mobileNumber, setMobileNumber] = useState("");
  const [department, setDepartment] = useState("");

  const [rightPanelActive, setRightPanelActive] = useState(false);

  const handleSignUpClick = () => {
    setRightPanelActive(true);
  };

  const handleSignInClick = () => {
    setRightPanelActive(false);
  };

  useEffect(() => {
    if (authContext.verifyEmailMessageSuccess) {
      toast.success(authContext.verifyEmailMessageSuccess);
    }
  }, [authContext.verifyEmailMessageSuccess]);

  useEffect(() => {
    if (authContext.error) {
      toast.error(authContext.error);
    }
  }, [authContext.error]);
  useEffect(() => {
    if (authContext.success) {
      toast.success(authContext.success);
    }
  }, [authContext.success]);
  useEffect(() => {
    if (authContext.updatePasswordTokenSuccess) {
      toast.success(authContext.updatePasswordTokenSuccess);
    }
  }, [authContext.updatePasswordTokenSuccess]);
  const handleFormSubmit = async (event) => {
    event.preventDefault();
    const operation = event.target.operation.value;
    if (operation === "signup") {
      const data = {
        username: email,
        password: password,
        mobilenumber: "" + mobileNumber,
        data: {
          id: 0,
          name: username,
          joining_date: null,
          department: "hi",
          designation: null,
          teachingexperience: 0,
          industryexperience: 0,
          total_experience: 0,
        },
      };
      console.log(data);
      const signupSuccess = await authContext.signup(data);

      if (signupSuccess) {
        //toast.success("Account Created");
        // actually if you code then use
        navigate(`/verify`);
        // if you want linked to verify use below
        navigate(`/login`);
      } else {
        //   toast.error("Username or email already exists");
      }
    } else if (operation === "login") {
      const loginSuccess = await authContext.login(email, password);
      //  console.log(loginSuccess);
      if (loginSuccess) {
        console.log("login");
        navigate(`/welcome`);
      } else {
        //    if (authContext.error) toast.error(authContext.error);
      }
    }
  };

  return (
    <div className="total-body">
      <div className="login-total">
        <div
          className={`login-container ${
            rightPanelActive ? "login-right-panel-active" : ""
          }`}
          id="main"
        >
          <div className="login-sign-up">
            <form id="signupForm" method="post" onSubmit={handleFormSubmit}>
              <h1>Create Account</h1>
              <input type="hidden" name="facultyid" />
              <input
                type="text"
                name="username"
                placeholder="Username"
                required
                value={username}
                onChange={(e) => setUsername(e.target.value)}
              />
              <input
                type="email"
                name="email"
                placeholder="Email"
                required
                value={email}
                onChange={(e) => setEmail(e.target.value)}
              />
              <input
                type="text"
                name="number"
                placeholder="Mobile Number"
                required
                value={mobileNumber}
                onChange={(e) => setMobileNumber(e.target.value)}
              />
              <input
                list="browsers"
                name="department"
                placeholder="Department"
                value={department}
                onChange={(e) => setDepartment(e.target.value)}
              />
              <datalist id="browsers">
                <option value="Information Technology" />
                <option value="Artificial Intelligence" />
                <option value="Data Science" />
                <option value="Computer Science Engineering" />
                <option value="Ece" />
              </datalist>
              <input
                type="password"
                name="password"
                placeholder="Password"
                required
                value={password}
                onChange={(e) => setPassword(e.target.value)}
              />
              <input type="password" placeholder="Confirm Password" required />
              <button type="submit">Sign-Up</button>
              <input type="hidden" name="operation" value="signup" />
            </form>
          </div>
          <div className="login-sign-in">
            <form id="signinForm" method="post" onSubmit={handleFormSubmit}>
              <h1>Sign In</h1>
              <input
                type="text"
                name="email"
                placeholder="Email"
                required
                value={email}
                onChange={(e) => setEmail(e.target.value)}
              />
              <input
                type="password"
                name="password"
                placeholder="Password"
                required
                value={password}
                onChange={(e) => setPassword(e.target.value)}
              />
              <input type="hidden" name="operation" value="login" />
              <button type="submit">Sign-In</button>
              <Link to="/forgetpassword">ForgetPassword?</Link>
            </form>
          </div>
          <div className="login-overlay-container">
            <div className="login-overlay">
              <div className="login-overlay-left">
                <h1>Already have an</h1>
                <h1> Account</h1>
                <button id="signIn" onClick={handleSignInClick}>
                  Sign In
                </button>
              </div>
              <div className="login-overlay-right">
                <h1>Create Account</h1>
                <button id="signUp" onClick={handleSignUpClick}>
                  Sign Up
                </button>
              </div>
            </div>
          </div>
        </div>
        <ToastContainer />
      </div>
    </div>
  );
}

export default LoginComponent;
