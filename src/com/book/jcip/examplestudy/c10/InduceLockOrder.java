package com.book.jcip.examplestudy.c10;

public class InduceLockOrder {

    /**
     * 通过锁顺序来避免死锁
     */
    private static final Object tieLock = new Object();

    public void transferMoney(final Account fromAcct,
                              final Account toAcct,
                              final DollarAmount amount
                              ) throws InsufficientFundsException {
        class Helper {
            public void transfer() throws InsufficientFundsException {
                if (fromAcct.getBalance().compareTo(amount) < 0) {
                    throw new InsufficientFundsException();
                }else {
                    fromAcct.debit(amount);
                    toAcct.credit(amount);
                }
            }
        }

        int fromHash = System.identityHashCode(fromAcct);
        int toHash = System.identityHashCode(toAcct);

        if (fromHash < toHash) {
            synchronized (fromAcct) {
                synchronized (toAcct) {
                    new Helper().transfer();
                }
            }
        } else if (fromHash > toHash) {
            synchronized (toAcct) {
                synchronized (fromAcct) {
                    new Helper().transfer();
                }
            }
        } else {
            //在极少的情况下,两个对象可能拥有相同的散列值,此时必须通过某种任意的方法来决定锁的顺序
            synchronized (tieLock) {
                synchronized (fromAcct) {
                    synchronized (toAcct) {
                        new Helper().transfer();
                    }
                }
            }
        }
    }



    interface Account {
        void debit(DollarAmount d);

        void credit(DollarAmount d);

        DollarAmount getBalance();

        int getAcctNo();
    }

    interface DollarAmount extends Comparable<DollarAmount> {
    }

    class InsufficientFundsException extends Exception {
    }
}
