package com.book.jcip.examplestudy.c15;


import javax.persistence.criteria.CriteriaBuilder;
import java.util.concurrent.atomic.AtomicReference;

/**
 * //通过CAS来维持包含多个变量的不变性条件
 */
public class CasNumberRange {

    private static class IntPair {
        final int lower;
        final int upper;

        public IntPair(int lower, int upper) {
            this.lower = lower;
            this.upper = upper;
        }

        public int getLower() {
            return lower;
        }

        public int getUpper() {
            return upper;
        }
    }

    private final AtomicReference<IntPair> values = new AtomicReference<>(new IntPair(0, 0));

    public int getLower() {
        return values.get().lower;
    }

    public int getUpper() {
        return values.get().upper;
    }

    public void setLower (int i) {
        while (true) {
            IntPair oldV = values.get();
            if (i > oldV.upper) {
                throw new IllegalArgumentException("Cant't set lower to " + i + " > upper");
            }
            IntPair newV = new IntPair(i, oldV.upper);
            if (values.compareAndSet(oldV, newV)) {
                return;
            }
        }
    }
}
