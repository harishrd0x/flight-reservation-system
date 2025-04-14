
import { useState, useEffect } from "react";
import { useAuth } from "@/context/AuthContext";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Card, CardContent } from "@/components/ui/card";
import { Plane, Search, Calendar, MapPin, AlertCircle } from "lucide-react";
import { toast } from "@/components/ui/use-toast";
import { useNavigate } from "react-router-dom";
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogFooter,
  DialogHeader,
  DialogTitle,
} from "@/components/ui/dialog";

const MOCK_FLIGHTS = [
  {
    id: 'CJ-1245',
    from: 'New York',
    fromCode: 'JFK',
    to: 'London',
    toCode: 'LHR',
    departureDate: '2025-06-15',
    departureTime: '09:30',
    arrivalDate: '2025-06-16',
    arrivalTime: '22:15',
    price: 430,
    seatsAvailable: 42,
  },
  {
    id: 'CJ-2347',
    from: 'London',
    fromCode: 'LHR',
    to: 'Paris',
    toCode: 'CDG',
    departureDate: '2025-06-22',
    departureTime: '14:15',
    arrivalDate: '2025-06-22',
    arrivalTime: '16:30',
    price: 180,
    seatsAvailable: 28,
  },
  {
    id: 'CJ-3782',
    from: 'Paris',
    fromCode: 'CDG',
    to: 'Dubai',
    toCode: 'DXB',
    departureDate: '2025-06-30',
    departureTime: '22:45',
    arrivalDate: '2025-07-01',
    arrivalTime: '07:20',
    price: 720,
    seatsAvailable: 15,
  },
  {
    id: 'CJ-9012',
    from: 'Tokyo',
    fromCode: 'HND',
    to: 'Sydney',
    toCode: 'SYD',
    departureDate: '2025-07-15',
    departureTime: '00:30',
    arrivalDate: '2025-07-16',
    arrivalTime: '10:45',
    price: 850,
    seatsAvailable: 5,
  },
];

interface FlightData {
  id: string;
  from: string;
  fromCode: string;
  to: string;
  toCode: string;
  departureDate: string;
  departureTime: string;
  arrivalDate: string;
  arrivalTime: string;
  price: number;
  seatsAvailable: number;
}

export default function FlightsPage() {
  const { user } = useAuth();
  const navigate = useNavigate();
  const isAdmin = user?.userType === 'admin';
  const [searchTerm, setSearchTerm] = useState("");
  const [flights, setFlights] = useState<FlightData[]>([]);
  const [isDeleteDialogOpen, setIsDeleteDialogOpen] = useState(false);
  const [flightToDelete, setFlightToDelete] = useState<string | null>(null);
  const [editingFlight, setEditingFlight] = useState<FlightData | null>(null);
  const [isEditDialogOpen, setIsEditDialogOpen] = useState(false);

  // Load flights from localStorage on component mount
  useEffect(() => {
    try {
      const savedFlights = JSON.parse(localStorage.getItem('flights') || '[]');
      
      if (savedFlights && savedFlights.length > 0) {
        console.log("Found flights in localStorage:", savedFlights);
        setFlights(savedFlights);
      } else {
        // No flights found in localStorage, use mock flights and save them
        localStorage.setItem('flights', JSON.stringify(MOCK_FLIGHTS));
        setFlights(MOCK_FLIGHTS);
      }
    } catch (error) {
      console.error("Error loading flights:", error);
      // Fallback to mock flights
      setFlights(MOCK_FLIGHTS);
    }
  }, []);

  const filteredFlights = flights.filter(
    (flight) =>
      flight.from.toLowerCase().includes(searchTerm.toLowerCase()) ||
      flight.to.toLowerCase().includes(searchTerm.toLowerCase()) ||
      flight.id.toLowerCase().includes(searchTerm.toLowerCase())
  );

  const handleBookNow = (flight: FlightData) => {
    // Check if user is authenticated
    if (!user) {
      toast({
        title: "Authentication Required",
        description: "You must be logged in to book a flight.",
        variant: "destructive"
      });
      navigate('/login');
      return;
    }
    
    // Save the booking to local storage
    try {
      const existingBookings = JSON.parse(localStorage.getItem('bookings') || '[]');
      
      const newBooking = {
        id: `BK-${Math.floor(Math.random() * 100000)}`,
        flightId: flight.id,
        userId: user?.email || 'anonymous',
        userName: user?.email?.split('@')[0] || 'Guest User',
        from: flight.from,
        fromCode: flight.fromCode,
        to: flight.to,
        toCode: flight.toCode,
        departureDate: flight.departureDate,
        departureTime: flight.departureTime,
        arrivalDate: flight.arrivalDate,
        arrivalTime: flight.arrivalTime,
        price: flight.price,
        status: 'confirmed',
        bookingDate: new Date().toISOString().split('T')[0]
      };
      
      localStorage.setItem('bookings', JSON.stringify([...existingBookings, newBooking]));
      
      // Update seat availability
      const updatedFlights = flights.map(f => {
        if (f.id === flight.id) {
          return { ...f, seatsAvailable: f.seatsAvailable - 1 };
        }
        return f;
      });
      
      setFlights(updatedFlights);
      localStorage.setItem('flights', JSON.stringify(updatedFlights));
      
      toast({
        title: "Flight Booked Successfully!",
        description: `Your booking for flight ${flight.id} from ${flight.from} to ${flight.to} has been confirmed.`,
      });
      
      // Navigate to bookings page
      navigate('/dashboard/bookings');
    } catch (error) {
      console.error("Error booking flight:", error);
      toast({
        title: "Booking Failed",
        description: "There was an error processing your booking. Please try again.",
        variant: "destructive"
      });
    }
  };

  const handleEdit = (flight: FlightData) => {
    setEditingFlight({ ...flight });
    setIsEditDialogOpen(true);
  };

  const handleEditChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    
    if (editingFlight) {
      setEditingFlight(prev => ({
        ...prev,
        [name]: name === 'price' || name === 'seatsAvailable' ? 
                parseFloat(value) || 0 : value
      }));
    }
  };

  const saveEditedFlight = () => {
    if (!editingFlight) return;
    
    try {
      const updatedFlights = flights.map(flight => 
        flight.id === editingFlight.id ? editingFlight : flight
      );
      
      setFlights(updatedFlights);
      localStorage.setItem('flights', JSON.stringify(updatedFlights));
      
      toast({
        title: "Flight Updated",
        description: `Flight ${editingFlight.id} has been updated successfully.`,
      });
      
      setIsEditDialogOpen(false);
      setEditingFlight(null);
    } catch (error) {
      console.error("Error updating flight:", error);
      toast({
        title: "Update Failed",
        description: "There was an error updating the flight. Please try again.",
        variant: "destructive"
      });
    }
  };

  const openDeleteDialog = (flightId: string) => {
    setFlightToDelete(flightId);
    setIsDeleteDialogOpen(true);
  };

  const handleDelete = () => {
    if (!flightToDelete) return;
    
    try {
      const updatedFlights = flights.filter(flight => flight.id !== flightToDelete);
      
      setFlights(updatedFlights);
      localStorage.setItem('flights', JSON.stringify(updatedFlights));
      
      toast({
        title: "Flight Deleted",
        description: `Flight ${flightToDelete} has been deleted successfully.`,
      });
      
      setIsDeleteDialogOpen(false);
      setFlightToDelete(null);
    } catch (error) {
      console.error("Error deleting flight:", error);
      toast({
        title: "Delete Failed",
        description: "There was an error deleting the flight. Please try again.",
        variant: "destructive"
      });
    }
  };

  return (
    <div className="space-y-6">
      <div className="flex justify-between">
        <div className="space-y-2">
          <h1 className="text-3xl font-bold tracking-tight">Available Flights</h1>
          <p className="text-gray-500">
            {isAdmin
              ? 'Manage all flights in the system.'
              : 'Browse and book available flights.'}
          </p>
        </div>
        {isAdmin && (
          <Button 
            className="bg-airline-blue hover:bg-airline-navy"
            onClick={() => navigate('/dashboard/add-flight')}
          >
            <Plane className="mr-2 h-4 w-4" />
            Add New Flight
          </Button>
        )}
      </div>

      <div className="flex flex-col md:flex-row gap-4">
        <div className="flex-1">
          <div className="relative">
            <Search className="absolute left-2 top-1/2 h-4 w-4 -translate-y-1/2 text-gray-500" />
            <Input
              placeholder="Search flights by destination or flight number..."
              className="pl-8"
              value={searchTerm}
              onChange={(e) => setSearchTerm(e.target.value)}
            />
          </div>
        </div>
        <div className="flex gap-2">
          <div>
            <Label htmlFor="date" className="sr-only">
              Date
            </Label>
            <Input
              id="date"
              type="date"
              className="w-full"
            />
          </div>
          <Button variant="outline">Filter</Button>
        </div>
      </div>

      <div className="space-y-4">
        {filteredFlights.length > 0 ? (
          filteredFlights.map((flight) => (
            <Card key={flight.id} className="overflow-hidden hover:shadow-lg transition">
              <CardContent className="p-0">
                <div className="grid grid-cols-1 lg:grid-cols-7 gap-4">
                  <div className="lg:col-span-5 p-6">
                    <div className="flex flex-col md:flex-row justify-between md:items-center mb-4">
                      <div className="flex items-center mb-4 md:mb-0">
                        <Plane className="h-5 w-5 text-airline-blue mr-2" />
                        <span className="font-bold text-lg">{flight.id}</span>
                        <span className="text-gray-500 ml-2">• Cloud Jet Airways</span>
                      </div>
                      <div className="font-bold text-xl text-airline-blue">
                        ₹{(flight.price * 83).toFixed(2)}
                      </div>
                    </div>

                    <div className="flex flex-col sm:flex-row justify-between sm:items-center">
                      <div className="space-y-1 mb-4 sm:mb-0">
                        <div className="flex items-center">
                          <MapPin className="h-4 w-4 text-gray-500 mr-2" />
                          <div className="flex items-center">
                            <span className="font-bold">{flight.fromCode}</span>
                            <span className="mx-2 text-gray-500">•</span>
                            <span className="text-gray-500">{flight.from}</span>
                          </div>
                        </div>
                        <div className="flex items-center">
                          <MapPin className="h-4 w-4 text-gray-500 mr-2" />
                          <div className="flex items-center">
                            <span className="font-bold">{flight.toCode}</span>
                            <span className="mx-2 text-gray-500">•</span>
                            <span className="text-gray-500">{flight.to}</span>
                          </div>
                        </div>
                      </div>

                      <div className="space-y-1">
                        <div className="flex items-center">
                          <Calendar className="h-4 w-4 text-gray-500 mr-2" />
                          <div className="flex items-center">
                            <span className="font-bold">{flight.departureTime}</span>
                            <span className="mx-2 text-gray-500">•</span>
                            <span className="text-gray-500">{flight.departureDate}</span>
                          </div>
                        </div>
                        <div className="flex items-center">
                          <Calendar className="h-4 w-4 text-gray-500 mr-2" />
                          <div className="flex items-center">
                            <span className="font-bold">{flight.arrivalTime}</span>
                            <span className="mx-2 text-gray-500">•</span>
                            <span className="text-gray-500">{flight.arrivalDate}</span>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>

                  <div className="lg:col-span-2 p-6 bg-gray-50 flex flex-col justify-center border-t lg:border-t-0 lg:border-l">
                    <div className="text-center mb-4">
                      <p className="text-gray-500 mb-1">Seats available</p>
                      <p className="font-bold text-lg">{flight.seatsAvailable}</p>
                    </div>
                    {isAdmin ? (
                      <div className="space-y-2">
                        <Button 
                          variant="outline" 
                          className="w-full"
                          onClick={() => handleEdit(flight)}
                        >
                          Edit
                        </Button>
                        <Button 
                          variant="destructive" 
                          className="w-full"
                          onClick={() => openDeleteDialog(flight.id)}
                        >
                          Delete
                        </Button>
                      </div>
                    ) : (
                      <Button 
                        className="w-full bg-airline-blue hover:bg-airline-navy"
                        onClick={() => handleBookNow(flight)}
                        disabled={flight.seatsAvailable <= 0}
                      >
                        {flight.seatsAvailable > 0 ? "Book Now" : "Sold Out"}
                      </Button>
                    )}
                  </div>
                </div>
              </CardContent>
            </Card>
          ))
        ) : (
          <div className="text-center py-12">
            <Plane className="h-12 w-12 mx-auto text-gray-300" />
            <h3 className="mt-4 text-lg font-medium">No flights found</h3>
            <p className="mt-1 text-gray-500">Try adjusting your search terms</p>
          </div>
        )}
      </div>
      
      {/* Edit Flight Dialog */}
      <Dialog open={isEditDialogOpen} onOpenChange={setIsEditDialogOpen}>
        <DialogContent className="sm:max-w-[625px]">
          <DialogHeader>
            <DialogTitle>Edit Flight Details</DialogTitle>
            <DialogDescription>
              Update the details for flight {editingFlight?.id}
            </DialogDescription>
          </DialogHeader>
          
          {editingFlight && (
            <div className="grid grid-cols-1 md:grid-cols-2 gap-4 py-4">
              <div className="space-y-2">
                <Label htmlFor="edit-from">Origin City</Label>
                <Input
                  id="edit-from"
                  name="from"
                  value={editingFlight.from}
                  onChange={handleEditChange}
                />
              </div>
              
              <div className="space-y-2">
                <Label htmlFor="edit-fromCode">Origin Code</Label>
                <Input
                  id="edit-fromCode"
                  name="fromCode"
                  value={editingFlight.fromCode}
                  onChange={handleEditChange}
                  maxLength={3}
                />
              </div>
              
              <div className="space-y-2">
                <Label htmlFor="edit-to">Destination City</Label>
                <Input
                  id="edit-to"
                  name="to"
                  value={editingFlight.to}
                  onChange={handleEditChange}
                />
              </div>
              
              <div className="space-y-2">
                <Label htmlFor="edit-toCode">Destination Code</Label>
                <Input
                  id="edit-toCode"
                  name="toCode"
                  value={editingFlight.toCode}
                  onChange={handleEditChange}
                  maxLength={3}
                />
              </div>
              
              <div className="space-y-2">
                <Label htmlFor="edit-departureDate">Departure Date</Label>
                <Input
                  id="edit-departureDate"
                  name="departureDate"
                  type="date"
                  value={editingFlight.departureDate}
                  onChange={handleEditChange}
                />
              </div>
              
              <div className="space-y-2">
                <Label htmlFor="edit-departureTime">Departure Time</Label>
                <Input
                  id="edit-departureTime"
                  name="departureTime"
                  type="time"
                  value={editingFlight.departureTime}
                  onChange={handleEditChange}
                />
              </div>
              
              <div className="space-y-2">
                <Label htmlFor="edit-arrivalDate">Arrival Date</Label>
                <Input
                  id="edit-arrivalDate"
                  name="arrivalDate"
                  type="date"
                  value={editingFlight.arrivalDate}
                  onChange={handleEditChange}
                />
              </div>
              
              <div className="space-y-2">
                <Label htmlFor="edit-arrivalTime">Arrival Time</Label>
                <Input
                  id="edit-arrivalTime"
                  name="arrivalTime"
                  type="time"
                  value={editingFlight.arrivalTime}
                  onChange={handleEditChange}
                />
              </div>
              
              <div className="space-y-2">
                <Label htmlFor="edit-price">Price (USD)</Label>
                <Input
                  id="edit-price"
                  name="price"
                  type="number"
                  min="0"
                  step="0.01"
                  value={editingFlight.price}
                  onChange={handleEditChange}
                />
              </div>
              
              <div className="space-y-2">
                <Label htmlFor="edit-seatsAvailable">Seats Available</Label>
                <Input
                  id="edit-seatsAvailable"
                  name="seatsAvailable"
                  type="number"
                  min="0"
                  step="1"
                  value={editingFlight.seatsAvailable}
                  onChange={handleEditChange}
                />
              </div>
            </div>
          )}
          
          <DialogFooter>
            <Button variant="outline" onClick={() => setIsEditDialogOpen(false)}>
              Cancel
            </Button>
            <Button onClick={saveEditedFlight} className="bg-airline-blue hover:bg-airline-navy">
              Save Changes
            </Button>
          </DialogFooter>
        </DialogContent>
      </Dialog>
      
      {/* Delete Confirmation Dialog */}
      <Dialog open={isDeleteDialogOpen} onOpenChange={setIsDeleteDialogOpen}>
        <DialogContent className="sm:max-w-[425px]">
          <DialogHeader>
            <DialogTitle className="flex items-center">
              <AlertCircle className="h-5 w-5 text-red-500 mr-2" />
              Confirm Deletion
            </DialogTitle>
            <DialogDescription>
              Are you sure you want to delete flight {flightToDelete}? This action cannot be undone.
            </DialogDescription>
          </DialogHeader>
          <DialogFooter>
            <Button variant="outline" onClick={() => setIsDeleteDialogOpen(false)}>
              Cancel
            </Button>
            <Button variant="destructive" onClick={handleDelete}>
              Delete Flight
            </Button>
          </DialogFooter>
        </DialogContent>
      </Dialog>
    </div>
  );
}
