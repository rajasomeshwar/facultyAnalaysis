import { useLocation, useNavigate } from "react-router-dom";
import { useAuth } from "../security/AuthContext";
import { useEffect } from "react";
import { ToastContainer, toast } from "react-toastify";

export default function VerificationComponent() {
  const authContext = useAuth();
  const location = useLocation();
  const navigate = useNavigate();

  useEffect(() => {
    // Extract token from the query parameters
    const queryParams = new URLSearchParams(location.search);
    const token = queryParams.get("token");

    console.log("token " + token);

    // Call the verifyEmail function with the token
    const verifyEmail = async () => {
      if (token) {
        const value = await authContext.verifyEmail(token);
        if (value) {
          navigate(`/login`);
        }
      }
    };

    verifyEmail();
  }, [false]);

  useEffect(() => {
    if (authContext.verifyEmailMessageError) {
      toast.error(authContext.verifyEmailMessageError);
    }
  }, [authContext.verifyEmailMessageError]);

  return (
    <div>
      Welcome
      <ToastContainer />
    </div>
  );
}
