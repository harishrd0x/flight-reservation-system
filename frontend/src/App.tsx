
import { Toaster } from "@/components/ui/toaster";
import { Toaster as Sonner } from "@/components/ui/sonner";
import { TooltipProvider } from "@/components/ui/tooltip";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import { AuthProvider } from "@/context/AuthContext";

// Pages
import LandingPage from "./pages/LandingPage";
import AboutUs from "./pages/AboutUs";
import Contact from "./pages/Contact";
import LoginForm from "./components/auth/LoginForm";
import RegisterForm from "./components/auth/RegisterForm";
import NotFound from "./pages/NotFound";
import DashboardLayout from "./components/layout/DashboardLayout";
import DashboardOverview from "./pages/Dashboard/Overview";
import FlightsPage from "./pages/Dashboard/Flights";
import BookingsPage from "./pages/Dashboard/Bookings";
import WalletsPage from "./pages/Dashboard/Wallets";
import WalletPage from "./pages/Dashboard/Wallet";
import AddAirplanePage from "./pages/Dashboard/AddAirplane";
import AddAirportPage from "./pages/Dashboard/AddAirport";
import ReviewsPage from "./pages/Dashboard/Reviews";
import ReviewPage from "./pages/Dashboard/Review";
import Index from "./pages/Index";
import AddFlightPage from "./pages/Dashboard/AddFlight";
import RoleGuard from "./components/auth/RoleGuard";
import Profile from "./pages/Dashboard/Profile";

/**
 * BACKEND INTEGRATION NOTE:
 * - Set up QueryClient with proper configuration for API calls
 * - Consider adding default headers for authentication
 * - Configure caching strategy appropriate for your application
 * - Add interceptors for handling token expiration and refresh
 */
const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      // Retry failed requests 1 time before displaying an error
      retry: 1,
      // Cache data for 5 minutes
      staleTime: 5 * 60 * 1000,
    },
  },
});

/**
 * Main application component with routing configuration
 * 
 * BACKEND INTEGRATION NOTE:
 * - Routes should align with backend API endpoints
 * - Consider implementing lazy loading for routes
 * - Ensure role-based access control matches Spring Security configuration
 * - Add global error handling for API requests
 */
const App = () => (
  <QueryClientProvider client={queryClient}>
    <AuthProvider>
      <TooltipProvider>
        <Toaster />
        <Sonner />
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<Index />} />
            <Route path="/about" element={<AboutUs />} />
            <Route path="/contact" element={<Contact />} />
            <Route path="/login" element={<LoginForm />} />
            <Route path="/register" element={<RegisterForm />} />
            
            {/* 
              Dashboard Routes
              
              BACKEND INTEGRATION NOTE:
              - These routes require authentication
              - They should align with protected backend endpoints
              - Role-based access control should match Spring Security configuration
            */}
            <Route path="/dashboard" element={<DashboardLayout />}>
              <Route index element={<DashboardOverview />} />
              <Route path="flights" element={<FlightsPage />} />
              <Route path="bookings" element={<BookingsPage />} />
              <Route path="profile" element={<Profile />} />
              
              {/* 
                Admin-only routes
                
                BACKEND INTEGRATION NOTE:
                - These routes require admin role
                - They should align with admin-only backend endpoints
                - Spring Security should have matching role-based authorization
              */}
              <Route element={<RoleGuard allowedRoles={['admin']} />}>
                <Route path="wallets" element={<WalletsPage />} />
                <Route path="add-airplane" element={<AddAirplanePage />} />
                <Route path="add-airport" element={<AddAirportPage />} />
                <Route path="add-flight" element={<AddFlightPage />} />
                <Route path="reviews" element={<ReviewsPage />} />
              </Route>
              
              {/* 
                Customer-only routes
                
                BACKEND INTEGRATION NOTE:
                - These routes require customer role
                - They should align with customer-only backend endpoints
                - Spring Security should have matching role-based authorization
              */}
              <Route element={<RoleGuard allowedRoles={['customer']} />}>
                <Route path="wallet" element={<WalletPage />} />
                <Route path="review" element={<ReviewPage />} />
              </Route>
            </Route>
            
            <Route path="*" element={<NotFound />} />
          </Routes>
        </BrowserRouter>
      </TooltipProvider>
    </AuthProvider>
  </QueryClientProvider>
);

export default App;
