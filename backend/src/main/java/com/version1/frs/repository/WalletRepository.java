package com.version1.frs.repository;

import com.version1.frs.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

    // Find a wallet by User ID
    Optional<Wallet> findByUser_Id(Long userId);
}
