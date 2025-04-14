
/**
 * JWT utility functions
 * 
 * BACKEND INTEGRATION NOTE:
 * - In production, replace this with proper JWT implementation
 * - The backend will generate and validate tokens using a secure key
 * - Front-end will only need to store and use the token for API calls
 */

interface JwtPayload {
  email: string;
  userType: 'admin' | 'customer' | null;
  exp: number;
}

/**
 * BACKEND INTEGRATION NOTE:
 * - In production, tokens are generated by the Spring Boot backend
 * - This function will be removed in production
 * - Use the token received from the backend instead
 * - Spring Security handles token generation with proper encryption
 * 
 * @param email User's email address 
 * @param userType User's role type
 * @returns JWT token string
 */
export const generateToken = (email: string, userType: 'admin' | 'customer' | null): string => {
  // In a real app, we would use a library like jsonwebtoken
  // For now, we'll create a simple implementation
  const payload: JwtPayload = {
    email,
    userType,
    exp: Math.floor(Date.now() / 1000) + (24 * 60 * 60) // 24 hours
  };
  
  return btoa(JSON.stringify(payload));
};

/**
 * BACKEND INTEGRATION NOTE:
 * - In production, token validation is primarily done by the backend
 * - Frontend may need to decode token to get user information
 * - Use a proper JWT library for secure token handling
 * - Consider libraries like jwt-decode for client-side reading
 * 
 * @param token JWT token string
 * @returns Decoded token payload or null if invalid
 */
export const validateToken = (token: string): JwtPayload | null => {
  try {
    const payload = JSON.parse(atob(token)) as JwtPayload;
    
    // Check if token is expired
    if (payload.exp < Math.floor(Date.now() / 1000)) {
      return null;
    }
    
    return payload;
  } catch (error) {
    console.error('Invalid token:', error);
    return null;
  }
};
