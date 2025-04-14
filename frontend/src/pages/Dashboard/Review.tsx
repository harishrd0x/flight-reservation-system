
import { useState } from "react";
import { ReviewForm } from "@/components/forms/ReviewForm";
import { toast } from "@/components/ui/use-toast";
import { Button } from "@/components/ui/button";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs";
import ReviewCard from "@/components/reviews/ReviewCard";
import { CalendarClock } from "lucide-react";

// Mock user's reviews data
const MOCK_USER_REVIEWS = [
  {
    id: "r1",
    userId: "user1",
    userName: "John Doe", // Current user
    flightId: "CJ-1245",
    rating: 4,
    comment: "Great service and comfortable seating. The flight attendants were very helpful and friendly. Food was good too.",
    date: "2025-06-02",
  },
  {
    id: "r3",
    userId: "user1",
    userName: "John Doe", // Current user
    flightId: "CJ-3782",
    rating: 3,
    comment: "The flight was delayed by an hour, but the staff handled it professionally. The in-flight entertainment options could be improved.",
    date: "2025-05-20",
  },
];

// Mock flight booking history for the user
const MOCK_USER_FLIGHTS = [
  {
    id: "CJ-1245",
    from: "New York",
    to: "London",
    date: "2025-06-15",
    hasReview: true,
  },
  {
    id: "CJ-3782",
    from: "Paris",
    to: "Dubai",
    date: "2025-05-18",
    hasReview: true,
  },
  {
    id: "CJ-5678",
    from: "Dubai",
    to: "Tokyo",
    date: "2025-04-22",
    hasReview: false,
  },
];

export default function ReviewPage() {
  const [userReviews, setUserReviews] = useState(MOCK_USER_REVIEWS);
  const [userFlights, setUserFlights] = useState(MOCK_USER_FLIGHTS);
  
  const handleSubmitReview = (data: {
    flightId: string;
    rating: number;
    comment: string;
  }) => {
    // Check if flight exists in user's flight history
    const flightExists = userFlights.some(flight => flight.id === data.flightId);
    
    if (!flightExists) {
      toast({
        title: "Error",
        description: "You can only review flights you have taken.",
        variant: "destructive",
      });
      return;
    }
    
    // Check if review already exists
    const reviewExists = userReviews.some(review => review.flightId === data.flightId);
    
    if (reviewExists) {
      toast({
        title: "Error",
        description: "You have already reviewed this flight.",
        variant: "destructive",
      });
      return;
    }
    
    // In a real app, this would make an API call
    const newReview = {
      id: `r${Date.now()}`,
      userId: "user1",
      userName: "John Doe", // Current user
      flightId: data.flightId,
      rating: data.rating,
      comment: data.comment,
      date: new Date().toISOString().split('T')[0],
    };
    
    setUserReviews([newReview, ...userReviews]);
    
    // Update flight to indicate it has been reviewed
    setUserFlights(userFlights.map(flight => 
      flight.id === data.flightId ? { ...flight, hasReview: true } : flight
    ));
    
    toast({
      title: "Review Submitted",
      description: "Thank you for your feedback!",
    });
  };

  return (
    <div className="space-y-6">
      <div>
        <h1 className="text-3xl font-bold tracking-tight">Review Your Flights</h1>
        <p className="text-gray-500">
          Share your experience with other travelers
        </p>
      </div>

      <Tabs defaultValue="submit">
        <TabsList>
          <TabsTrigger value="submit">Submit a Review</TabsTrigger>
          <TabsTrigger value="history">Your Reviews</TabsTrigger>
        </TabsList>
        
        <TabsContent value="submit" className="pt-4">
          <div className="grid grid-cols-1 lg:grid-cols-2 gap-6">
            <div>
              <ReviewForm onSubmit={handleSubmitReview} />
            </div>
            
            <div>
              <Card>
                <CardHeader>
                  <CardTitle className="flex items-center text-2xl">
                    <CalendarClock className="mr-2 h-6 w-6 text-airline-blue" />
                    Recent Flights
                  </CardTitle>
                </CardHeader>
                <CardContent>
                  <p className="text-sm text-gray-500 mb-4">
                    Select a flight from the list below to review:
                  </p>
                  <div className="space-y-2">
                    {userFlights.map(flight => (
                      <div 
                        key={flight.id}
                        className={`p-3 rounded-md cursor-pointer transition ${
                          flight.hasReview 
                            ? 'bg-gray-100 opacity-60' 
                            : 'border hover:bg-gray-50'
                        }`}
                      >
                        <div className="flex justify-between">
                          <span className="font-medium">{flight.id}</span>
                          <span className="text-sm text-gray-500">{flight.date}</span>
                        </div>
                        <p className="text-sm text-gray-600">{flight.from} to {flight.to}</p>
                        {flight.hasReview && (
                          <span className="text-xs bg-green-100 text-green-800 px-2 py-1 rounded-full mt-1 inline-block">
                            Reviewed
                          </span>
                        )}
                      </div>
                    ))}
                  </div>
                </CardContent>
              </Card>
            </div>
          </div>
        </TabsContent>
        
        <TabsContent value="history" className="pt-4">
          <div className="space-y-4">
            {userReviews.length > 0 ? (
              userReviews.map(review => (
                <ReviewCard key={review.id} {...review} />
              ))
            ) : (
              <div className="text-center py-12">
                <p className="text-gray-500">You haven't submitted any reviews yet.</p>
                <Button 
                  className="mt-4 bg-airline-blue hover:bg-airline-navy"
                  onClick={() => {
                    const element = document.querySelector('[data-value="submit"]');
                    if (element) {
                      (element as HTMLElement).click();
                    }
                  }}
                >
                  Submit a Review
                </Button>
              </div>
            )}
          </div>
        </TabsContent>
      </Tabs>
    </div>
  );
}
