import { createContext, useContext, useEffect, useState } from "react";
import {
  createAccountApi,
  executeJwtBasicAuth,
} from "../ApiClinent/BasicAuthenicationSerivce";
import { apiClient } from "../ApiClinent/clientApi";
import axios from "axios";
import {
  sendEmailVerification,
  ValidateVerficationCode,
  VerifyEmailByToken,
  SendToMailTokenForgetPasswordApi,
  updatePasswordByToken,
  getFacultyInfoByTokenApi,
  submitFacultyInfoDataApi,
  getContributionToSchoolByTokenApi,
  AddContributionToSchoolByTokenApi,
  RemoveContributionToSchoolByTokenApi,
  AddAllContributionToSchoolByTokenApi,
  getContributionToDepByTokenApi,
  AddContributionToDepByTokenApi,
  RemoveContributionToDepTokenApi,
  AddAllContributionToDepByTokenApi,
} from "../ApiClinent/BasicAuthenicationSerivce";
import { json } from "react-router-dom";

export const AuthContext = createContext();
export const useAuth = () => useContext(AuthContext);
export default function AuthProvider({ children }) {
  const [username, setUsername] = useState(10);
  const [isAuthenticated, setAuthentication] = useState(false);
  const [tokenr, setToken] = useState();
  const [useremailToverifty, setUserEmailToverifty] = useState("");
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");
  const [verficationMessageSucces, setverficationMessageSucces] = useState("");
  const [verficationMessageError, setverficationMessageError] = useState("");
  const [verifyEmailMessageSuccess, setverifyEmailMessageSuccess] =
    useState("");
  const [sentMailMessage, setsentMailMessage] = useState("");
  const [sentMailMessageError, setsentMailMessageError] = useState("");
  const [verifyEmailMessageError, setverifyEmailMessageError] = useState("");
  const [updatePasswordTokenSuccess, setupdatePasswordTokenSuccess] =
    useState("");
  const [updatePasswordTokenError, setupdatePasswordTokenError] = useState("");
  const [facultyData1, setfacultyData] = useState({
    name: "",
    department: "",
    teachingexperience: "",
    industryexperience: "",
    joining_date: "",
    designation: "",
    total_experience: "",
  });
  // useEffect(async () => {
  //   const token = localStorage.getItem("jwtToken");
  //   console.log("refer " + token);
  //   if (!token) {
  //     setAuthentication(false);
  //     // Redirect to login page
  //   }
  //   setAuthentication(true);
  // }, []);
  function isTokenValid() {
    if (getToken()) return true;
    return false;
  }
  async function updatePasswordToken(token, password) {
    try {
      const response = await updatePasswordByToken(token, password);
      if (response.status == 200) {
        console.log(response.data);
        setupdatePasswordTokenSuccess(response.data);
        return true;
      } else {
        console.log(response);
        setupdatePasswordTokenError(response.data);
      }
    } catch (error) {
      console.log(error.response.data);
      setupdatePasswordTokenError(error.response.data.message);
    }
  }
  async function SentMailTokeToForgetPassword(email) {
    setsentMailMessageError("");
    setsentMailMessage("");
    // console.log(" here " + email);
    try {
      const response = await SendToMailTokenForgetPasswordApi(email);
      if (response.status == 200) {
        setsentMailMessage("Reset Link Sent to Mail !");
        return true;
      }
      //  console.log(" x " + response.data.message);
      setsentMailMessageError(response.response.data.message);
    } catch (error) {
      // console.log(error.response.data);
      setsentMailMessageError(error.response.data.message);
    }
    // console.log("next "); //+ response);
    return false;
  }

  async function UpdateFacultyInformationFormHandle(facultyData) {
    try {
      console.log(getToken());
      console.log("before the data is x ");

      //    console.log(localStorage.getItem("jwtToken"));
      // apiClient.interceptors.request.use(
      //   (config) => {
      //     console.log("Interceptors");
      //     const token = localStorage.getItem("jwtToken");
      //     if (token) {
      //       config.headers["Authorization"] = `${token}`;
      //     }
      //     return config;
      //   },
      //   (error) => {
      //     return Promise.reject(error);
      //   }
      // );

      //   axios.defaults.headers.common["Authorization"] = `${token}`;

      const response = await submitFacultyInfoDataApi(facultyData);

      console.log(response.data);
      return response.data;
    } catch (error) {
      console.log(error);

      // set error data can't down properly;
    }
  }

  // start dep
  async function RemoveContributionToDepByToken(data) {
    try {
      const response = await RemoveContributionToDepTokenApi(data);
      console.log(" x " + response);
      if (response.status == 200) {
        console.log(response.data + " n ");
        return response.data;
      }
    } catch (error) {
      console.log(error);
      return false;
      // set error data can't down properly;
    }
  }
  async function AddAllContributionToDepByToken(data) {
    try {
      const response = await AddAllContributionToDepByTokenApi(data);
      console.log(" x " + response);
      if (response.status == 200) {
        console.log(response.data + " n ");
        return response.data;
      }
    } catch (error) {
      console.log(error);
      return false;
      // set error data can't down properly;
    }
  }

  async function AddContributionToDepByToken(data) {
    try {
      const response = await AddContributionToDepByTokenApi(data);
      console.log(" x " + response);
      if (response.status == 200) {
        console.log(response.data + " n ");
        return response.data;
      }
    } catch (error) {
      console.log(error);
      return "x";
      // set error data can't down properly;
    }
  }
  async function getContributionToDepByToken() {
    try {
      const response = await getContributionToDepByTokenApi();
      console.log(" x " + response);
      if (response.status == 200) {
        console.log(response.data + " n ");
        return response.data;
      }
    } catch (error) {
      console.log(error);
      return "x";
      // set error data can't down properly;
    }
  }

  // end

  async function RemoveContributionToSchoolByToken(data) {
    try {
      const response = await RemoveContributionToSchoolByTokenApi(data);
      console.log(" x " + response);
      if (response.status == 200) {
        console.log(response.data + " n ");
        return response.data;
      }
    } catch (error) {
      console.log(error);
      return "x";
      // set error data can't down properly;
    }
  }
  async function AddAllContributionToSchoolByToken(data) {
    try {
      const response = await AddAllContributionToSchoolByTokenApi(data);
      console.log(" x " + response);
      if (response.status == 200) {
        console.log(response.data + " n ");
        return response.data;
      }
    } catch (error) {
      console.log(error);
      return "x";
      // set error data can't down properly;
    }
  }

  async function AddContributionToSchoolByToken(data) {
    try {
      const response = await AddContributionToSchoolByTokenApi(data);
      console.log(" x " + response);
      if (response.status == 200) {
        console.log(response.data + " n ");
        return response.data;
      }
    } catch (error) {
      console.log(error);
      return "x";
      // set error data can't down properly;
    }
  }
  async function getContributionToSchoolByToken() {
    try {
      const response = await getContributionToSchoolByTokenApi();
      console.log(" x " + response);
      if (response.status == 200) {
        console.log(response.data + " n ");
        return response.data;
      }
    } catch (error) {
      console.log(error);
      return "x";
      // set error data can't down properly;
    }
  }
  async function getFacultyInfoByToken() {
    try {
      // console.log(localStorage.getItem("jwtToken"));

      //   axios.defaults.headers.common["Authorization"] = `${token}`;
      const response = await getFacultyInfoByTokenApi();

      //  console.log(response.data);
      return response.data;
    } catch (error) {
      //  console.log(error);
      return false;
      // set error data can't down properly;
    }
  }
  async function verifyEmail(token) {
    setverifyEmailMessageSuccess("");
    setverifyEmailMessageError("");
    try {
      const response = await VerifyEmailByToken(token);
      console.log(response + " ereach");
      if (response.status == 200) {
        console.log(response.data);
        setverifyEmailMessageSuccess(response.data);
        return true;
      }
      setverifyEmailMessageError(response.data);
      return true;
    } catch (error) {
      console.log(error.response.data.message);
      setverifyEmailMessageError(error.response.data.message);
    }
    return false;
  }
  function getToken() {
    return localStorage.getItem("jwtToken");
  }
  async function login(email, password) {
    try {
      setError("");
      localStorage.removeItem("jwtToken");
      const response = await executeJwtBasicAuth(email, password);

      if (response.status == 200) {
        const token = "Bearer " + response.data.jwt;

        localStorage.setItem("jwtToken", token);
        setToken(token);
        console.log(token);
        setAuthentication(true);
        setUsername(username);

        return true;
      }

      setAuthentication(false);
      setError(response.response.data.message);
      return false;
    } catch (error) {
      setAuthentication(false);
      // console.log(error);
      if (error.response)
        if (error.response.data) setError(error.response.data.message);
      return false;
    }
    return false;
  }
  function logout() {
    localStorage.removeItem("jwtToken");
    setAuthentication(false);
  }
  // sent to verification
  async function sendVerification() {
    setverficationMessageSucces("");
    setverficationMessageError("");
    if (useremailToverifty !== "") {
      try {
        const response = await sendEmailVerification(useremailToverifty);
        if (response.status == 200) {
          setverficationMessageSucces(" Sent!");
          return;
        }
        console.log(response);
        setverficationMessageError(response.data.message);
      } catch (error) {
        setverficationMessageError(error.response.data.message);
      }
    }
  }
  async function validateCode(code) {
    setverficationMessageSucces("");
    setverficationMessageError("");
    if (useremailToverifty !== "") {
      try {
        const response = await ValidateVerficationCode(
          useremailToverifty,
          code
        );
        if (response.status == 200) {
          setverficationMessageSucces(" Verfication Done !");
          return true;
        }
        console.log(response);
        setverficationMessageError(response.response.data.message);
        return false;
      } catch (error) {
        console.log(error);
        setverficationMessageError(error.response.data.message);
        return false;
      }
    }
    return false;
  }
  async function signup(data) {
    try {
      setError("");
      setSuccess("");
      console.log(data);
      const response = await createAccountApi(data);

      if (response.status == 200) {
        setSuccess("Account Created");
        setUserEmailToverifty(data.name);

        return true;
      }

      setError(response.response.data.message);
      return false;
    } catch (error) {
      setError(error.response.data.message);
      return false;
    }
    return false;
  }

  return (
    <AuthContext.Provider
      value={{
        username,
        isAuthenticated,
        logout,
        setUsername,
        login,
        signup,
        sendVerification,
        validateCode,
        verifyEmail,
        SentMailTokeToForgetPassword,
        updatePasswordToken,
        getFacultyInfoByToken,
        UpdateFacultyInformationFormHandle,
        setfacultyData,
        getToken,
        isTokenValid,
        getContributionToSchoolByToken,
        AddContributionToSchoolByToken,
        RemoveContributionToSchoolByToken,
        AddAllContributionToSchoolByToken,
        getContributionToDepByToken,
        AddContributionToDepByToken,
        RemoveContributionToDepByToken,
        AddAllContributionToDepByToken,
        sentMailMessage,
        updatePasswordTokenSuccess,
        updatePasswordTokenError,
        verficationMessageSucces,
        tokenr,
        error,

        success,
        verifyEmailMessageSuccess,
        useremailToverifty,
        verifyEmailMessageError,
        sentMailMessageError,
        sentMailMessageError,
        verficationMessageError,
      }}
    >
      {children}
    </AuthContext.Provider>
  );
}
