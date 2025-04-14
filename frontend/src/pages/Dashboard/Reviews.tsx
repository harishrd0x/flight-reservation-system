
import { useState } from "react";
import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";
import ReviewCard from "@/components/reviews/ReviewCard";
import { Search, MessageSquare } from "lucide-react";

// Mock reviews data
const MOCK_REVIEWS = [
  {
    id: "r1",
    userId: "user1",
    userName: "John Doe",
    flightId: "CJ-1245",
    rating: 4,
    comment: "Great service and comfortable seating. The flight attendants were very helpful and friendly. Food was good too.",
    date: "2025-06-02",
  },
  {
    id: "r2",
    userId: "user2",
    userName: "Jane Smith",
    flightId: "CJ-2347",
    rating: 5,
    comment: "Excellent experience from start to finish! The check-in process was smooth, and the flight was on time. Very satisfied with Cloud Jet Services.",
    date: "2025-05-28",
  },
  {
    id: "r3",
    userId: "user3",
    userName: "Alex Johnson",
    flightId: "CJ-3782",
    rating: 3,
    comment: "The flight was delayed by an hour, but the staff handled it professionally. The in-flight entertainment options could be improved.",
    date: "2025-05-20",
  },
  {
    id: "r4",
    userId: "user4",
    userName: "Sarah Williams",
    flightId: "CJ-9012",
    rating: 2,
    comment: "Disappointing experience. Long check-in queues and cramped seating. The food quality was below average for a premium airline.",
    date: "2025-05-15",
  },
];

export default function ReviewsPage() {
  const [searchTerm, setSearchTerm] = useState("");
  const [reviews] = useState(MOCK_REVIEWS);
  
  const filteredReviews = reviews.filter(
    (review) =>
      review.userName.toLowerCase().includes(searchTerm.toLowerCase()) ||
      review.flightId.toLowerCase().includes(searchTerm.toLowerCase()) ||
      review.comment.toLowerCase().includes(searchTerm.toLowerCase())
  );

  // Calculate average rating
  const averageRating = reviews.reduce((acc, review) => acc + review.rating, 0) / reviews.length;

  // Count reviews by rating
  const ratingCounts = [0, 0, 0, 0, 0];
  reviews.forEach(review => {
    ratingCounts[review.rating - 1]++;
  });

  return (
    <div className="space-y-6">
      <div>
        <h1 className="text-3xl font-bold tracking-tight">Customer Reviews</h1>
        <p className="text-gray-500">
          View and manage customer feedback for your flights.
        </p>
      </div>

      <div className="grid grid-cols-1 lg:grid-cols-3 gap-6">
        <div className="lg:col-span-1">
          <div className="bg-white p-6 rounded-lg border h-full">
            <div className="text-center mb-6">
              <h2 className="text-3xl font-bold text-airline-blue">
                {averageRating.toFixed(1)}
                <span className="text-xl text-gray-500">/5</span>
              </h2>
              <div className="flex justify-center my-2">
                {[1, 2, 3, 4, 5].map((star) => (
                  <svg
                    key={star}
                    className={`h-6 w-6 ${
                      star <= Math.round(averageRating)
                        ? "text-yellow-400 fill-yellow-400"
                        : "text-gray-200 fill-gray-200"
                    }`}
                    xmlns="http://www.w3.org/2000/svg"
                    viewBox="0 0 24 24"
                    fill="none"
                    stroke="currentColor"
                    strokeWidth="2"
                    strokeLinecap="round"
                    strokeLinejoin="round"
                  >
                    <polygon points="12 2 15.09 8.26 22 9.27 17 14.14 18.18 21.02 12 17.77 5.82 21.02 7 14.14 2 9.27 8.91 8.26 12 2" />
                  </svg>
                ))}
              </div>
              <p className="text-gray-500">Based on {reviews.length} reviews</p>
            </div>

            <div className="space-y-2">
              {[5, 4, 3, 2, 1].map((rating) => (
                <div key={rating} className="flex items-center gap-2">
                  <div className="text-sm font-medium w-2">{rating}</div>
                  <div className="w-full bg-gray-200 rounded-full h-2.5">
                    <div
                      className="bg-airline-blue h-2.5 rounded-full"
                      style={{
                        width: `${(ratingCounts[rating - 1] / reviews.length) * 100}%`,
                      }}
                    ></div>
                  </div>
                  <div className="text-sm text-gray-500 w-6">
                    {ratingCounts[rating - 1]}
                  </div>
                </div>
              ))}
            </div>
          </div>
        </div>

        <div className="lg:col-span-2">
          <div className="relative w-full mb-6">
            <Search className="absolute left-2 top-1/2 h-4 w-4 -translate-y-1/2 text-gray-500" />
            <Input
              placeholder="Search reviews by customer, flight ID, or content..."
              className="pl-8"
              value={searchTerm}
              onChange={(e) => setSearchTerm(e.target.value)}
            />
          </div>

          <div className="space-y-4">
            {filteredReviews.length > 0 ? (
              filteredReviews.map((review) => (
                <ReviewCard key={review.id} {...review} />
              ))
            ) : (
              <div className="text-center py-12">
                <MessageSquare className="h-12 w-12 mx-auto text-gray-300" />
                <h3 className="mt-4 text-lg font-medium">No reviews found</h3>
                <p className="mt-1 text-gray-500">Try adjusting your search terms</p>
              </div>
            )}
          </div>
        </div>
      </div>
    </div>
  );
}
