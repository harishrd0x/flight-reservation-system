
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "@/context/AuthContext";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { toast } from "@/components/ui/use-toast";
import { Plane } from "lucide-react";

interface FlightFormData {
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

export default function AddFlightPage() {
  const { checkRole } = useAuth();
  const navigate = useNavigate();
  
  const [formData, setFormData] = useState<FlightFormData>({
    id: `CJ-${Math.floor(1000 + Math.random() * 9000)}`,
    from: "",
    fromCode: "",
    to: "",
    toCode: "",
    departureDate: "",
    departureTime: "",
    arrivalDate: "",
    arrivalTime: "",
    price: 0,
    seatsAvailable: 0,
  });
  
  const [errors, setErrors] = useState<Record<string, string>>({});

  // Redirect if user is not an admin
  if (!checkRole('admin')) {
    navigate('/dashboard');
    return null;
  }

  const validateForm = () => {
    const newErrors: Record<string, string> = {};
    
    if (!formData.from) newErrors.from = "Origin city is required";
    if (!formData.fromCode) newErrors.fromCode = "Origin airport code is required";
    if (!formData.to) newErrors.to = "Destination city is required";
    if (!formData.toCode) newErrors.toCode = "Destination airport code is required";
    if (!formData.departureDate) newErrors.departureDate = "Departure date is required";
    if (!formData.departureTime) newErrors.departureTime = "Departure time is required";
    if (!formData.arrivalDate) newErrors.arrivalDate = "Arrival date is required";
    if (!formData.arrivalTime) newErrors.arrivalTime = "Arrival time is required";
    
    if (formData.price <= 0) newErrors.price = "Price must be greater than 0";
    if (formData.seatsAvailable <= 0) newErrors.seatsAvailable = "Seats available must be greater than 0";
    
    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    
    setFormData(prev => ({
      ...prev,
      [name]: name === 'price' || name === 'seatsAvailable' ? parseFloat(value) || 0 : value
    }));
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    
    if (validateForm()) {
      try {
        // Load existing flights from localStorage
        const existingFlights = JSON.parse(localStorage.getItem('flights') || '[]');
        
        // Save the new flight
        localStorage.setItem('flights', JSON.stringify([...existingFlights, formData]));
        
        toast({
          title: "Flight Added",
          description: `Flight ${formData.id} from ${formData.from} to ${formData.to} has been added successfully.`,
        });
        
        // Navigate back to flights page
        navigate('/dashboard/flights');
      } catch (error) {
        console.error("Error adding flight:", error);
        toast({
          title: "Error",
          description: "There was an error adding the flight. Please try again.",
          variant: "destructive"
        });
      }
    }
  };

  return (
    <div className="space-y-6">
      <div className="flex justify-between items-center">
        <div>
          <h1 className="text-3xl font-bold tracking-tight">Add New Flight</h1>
          <p className="text-gray-500">
            Create a new flight route with all details
          </p>
        </div>
        <Button 
          variant="outline" 
          onClick={() => navigate('/dashboard/flights')}
        >
          Cancel
        </Button>
      </div>

      <Card>
        <CardHeader>
          <CardTitle className="flex items-center">
            <Plane className="mr-2 h-5 w-5 text-airline-blue" />
            Flight Details
          </CardTitle>
        </CardHeader>
        <CardContent>
          <form onSubmit={handleSubmit} className="space-y-6">
            <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
              {/* Flight ID */}
              <div className="space-y-2">
                <Label htmlFor="id">Flight Number</Label>
                <Input
                  id="id"
                  name="id"
                  value={formData.id}
                  onChange={handleInputChange}
                  readOnly
                  className="bg-gray-100"
                />
              </div>
              
              {/* Origin */}
              <div className="space-y-2">
                <Label htmlFor="from">Origin City</Label>
                <Input
                  id="from"
                  name="from"
                  value={formData.from}
                  onChange={handleInputChange}
                  className={errors.from ? "border-red-500" : ""}
                />
                {errors.from && (
                  <p className="text-red-500 text-sm">{errors.from}</p>
                )}
              </div>
              
              {/* Origin Code */}
              <div className="space-y-2">
                <Label htmlFor="fromCode">Origin Airport Code</Label>
                <Input
                  id="fromCode"
                  name="fromCode"
                  value={formData.fromCode}
                  onChange={handleInputChange}
                  className={errors.fromCode ? "border-red-500" : ""}
                  maxLength={3}
                  placeholder="JFK"
                />
                {errors.fromCode && (
                  <p className="text-red-500 text-sm">{errors.fromCode}</p>
                )}
              </div>
              
              {/* Destination */}
              <div className="space-y-2">
                <Label htmlFor="to">Destination City</Label>
                <Input
                  id="to"
                  name="to"
                  value={formData.to}
                  onChange={handleInputChange}
                  className={errors.to ? "border-red-500" : ""}
                />
                {errors.to && (
                  <p className="text-red-500 text-sm">{errors.to}</p>
                )}
              </div>
              
              {/* Destination Code */}
              <div className="space-y-2">
                <Label htmlFor="toCode">Destination Airport Code</Label>
                <Input
                  id="toCode"
                  name="toCode"
                  value={formData.toCode}
                  onChange={handleInputChange}
                  className={errors.toCode ? "border-red-500" : ""}
                  maxLength={3}
                  placeholder="LHR"
                />
                {errors.toCode && (
                  <p className="text-red-500 text-sm">{errors.toCode}</p>
                )}
              </div>
              
              {/* Departure Date */}
              <div className="space-y-2">
                <Label htmlFor="departureDate">Departure Date</Label>
                <Input
                  id="departureDate"
                  name="departureDate"
                  type="date"
                  value={formData.departureDate}
                  onChange={handleInputChange}
                  className={errors.departureDate ? "border-red-500" : ""}
                />
                {errors.departureDate && (
                  <p className="text-red-500 text-sm">{errors.departureDate}</p>
                )}
              </div>
              
              {/* Departure Time */}
              <div className="space-y-2">
                <Label htmlFor="departureTime">Departure Time</Label>
                <Input
                  id="departureTime"
                  name="departureTime"
                  type="time"
                  value={formData.departureTime}
                  onChange={handleInputChange}
                  className={errors.departureTime ? "border-red-500" : ""}
                />
                {errors.departureTime && (
                  <p className="text-red-500 text-sm">{errors.departureTime}</p>
                )}
              </div>
              
              {/* Arrival Date */}
              <div className="space-y-2">
                <Label htmlFor="arrivalDate">Arrival Date</Label>
                <Input
                  id="arrivalDate"
                  name="arrivalDate"
                  type="date"
                  value={formData.arrivalDate}
                  onChange={handleInputChange}
                  className={errors.arrivalDate ? "border-red-500" : ""}
                />
                {errors.arrivalDate && (
                  <p className="text-red-500 text-sm">{errors.arrivalDate}</p>
                )}
              </div>
              
              {/* Arrival Time */}
              <div className="space-y-2">
                <Label htmlFor="arrivalTime">Arrival Time</Label>
                <Input
                  id="arrivalTime"
                  name="arrivalTime"
                  type="time"
                  value={formData.arrivalTime}
                  onChange={handleInputChange}
                  className={errors.arrivalTime ? "border-red-500" : ""}
                />
                {errors.arrivalTime && (
                  <p className="text-red-500 text-sm">{errors.arrivalTime}</p>
                )}
              </div>
              
              {/* Price */}
              <div className="space-y-2">
                <Label htmlFor="price">Price (USD)</Label>
                <Input
                  id="price"
                  name="price"
                  type="number"
                  min="0"
                  step="0.01"
                  value={formData.price}
                  onChange={handleInputChange}
                  className={errors.price ? "border-red-500" : ""}
                />
                {errors.price && (
                  <p className="text-red-500 text-sm">{errors.price}</p>
                )}
              </div>
              
              {/* Seats Available */}
              <div className="space-y-2">
                <Label htmlFor="seatsAvailable">Seats Available</Label>
                <Input
                  id="seatsAvailable"
                  name="seatsAvailable"
                  type="number"
                  min="0"
                  step="1"
                  value={formData.seatsAvailable}
                  onChange={handleInputChange}
                  className={errors.seatsAvailable ? "border-red-500" : ""}
                />
                {errors.seatsAvailable && (
                  <p className="text-red-500 text-sm">{errors.seatsAvailable}</p>
                )}
              </div>
            </div>
            
            <div className="flex justify-end space-x-4">
              <Button
                type="button"
                variant="outline"
                onClick={() => navigate('/dashboard/flights')}
              >
                Cancel
              </Button>
              <Button type="submit" className="bg-airline-blue hover:bg-airline-navy">
                <Plane className="mr-2 h-4 w-4" />
                Add Flight
              </Button>
            </div>
          </form>
        </CardContent>
      </Card>
    </div>
  );
}
