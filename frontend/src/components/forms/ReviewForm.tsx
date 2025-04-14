
import React, { useState } from "react";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Textarea } from "@/components/ui/textarea";
import { toast } from "@/components/ui/use-toast";
import { MessageSquare, Star } from "lucide-react";

interface ReviewFormProps {
  onSubmit: (data: {
    flightId: string;
    rating: number;
    comment: string;
  }) => void;
}

export const ReviewForm: React.FC<ReviewFormProps> = ({ onSubmit }) => {
  const [flightId, setFlightId] = useState("");
  const [rating, setRating] = useState<number>(0);
  const [comment, setComment] = useState("");
  const [hoveredRating, setHoveredRating] = useState<number | null>(null);

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    
    if (!flightId || rating === 0 || !comment) {
      toast({
        title: "Error",
        description: "Please fill in all fields and provide a rating",
        variant: "destructive",
      });
      return;
    }
    
    onSubmit({
      flightId,
      rating,
      comment
    });

    // Reset form
    setFlightId("");
    setRating(0);
    setComment("");
  };

  return (
    <Card>
      <CardHeader>
        <CardTitle className="flex items-center text-2xl">
          <MessageSquare className="mr-2 h-6 w-6 text-airline-blue" />
          Submit Your Review
        </CardTitle>
      </CardHeader>
      <CardContent>
        <form onSubmit={handleSubmit} className="space-y-4">
          <div className="space-y-2">
            <Label htmlFor="flightId">Flight ID</Label>
            <Input
              id="flightId"
              placeholder="e.g. CJ-1245"
              value={flightId}
              onChange={(e) => setFlightId(e.target.value)}
            />
          </div>
          
          <div className="space-y-2">
            <Label>Your Rating</Label>
            <div className="flex space-x-2">
              {[1, 2, 3, 4, 5].map((star) => (
                <button
                  key={star}
                  type="button"
                  onClick={() => setRating(star)}
                  onMouseEnter={() => setHoveredRating(star)}
                  onMouseLeave={() => setHoveredRating(null)}
                  className="focus:outline-none"
                >
                  <Star
                    className={`h-8 w-8 ${
                      star <= (hoveredRating || rating)
                        ? "text-yellow-400 fill-yellow-400"
                        : "text-gray-300"
                    }`}
                  />
                </button>
              ))}
            </div>
          </div>
          
          <div className="space-y-2">
            <Label htmlFor="comment">Your Comments</Label>
            <Textarea
              id="comment"
              placeholder="Tell us about your experience..."
              rows={4}
              value={comment}
              onChange={(e) => setComment(e.target.value)}
            />
          </div>
          
          <Button 
            type="submit"
            className="w-full bg-airline-blue hover:bg-airline-navy"
          >
            Submit Review
          </Button>
        </form>
      </CardContent>
    </Card>
  );
}

export default ReviewForm;
