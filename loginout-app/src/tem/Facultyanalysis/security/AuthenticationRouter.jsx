import { Navigate } from 'react-router-dom';
import { useAuth } from './AuthContext';

export default function AuthenticationRouter({ children }) {
  const auth = useAuth();
  if (auth.isAuthenticated) {
    return children;
  } else {
    return <Navigate to="/" />;
  }
}
