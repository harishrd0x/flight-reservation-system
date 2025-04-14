
import { useState, useEffect } from "react";
import { useAuth } from "@/context/AuthContext";
import BookingCard from "@/components/bookings/BookingCard";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { toast } from "@/components/ui/use-toast";
import { Search, Calendar, FileText, Download } from "lucide-react";
import { generateBookingPDF } from "@/utils/pdfUtils";
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogFooter,
  DialogHeader,
  DialogTitle,
} from "@/components/ui/dialog";

/**
 * BACKEND INTEGRATION NOTE:
 * - This mock data will be replaced with API calls to Spring Boot backend
 * - Implement proper error handling for API calls
 * - Add loading states while fetching data
 */
const DEFAULT_BOOKINGS = [
  {
    id: 'BK-12345',
    flightId: 'CJ-1245',
    userId: 'user1',
    userName: 'John Doe',
    from: 'New York',
    fromCode: 'JFK',
    to: 'London',
    toCode: 'LHR',
    departureDate: '2025-06-15',
    departureTime: '09:30',
    arrivalDate: '2025-06-16',
    arrivalTime: '22:15',
    price: 430,
    status: 'confirmed' as const,
  },
  {
    id: 'BK-54321',
    flightId: 'CJ-2347',
    userId: 'user2',
    userName: 'Jane Smith',
    from: 'London',
    fromCode: 'LHR',
    to: 'Paris',
    toCode: 'CDG',
    departureDate: '2025-06-22',
    departureTime: '14:15',
    arrivalDate: '2025-06-22',
    arrivalTime: '16:30',
    price: 180,
    status: 'pending' as const,
  },
  {
    id: 'BK-98765',
    flightId: 'CJ-3782',
    userId: 'user1',
    userName: 'John Doe',
    from: 'Paris',
    fromCode: 'CDG',
    to: 'Dubai',
    toCode: 'DXB',
    departureDate: '2025-06-30',
    departureTime: '22:45',
    arrivalDate: '2025-07-01',
    arrivalTime: '07:20',
    price: 720,
    status: 'cancelled' as const,
  }
];

/**
 * BACKEND INTEGRATION NOTE:
 * - This interface should match the DTO from the Spring Boot backend
 * - Ensure consistent naming convention between frontend and backend
 * - Add validation rules matching backend validation
 */
interface BookingDetails {
  id: string;
  flightId: string;
  userId: string;
  userName: string;
  from: string;
  fromCode: string;
  to: string;
  toCode: string;
  departureDate: string;
  departureTime: string;
  arrivalDate: string;
  arrivalTime: string;
  price: number;
  status: 'confirmed' | 'pending' | 'cancelled';
  bookingDate?: string;
}

export default function BookingsPage() {
  const { user } = useAuth();
  const isAdmin = user?.userType === 'admin';
  
  const [searchTerm, setSearchTerm] = useState("");
  const [bookings, setBookings] = useState<BookingDetails[]>([]);
  const [filteredBookings, setFilteredBookings] = useState<BookingDetails[]>([]);
  const [isDetailsDialogOpen, setIsDetailsDialogOpen] = useState(false);
  const [selectedBooking, setSelectedBooking] = useState<BookingDetails | null>(null);

  /**
   * BACKEND INTEGRATION NOTE:
   * - Replace with API call to Spring Boot backend
   * - Use fetch or axios to make a GET request to /api/bookings
   * - Include JWT token in Authorization header
   * - Handle loading, error, and success states
   */
  useEffect(() => {
    try {
      // Get bookings from localStorage or use default if none
      const savedBookings = JSON.parse(localStorage.getItem('bookings') || '[]');
      
      if (savedBookings && savedBookings.length > 0) {
        console.log("Found bookings in localStorage:", savedBookings);
        
        if (isAdmin) {
          // Admin sees all bookings
          // Make sure we explicitly cast the status to the correct type
          const typedBookings = savedBookings.map((booking: any) => ({
            ...booking,
            status: booking.status as 'confirmed' | 'pending' | 'cancelled'
          }));
          setBookings(typedBookings);
        } else {
          // Users only see their own bookings
          const userBookings = savedBookings
            .filter((booking: any) => booking.userId === user?.email)
            .map((booking: any) => ({
              ...booking,
              status: booking.status as 'confirmed' | 'pending' | 'cancelled'
            }));
          
          if (userBookings.length > 0) {
            setBookings(userBookings);
          } else {
            // If a user has no bookings yet, show a filtered version of the default bookings
            // This is just for demo purposes
            setBookings(DEFAULT_BOOKINGS.filter(booking => 
              booking.userId === 'user1' // Using 'user1' as current user for demo
            ));
          }
        }
      } else {
        // No bookings found in localStorage, use default bookings
        if (!isAdmin) {
          setBookings(DEFAULT_BOOKINGS.filter(booking => 
            booking.userId === 'user1' // Using 'user1' as current user for demo
          ));
        } else {
          setBookings(DEFAULT_BOOKINGS);
        }
      }
    } catch (error) {
      console.error("Error loading bookings:", error);
      // Fallback to default bookings
      if (!isAdmin) {
        setBookings(DEFAULT_BOOKINGS.filter(booking => 
          booking.userId === 'user1'
        ));
      } else {
        setBookings(DEFAULT_BOOKINGS);
      }
    }
  }, [isAdmin, user?.email]);

  useEffect(() => {
    // Filter bookings based on search term
    const filtered = bookings.filter(
      (booking) =>
        booking.flightId.toLowerCase().includes(searchTerm.toLowerCase()) ||
        booking.from.toLowerCase().includes(searchTerm.toLowerCase()) ||
        booking.to.toLowerCase().includes(searchTerm.toLowerCase()) ||
        (isAdmin && booking.userName.toLowerCase().includes(searchTerm.toLowerCase()))
    );
    setFilteredBookings(filtered);
  }, [searchTerm, bookings, isAdmin]);

  /**
   * BACKEND INTEGRATION NOTE:
   * - Replace with API call to Spring Boot backend
   * - Use fetch or axios to make a PUT request to /api/bookings/{id}
   * - Include JWT token in Authorization header
   * - Handle loading, error, and success states
   * 
   * @param id Booking ID to cancel
   */
  const handleCancelBooking = (id: string) => {
    // Update booking status in localStorage
    try {
      const savedBookings = JSON.parse(localStorage.getItem('bookings') || '[]');
      const updatedBookings = savedBookings.map((booking: any) => 
        booking.id === id ? { ...booking, status: 'cancelled' } : booking
      );
      
      localStorage.setItem('bookings', JSON.stringify(updatedBookings));
      
      // Also update the state
      const newBookings = bookings.map(booking => 
        booking.id === id ? { ...booking, status: 'cancelled' } : booking
      );
      
      setBookings(newBookings);
      
      toast({
        title: "Booking Cancelled",
        description: `Booking ${id} has been cancelled successfully.`,
      });
    } catch (error) {
      console.error("Error cancelling booking:", error);
      toast({
        title: "Error",
        description: "Failed to cancel booking. Please try again.",
        variant: "destructive"
      });
    }
  };

  /**
   * BACKEND INTEGRATION NOTE:
   * - Replace with API call to Spring Boot backend if needed
   * - Use fetch or axios to make a GET request to /api/bookings/{id}
   * - Include JWT token in Authorization header
   * 
   * @param id Booking ID to view details
   */
  const handleViewDetails = (id: string) => {
    const booking = bookings.find(b => b.id === id);
    if (booking) {
      setSelectedBooking(booking);
      setIsDetailsDialogOpen(true);
    }
  };
  
  /**
   * BACKEND INTEGRATION NOTE:
   * - Could be enhanced to request PDF generation from backend
   * - Alternative: backend could provide PDF endpoint that returns PDF data
   * 
   * @param booking Booking details for PDF generation
   */
  const handleDownloadPDF = (booking: BookingDetails) => {
    generateBookingPDF(booking);
  };

  return (
    <div className="space-y-6">
      <div>
        <h1 className="text-3xl font-bold tracking-tight">Your Bookings</h1>
        <p className="text-gray-500">
          {isAdmin 
            ? 'Manage all customer bookings in the system.' 
            : 'View and manage your flight bookings.'}
        </p>
      </div>

      {/* 
        BACKEND INTEGRATION NOTE:
        - Search implementation should be connected to backend search API
        - Consider implementing server-side search for large datasets
        - Add debouncing for search input to reduce API calls
      */}
      <div className="flex flex-col md:flex-row gap-4">
        <div className="flex-1">
          <div className="relative">
            <Search className="absolute left-2 top-1/2 h-4 w-4 -translate-y-1/2 text-gray-500" />
            <Input
              placeholder={isAdmin ? "Search by destination, flight number, or customer name..." : "Search by destination or flight number..."}
              className="pl-8"
              value={searchTerm}
              onChange={(e) => setSearchTerm(e.target.value)}
            />
          </div>
        </div>
        <div className="flex gap-2">
          <div>
            <Input id="date" type="date" className="w-full" />
          </div>
          <Button variant="outline">
            <Calendar className="mr-2 h-4 w-4" />
            Filter
          </Button>
        </div>
      </div>

      {/* 
        BACKEND INTEGRATION NOTE:
        - This section displays booking data fetched from the API
        - Implement proper loading states while fetching data
        - Add error handling for failed API requests
      */}
      <div className="space-y-4">
        {filteredBookings.length > 0 ? (
          filteredBookings.map((booking) => (
            <BookingCard
              key={booking.id}
              onCancel={() => handleCancelBooking(booking.id)}
              onViewDetails={() => handleViewDetails(booking.id)}
              {...booking}
              // Add these props for the new buttons
              extraButtons={
                <div className="flex space-x-2">
                  <Button 
                    variant="outline" 
                    size="sm" 
                    onClick={(e) => {
                      e.stopPropagation();
                      handleViewDetails(booking.id);
                    }}
                  >
                    <FileText className="h-4 w-4 mr-2" />
                    Details
                  </Button>
                  <Button 
                    variant="outline" 
                    size="sm"
                    onClick={(e) => {
                      e.stopPropagation();
                      handleDownloadPDF(booking);
                    }}
                  >
                    <Download className="h-4 w-4 mr-2" />
                    PDF
                  </Button>
                </div>
              }
            />
          ))
        ) : (
          <div className="text-center py-12">
            <Calendar className="h-12 w-12 mx-auto text-gray-300" />
            <h3 className="mt-4 text-lg font-medium">No bookings found</h3>
            <p className="mt-1 text-gray-500">
              {searchTerm ? "Try adjusting your search terms" : "You haven't made any bookings yet"}
            </p>
          </div>
        )}
      </div>
      
      {/* 
        Booking Details Dialog
        
        BACKEND INTEGRATION NOTE:
        - This dialog displays detailed booking information
        - Data should come from API response
        - Consider adding loading state while fetching details
      */}
      <Dialog open={isDetailsDialogOpen} onOpenChange={setIsDetailsDialogOpen}>
        <DialogContent className="sm:max-w-[500px]">
          <DialogHeader>
            <DialogTitle>Booking Details</DialogTitle>
            <DialogDescription>
              View the details of your booking
            </DialogDescription>
          </DialogHeader>
          
          {selectedBooking && (
            <div className="space-y-4 py-4">
              <div className="flex justify-between items-center pb-3 border-b">
                <div>
                  <h3 className="font-bold text-lg text-airline-blue">{selectedBooking.flightId}</h3>
                  <p className="text-sm text-gray-500">Cloud Jet Airways</p>
                </div>
                <div className="px-3 py-1 rounded-full text-sm font-medium capitalize"
                     style={{
                       backgroundColor: selectedBooking.status === 'confirmed' ? 'rgba(16, 185, 129, 0.1)' : 
                                        selectedBooking.status === 'pending' ? 'rgba(245, 158, 11, 0.1)' : 
                                        'rgba(239, 68, 68, 0.1)',
                       color: selectedBooking.status === 'confirmed' ? 'rgb(16, 185, 129)' : 
                              selectedBooking.status === 'pending' ? 'rgb(245, 158, 11)' : 
                              'rgb(239, 68, 68)'
                     }}
                >
                  {selectedBooking.status}
                </div>
              </div>
              
              <div className="grid grid-cols-2 gap-4">
                <div>
                  <h4 className="text-sm font-medium text-gray-500">From</h4>
                  <p className="font-semibold">{selectedBooking.from} ({selectedBooking.fromCode})</p>
                </div>
                <div>
                  <h4 className="text-sm font-medium text-gray-500">To</h4>
                  <p className="font-semibold">{selectedBooking.to} ({selectedBooking.toCode})</p>
                </div>
                <div>
                  <h4 className="text-sm font-medium text-gray-500">Departure</h4>
                  <p className="font-semibold">{selectedBooking.departureDate}</p>
                  <p className="text-sm text-gray-500">{selectedBooking.departureTime}</p>
                </div>
                <div>
                  <h4 className="text-sm font-medium text-gray-500">Arrival</h4>
                  <p className="font-semibold">{selectedBooking.arrivalDate}</p>
                  <p className="text-sm text-gray-500">{selectedBooking.arrivalTime}</p>
                </div>
              </div>
              
              <div className="pt-3 border-t">
                <div className="flex justify-between items-center">
                  <h4 className="text-sm font-medium text-gray-500">Booking ID</h4>
                  <p className="font-semibold">{selectedBooking.id}</p>
                </div>
                {isAdmin && (
                  <div className="flex justify-between items-center mt-2">
                    <h4 className="text-sm font-medium text-gray-500">Customer</h4>
                    <p className="font-semibold">{selectedBooking.userName}</p>
                  </div>
                )}
                <div className="flex justify-between items-center mt-2">
                  <h4 className="text-sm font-medium text-gray-500">Booking Date</h4>
                  <p className="font-semibold">{selectedBooking.bookingDate || 'N/A'}</p>
                </div>
                <div className="flex justify-between items-center mt-2">
                  <h4 className="text-sm font-medium text-gray-500">Total Price</h4>
                  <p className="font-bold text-lg text-airline-blue">₹{(selectedBooking.price * 83).toFixed(2)}</p>
                </div>
              </div>
            </div>
          )}
          
          <DialogFooter>
            <Button 
              variant="outline" 
              onClick={() => setIsDetailsDialogOpen(false)}
            >
              Close
            </Button>
            <Button 
              onClick={() => selectedBooking && handleDownloadPDF(selectedBooking)}
              className="bg-airline-blue hover:bg-airline-navy"
            >
              <Download className="mr-2 h-4 w-4" />
              Download as PDF
            </Button>
          </DialogFooter>
        </DialogContent>
      </Dialog>
    </div>
  );
}
