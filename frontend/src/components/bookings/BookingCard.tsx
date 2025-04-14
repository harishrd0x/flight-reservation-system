
import React from "react";
import { Card, CardContent } from "@/components/ui/card";
import { Button } from "@/components/ui/button";
import { Plane, Calendar, X } from "lucide-react";

interface BookingCardProps {
  id: string;
  flightId: string;
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
  userName?: string;
  onCancel: () => void;
  onViewDetails: () => void;
  extraButtons?: React.ReactNode;
}

const BookingCard: React.FC<BookingCardProps> = ({
  id,
  flightId,
  from,
  fromCode,
  to,
  toCode,
  departureDate,
  departureTime,
  arrivalDate,
  arrivalTime,
  price,
  status,
  userName,
  onCancel,
  onViewDetails,
  extraButtons
}) => {
  const statusColor = 
    status === 'confirmed' ? 'bg-green-100 text-green-700' : 
    status === 'pending' ? 'bg-yellow-100 text-yellow-700' : 
    'bg-red-100 text-red-700';

  const handleViewDetailsClick = (e: React.MouseEvent) => {
    e.stopPropagation();
    onViewDetails();
  };

  const handleCancelClick = (e: React.MouseEvent) => {
    e.stopPropagation();
    onCancel();
  };

  return (
    <Card className="overflow-hidden hover:shadow-lg transition cursor-pointer" onClick={handleViewDetailsClick}>
      <CardContent className="p-0">
        <div className="grid grid-cols-1 lg:grid-cols-7 gap-4">
          <div className="lg:col-span-5 p-6">
            <div className="flex flex-col md:flex-row justify-between md:items-center mb-4">
              <div className="flex items-center mb-4 md:mb-0">
                <Plane className="h-5 w-5 text-airline-blue mr-2" />
                <span className="font-bold text-lg">{flightId}</span>
                <span className="text-gray-500 ml-2">• Cloud Jet Airways</span>
              </div>
              <div className="flex items-center">
                <span className={`px-3 py-1 rounded-full text-xs font-medium capitalize ${statusColor}`}>
                  {status}
                </span>
              </div>
            </div>

            <div className="flex flex-col sm:flex-row justify-between sm:items-center">
              <div className="space-y-1 mb-4 sm:mb-0">
                <div className="flex items-center">
                  <div className="flex items-center">
                    <span className="font-bold">{fromCode}</span>
                    <span className="mx-2 text-gray-500">•</span>
                    <span className="text-gray-500">{from}</span>
                  </div>
                </div>
                <div className="flex items-center">
                  <div className="flex items-center">
                    <span className="font-bold">{toCode}</span>
                    <span className="mx-2 text-gray-500">•</span>
                    <span className="text-gray-500">{to}</span>
                  </div>
                </div>
                {userName && (
                  <div className="flex items-center mt-2">
                    <div className="flex items-center">
                      <span className="text-xs text-gray-500">Passenger:</span>
                      <span className="ml-1 text-sm font-medium">{userName}</span>
                    </div>
                  </div>
                )}
              </div>

              <div className="space-y-1">
                <div className="flex items-center">
                  <Calendar className="h-4 w-4 text-gray-500 mr-2" />
                  <div className="flex items-center">
                    <span className="font-bold">{departureTime}</span>
                    <span className="mx-2 text-gray-500">•</span>
                    <span className="text-gray-500">{departureDate}</span>
                  </div>
                </div>
                <div className="flex items-center">
                  <Calendar className="h-4 w-4 text-gray-500 mr-2" />
                  <div className="flex items-center">
                    <span className="font-bold">{arrivalTime}</span>
                    <span className="mx-2 text-gray-500">•</span>
                    <span className="text-gray-500">{arrivalDate}</span>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <div className="lg:col-span-2 p-6 bg-gray-50 flex flex-col justify-center border-t lg:border-t-0 lg:border-l">
            <div className="text-center mb-4">
              <p className="text-gray-500 mb-1">Booking ID</p>
              <p className="font-bold text-sm">{id}</p>
              <p className="text-lg font-bold text-airline-blue mt-2">₹{(price * 83).toFixed(2)}</p>
            </div>
            
            <div className="space-y-2">
              {status !== 'cancelled' && (
                <Button 
                  variant="outline" 
                  className="w-full text-red-600 border-red-200 hover:bg-red-50"
                  onClick={handleCancelClick}
                >
                  <X className="mr-2 h-4 w-4" />
                  Cancel
                </Button>
              )}
              
              {extraButtons}
            </div>
          </div>
        </div>
      </CardContent>
    </Card>
  );
};

export default BookingCard;
