
import { useState } from "react";
import AirportForm from "@/components/forms/AirportForm";
import { toast } from "@/components/ui/use-toast";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { Button } from "@/components/ui/button";
import { Building2, MapPin, Trash2 } from "lucide-react";

// Mock airports data
const MOCK_AIRPORTS = [
  {
    id: "ap1",
    name: "John F. Kennedy International Airport",
    code: "JFK",
    city: "New York",
    country: "United States",
  },
  {
    id: "ap2",
    name: "London Heathrow Airport",
    code: "LHR",
    city: "London",
    country: "United Kingdom",
  },
  {
    id: "ap3",
    name: "Charles de Gaulle Airport",
    code: "CDG",
    city: "Paris",
    country: "France",
  },
];

export default function AddAirportPage() {
  const [airports, setAirports] = useState(MOCK_AIRPORTS);

  const handleAddAirport = (data: {
    name: string;
    code: string;
    city: string;
    country: string;
  }) => {
    // Check if code already exists
    if (airports.some(airport => airport.code === data.code)) {
      toast({
        title: "Error",
        description: `Airport with code ${data.code} already exists.`,
        variant: "destructive",
      });
      return;
    }
    
    // In a real app, this would make an API call
    const newAirport = {
      id: `ap${Date.now()}`,
      ...data
    };
    
    setAirports([...airports, newAirport]);
    
    toast({
      title: "Airport Added",
      description: `${data.name} (${data.code}) has been added.`,
    });
  };

  const handleDeleteAirport = (id: string) => {
    // In a real app, this would make an API call
    setAirports(airports.filter(airport => airport.id !== id));
    
    toast({
      title: "Airport Removed",
      description: "The airport has been removed from the system.",
    });
  };

  return (
    <div className="space-y-6">
      <div>
        <h1 className="text-3xl font-bold tracking-tight">Manage Airports</h1>
        <p className="text-gray-500">
          Add new airports to your system or manage existing ones.
        </p>
      </div>

      <div className="grid grid-cols-1 lg:grid-cols-2 gap-6">
        <div>
          <AirportForm onSubmit={handleAddAirport} />
        </div>

        <div>
          <Card>
            <CardHeader>
              <CardTitle className="flex items-center text-2xl">
                <Building2 className="mr-2 h-6 w-6 text-airline-blue" />
                Available Airports ({airports.length})
              </CardTitle>
            </CardHeader>
            <CardContent>
              {airports.length > 0 ? (
                <div className="space-y-4">
                  {airports.map((airport) => (
                    <div 
                      key={airport.id} 
                      className="flex justify-between items-center p-4 border rounded-lg hover:bg-gray-50"
                    >
                      <div className="flex items-start gap-3">
                        <div className="bg-airline-blue/10 p-2 rounded-md">
                          <MapPin className="h-5 w-5 text-airline-blue" />
                        </div>
                        <div>
                          <h3 className="font-medium flex items-center">
                            <span className="mr-2">{airport.name}</span>
                            <span className="bg-gray-200 text-gray-800 px-2 py-0.5 rounded text-xs font-bold">
                              {airport.code}
                            </span>
                          </h3>
                          <p className="text-sm text-gray-500">{airport.city}, {airport.country}</p>
                        </div>
                      </div>
                      <Button 
                        variant="destructive" 
                        size="icon"
                        onClick={() => handleDeleteAirport(airport.id)}
                      >
                        <Trash2 className="h-4 w-4" />
                      </Button>
                    </div>
                  ))}
                </div>
              ) : (
                <div className="text-center py-8 text-gray-500">
                  No airports added yet
                </div>
              )}
            </CardContent>
          </Card>
        </div>
      </div>
    </div>
  );
}
