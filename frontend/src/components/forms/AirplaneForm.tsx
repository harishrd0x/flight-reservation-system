
import React, { useState } from "react";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { toast } from "@/components/ui/use-toast";
import { Plane } from "lucide-react";

interface AirplaneFormProps {
  onSubmit: (data: {
    model: string;
    capacity: number;
    manufacturer: string;
    yearManufactured: number;
  }) => void;
}

const AirplaneForm: React.FC<AirplaneFormProps> = ({ onSubmit }) => {
  const [model, setModel] = useState("");
  const [capacity, setCapacity] = useState("");
  const [manufacturer, setManufacturer] = useState("");
  const [yearManufactured, setYearManufactured] = useState("");

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    
    if (!model || !capacity || !manufacturer || !yearManufactured) {
      toast({
        title: "Error",
        description: "Please fill in all fields",
        variant: "destructive",
      });
      return;
    }

    const capacityNum = parseInt(capacity);
    const yearNum = parseInt(yearManufactured);

    if (isNaN(capacityNum) || capacityNum <= 0) {
      toast({
        title: "Error",
        description: "Capacity must be a positive number",
        variant: "destructive",
      });
      return;
    }

    if (isNaN(yearNum) || yearNum < 1900 || yearNum > new Date().getFullYear()) {
      toast({
        title: "Error",
        description: "Please enter a valid year",
        variant: "destructive",
      });
      return;
    }
    
    onSubmit({
      model,
      capacity: capacityNum,
      manufacturer,
      yearManufactured: yearNum,
    });

    // Reset form
    setModel("");
    setCapacity("");
    setManufacturer("");
    setYearManufactured("");
  };

  return (
    <Card>
      <CardHeader>
        <CardTitle className="flex items-center text-2xl">
          <Plane className="mr-2 h-6 w-6 text-airline-blue" />
          Add New Airplane
        </CardTitle>
      </CardHeader>
      <CardContent>
        <form onSubmit={handleSubmit} className="space-y-4">
          <div className="space-y-2">
            <Label htmlFor="model">Airplane Model</Label>
            <Input
              id="model"
              placeholder="e.g. Boeing 737"
              value={model}
              onChange={(e) => setModel(e.target.value)}
            />
          </div>
          
          <div className="space-y-2">
            <Label htmlFor="manufacturer">Manufacturer</Label>
            <Input
              id="manufacturer"
              placeholder="e.g. Boeing"
              value={manufacturer}
              onChange={(e) => setManufacturer(e.target.value)}
            />
          </div>
          
          <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
            <div className="space-y-2">
              <Label htmlFor="capacity">Passenger Capacity</Label>
              <Input
                id="capacity"
                type="number"
                placeholder="e.g. 180"
                value={capacity}
                onChange={(e) => setCapacity(e.target.value)}
              />
            </div>
            
            <div className="space-y-2">
              <Label htmlFor="yearManufactured">Year Manufactured</Label>
              <Input
                id="yearManufactured"
                type="number"
                placeholder="e.g. 2020"
                value={yearManufactured}
                onChange={(e) => setYearManufactured(e.target.value)}
              />
            </div>
          </div>
          
          <Button 
            type="submit"
            className="w-full bg-airline-blue hover:bg-airline-navy"
          >
            Add Airplane
          </Button>
        </form>
      </CardContent>
    </Card>
  );
}

export default AirplaneForm;
