package com.version1.frs.model;
 
import jakarta.persistence.*;
 
@Entity
@Table(name = "TBL_REVIEWS")
public class Review {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REVIEW_ID")
    private Long reviewId;
 
    @Column(name = "FLIGHT_ID", nullable = false)
    private Long flightId;
 
    @Column(name = "USER_ID", nullable = false)
    private Long userId;
 
    @Column(name = "RATING", nullable = false)
    private int rating;
 
    @Column(name = "REVIEW_TEXT", length = 500)
    private String reviewText;
 
    // Getters and Setters
    public Long getReviewId() {
        return reviewId;
    }
 
    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }
 
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