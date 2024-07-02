import { useNavigate} from "react-router-dom";
import { useAuth } from "./security/AuthContext";
function WelcomeComponent()
{
    const navigate=useNavigate();
    const authContext=useAuth ();
    
    console.log(authContext);
    return(
        <div className="WelcomeComponent">
            <h1>

            Welcome {authContext.username}
            </h1>
            Manage Your profile- <button onClick={() => {
                 navigate(`/profile`);
            }}> click here</button>
        </div>
    );
}
export default WelcomeComponent;