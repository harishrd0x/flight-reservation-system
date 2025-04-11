package com.version1.frs.service;

import com.version1.frs.dto.WalletRequest;
import com.version1.frs.model.Wallet;

public interface WalletService {

    String addMoney(WalletRequest request);

    Wallet getWalletByUserId(int userId);
}