package com.design.oop.c12.mvc;

import java.math.BigDecimal;

/**
 * @author bingshan
 * @date 2022/11/25 8:17
 */
public class VirtualWalletController {
    // 通过构造函数或者IOC框架注入
    private VirtualWalletService virtualWalletService;
    public BigDecimal getBalance(Long walletId) { return null; }
    //查询余额
    public void debit(Long walletId, BigDecimal amount) { return; }
    //出账
    public void credit(Long walletId, BigDecimal amount) { return; }
    //入账
    public void transfer(Long fromWalletId, Long toWalletId, BigDecimal amount) { return;}//转账
    //省略查询transaction的接口

}
