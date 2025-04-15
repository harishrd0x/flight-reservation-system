package com.version1.frs.service;

import com.version1.frs.dto.WalletRequest;
import com.version1.frs.dto.WalletResponse;

public interface WalletService {

	WalletResponse createWallet(Long userId);

	WalletResponse getWalletByUserId(Long userId);

	WalletResponse addMoney(WalletRequest request);

	WalletResponse makePayment(WalletRequest request);

	WalletResponse createWallet(WalletRequest request);

	WalletResponse updateWallet(Long walletId, WalletRequest request);
}