import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

export default function CreateAccountCompoment() {
  const [usergamil, setusergamil] = useState("");
  const [password, setpassword] = useState("");
  const [passwordagain, setpasswordagain] = useState("");
  const [verficationCode, setverficationCode] = useState(0);
  const [error, seterror] = useState("");
  const [message, setmessage] = useState("");
  const usenavi = useNavigate();
  const [verifyStage, setVerifyStage] = useState(false);
  async function handleLogin() {
    if (password != passwordagain) {
      seterror("Password Does not match !");
      return false;
    }
    await axios
      .post("http://localhost:8089/auth/register", {
        username: usergamil,
        password: password,
      })
      .then((e) => {
        console.log(e);
        setVerifyStage(true);
        seterror("");
        handleResend();
      })
      .catch((e) => {
        console.log(e);
        seterror(e.response.data.message);
      });
  }

  async function handleResend() {
    await axios
      .post("http://localhost:8089/auth/resend", {
        email: usergamil,
      })
      .then((e) => {
        console.log(e);
        setmessage("Sent!");
      })
      .catch((e) => {
        console.log(e);
        seterror("fails  to sent");
      });
    console.log("send ");
  }
  async function handleVerfication() {
    await axios
      .post("http://localhost:8089/auth/verify", {
        email: usergamil,
        code: verficationCode,
      })
      .then((e) => {
        console.log(e);
        if (e.data) usenavi("/login");
        else {
          seterror("Incorrect code");
        }
      })
      .catch((e) => {
        seterror("Interal Server Error!");
        console.log(e);
      });
  }
  return (
    <div className="CreateAccount">
      {!verifyStage && (
        <div>
          <h1>Let's Start create</h1>
          <div className="Form">
            <div className="userGmailDiv">
              <label>UserGmail : </label>
              <input
                type="text"
                name="gmail"
                onChange={(e) => setusergamil(e.target.value)}
                value={usergamil}
              ></input>
            </div>
            <div className="userPasswordDiv">
              <label>Password : </label>
              <input
                type="password"
                name="password"
                onChange={(e) => setpassword(e.target.value)}
                value={password}
              ></input>
            </div>
            <div className="userPasswordDiv">
              <label>ReEnter-Password : </label>
              <input
                type="password"
                name="password"
                onChange={(e) => setpasswordagain(e.target.value)}
                value={passwordagain}
              ></input>
            </div>
            <div className="ErrorCompoment">{error}</div>
            <div>
              <button type="button" onClick={handleLogin} name="loginButton">
                Login
              </button>
            </div>
          </div>
        </div>
      )}
      {verifyStage && (
        <div>
          <h1>Verify Your Account</h1>
          <div className="Form">
            <div className="userGmailDiv">
              <div className="Message">{message}</div>
              <p>WE Sent to Verfication code to {usergamil}</p>
            </div>
            <div className="verificatioin">
              <label>code : </label>
              <input
                type="number"
                name="password"
                max="99999"
                min="10000"
                onChange={(e) => setverficationCode(e.target.value)}
                value={verficationCode}
              ></input>
            </div>
            <div className="ErrorCompoment">{error}</div>
            <div className="ResendVerficatio">
              <button type="button" onClick={handleResend} name="ResendButton">
                Resend
              </button>
            </div>
            <div>
              <button
                type="button"
                onClick={handleVerfication}
                name="loginButton"
              >
                Verify
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}
