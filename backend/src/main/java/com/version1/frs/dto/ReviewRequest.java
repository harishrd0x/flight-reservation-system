package com.version1.frs.dto;
 
public class ReviewRequest {
    private Long flightId;
    private Long userId;
    private int rating;
    private String reviewText;
 
    // Getters and Setters
    public Long getFlightId() {
        return flightId;
    }
 
    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }
 
    public Long getUserId() {
        return userId;
    }
 
    public void setUserId(Long userId) {
        this.userId = userId;
    }
 
    public int getRating() {
        return rating;
    }
 
    public void setRating(int rating) {
        this.rating = rating;
    }
 
    public String getReviewText() {
        return reviewText;
    }
 
    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }
}