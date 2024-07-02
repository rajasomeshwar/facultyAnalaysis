import axios from "axios";
import { useState } from "react";
import FacultyAnalysisHome from "./Facultyanalysis/Home";
export default function Temp() {
  const [name, setname] = useState("");
  const [password, setpassword] = useState("");
  async function submit() {
    // Add your submit logic here
    console.log("His ");
    const response = await axios.get(`http://localhost:8022/hi`);
    console.log(response);
    console.log(response.data);
    // setval(response.data.name);
  }
  async function submit1() {
    // Add your submit logic here
    console.log("His ");
    setname("king one ");
    setpassword("nothing");
    // ture or false; name;
    // call fun =>
    const response = await axios.post(`http://localhost:8022/update`, {
      name: name,
      password: password,
    });
    console.log(response);
    console.log(response.data);
    // setval(response.data.name);
  }

  return (
    <div>
      {/* HI <>{name} </>
      {password}
      <input type="button" onClick={submit} value="Api" />
      <input type="button" onClick={submit1} value="Api" /> */}
      <FacultyAnalysisHome />
    </div>
  );
}
