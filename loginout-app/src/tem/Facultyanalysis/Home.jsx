import {BrowserRouter,Navigate,Route,Routes} from 'react-router-dom'
import LogoutComponent from "./Logout";
import LoginComponent from "./login/Login";
import WelcomeComponent from "./Welcome";
import HeaderComponent from "./Header/Header";
import AuthProvider, { useAuth } from './security/AuthContext';
import Profile from './Profileforms/controller';
import './Home.css';
function AuthenticationRouter({children})
{
    const auth=useAuth();

    if(auth.isAuthenticated)
    return children
   return <Navigate to="/"/>;
}
export default function FacultyAnalysisHome()
{   return (

        <div className="FacultyAnalysisApp">
            <AuthProvider>
            <BrowserRouter>
            {/* <HeaderComponent/> */}
            <Routes>
                <Route path='/' element={<LoginComponent/>}>  </Route>
                <Route path='/login' element={<LoginComponent/>}>  </Route>
                <Route path='/welcome' element={

                    <>
                    <HeaderComponent/> 
                    <AuthenticationRouter>

                    <WelcomeComponent/>
                    </AuthenticationRouter>
                    </>
                    }>  </Route>
                <Route path='/profile' element={
                    <>
                    <HeaderComponent/> 
                    <AuthenticationRouter>
                    <Profile/>
                    </AuthenticationRouter>
                    </>
                    }>  </Route>
                <Route path='/logout' element={<>
                <HeaderComponent/> <LogoutComponent/>
                </>
                    }>  </Route>
            </Routes>
            </BrowserRouter>
            </AuthProvider>
            {/* <FotterComponent/> */}
                </div>
    );
}