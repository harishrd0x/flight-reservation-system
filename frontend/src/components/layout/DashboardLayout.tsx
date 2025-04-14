
import { Outlet, Navigate, useLocation } from "react-router-dom";
import { useAuth } from "@/context/AuthContext";
import { 
  Plane, 
  Calendar, 
  CreditCard, 
  MessageSquare,
  Users, 
  Building2, 
  PlusCircle
} from "lucide-react";
import Header from "./Header";
import Sidebar from "./Sidebar";

export default function DashboardLayout() {
  const { isAuthenticated, user } = useAuth();
  const location = useLocation();

  if (!isAuthenticated) {
    return <Navigate to="/login" state={{ from: location }} replace />;
  }

  const adminNavItems = [
    { 
      name: 'Flights Available', 
      path: '/dashboard/flights', 
      icon: <Plane className="h-5 w-5" /> 
    },
    { 
      name: 'View Bookings', 
      path: '/dashboard/bookings', 
      icon: <Calendar className="h-5 w-5" /> 
    },
    { 
      name: 'Wallets', 
      path: '/dashboard/wallets', 
      icon: <CreditCard className="h-5 w-5" /> 
    },
    { 
      name: 'Add Airplanes', 
      path: '/dashboard/add-airplane', 
      icon: <PlusCircle className="h-5 w-5" /> 
    },
    { 
      name: 'Add Flight', 
      path: '/dashboard/add-flight', 
      icon: <Plane className="h-5 w-5" /> 
    },
    { 
      name: 'Add Airport', 
      path: '/dashboard/add-airport', 
      icon: <Building2 className="h-5 w-5" /> 
    },
    { 
      name: 'View Reviews', 
      path: '/dashboard/reviews', 
      icon: <MessageSquare className="h-5 w-5" /> 
    }
  ];

  const customerNavItems = [
    { 
      name: 'Flights Available', 
      path: '/dashboard/flights', 
      icon: <Plane className="h-5 w-5" /> 
    },
    { 
      name: 'View Bookings', 
      path: '/dashboard/bookings', 
      icon: <Calendar className="h-5 w-5" /> 
    },
    { 
      name: 'Wallet', 
      path: '/dashboard/wallet', 
      icon: <CreditCard className="h-5 w-5" /> 
    },
    { 
      name: 'Submit Review', 
      path: '/dashboard/review', 
      icon: <MessageSquare className="h-5 w-5" /> 
    }
  ];

  const navItems = user?.userType === 'admin' ? adminNavItems : customerNavItems;

  return (
    <div className="flex min-h-screen flex-col">
      <Header />
      <div className="flex flex-1">
        <Sidebar items={navItems} />
        <div className="flex-1 p-6 md:p-8">
          <Outlet />
        </div>
      </div>
    </div>
  );
}
