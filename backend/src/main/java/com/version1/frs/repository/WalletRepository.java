package com.version1.frs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.version1.frs.model.Wallet;

/**
 * Repository interface for managing {@link Wallet} entities. Provides CRUD
 * operations and custom queries related to wallets.
 */
public interface WalletRepository extends JpaRepository<Wallet, Long> {

	/**
	 * Retrieves a wallet associated with a specific user by their user ID.
	 * 
	 * @param userId the ID of the user whose wallet is to be retrieved
	 * @return an {@link Optional} containing the wallet if found, or an empty
	 *         {@link Optional} if not
	 */
	Optional<Wallet> findByUser_UserId(Long userId);
}
