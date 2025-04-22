package com.version1.frs.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.version1.frs.dto.WalletRequest;
import com.version1.frs.dto.WalletResponse;
import com.version1.frs.model.Wallet;
import com.version1.frs.repository.WalletRepository;
import com.version1.frs.service.WalletService;

/**
 * Implementation of the {@link WalletService} interface. Provides methods to
 * manage wallets, including retrieving wallet information, updating balances,
 * adding money, and making payments.
 */
@Service
public class WalletServiceImpl implements WalletService {

	private WalletRepository walletRepository;

	// Constructor injection for dependencies

	public WalletServiceImpl(WalletRepository walletRepository) {
		this.walletRepository = walletRepository;
	}

	/**
	 * Retrieves the wallet information for a user by their user ID.
	 * 
	 * @param userId the ID of the user whose wallet is being fetched
	 * @return the {@link WalletResponse} DTO containing wallet details
	 * @throws RuntimeException if the wallet is not found for the user
	 */
	@Override
	public WalletResponse getWalletByUserId(Long userId) {
		Wallet wallet = walletRepository.findByUser_UserId(userId)
				.orElseThrow(() -> new RuntimeException("Wallet not found for the user"));
		return mapToResponse(wallet);
	}

	/**
	 * Updates the wallet balance for a specific wallet ID.
	 * 
	 * @param walletId the ID of the wallet to update
	 * @param request  the {@link WalletRequest} containing the new balance to set
	 * @return the updated {@link WalletResponse} DTO
	 * @throws RuntimeException if the wallet is not found
	 */
	@Override
	public WalletResponse updateWallet(Long walletId, WalletRequest request) {
		Optional<Wallet> optionalWallet = walletRepository.findById(walletId);
		if (optionalWallet.isPresent()) {
			Wallet wallet = optionalWallet.get();
			wallet.setBalance(request.getBalance());

			wallet = walletRepository.save(wallet);

			return mapToResponse(wallet);
		} else {
			throw new RuntimeException("Wallet not found");
		}
	}

	/**
	 * Adds money to a user's wallet.
	 * 
	 * @param userId  the ID of the user whose wallet is being updated
	 * @param request the {@link WalletRequest} containing the amount to add
	 * @return the updated {@link WalletResponse} DTO
	 * @throws RuntimeException if the wallet is not found for the user
	 */
	@Override
	public WalletResponse addMoney(Long userId, WalletRequest request) {
		// Get wallet by user ID
		Wallet wallet = walletRepository.findByUser_UserId(userId)
				.orElseThrow(() -> new RuntimeException("Wallet not found for the user"));

		// Add money to the wallet balance
		wallet.setBalance(wallet.getBalance().add(request.getBalance()));

		wallet = walletRepository.save(wallet);

		return mapToResponse(wallet);
	}

	/**
	 * Makes a payment by deducting money from the user's wallet.
	 * 
	 * @param userId  the ID of the user whose wallet is being used for payment
	 * @param request the {@link WalletRequest} containing the amount to be deducted
	 * @return the updated {@link WalletResponse} DTO
	 * @throws RuntimeException if the wallet is not found or if there is
	 *                          insufficient balance
	 */
	@Override
	public WalletResponse makePayment(Long userId, WalletRequest request) {
		// Get wallet by user ID
		Wallet wallet = walletRepository.findByUser_UserId(userId)
				.orElseThrow(() -> new RuntimeException("Wallet not found for the user"));

		// Check if the balance is enough for the payment
		if (wallet.getBalance().compareTo(request.getBalance()) < 0) {
			throw new RuntimeException("Insufficient balance for the payment");
		}

		// Deduct the payment from wallet balance
		wallet.setBalance(wallet.getBalance().subtract(request.getBalance()));

		wallet = walletRepository.save(wallet);

		return mapToResponse(wallet);
	}

	/**
	 * Maps a {@link Wallet} entity to a {@link WalletResponse} DTO.
	 * 
	 * @param wallet the wallet entity to convert
	 * @return the corresponding {@link WalletResponse} DTO
	 */
	private WalletResponse mapToResponse(Wallet wallet) {
		WalletResponse res = new WalletResponse();
		res.setWalletId(wallet.getWalletId());
		res.setUserId(wallet.getUser().getUserId());
		res.setBalance(wallet.getBalance());
		return res;
	}
}
