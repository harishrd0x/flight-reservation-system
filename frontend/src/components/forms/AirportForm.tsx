
import React, { useState } from "react";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { toast } from "@/components/ui/use-toast";
import { Building2, MapPin } from "lucide-react";

interface AirportFormProps {
  onSubmit: (data: {
    name: string;
    code: string;
    city: string;
    country: string;
  }) => void;
}

const AirportForm: React.FC<AirportFormProps> = ({ onSubmit }) => {
  const [name, setName] = useState("");
  const [code, setCode] = useState("");
  const [city, setCity] = useState("");
  const [country, setCountry] = useState("");

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    
    if (!name || !code || !city || !country) {
      toast({
        title: "Error",
        description: "Please fill in all fields",
        variant: "destructive",
      });
      return;
    }

    if (code.length !== 3) {
      toast({
        title: "Error",
        description: "Airport code must be 3 characters",
        variant: "destructive",
      });
      return;
    }
    
    onSubmit({
      name,
      code: code.toUpperCase(),
      city,
      country
    });

    // Reset form
    setName("");
    setCode("");
    setCity("");
    setCountry("");
  };

  return (
    <Card>
      <CardHeader>
        <CardTitle className="flex items-center text-2xl">
          <Building2 className="mr-2 h-6 w-6 text-airline-blue" />
          Add New Airport
        </CardTitle>
      </CardHeader>
      <CardContent>
        <form onSubmit={handleSubmit} className="space-y-4">
          <div className="space-y-2">
            <Label htmlFor="name">Airport Name</Label>
            <Input
              id="name"
              placeholder="e.g. John F. Kennedy International Airport"
              value={name}
              onChange={(e) => setName(e.target.value)}
            />
          </div>
          
          <div className="space-y-2">
            <Label htmlFor="code">Airport Code (3 characters)</Label>
            <Input
              id="code"
              placeholder="e.g. JFK"
              maxLength={3}
              value={code}
              onChange={(e) => setCode(e.target.value.toUpperCase())}
            />
          </div>
          
          <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
            <div className="space-y-2">
              <Label htmlFor="city">City</Label>
              <Input
                id="city"
                placeholder="e.g. New York"
                value={city}
                onChange={(e) => setCity(e.target.value)}
              />
            </div>
            
            <div className="space-y-2">
              <Label htmlFor="country">Country</Label>
              <Input
                id="country"
                placeholder="e.g. United States"
                value={country}
                onChange={(e) => setCountry(e.target.value)}
              />
            </div>
          </div>
          
          <div className="pt-2">
            <Button 
              type="submit"
              className="w-full bg-airline-blue hover:bg-airline-navy"
            >
              <MapPin className="mr-2 h-4 w-4" />
              Add Airport
            </Button>
          </div>
        </form>
      </CardContent>
    </Card>
  );
}

export default AirportForm;
