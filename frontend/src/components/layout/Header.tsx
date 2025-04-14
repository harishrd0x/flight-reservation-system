
import { Link, useNavigate } from "react-router-dom";
import { useAuth } from "@/context/AuthContext";
import { Button } from "@/components/ui/button";
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuLabel,
  DropdownMenuSeparator,
  DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu";
import { Plane, Menu, User, LogOut, UserCircle } from "lucide-react";
import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar";

/**
 * Header component with navigation and user authentication status
 * 
 * BACKEND INTEGRATION NOTE:
 * - This component displays user information from JWT token
 * - Avatar and user display name come from profile API
 * - Authentication status is determined by presence of valid JWT token
 */
export default function Header() {
  const { user, userProfile, logout, isAuthenticated } = useAuth();
  const navigate = useNavigate();

  const handleHomeClick = (e) => {
    e.preventDefault();
    navigate('/');
  };

  /**
   * Generate avatar fallback from email or display name
   * 
   * BACKEND INTEGRATION NOTE:
   * - Consider fetching user avatar from backend API
   * - Implement proper image caching for avatars
   * - Handle loading states while fetching user data
   */
  const getAvatarFallback = () => {
    if (!userProfile) return 'U';
    
    if (userProfile.displayName) {
      return userProfile.displayName.split(' ').map(n => n[0]).join('').toUpperCase();
    }
    return userProfile.email.substring(0, 2).toUpperCase();
  };

  return (
    <header className="sticky top-0 z-50 w-full border-b bg-white">
      <div className="container flex items-center justify-between h-16 px-4 md:px-6">
        <a href="/" onClick={handleHomeClick} className="flex items-center gap-2">
          <Plane className="h-6 w-6 text-airline-blue" />
          <span className="font-bold text-xl text-airline-blue">Cloud Jet Services</span>
        </a>
        <nav className="hidden md:flex items-center gap-6">
          <a href="/" onClick={handleHomeClick} className="text-sm font-medium hover:text-airline-blue">
            Home
          </a>
          <Link to="/dashboard/flights" className="text-sm font-medium hover:text-airline-blue">
            Flights
          </Link>
          <Link to="/about" className="text-sm font-medium hover:text-airline-blue">
            About Us
          </Link>
          <Link to="/contact" className="text-sm font-medium hover:text-airline-blue">
            Contact
          </Link>
        </nav>
        <div className="flex items-center gap-4">
          {isAuthenticated ? (
            <DropdownMenu>
              <DropdownMenuTrigger asChild>
                <Button variant="ghost" className="relative h-10 w-10 rounded-full flex items-center justify-center p-0">
                  <Avatar className="h-9 w-9">
                    <AvatarImage src={userProfile?.avatarUrl} />
                    <AvatarFallback>{getAvatarFallback()}</AvatarFallback>
                  </Avatar>
                </Button>
              </DropdownMenuTrigger>
              <DropdownMenuContent align="end">
                <DropdownMenuLabel>
                  {userProfile?.displayName || user?.email} 
                  <span className="text-xs capitalize text-gray-500 ml-1">({user?.userType})</span>
                </DropdownMenuLabel>
                <DropdownMenuSeparator />
                <DropdownMenuItem asChild>
                  <Link to="/dashboard" className="w-full flex">Dashboard</Link>
                </DropdownMenuItem>
                <DropdownMenuItem asChild>
                  <Link to="/dashboard/profile" className="w-full flex">
                    <UserCircle className="mr-2 h-4 w-4" /> Profile
                  </Link>
                </DropdownMenuItem>
                <DropdownMenuItem onClick={() => {
                  logout();
                  navigate('/');
                }}>
                  <LogOut className="mr-2 h-4 w-4" /> Logout
                </DropdownMenuItem>
              </DropdownMenuContent>
            </DropdownMenu>
          ) : (
            <div className="flex items-center gap-4">
              <Link to="/login">
                <Button variant="ghost" className="text-airline-blue hover:text-airline-navy">
                  Login
                </Button>
              </Link>
              <Link to="/register">
                <Button className="bg-airline-blue hover:bg-airline-navy">
                  Register
                </Button>
              </Link>
            </div>
          )}
          <button className="md:hidden">
            <Menu className="h-6 w-6" />
          </button>
        </div>
      </div>
    </header>
  );
}
