import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
import LogoutComponent from "./Logout";
import LoginComponent from "./login/Login";
import WelcomeComponent from "./Welcome";
import HeaderComponent from "./Header/Header";
import FotterComponent from "./Footer";
import VerificationCompoment from "./Verifications/verificationCompoment";
import OtpInput from "./login/Verify";
import TodoComponent from "./Profile";
import FacultyInformationForm from "./faculty/FacultyInformationForm";
import ForgetPasswordDataComp from "./Verifications/forgetPasswordDataComp";
import AuthProvider, { useAuth } from "./security/AuthContext";
import Profile from "./Profileforms/controller";
import ForgotPassword from "./Verifications/ForgotPassword";
import TeachingPerformanceTable from "./faculty/AcademicworkForm";
function AuthenticationRouter({ children }) {
  const auth = useAuth();

  if (auth.isTokenValid) return children;
  return <Navigate to="/" />;
}
export default function FacultyAnalysisHome() {
  return (
    <div className="TodoApp">
      <AuthProvider>
        <BrowserRouter>
          {/* <HeaderComponent/> */}
          <Routes>
            <Route path="/" element={<LoginComponent />}>
              {" "}
            </Route>
            <Route path="/login" element={<LoginComponent />}>
              {" "}
            </Route>
            <Route path="/table" element={<TeachingPerformanceTable />}>
              {" "}
            </Route>

            <Route path="/verify" element={<OtpInput />}>
              {" "}
            </Route>
            <Route
              path="/auth/verifyEmail/*"
              element={<VerificationCompoment />}
            >
              {" "}
            </Route>
            <Route
              path="/auth/forgetpassword/*"
              element={<ForgetPasswordDataComp />}
            >
              {" "}
            </Route>

            <Route path="/forgetpassword" element={<ForgotPassword />}>
              {" "}
            </Route>
            <Route
              path="/welcome"
              element={
                <>
                  <HeaderComponent />
                  <AuthenticationRouter>
                    <WelcomeComponent />
                  </AuthenticationRouter>
                </>
              }
            >
              {" "}
            </Route>
            <Route
              path="/tablesdata"
              element={
                <>
                  <HeaderComponent />
                  <AuthenticationRouter>
                    <WelcomeComponent />
                  </AuthenticationRouter>
                </>
              }
            >
              {" "}
            </Route>
            <Route
              path="/profile"
              element={
                <>
                  <HeaderComponent />
                  <AuthenticationRouter>
                    <Profile />
                  </AuthenticationRouter>
                </>
              }
            >
              {" "}
            </Route>
            <Route
              path="/logout"
              element={
                <>
                  <HeaderComponent /> <LogoutComponent />
                </>
              }
            >
              {" "}
            </Route>
          </Routes>
        </BrowserRouter>
      </AuthProvider>
      {/* <FotterComponent/> */}
    </div>
  );
}
