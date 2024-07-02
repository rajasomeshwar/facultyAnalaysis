import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { Link } from "react-router-dom";
export function LoginComponentDetails() {
  const [usergamil, setusergamil] = useState(" ");
  const [password, setpassword] = useState("");
  const [error, seterror] = useState("");
  const usenavi = useNavigate();
  console.log("Fil details ");

  async function login() {
    console.log("Completed login");

    await axios
      .post("http://localhost:8089/auth/login", {
        username: usergamil,
        password: password,
      })
      .then((e) => {
        console.log("x " + e);
        usenavi(`/welcome/${usergamil}`);
      })
      .catch((e) => {
        console.log(e);
        seterror(e.response.data.message);
      });
    console.log(usergamil + " " + password);

    return true;
  }
  function changepassword(e) {
    setpassword(e.target.value);
  }
  return (
    <>
      <div className="LoginDiv">
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
            onChange={changepassword}
            value={password}
          ></input>
        </div>
        <div className="ErrorDisplay">{error}</div>

        <div>
          <button type="button" onClick={login} name="loginButton">
            Login
          </button>
        </div>
        <div className="LinkCreate">
          <Link className="nav-link" to="/createAccount">
            create new account?
          </Link>
        </div>
        {/* <input type="button">Login</input> */}
      </div>
    </>
  );
}
export default function Noth() {
  console.log("her called wihout ");
}
