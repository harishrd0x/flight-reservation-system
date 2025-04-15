package com.version1.frs.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.version1.frs.dto.WalletRequest;
import com.version1.frs.dto.WalletResponse;
import com.version1.frs.model.User;
import com.version1.frs.model.Wallet;
import com.version1.frs.repository.UserRepository;
import com.version1.frs.repository.WalletRepository;
import com.version1.frs.service.WalletService;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public WalletResponse createWallet(WalletRequest request) {
        // Validate the user exists
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Create Wallet
        Wallet wallet = new Wallet();
        wallet.setUser(user);
        wallet.setBalance(request.getBalance());

        wallet = walletRepository.save(wallet);

        return mapToResponse(wallet);
    }

    @Override
    public WalletResponse getWalletByUserId(Long userId) {
        Wallet wallet = walletRepository.findByUser_UserId(userId)
                .orElseThrow(() -> new RuntimeException("Wallet not found for the user"));
        return mapToResponse(wallet);
    }

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

    @Override
    public WalletResponse createWallet(Long userId) {
        // Validate the user exists
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Create Wallet
        Wallet wallet = new Wallet();
        wallet.setUser(user);
        wallet.setBalance(0.0); // Default balance is 0 when creating

        wallet = walletRepository.save(wallet);

        return mapToResponse(wallet);
    }

    @Override
    public WalletResponse addMoney(WalletRequest request) {
        // Get wallet by user ID
        Wallet wallet = walletRepository.findByUser_UserId(request.getUserId())
                .orElseThrow(() -> new RuntimeException("Wallet not found for the user"));

        // Add money to the wallet balance
        wallet.setBalance(wallet.getBalance() + request.getBalance());

        wallet = walletRepository.save(wallet);

        return mapToResponse(wallet);
    }

    @Override
    public WalletResponse makePayment(WalletRequest request) {
        // Get wallet by user ID
        Wallet wallet = walletRepository.findByUser_UserId(request.getUserId())
                .orElseThrow(() -> new RuntimeException("Wallet not found for the user"));

        // Check if the balance is enough for the payment
        if (wallet.getBalance() < request.getBalance()) {
            throw new RuntimeException("Insufficient balance for the payment");
        }

        // Deduct the payment from wallet balance
        wallet.setBalance(wallet.getBalance() - request.getBalance());

        wallet = walletRepository.save(wallet);

        return mapToResponse(wallet);
    }

    private WalletResponse mapToResponse(Wallet wallet) {
        WalletResponse res = new WalletResponse();
        res.setWalletId(wallet.getWalletId());
        res.setUserId(wallet.getUser().getUserId());
        res.setBalance(wallet.getBalance());
        return res;
    }
}
