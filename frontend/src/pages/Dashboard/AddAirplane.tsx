
import { useState } from "react";
import AirplaneForm from "@/components/forms/AirplaneForm";
import { toast } from "@/components/ui/use-toast";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { Button } from "@/components/ui/button";
import { Plane, Trash2 } from "lucide-react";

// Mock airplanes data
const MOCK_AIRPLANES = [
  {
    id: "a1",
    model: "Boeing 737-800",
    manufacturer: "Boeing",
    capacity: 189,
    yearManufactured: 2017,
  },
  {
    id: "a2",
    model: "Airbus A320neo",
    manufacturer: "Airbus",
    capacity: 180,
    yearManufactured: 2019,
  },
];

export default function AddAirplanePage() {
  const [airplanes, setAirplanes] = useState(MOCK_AIRPLANES);

  const handleAddAirplane = (data: {
    model: string;
    capacity: number;
    manufacturer: string;
    yearManufactured: number;
  }) => {
    // In a real app, this would make an API call
    const newAirplane = {
      id: `a${Date.now()}`,
      ...data
    };
    
    setAirplanes([...airplanes, newAirplane]);
    
    toast({
      title: "Airplane Added",
      description: `${data.manufacturer} ${data.model} has been added to the fleet.`,
    });
  };

  const handleDeleteAirplane = (id: string) => {
    // In a real app, this would make an API call
    setAirplanes(airplanes.filter(airplane => airplane.id !== id));
    
    toast({
      title: "Airplane Removed",
      description: "The airplane has been removed from the fleet.",
    });
  };

  return (
    <div className="space-y-6">
      <div>
        <h1 className="text-3xl font-bold tracking-tight">Manage Airplanes</h1>
        <p className="text-gray-500">
          Add new airplanes to your fleet or manage existing ones.
        </p>
      </div>

      <div className="grid grid-cols-1 lg:grid-cols-2 gap-6">
        <div>
          <AirplaneForm onSubmit={handleAddAirplane} />
        </div>

        <div>
          <Card>
            <CardHeader>
              <CardTitle className="flex items-center text-2xl">
                <Plane className="mr-2 h-6 w-6 text-airline-blue" />
                Current Fleet ({airplanes.length})
              </CardTitle>
            </CardHeader>
            <CardContent>
              {airplanes.length > 0 ? (
                <div className="space-y-4">
                  {airplanes.map((airplane) => (
                    <div 
                      key={airplane.id} 
                      className="flex justify-between items-center p-4 border rounded-lg hover:bg-gray-50"
                    >
                      <div>
                        <h3 className="font-medium">{airplane.manufacturer} {airplane.model}</h3>
                        <div className="text-sm text-gray-500 space-y-1">
                          <p>Capacity: {airplane.capacity} passengers</p>
                          <p>Year: {airplane.yearManufactured}</p>
                        </div>
                      </div>
                      <Button 
                        variant="destructive" 
                        size="icon"
                        onClick={() => handleDeleteAirplane(airplane.id)}
                      >
                        <Trash2 className="h-4 w-4" />
                      </Button>
                    </div>
                  ))}
                </div>
              ) : (
                <div className="text-center py-8 text-gray-500">
                  No airplanes added yet
                </div>
              )}
            </CardContent>
          </Card>
        </div>
      </div>
    </div>
  );
}
