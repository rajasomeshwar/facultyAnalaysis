import { createContext, useContext, useState } from "react";
import {
  createAccountApi,
  executeJwtBasicAuth,
} from "../ApiClinent/BasicAuthenicationSerivce";
import { apiClient } from "../ApiClinent/clientApi";
import {
  sendEmailVerification,
  ValidateVerficationCode,
  VerifyEmailByToken,
  SendToMailTokenForgetPasswordApi,
  updatePasswordByToken,
} from "../ApiClinent/BasicAuthenicationSerivce";

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
  async function login(username, password) {
    try {
      setError("");

      const response = await executeJwtBasicAuth(username, password);

      if (response.status == 200) {
        const token = "Bearer " + response.data.token;
        setToken(token);
        setAuthentication(true);
        setUsername(username);
        apiClient.interceptors.request.use((config) => {
          config.headers.Authorization = tokenr;
          return config;
        });

        return true;
      }

      setAuthentication(false);
      setError(response.response.data.message);
      return false;
    } catch (error) {
      setAuthentication(false);
      setError(error.response.data.message);
      return false;
    }
    return false;
  }
  function logout() {
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
  async function signup({ email, password }) {
    try {
      setError("");
      setSuccess("");
      const response = await createAccountApi(email, password);

      if (response.status == 200) {
        setSuccess("Account Created");
        setUserEmailToverifty(email);

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
