package com.design.oop.c12.ddd;

import java.math.BigDecimal;

/**
 * 在上一节课中，我们讲到，基于充血模型的 DDD 开发模式，跟基于贫血模型的传统开发模式的主要区别就在 Service 层，
 * Controller 层和 Repository 层的代码基本上相同。
 * 所以，我们重点看一下，Service 层按照基于充血模型的 DDD 开发模式该如何来实现。
 *
 *
 * 在这种开发模式下，我们把虚拟钱包 VirtualWallet 类设计成一个充血的 Domain 领域模型，
 * 并且将原来在 Service 类中的部分业务逻辑移动到 VirtualWallet 类中，让 Service 类的实现依赖 VirtualWallet 类。
 * 具体的代码实现如下所示：
 *
 * @author bingshan
 * @date 2022/11/26 9:50
 */
public class VirtualWallet {

    private Long id;
    private Long createTime = System.currentTimeMillis();;
    private BigDecimal balance = BigDecimal.ZERO;

    public VirtualWallet(Long preAllocatedId) {
        this.id = preAllocatedId;
    }

    public BigDecimal balance() {
        return this.balance;
    }

    public void debit(BigDecimal amount) {
        if (this.balance.compareTo(amount) < 0) {
            //throw new InsufficientBalanceException("...");
        }
        this.balance = this.balance.subtract(amount);
    }

    public void credit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            //throw new InvalidAmountException("...");
        }
        this.balance = this.balance.add(amount);
    }
}
