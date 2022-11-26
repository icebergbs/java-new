package com.design.oop.c12.ddd;

import java.math.BigDecimal;

/**
 * 看了上面的代码，你可能会说，领域模型 VirtualWallet 类很单薄，包含的业务逻辑很简单。
 * 相对于原来的贫血模型的设计思路，这种充血模型的设计思路，貌似并没有太大优势。
 * 你说得没错！这也是大部分业务系统都使用基于贫血模型开发的原因。
 * 不过，如果虚拟钱包系统需要支持更复杂的业务逻辑，那充血模型的优势就显现出来了。
 * 比如，我们要支持透支一定额度和冻结部分余额的功能。
 * 这个时候，我们重新来看一下 VirtualWallet 类的实现代码。
 *
 * @author bingshan
 * @date 2022/11/26 9:56
 */
public class VirtualWallet1 {
    private Long id;
    private Long createTime = System.currentTimeMillis();;
    private BigDecimal balance = BigDecimal.ZERO;
    private boolean isAllowedOverdraft = true;
    private BigDecimal overdraftAmount = BigDecimal.ZERO;
    private BigDecimal frozenAmount = BigDecimal.ZERO;

    public VirtualWallet1(Long preAllocatedId) {
        this.id = preAllocatedId;
    }

    public void freeze(BigDecimal amount) { return; }
    public void unfreeze(BigDecimal amount) { return;}
    public void increaseOverdraftAmount(BigDecimal amount) { return; }
    public void decreaseOverdraftAmount(BigDecimal amount) { return; }
    public void closeOverdraft() { return; }
    public void openOverdraft() { return; }

    public BigDecimal balance() {
        return this.balance;
    }

//    public BigDecimal getAvaliableBalance() {
//        BigDecimal totalAvaliableBalance = this.balance.subtract(this.frozenAmount);
//        if (isAllowedOverdraft) {
//            totalAvaliableBalance += this.overdraftAmount;
//        }
//        return totalAvaliableBalance;
//    }
//
//    public void debit(BigDecimal amount) {
//        BigDecimal totalAvaliableBalance = getAvaliableBalance();
//        if (totoalAvaliableBalance.compareTo(amount) < 0) {
//            throw new InsufficientBalanceException(...);
//        }
//        this.balance = this.balance.subtract(amount);
//    }
//
//    public void credit(BigDecimal amount) {
//        if (amount.compareTo(BigDecimal.ZERO) < 0) {
//            throw new InvalidAmountException(...);
//        }
//        this.balance = this.balance.add(amount);
//    }
}
