
import { useAuth } from "@/context/AuthContext";

/**
 * Base API URL for Spring Boot backend
 * 
 * BACKEND INTEGRATION NOTE:
 * - Update this URL to match your Spring Boot backend URL
 * - For development, you might use http://localhost:8080/api
 * - For production, use your deployed backend URL
 */
const API_BASE_URL = 'http://localhost:8080/api';

/**
 * Custom hook for making authenticated API requests
 * 
 * BACKEND INTEGRATION NOTE:
 * - This hook provides a client for making API calls to Spring Boot backend
 * - It automatically adds JWT token to Authorization header
 * - Handles token refresh or redirects to login on authentication errors
 * 
 * @returns Object with API request methods
 */
export const useApiClient = () => {
  const { getToken, logout } = useAuth();

  /**
   * Makes a fetch request with authentication header
   * 
   * @param endpoint API endpoint path
   * @param options Fetch options
   * @returns Promise with response data
   */
  const fetchWithAuth = async (endpoint: string, options: RequestInit = {}) => {
    const token = getToken();
    
    const headers = {
      'Content-Type': 'application/json',
      ...(token ? { 'Authorization': `Bearer ${token}` } : {}),
      ...(options.headers || {})
    };

    try {
      const response = await fetch(`${API_BASE_URL}${endpoint}`, {
        ...options,
        headers
      });

      // Handle authentication errors
      if (response.status === 401) {
        logout();
        throw new Error('Session expired. Please login again.');
      }

      // Handle other errors
      if (!response.ok) {
        const errorData = await response.json().catch(() => ({}));
        throw new Error(errorData.message || 'API request failed');
      }

      // Return data if available, or empty object for 204 No Content
      if (response.status === 204) {
        return {};
      }

      return await response.json();
    } catch (error) {
      console.error('API request failed:', error);
      throw error;
    }
  };

  /**
   * HTTP GET request
   * 
   * @param endpoint API endpoint path
   * @param params URL query parameters
   * @returns Promise with response data
   */
  const get = (endpoint: string, params: Record<string, string> = {}) => {
    const url = new URL(`${API_BASE_URL}${endpoint}`);
    Object.keys(params).forEach(key => {
      url.searchParams.append(key, params[key]);
    });
    
    return fetchWithAuth(url.toString().replace(API_BASE_URL, ''), {
      method: 'GET'
    });
  };

  /**
   * HTTP POST request
   * 
   * @param endpoint API endpoint path
   * @param data Request body data
   * @returns Promise with response data
   */
  const post = (endpoint: string, data: any) => {
    return fetchWithAuth(endpoint, {
      method: 'POST',
      body: JSON.stringify(data)
    });
  };

  /**
   * HTTP PUT request
   * 
   * @param endpoint API endpoint path
   * @param data Request body data
   * @returns Promise with response data
   */
  const put = (endpoint: string, data: any) => {
    return fetchWithAuth(endpoint, {
      method: 'PUT',
      body: JSON.stringify(data)
    });
  };

  /**
   * HTTP DELETE request
   * 
   * @param endpoint API endpoint path
   * @returns Promise with response data
   */
  const del = (endpoint: string) => {
    return fetchWithAuth(endpoint, {
      method: 'DELETE'
    });
  };

  return {
    get,
    post,
    put,
    delete: del
  };
};

/**
 * Example usages:
 * 
 * // In a component:
 * const apiClient = useApiClient();
 * 
 * // GET request
 * const fetchFlights = async () => {
 *   try {
 *     const flights = await apiClient.get('/flights');
 *     console.log(flights);
 *   } catch (error) {
 *     console.error(error);
 *   }
 * };
 * 
 * // POST request
 * const createBooking = async (bookingData) => {
 *   try {
 *     const newBooking = await apiClient.post('/bookings', bookingData);
 *     console.log(newBooking);
 *   } catch (error) {
 *     console.error(error);
 *   }
 * };
 */
