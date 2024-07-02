// import AuthoProvider from "./Security/AuthoProvider";

import WelcomeCompoment from "./WelcomeCompoment";
import { LoginComponentDetails } from "./LoginComponentDetails";
import LogoutComponent from "./LogoutComponent";
import CreateAccountCompoment from "./CreateAccountCompoment";

import { BrowserRouter, Route, Routes } from "react-router-dom";
export default function LoginCompoment() {
  return (
    <div className="Login">
      {/* <AuthoProvider> */}
      <BrowserRouter>
        {/* <HeaderComponent /> */}
        <Routes>
          <Route path="/" element={<LoginComponentDetails />}></Route>
          <Route path="/login" element={<LoginComponentDetails />}></Route>
          <Route
            path="/logout"
            element={
              // it is used to allow when logined we called funtion then it will returns child
              // <AuthenticatedRoute>
              <LogoutComponent />
              // </AuthenticatedRoute>
            }
          ></Route>
          <Route
            path="/createAccount"
            element={
              // it is used to allow when logined we called funtion then it will returns child
              // <AuthenticatedRoute>
              <CreateAccountCompoment />
              // </AuthenticatedRoute>
            }
          ></Route>
          {/* below one used for requset param similar to pathvariable */}
          <Route
            path="/welcome/:username"
            element={
              // <AuthenticatedRoute>
              <WelcomeCompoment />
              // </AuthenticatedRoute>
            }
          ></Route>

          {/* <Route path="/*" element={<ErrorComponent />}></Route> */}
        </Routes>
        {/* <FooterComponent /> */}
      </BrowserRouter>
      {/* </AuthoProvider> */}
    </div>
  );
}
