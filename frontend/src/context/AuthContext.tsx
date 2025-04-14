
import React, { createContext, useState, useContext, ReactNode, useEffect } from 'react';
import { toast } from '@/components/ui/use-toast';
import { generateToken, validateToken } from '@/utils/jwtUtils';
import { UserProfile } from '@/types/user';

type UserType = 'admin' | 'customer' | null;

interface User {
  email: string;
  userType: UserType;
}

interface AuthContextType {
  user: User | null;
  userProfile: UserProfile | null;
  login: (email: string, password: string) => boolean;
  register: (email: string, password: string, userType: UserType) => boolean;
  logout: () => void;
  isAuthenticated: boolean;
  getToken: () => string | null;
  checkRole: (role: UserType) => boolean;
  updateUserProfile: (updatedProfile: Partial<UserProfile>) => void;
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

interface AuthProviderProps {
  children: ReactNode;
}

/**
 * Mock user database with persistent hardcoded users
 * 
 * BACKEND INTEGRATION NOTE:
 * - In production, replace this with API calls to your Spring Boot backend
 * - The backend should handle authentication and return a JWT token
 * - JWT token should contain user information including role (admin/customer)
 */
const defaultUsers = [
  { email: 'admin@gmail.com', password: 'admin123', userType: 'admin' as UserType },
  { email: 'user@123', password: 'user123', userType: 'customer' as UserType },
  { email: 'user@example.com', password: 'password', userType: 'customer' as UserType },
];

export const AuthProvider: React.FC<AuthProviderProps> = ({ children }) => {
  /**
   * BACKEND INTEGRATION NOTE:
   * - Replace localStorage with proper JWT token storage
   * - Consider using HttpOnly cookies for better security
   * - Implement token refresh mechanism for expired tokens
   * - Add token validation with your Spring Boot backend
   */
  const [users, setUsers] = useState(() => {
    // Get stored users from localStorage or use default users
    const storedUsers = localStorage.getItem('users');
    return storedUsers ? JSON.parse(storedUsers) : defaultUsers;
  });
  
  const [user, setUser] = useState<User | null>(() => {
    const token = localStorage.getItem('token');
    if (token) {
      const payload = validateToken(token);
      if (payload) {
        return { email: payload.email, userType: payload.userType };
      } else {
        // Token is invalid or expired
        localStorage.removeItem('token');
        return null;
      }
    }
    return null;
  });

  const [userProfile, setUserProfile] = useState<UserProfile | null>(() => {
    if (user) {
      const storedProfile = localStorage.getItem(`userProfile_${user.email}`);
      if (storedProfile) {
        return JSON.parse(storedProfile);
      }
      return {
        email: user.email,
        userType: user.userType,
      };
    }
    return null;
  });

  // Save users to localStorage whenever they change
  useEffect(() => {
    localStorage.setItem('users', JSON.stringify(users));
  }, [users]);

  // Save user profile to localStorage whenever it changes
  useEffect(() => {
    if (userProfile) {
      localStorage.setItem(`userProfile_${userProfile.email}`, JSON.stringify(userProfile));
    }
  }, [userProfile]);

  // Update userProfile when user changes
  useEffect(() => {
    if (user) {
      const storedProfile = localStorage.getItem(`userProfile_${user.email}`);
      if (storedProfile) {
        setUserProfile(JSON.parse(storedProfile));
      } else {
        setUserProfile({
          email: user.email,
          userType: user.userType,
        });
      }
    } else {
      setUserProfile(null);
    }
  }, [user]);

  /**
   * BACKEND INTEGRATION NOTE:
   * - Replace with actual API call to Spring Boot backend
   * - Use fetch or axios to make a POST request to /api/auth/login
   * - Send email and password in request body
   * - Handle JWT token received from backend
   * - Parse token payload for user information
   * - Store token securely for subsequent authenticated requests
   * 
   * @param email User's email address
   * @param password User's password
   * @returns boolean indicating success or failure
   */
  const login = (email: string, password: string): boolean => {
    console.log("Login attempt:", email, password);
    
    // Find user with case-insensitive email comparison
    const foundUser = users.find(
      (u) => u.email.toLowerCase() === email.toLowerCase() && u.password === password
    );

    console.log("Found user:", foundUser);

    if (foundUser) {
      const { password: _, ...userWithoutPassword } = foundUser;
      
      // Generate JWT token
      const token = generateToken(foundUser.email, foundUser.userType);
      localStorage.setItem('token', token);
      
      setUser(userWithoutPassword);
      
      toast({
        title: 'Login successful',
        description: `Welcome back, ${email}!`,
      });
      return true;
    } else {
      toast({
        title: 'Login failed',
        description: 'Invalid email or password.',
        variant: 'destructive',
      });
      return false;
    }
  };

  /**
   * BACKEND INTEGRATION NOTE:
   * - Replace with actual API call to Spring Boot backend
   * - Use fetch or axios to make a POST request to /api/auth/register
   * - Send user registration details in request body
   * - Handle response from backend
   * - Redirect to login or authenticate user after successful registration
   * 
   * @param email User's email address
   * @param password User's password
   * @param userType User's role type
   * @returns boolean indicating success or failure
   */
  const register = (email: string, password: string, userType: UserType): boolean => {
    const existingUser = users.find((u) => u.email.toLowerCase() === email.toLowerCase());
    
    if (existingUser) {
      toast({
        title: 'Registration failed',
        description: 'This email is already registered.',
        variant: 'destructive',
      });
      return false;
    }

    // Always register as customer
    const newUser = { email, password, userType: "customer" };
    const updatedUsers = [...users, newUser];
    setUsers(updatedUsers);
    
    toast({
      title: 'Registration successful',
      description: 'You can now log in with your credentials.',
    });
    return true;
  };

  /**
   * BACKEND INTEGRATION NOTE:
   * - Implement proper logout mechanism
   * - Clear JWT token from storage
   * - Make API call to backend to invalidate token if needed
   * - Reset user state in the application
   */
  const logout = () => {
    setUser(null);
    setUserProfile(null);
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    toast({
      title: 'Logged out',
      description: 'You have been logged out successfully.',
    });
  };
  
  /**
   * BACKEND INTEGRATION NOTE:
   * - Get JWT token from secure storage
   * - Add token verification with the backend
   * - Implement token refresh logic if needed
   * 
   * @returns JWT token string or null
   */
  const getToken = (): string | null => {
    return localStorage.getItem('token');
  };
  
  /**
   * BACKEND INTEGRATION NOTE:
   * - When integrated with Spring Security, roles should match backend roles
   * - Verify that roles in JWT match what Spring Security expects
   * - Ensure role naming convention is consistent between frontend and backend
   * 
   * @param role Role to check
   * @returns boolean indicating if user has the role
   */
  const checkRole = (role: UserType): boolean => {
    return user?.userType === role;
  };

  /**
   * BACKEND INTEGRATION NOTE:
   * - Replace with actual API call to Spring Boot backend
   * - Use fetch or axios to make a PUT request to /api/users/profile
   * - Send updated profile details in request body
   * - Include JWT token in Authorization header
   * - Handle response from backend
   * 
   * @param updatedProfile Updated profile information
   */
  const updateUserProfile = (updatedProfile: Partial<UserProfile>) => {
    if (userProfile) {
      const newProfile = { ...userProfile, ...updatedProfile };
      setUserProfile(newProfile);
      toast({
        title: 'Profile updated',
        description: 'Your profile has been updated successfully.',
      });
    }
  };

  return (
    <AuthContext.Provider
      value={{
        user,
        userProfile,
        login,
        register,
        logout,
        isAuthenticated: !!user,
        getToken,
        checkRole,
        updateUserProfile,
      }}
    >
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (context === undefined) {
    throw new Error('useAuth must be used within an AuthProvider');
  }
  return context;
};
