package com.algorithm.codinginterviewguide.chapter1;


import java.util.LinkedList;
import java.util.Stack;

/**
 * 题目:
 *     一个不含有负数的数组可以代表一圈环形山，每个位置的值代表山的高度。
 *     比如，{3,1,2,4,5} {4,5,3,1,2} {1,2,4,5,3} 都代表同样结构的环形山
 *     3-1-2-4-5-3 方向叫做next方向（逆时针）， 3-5-4-2-1-3方向叫做last方向（顺时针），
 *     如图所示：
 *          Codeing interview guide
 *          图 1-8
 *
 *     山峰A 和 山峰B 能够相互看见的条件为：
 *       1. 如果A 和 B是同一座山， 认为不能相互看见。
 *       2. 如果A 和 B是不同的山，并且在环中相邻，认为可以相互看见。比如图1-8中， 相邻的山峰对有（1，2）（2，4）（4，5）（3，5）（1，3）
 *       3. 如果A 和 B是不同的山，并且在环中不相邻， 假设两座山高度的最小值为min.
 *             如果A通过next方向到B的途中没有高度比min大的山峰， 或者A通过last方向到B的途中没有高度比min大的山峰，
 *             认为A和B可以相互看见。
 *             比如图1-8中，高度为3的山和高度为4的山，两座山的高度最小值为3. 3从last方向走向4，中途会遇到5，所以last方向走不通；
 *             3从next方向走向4，中途会遇到1和2，但是都不大于两座山高度的最小值3，所以next方向可以走通。
 *             有一个能走通就认为可以相互看见，再如，高度为2的山和高度为5的山，两个方向上都走不通，所以不能相互看见。
 *             图1-8中，所有在环中不相邻，并且能看见的山峰对有（2，3）（3，4）
 *
 *     给定一个不含有负数且没有重复值的数组arr, 请返回有多少山峰能够相互看见。
 *
 *
 *  进阶问题：
 *      给定一个不含有负数但可能含有重复值的数组arr, 返回有多少山峰能够相互看见。
 *
 *
 *       要求：
 *          如果arr长度为N, 没有重复值的情况下时间复杂度达到O(1), 可能有重复值的情况下时间复杂度达到O(N)
 *
 */
public class C10_1x_可见的山峰对数量 {


    /**
     * 解法:
     *    原问题： 时间复杂度O（1）解法, 如果数组中所有的数字都不一样，可见山峰的数量可以由简单公式得到。
     *    1座山峰： 可见山峰对数0
     *    2座山峰： 可见山峰对数1
     *    i>2时： 可见山峰对数 2*i -3
     *  证明：
     *     用高度小的山峰找高度大的山峰，从每一个位置出发， 都按照”小找大“原则找到山峰对的数量，就是总的可见山峰对数量。
     *     如果由i座山峰并且高度都不一样，必然在环中存在唯一的最大值和唯一的次大值。如图1-9
     *
     *     图 1-9 中，x节点表示除最高值之外的任何一座山峰。因为x即不是最大值，也不是次大值，所以x在last方向上必存在
     *     第一个高度比它大的节点，假设这个节点是y, y有可能就是最大值节点，但是一定存在。
     *     x在next方向上必存在一个高度比它大的节点，假设这个大的节点是z, z有可能就是次大值节点，但是一定存在.
     *     因为y时x在last方向上第一个高度比它大的节点，所以x在last方向上没有到达y之前遇到的所有山峰高度都小于x,不符合”小找大“。
     *     同理，x在next方向上没有到达z之前遇到的所有山峰高度都小于x,不符合”小找大“。
     *     同时根据可见山峰对的定义，y从last方向到达z这一段的每一个节点x都看不见。所以从x 出发能找到且只能找到（x,y)(x,z)这2对。
     *     如果环中有i个节点，除了最大值和次大值之外，还剩i-2个节点，这i-2个节点都根据”小找大“,每一个能找到2对。
     *     所以一共有 （i-2) * 2 对，还有一对就是次大值能够看到最大值， 一共 2*i - 3对。
     *
     */


    /**
     * 进阶问题：
     *      时间复杂度为 O(N)的解法。
     *      还是按照”小找大“的方式来解， 并利用”单调栈结构“。 举例说明，假设环形山
     *                     如图 1-10：
     *          首先遍历一次环形山结构， 找到最大值的位置， 如果最大值不止一个， 找哪一个最大值都行，比如图1-10 中5是最大值且不止一个，
     *      找哪一个都行，我们选最下方的5， 准备一个栈，记为stack<Rocord> ,stack放入的是如下数据结构：
     *           class Record
     *
     *      接下来从最大值开始沿着 next方向在遍历一遍环形山，stack中先放入 [5,1], 表示5这个高度，收集一个。 以后放入记录时，
     *      都保证第一维的数字从顶到底依次增大。 目前stack从顶到底为[5,1]
     *          沿着next方向来到4， 生成记录[4, 1], 表示4这个数，收集1个， 发现如果这个记录加入stack, 第一维的数字从顶到底时
     *      依次增大的， 所以放入[4,1], 目前stack从顶到地依次为(4,1), (5,1)
     *          沿着next方向来到3， 生成记录[3, 1], 表示3这个数，收集1个， 发现如果这个记录加入stack, 第一维的数字从顶到底时
     *      依次增大的， 所以放入[3,1], 目前stack从顶到地依次为(3,1), (4,1), (5,1)
     *          沿着next方向来到5， 生成记录[5, 1], 发现如果这个记录加入stack, 第一维的数字从顶到底不是依次增大的，所以stack开始弹出记录，
     *       首先弹出[3,1], 当前来到的数字是5，当前弹出的数字是3，原来在栈中的时候当前弹出数字的下面是4， 说明当前弹出的3在next方向上遇到的
     *       第一个大于它的数就是当前来到的数字5，在last方向遇到第一个大于它的数就是此时的栈顶4， 进一步说明从当前弹出的3出发，通过”小找大“的
     *       方式，可以找到2个可见山峰对(3,4)(3,5).   stack继续弹出（4，1），当前来到的数字是5，当前弹出的数字是4，原来在栈中的时候，当前
     *       弹出数字下面的数字是5， 说明从当前弹出的4出发，通过”小找大“的方式，又找到2个可见山峰对。  stack从顶到底只剩下（5，1）这个记录，
     *       当前生成新记录是（5，1），把两个记录合并， 目前stack从顶到底为：（5.1），发现山峰对数量： 4
     *          沿着next方向来到4， 生成记录[4, 1].。。
     *          沿着next方向来到2， 生成记录[2, 1].。。
     *          沿着next方向来到4， 生成记录[4, 1]。 。。。弹出（2，1），与上面同理，。。。  目前stack栈： （4，2）（5，2） 发现山峰对数：6
     *          沿着next方向来到4， 生成记录[4, 1]. 此时顶部记录（4.2）， 合并
     *          沿着next方向来到5， 生成记录[5, 1]. 发现如果这个记录加入stack, 第一维的数字从顶到底不是依次增大的，所以stack开始弹出记录，
     *       弹出（4，3）， 这条记录表示当前收集到的这3个4可能相邻；或者即便不相邻，中间夹的数字也一定小于4（比如2），并且所夹的数字一定已经用
     *       ”小找大“的方式算过山峰对了，
     *                  如同1-11所示：
     *       图1-11中虚线表示可以可能夹住某些数字， 但是都比4小，而且都是算过山峰对的数字，不需要关心。 那么这个3个4一共产生多少对可见山峰对呢？
     *       首先，每一个4都可以看到last方向的5， 和next方向上的5； 其次，这3个4内部，每两个4都可以相互看见。所以产生2*3 + C(2,3) = 9对
     *
     *          总结一下，如果子啊遍历阶段，某个记录(x, k)从stack中弹出了，产生可见山峰对的数量为：
     *          1）如果k=1, 产生2对
     *          2）如果k>1, 产生2*k + C(2,k)对
     *
     *          stack在弹出（4，3）之后，当前顶部记录为（5，2）， 当前生成的记录是[5，1]，合并。 目前stack: (5,3), 发现山峰对：15
     *          沿着next方向来到3， 生成记录[3, 1].。。
     *          沿着next方向来到2， 生成记录[2, 1].。。
     *
     *          遍历完毕。 进行最后一共阶段：单独清算栈中记录的阶段。这个阶段又分3个小阶段。
     *              1. 弹出的记录不是栈中最后一共记录，也不是倒数第二个记录
     *              2. 弹出的记录是栈中倒数第二个记录
     *              3. 弹出的记录是栈中最后一共记录
     *           比如上面例， 3个阶段都有，弹出（2，1）时第一阶段
     *           图1-12是没有第一阶段的例子
     *           图1-13是没有第1，2阶段的例子
     *
     *           任何环形结构都不可能出现没有第3小阶段的情况，因为我们总是从环形结构的最大值开始遍历的，既然是环形结构的最大值，所以在遍历过程中
     *           不会被其他高度的山峰释放
     *
     *           在最后清算阶段，假设从栈中弹出的记录为(X,K), 那么产生山峰对的逻辑如下：
     *              1） 如果发现当前记录位于第1小阶段，产生山峰对为：如果k==1, 产生2对:
     *                  如果k>1,产生2*K + C(2,K)对。这是因为（X,K）这个记录弹出之后，剩下的记录大于或等于2条，而整个图形是环，说明这K个x
     *                  在last方向和 next方向一定能找到大于它们高度的山，
     *              2） 如果发现当前记录位于第2小阶段，那么需要查看栈中的最后一条记录，假设最后一条记录为（Y,M）.
     *                  如果M==1,产生1*M+C(2,M)对；
     *                  如果M>1,产生2*M+C(2,M)对。
     *              3)  如果发现当前记录位于第3小阶段，这X一定是环中最大值。 根据”小找大“的方式，对外不产生山峰对，只是K个x内部产生山峰对，
     *                  如果K==1,产生0对； 如果K>1, 产生C(2,K)对
     *            根据单调栈的性质，全部过程的时间复杂度为O(N)
     *
     *
     * @param arr
     */

    public int getVisibleNumXX(int[] arr) {
        int num = 0;
        int maxIndex = 0;
        for (int i=0; i< arr.length; i++) {
            maxIndex = arr[maxIndex] < arr[i] ? i : maxIndex;
        }
        Stack<Record> stack = new Stack<>();
        Record maxRecord = new Record(arr[maxIndex]);
        stack.push(maxRecord);
        maxIndex++;
        for (int i = 0; i < arr.length - 1; i++) {
            int curValue = arr[maxIndex % arr.length];
            Record topRecord = stack.peek();
            if (topRecord.getValue() == curValue) {
                topRecord.setTimes(topRecord.getTimes() + 1);
            }
            if (topRecord.getValue() > curValue) {
                Record newRecord = new Record(curValue);
                stack.push(newRecord);
            }
            if (topRecord.getValue() < curValue) {
                while (stack.peek() != null && stack.peek().getValue() < curValue) {
                    Record lastRecord = stack.pop();
                    if (lastRecord.getTimes() == 1) {
                        num += 2;
                    } else {
                        num = 2*lastRecord.getTimes() + getInternalSum(lastRecord.getTimes());
                    }
                }
            }

        }
        if (!stack.isEmpty()) {
            while (stack.peek() != null) {
                Record lastRecord = stack.peek();
                if (stack.size() > 2) {
                    if (lastRecord.getTimes() == 1) {
                        num += 2;
                    } else {
                        num = 2*lastRecord.getTimes() + getInternalSum(lastRecord.getTimes());
                    }
                } else if (stack.size() == 2) {
                    if (lastRecord.getTimes() == 1) {
                        num += 3;
                    } else {
                        num = 2*lastRecord.getTimes() + getInternalSum(lastRecord.getTimes());
                    }
                } else {
                    if (lastRecord.getTimes() > 0) {
                        num += getInternalSum(lastRecord.getTimes());
                    }
                }
            }
        }
        return num;
    }



    public int getVisibleNum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int size = arr.length;
        int maxIndex = 0;
        //先在环中找到其中一个最大值的位置，哪一个都行
        for (int i = 0; i < size; i++) {
            maxIndex = arr[maxIndex] < arr[i] ? i : maxIndex;
        }
        Stack<Record> stack = new Stack<>();
        //先把（最大值， 1），这个记录放入栈中
        Record record = new Record(arr[maxIndex]);
        stack.push(record);
        //从最大值位置的下一个位置开始沿next方向遍历
        int index = nextIndex(maxIndex, size);
        //用 "小找大“的方式统计所有可见山峰对
        int res = 0;
        //遍历阶段开始，当index再次回到maxIndex的时候，说明转了一圈，遍历阶段就结束
        while (index != maxIndex) {
            //当前数字arr[index] 要进栈，判断会不会破坏第一维的数字从顶到底依次变大
            //如果破坏了，就依次弹出栈顶记录，并计算山峰对数量
            while (stack.peek().value < arr[index]) {
                int k = stack.pop().times;
                //弹出记录为（x,K), 如果k==1, 产生2对
                //如果k > 1, 产生2*k + c(2,K)对
                res += getInternalSum(k) + 2*k;
            }

            //当前数字arr[index] 要进入栈了， 如果和当前栈顶数字一样就合并
            // 比一样就把记录（arr[index], 1)放入栈中
            if (stack.peek().value == arr[index]) {
                stack.peek().times++;
            } else {
                stack.push(new Record(arr[index]));
            }
            index = nextIndex(index, size);
        }

        //清算阶段开始
        //清算阶段的第1小阶段
        while (stack.size() > 2) {
            int times = stack.pop().times;
            res += getInternalSum(times) + 2*times;
        }
        //清算阶段的第2小阶段
        if (stack.size() == 2) {
            int times = stack.pop().times;
            res += getInternalSum(times)
                    + (stack.peek().times == 1 ? times : 2*times);
        }
        //清算阶段的第3小阶段
        res += getInternalSum(stack.pop().times);
        return res;

    }

    //如果K==1, 返回0； 如果k>1, 返回C(2,k)
    public int getInternalSum(int k) {
        return k == 1 ? 0 : (k * (k - 1) / 2);
    }

    //环形数组中当前位置为i, 数组长度为size, 返回i的下一个位置
    public int nextIndex(int i, int size) {
        return i < (size - 1) ? (i + 1) : 0;
    }


    public static void main(String[] args) {

    }


}

class Record {
    public int value;
    public int times;

    public Record(int value) {
        this.value = value;
        this.times = 1;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }
}
