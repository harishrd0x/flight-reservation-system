
import React from "react";
import { Card, CardContent, CardHeader } from "@/components/ui/card";
import { Star, User, Calendar, Plane } from "lucide-react";

interface ReviewCardProps {
  id: string;
  userId: string;
  userName: string;
  flightId: string;
  rating: number;
  comment: string;
  date: string;
}

const ReviewCard: React.FC<ReviewCardProps> = ({
  id,
  userId,
  userName,
  flightId,
  rating,
  comment,
  date
}) => {
  return (
    <Card className="overflow-hidden hover:shadow-sm transition">
      <CardHeader className="bg-gray-50 p-4">
        <div className="flex items-center justify-between">
          <div className="flex items-center space-x-2">
            <div className="bg-airline-blue text-white p-2 rounded-full">
              <User className="h-4 w-4" />
            </div>
            <span className="font-medium">{userName}</span>
          </div>
          <div className="flex items-center">
            {[1, 2, 3, 4, 5].map((star) => (
              <Star
                key={star}
                className={`h-4 w-4 ${
                  star <= rating
                    ? "text-yellow-400 fill-yellow-400"
                    : "text-gray-200"
                }`}
              />
            ))}
          </div>
        </div>
      </CardHeader>
      <CardContent className="p-4">
        <div className="flex items-center text-sm text-gray-500 mb-2">
          <Plane className="h-4 w-4 mr-1" />
          <span>Flight: {flightId}</span>
          <span className="mx-2">•</span>
          <Calendar className="h-4 w-4 mr-1" />
          <span>{date}</span>
        </div>
        <p className="text-gray-700">{comment}</p>
      </CardContent>
    </Card>
  );
}

export default ReviewCard;
