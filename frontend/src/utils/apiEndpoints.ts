
/**
 * API Endpoints Documentation for Spring Boot Backend
 * 
 * This file documents the expected API endpoints from the Spring Boot backend
 * for integration with the frontend application.
 * 
 * BACKEND INTEGRATION NOTE:
 * - Ensure Spring Boot controllers map to these endpoints
 * - Match request/response formats between frontend and backend
 * - Implement proper error handling and status codes
 */

/**
 * Authentication Endpoints
 * 
 * Base path: /api/auth
 */
export const AUTH_ENDPOINTS = {
  /**
   * Login endpoint
   * 
   * Method: POST
   * Payload: { email: string, password: string }
   * Response: { token: string, user: { email: string, userType: string, ... } }
   */
  LOGIN: '/auth/login',
  
  /**
   * Register endpoint
   * 
   * Method: POST
   * Payload: { email: string, password: string, ... }
   * Response: { success: boolean, message: string }
   */
  REGISTER: '/auth/register',
  
  /**
   * Logout endpoint
   * 
   * Method: POST
   * Headers: Authorization: Bearer <token>
   * Response: { success: boolean }
   */
  LOGOUT: '/auth/logout',
  
  /**
   * Refresh token endpoint
   * 
   * Method: POST
   * Headers: Authorization: Bearer <refresh_token>
   * Response: { token: string }
   */
  REFRESH_TOKEN: '/auth/refresh-token',
};

/**
 * User Endpoints
 * 
 * Base path: /api/users
 */
export const USER_ENDPOINTS = {
  /**
   * Get user profile
   * 
   * Method: GET
   * Headers: Authorization: Bearer <token>
   * Response: { email: string, userType: string, displayName: string, ... }
   */
  PROFILE: '/users/profile',
  
  /**
   * Update user profile
   * 
   * Method: PUT
   * Headers: Authorization: Bearer <token>
   * Payload: { displayName: string, phoneNumber: string, ... }
   * Response: { success: boolean, user: { ... } }
   */
  UPDATE_PROFILE: '/users/profile',
  
  /**
   * Upload user avatar
   * 
   * Method: POST
   * Headers: Authorization: Bearer <token>
   * Body: FormData with 'avatar' file
   * Response: { success: boolean, avatarUrl: string }
   */
  UPLOAD_AVATAR: '/users/avatar',
};

/**
 * Flights Endpoints
 * 
 * Base path: /api/flights
 */
export const FLIGHT_ENDPOINTS = {
  /**
   * Get all flights
   * 
   * Method: GET
   * Optional Query Params: { from: string, to: string, date: string }
   * Response: [ { id: string, from: string, to: string, ... }, ... ]
   */
  ALL: '/flights',
  
  /**
   * Get flight by ID
   * 
   * Method: GET
   * Response: { id: string, from: string, to: string, ... }
   */
  BY_ID: (id: string) => `/flights/${id}`,
  
  /**
   * Create new flight (Admin only)
   * 
   * Method: POST
   * Headers: Authorization: Bearer <token>
   * Payload: { from: string, to: string, departureDate: string, ... }
   * Response: { id: string, from: string, to: string, ... }
   */
  CREATE: '/flights',
  
  /**
   * Update flight (Admin only)
   * 
   * Method: PUT
   * Headers: Authorization: Bearer <token>
   * Payload: { from: string, to: string, departureDate: string, ... }
   * Response: { id: string, from: string, to: string, ... }
   */
  UPDATE: (id: string) => `/flights/${id}`,
  
  /**
   * Delete flight (Admin only)
   * 
   * Method: DELETE
   * Headers: Authorization: Bearer <token>
   * Response: { success: boolean }
   */
  DELETE: (id: string) => `/flights/${id}`,
};

/**
 * Bookings Endpoints
 * 
 * Base path: /api/bookings
 */
export const BOOKING_ENDPOINTS = {
  /**
   * Get all bookings (Admin gets all, User gets own)
   * 
   * Method: GET
   * Headers: Authorization: Bearer <token>
   * Response: [ { id: string, flightId: string, userId: string, ... }, ... ]
   */
  ALL: '/bookings',
  
  /**
   * Get booking by ID
   * 
   * Method: GET
   * Headers: Authorization: Bearer <token>
   * Response: { id: string, flightId: string, userId: string, ... }
   */
  BY_ID: (id: string) => `/bookings/${id}`,
  
  /**
   * Create new booking
   * 
   * Method: POST
   * Headers: Authorization: Bearer <token>
   * Payload: { flightId: string, ... }
   * Response: { id: string, flightId: string, userId: string, ... }
   */
  CREATE: '/bookings',
  
  /**
   * Cancel booking
   * 
   * Method: PUT
   * Headers: Authorization: Bearer <token>
   * Response: { success: boolean }
   */
  CANCEL: (id: string) => `/bookings/${id}/cancel`,
  
  /**
   * Generate booking PDF
   * 
   * Method: GET
   * Headers: Authorization: Bearer <token>
   * Response: PDF file (application/pdf)
   */
  GENERATE_PDF: (id: string) => `/bookings/${id}/pdf`,
};

/**
 * Wallet Endpoints
 * 
 * Base path: /api/wallets
 */
export const WALLET_ENDPOINTS = {
  /**
   * Get user wallet
   * 
   * Method: GET
   * Headers: Authorization: Bearer <token>
   * Response: { id: string, balance: number, userId: string, ... }
   */
  USER_WALLET: '/wallets/me',
  
  /**
   * Get all wallets (Admin only)
   * 
   * Method: GET
   * Headers: Authorization: Bearer <token>
   * Response: [ { id: string, balance: number, userId: string, ... }, ... ]
   */
  ALL: '/wallets',
  
  /**
   * Add money to wallet
   * 
   * Method: POST
   * Headers: Authorization: Bearer <token>
   * Payload: { amount: number }
   * Response: { id: string, balance: number, userId: string, ... }
   */
  ADD_MONEY: '/wallets/add',
  
  /**
   * Get wallet transactions
   * 
   * Method: GET
   * Headers: Authorization: Bearer <token>
   * Response: [ { id: string, amount: number, type: string, ... }, ... ]
   */
  TRANSACTIONS: '/wallets/transactions',
};

/**
 * Reviews Endpoints
 * 
 * Base path: /api/reviews
 */
export const REVIEW_ENDPOINTS = {
  /**
   * Get all reviews (Admin gets all, User gets own)
   * 
   * Method: GET
   * Headers: Authorization: Bearer <token>
   * Response: [ { id: string, rating: number, comment: string, ... }, ... ]
   */
  ALL: '/reviews',
  
  /**
   * Get review by ID
   * 
   * Method: GET
   * Headers: Authorization: Bearer <token>
   * Response: { id: string, rating: number, comment: string, ... }
   */
  BY_ID: (id: string) => `/reviews/${id}`,
  
  /**
   * Create new review
   * 
   * Method: POST
   * Headers: Authorization: Bearer <token>
   * Payload: { rating: number, comment: string, ... }
   * Response: { id: string, rating: number, comment: string, ... }
   */
  CREATE: '/reviews',
  
  /**
   * Update review
   * 
   * Method: PUT
   * Headers: Authorization: Bearer <token>
   * Payload: { rating: number, comment: string, ... }
   * Response: { id: string, rating: number, comment: string, ... }
   */
  UPDATE: (id: string) => `/reviews/${id}`,
  
  /**
   * Delete review
   * 
   * Method: DELETE
   * Headers: Authorization: Bearer <token>
   * Response: { success: boolean }
   */
  DELETE: (id: string) => `/reviews/${id}`,
};

/**
 * Airports Endpoints
 * 
 * Base path: /api/airports
 */
export const AIRPORT_ENDPOINTS = {
  /**
   * Get all airports
   * 
   * Method: GET
   * Response: [ { id: string, name: string, code: string, ... }, ... ]
   */
  ALL: '/airports',
  
  /**
   * Create new airport (Admin only)
   * 
   * Method: POST
   * Headers: Authorization: Bearer <token>
   * Payload: { name: string, code: string, city: string, ... }
   * Response: { id: string, name: string, code: string, ... }
   */
  CREATE: '/airports',
};

/**
 * Airplanes Endpoints
 * 
 * Base path: /api/airplanes
 */
export const AIRPLANE_ENDPOINTS = {
  /**
   * Get all airplanes
   * 
   * Method: GET
   * Headers: Authorization: Bearer <token>
   * Response: [ { id: string, model: string, capacity: number, ... }, ... ]
   */
  ALL: '/airplanes',
  
  /**
   * Create new airplane (Admin only)
   * 
   * Method: POST
   * Headers: Authorization: Bearer <token>
   * Payload: { model: string, capacity: number, ... }
   * Response: { id: string, model: string, capacity: number, ... }
   */
  CREATE: '/airplanes',
};
