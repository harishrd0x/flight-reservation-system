package com.version1.frs.repository;

import com.version1.frs.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Wallet findByUserId(int userId);
}