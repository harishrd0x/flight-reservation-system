package com.version1.frs.service.impl;

import com.version1.frs.dto.WalletRequest;
import com.version1.frs.model.Wallet;
import com.version1.frs.repository.WalletRepository;
import com.version1.frs.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Override
    public String addMoney(WalletRequest request) {
        Wallet wallet = walletRepository.findByUserId(request.getUserId());

        if (wallet == null) {
            wallet = new Wallet();
            wallet.setUserId(request.getUserId());
            wallet.setBalance(request.getAmount());
        } else {
            wallet.setBalance(wallet.getBalance() + request.getAmount());
        }

        walletRepository.save(wallet);
        return "Amount added to wallet successfully.";
    }

    @Override
    public Wallet getWalletByUserId(int userId) {
        return walletRepository.findByUserId(userId);
    }
}