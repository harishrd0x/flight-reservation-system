
import { Navigate, Outlet, useLocation } from "react-router-dom";
import { useAuth } from "@/context/AuthContext";

/**
 * Props interface for RoleGuard component
 * 
 * BACKEND INTEGRATION NOTE:
 * - Make sure role names match exactly with Spring Security role names
 * - Spring typically uses format like 'ROLE_ADMIN', 'ROLE_CUSTOMER'
 * - You may need to map between frontend and backend role conventions
 */
interface RoleGuardProps {
  allowedRoles: ('admin' | 'customer')[];
}

/**
 * Component that protects routes based on user roles
 * 
 * BACKEND INTEGRATION NOTE:
 * - This component works with JWT token-based authentication
 * - It checks user roles stored in the token or in user state
 * - Ensure role checking logic aligns with Spring Security implementation
 * - Consider adding a loading state while verifying token with backend
 * 
 * @param allowedRoles Array of roles that can access the route
 * @returns Outlet component or Navigate component
 */
export default function RoleGuard({ allowedRoles }: RoleGuardProps) {
  const { user, isAuthenticated } = useAuth();
  const location = useLocation();

  if (!isAuthenticated) {
    // Redirect to login page if not authenticated
    return <Navigate to="/login" state={{ from: location }} replace />;
  }

  // Check if user has required role
  const hasRequiredRole = user?.userType && allowedRoles.includes(user.userType);
  
  if (!hasRequiredRole) {
    // Redirect to dashboard if authenticated but wrong role
    return <Navigate to="/dashboard" replace />;
  }

  // If authenticated and has required role, render the outlet
  return <Outlet />;
}
