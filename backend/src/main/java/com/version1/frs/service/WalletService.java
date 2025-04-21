package com.version1.frs.service;

import com.version1.frs.dto.WalletRequest;
import com.version1.frs.dto.WalletResponse;

/**
 * Service interface for managing user wallets in the system. This includes
 * operations such as retrieving, updating, adding money, and making payments.
 */
public interface WalletService {

	// -------------------- Read --------------------

	/**
	 * Retrieves the wallet information of a user by their user ID.
	 * 
	 * @param userId the ID of the user whose wallet is to be retrieved
	 * @return a {@link WalletResponse} DTO containing wallet details for the
	 *         specified user
	 * @throws RuntimeException if the wallet for the user is not found
	 */
	WalletResponse getWalletByUserId(Long userId);

	// -------------------- Update --------------------

	/**
	 * Adds money to the user's wallet.
	 * 
	 * @param userId  the ID of the user whose wallet will be updated
	 * @param request the {@link WalletRequest} DTO containing the amount to be
	 *                added to the wallet
	 * @return a {@link WalletResponse} DTO reflecting the updated wallet balance
	 * @throws RuntimeException if the wallet is not found for the user
	 */
	WalletResponse addMoney(Long userId, WalletRequest request);

	/**
	 * Makes a payment from the user's wallet by deducting the specified amount.
	 * 
	 * @param userId  the ID of the user whose wallet will be used for the payment
	 * @param request the {@link WalletRequest} DTO containing the amount to be
	 *                deducted for the payment
	 * @return a {@link WalletResponse} DTO reflecting the updated wallet balance
	 * @throws RuntimeException if the wallet is not found or if the balance is
	 *                          insufficient
	 */
	WalletResponse makePayment(Long userId, WalletRequest request);

	// -------------------- Update --------------------

	/**
	 * Updates the wallet details for a specific wallet ID.
	 * 
	 * @param walletId the ID of the wallet to update
	 * @param request  the {@link WalletRequest} DTO containing the new wallet
	 *                 details
	 * @return a {@link WalletResponse} DTO reflecting the updated wallet details
	 * @throws RuntimeException if the wallet is not found for the given ID
	 */
	WalletResponse updateWallet(Long walletId, WalletRequest request);
}
