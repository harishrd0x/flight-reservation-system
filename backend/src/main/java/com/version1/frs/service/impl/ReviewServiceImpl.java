package com.version1.frs.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.version1.frs.dto.ReviewRequest;
import com.version1.frs.dto.ReviewResponse;
import com.version1.frs.model.Flight;
import com.version1.frs.model.Review;
import com.version1.frs.model.User;
import com.version1.frs.repository.BookingRepository;
import com.version1.frs.repository.FlightRepository;
import com.version1.frs.repository.ReviewRepository;
import com.version1.frs.repository.UserRepository;
import com.version1.frs.service.ReviewService;

/**
 * Implementation of the {@link ReviewService} interface. Provides methods for
 * managing flight reviews, including posting, retrieving, and mapping reviews.
 */
@Service
public class ReviewServiceImpl implements ReviewService {

	private final ReviewRepository reviewRepository;
	private final UserRepository userRepository;
	private final FlightRepository flightRepository;
	private final BookingRepository bookingRepository;

	// Constructor injection for dependencies
	public ReviewServiceImpl(ReviewRepository reviewRepository, UserRepository userRepository,
			FlightRepository flightRepository, BookingRepository bookingRepository) {
		this.reviewRepository = reviewRepository;
		this.userRepository = userRepository;
		this.flightRepository = flightRepository;
		this.bookingRepository = bookingRepository;
	}

	/**
	 * Posts a new review for a flight by a customer.
	 * 
	 * @param userId  the ID of the user posting the review
	 * @param request the review request object containing review details
	 * @return the posted review as a {@link ReviewResponse} DTO
	 * @throws RuntimeException if the user is not found, not a customer, or has not
	 *                          booked the flight
	 */
	@Override
	public ReviewResponse postReview(Long userId, ReviewRequest request) {
		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

		// Check if user is a customer
		if (user.getUserRole() == null || !user.getUserRole().equalsIgnoreCase("CUSTOMER")) {
			throw new RuntimeException("Only customers can post reviews");
		}

		Flight flight = flightRepository.findById(request.getFlightId())
				.orElseThrow(() -> new RuntimeException("Flight not found"));

		// Check if the user actually booked this flight
		boolean hasBooked = bookingRepository.existsByUser_UserIdAndFlight_Id(userId, flight.getId());
		if (!hasBooked) {
			throw new RuntimeException("You can only review flights you have booked.");
		}

		Review review = new Review();
		review.setUser(user);
		review.setFlight(flight);
		review.setRating(request.getRating());
		review.setReviewText(request.getReviewText());

		review = reviewRepository.save(review);
		return mapToResponse(review);
	}

	/**
	 * Retrieves all reviews for a specific flight.
	 * 
	 * @param flightId the ID of the flight whose reviews are to be fetched
	 * @return a list of {@link ReviewResponse} DTOs for the specified flight
	 */
	@Override
	public List<ReviewResponse> getReviewsByFlightId(Long flightId) {
		List<Review> reviews = reviewRepository.findByFlight_Id(flightId);
		return reviews.stream().map(this::mapToResponse).collect(Collectors.toList());
	}

	/**
	 * Retrieves all reviews posted by a specific user.
	 * 
	 * @param userId the ID of the user whose reviews are to be fetched
	 * @return a list of {@link ReviewResponse} DTOs for the specified user
	 */
	@Override
	public List<ReviewResponse> getReviewsByUserId(Long userId) {
		List<Review> reviews = reviewRepository.findByUser_UserId(userId);
		return reviews.stream().map(this::mapToResponse).collect(Collectors.toList());
	}

	/**
	 * Retrieves all reviews from the system.
	 * 
	 * @return a list of all {@link ReviewResponse} DTOs
	 */
	@Override
	public List<ReviewResponse> getAllReviews() {
		List<Review> reviews = reviewRepository.findAll(); // Using JpaRepository's findAll method
		return reviews.stream().map(this::mapToResponse).collect(Collectors.toList());
	}

	/**
	 * Maps a {@link Review} entity to a {@link ReviewResponse} DTO.
	 * 
	 * @param review the review entity to convert
	 * @return the corresponding {@link ReviewResponse} DTO
	 */
	private ReviewResponse mapToResponse(Review review) {
		ReviewResponse res = new ReviewResponse();
		res.setReviewId(review.getReviewId());
		res.setUserId(review.getUser().getUserId());
		res.setFlightId(review.getFlight().getId());
		res.setRating(review.getRating());
		res.setReviewText(review.getReviewText());
		return res;
	}
}
