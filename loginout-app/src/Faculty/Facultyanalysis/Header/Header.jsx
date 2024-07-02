import React from 'react';
import { Link } from 'react-router-dom';
import { useAuth } from '../security/AuthContext';
import './Header.css'; // Import the CSS file for styling

function HeaderComponent() {
    const authContext = useAuth();
    const isAuthenticated = authContext.isAuthenticated;

    return (
        <div className="nav-holder">
            <nav className="nav-bar">
                <h4>Faculty Analysis</h4>
                <ul className="navitems">
                    {isAuthenticated && <li className="link"><Link to="/welcome">Home</Link></li>}
                    {isAuthenticated && <li className="link"><Link to="/profile">Profile</Link></li>}
                    {!isAuthenticated && <li className="link"><Link to="/login">Login</Link></li>}
                    {isAuthenticated && (
                        <li className="link">
                            <Link to="/logout" onClick={() => authContext.logout()}>Logout</Link>
                        </li>
                    )}
                </ul>
                {isAuthenticated && (
                    <div className="dropdown">
                        <img src="https://static.vecteezy.com/system/resources/thumbnails/002/318/271/small/user-profile-icon-free-vector.jpg" alt="icon" width="50" height="50" />
                    
                        <div className="dropdown-content">
                            <a href="#">{authContext.username}</a>
                        </div>
                    </div>
                )}
            </nav>
        </div>
    );
}

export default HeaderComponent;
