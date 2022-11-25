package com.design.oop.c12.mvc;

import java.math.BigDecimal;

/**
 * Service 和 BO 负责核心业务逻辑，Repository 和 Entity 负责数据存取。
 * Repository 这一层的代码实现比较简单，不是我们讲解的重点，所以我也省略掉了。
 * Service 层的代码如下所示。注意，这里我省略了一些不重要的校验代码，比如，对 amount 是否小于 0、钱包是否存在的校验等等。
 *
 * @author bingshan
 * @date 2022/11/25 8:20
 */
public class VirtualWalletService {
      // 通过构造函数或者IOC框架注入
//    private VirtualWalletRepository walletRepo;
//    private VirtualWalletTransactionRepository transactionRepo;
//
//    public VirtualWalletBo getVirtualWallet(Long walletId) {
//        VirtualWalletEntity walletEntity = walletRepo.getWalletEntity(walletId);
//        VirtualWalletBo walletBo = convert(walletEntity);
//        return walletBo;
//    }
//
//    public BigDecimal getBalance(Long walletId) {
//        return walletRepo.getBalance(walletId);
//    }
//
//    @Transactional
//    public void debit(Long walletId, BigDecimal amount) {
//        VirtualWalletEntity walletEntity = walletRepo.getWalletEntity(walletId);
//        BigDecimal balance = walletEntity.getBalance();
//        if (balance.compareTo(amount) < 0) {
//            throw new NoSufficientBalanceException(...);
//        }
//        VirtualWalletTransactionEntity transactionEntity = new VirtualWalletTransactionEntity();
//        transactionEntity.setAmount(amount);
//        transactionEntity.setCreateTime(System.currentTimeMillis());
//        transactionEntity.setType(TransactionType.DEBIT);
//        transactionEntity.setFromWalletId(walletId);
//        transactionRepo.saveTransaction(transactionEntity);
//        walletRepo.updateBalance(walletId, balance.subtract(amount));
//    }
//
//    @Transactional
//    public void credit(Long walletId, BigDecimal amount) {
//        VirtualWalletTransactionEntity transactionEntity = new VirtualWalletTransactionEntity();
//        transactionEntity.setAmount(amount);
//        transactionEntity.setCreateTime(System.currentTimeMillis());
//        transactionEntity.setType(TransactionType.CREDIT);
//        transactionEntity.setFromWalletId(walletId);
//        transactionRepo.saveTransaction(transactionEntity);
//        VirtualWalletEntity walletEntity = walletRepo.getWalletEntity(walletId);
//        BigDecimal balance = walletEntity.getBalance();
//        walletRepo.updateBalance(walletId, balance.add(amount));
//    }
//
//    @Transactional
//    public void transfer(Long fromWalletId, Long toWalletId, BigDecimal amount) {
//        VirtualWalletTransactionEntity transactionEntity = new VirtualWalletTransactionEntity();
//        transactionEntity.setAmount(amount);
//        transactionEntity.setCreateTime(System.currentTimeMillis());
//        transactionEntity.setType(TransactionType.TRANSFER);
//        transactionEntity.setFromWalletId(fromWalletId);
//        transactionEntity.setToWalletId(toWalletId);
//        transactionRepo.saveTransaction(transactionEntity);
//        debit(fromWalletId, amount);
//        credit(toWalletId, amount);
//    }

}
