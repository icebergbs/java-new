package com.algorithm.codinginterviewguide.chapter1;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 题目:
 *  宠物. 狗 和 猫 的类如下:
 *
 *  实现一种狗猫队列的结构,要求如下
 *      1. 用户可以调用add方法将 cat类或dog类的实例放入队列中;
 *      2. 用户可以调用pollAll方法, 将队列中所有的实例按照进队的先后顺序依次弹出;
 *      3. 用户可以调用pollDog方法, 将队列中dog类的实例按照进队的先后顺序依次弹出;
 *      4. 用户可以调用pollCat方法, 将队列中cat类的实例按照进队的先后顺序依次弹出;
 *      5. 用户可以调用isEmpty方法, 检查队列中是否还有dog或cat的实例;
 *      6. 用户可以调用isDogEmpty方法, 检查队列中是否还有dog的实例;
 *      7. 用户可以调用isCatEmpty方法, 检查队列中是否还有cat的实例;
 *
 */
public class C03_1x_猫狗队列 {
    /**
     * 基础类
     */
    class Pet {
        private String type;

        public Pet(String type) {
            this.type = type;
        }

        public String getPetType() {
            return this.type;
        }
    }

    class Dog extends Pet {
        public Dog() {
            super("Dog");
        }
    }

    class Cat extends Pet {
        public Cat() {
            super("Cat");
        }
    }

    /**
     * 实现:
     *      将不同的实例盖上时间戳的方法,但又不能改变用户本身的类,
     *      所以定义一个新类
     */
    class PetEnterQueue {
        private Pet pet;
        private long count;

        public PetEnterQueue(Pet pet, long count) {
            this.pet = pet;
            this.count = count;
        }

        public Pet getPet() {
            return pet;
        }

        public void setPet(Pet pet) {
            this.pet = pet;
        }

        public String getEnterPetType() {
            return this.pet.getPetType();
        }

        public long getCount() {
            return count;
        }

        public void setCount(long count) {
            this.count = count;
        }
    }

    /**
     * 首先有一个不断累加的数据项, 用来表示实例进队列的时间;
     * 同时有两个队列,一个是只放dog 类实例的队列 dogQ, 另一个是只放cat类实例的队列 catQ
     * 想按实际顺序弹出,比较两个队列头的时间戳
     */
    class DogCatQueue {
        private Queue<PetEnterQueue> dogQ;
        private Queue<PetEnterQueue> catQ;
        private long count;

        public DogCatQueue() {
            this.dogQ = new LinkedList<>();
            this.catQ = new LinkedList<>();
            this.count = 0;
        }

        public void add(Pet pet) {
            if (pet.getPetType().equals("dog")) {
                this.dogQ.add(new PetEnterQueue(pet, this.count++));
            } else if (pet.getPetType().equals("cat")) {
                this.catQ.add(new PetEnterQueue(pet, this.count++));
            } else {
                throw new RuntimeException("err, not dog or cat");
            }
        }

        public Pet pollAll() {
            if (!this.dogQ.isEmpty() && !this.catQ.isEmpty()) {
                if (this.dogQ.peek().getCount() < this.catQ.peek().getCount()) {
                    return this.dogQ.poll().getPet();
                } else {
                    return this.catQ.poll().getPet();
                }
            } else if (!this.dogQ.isEmpty()) {
                return this.dogQ.poll().getPet();
            } else if (!this.catQ.isEmpty()) {
                return this.catQ.poll().getPet();
            } else {
                throw new RuntimeException("err, queue is empty!");
            }
        }

        public Dog pollDog() {
            if (!this.isDogQueueEmpty()) {
                return (Dog) this.dogQ.poll().getPet();
            } else {
                throw new RuntimeException("Dog queue is empty!");
            }
        }

        public Cat pollCat() {
            if (!this.isCatQueueEmpty()) {
                return (Cat)this.catQ.poll().getPet();
            } else {
                throw new RuntimeException("Cat queue is empty!");
            }
        }

        public boolean isEmpty() {
            return this.dogQ.isEmpty() && this.catQ.isEmpty();
        }

        public boolean isDogQueueEmpty() {
            return this.dogQ.isEmpty();
        }

        public boolean isCatQueueEmpty() {
            return this.catQ.isEmpty();
        }


    }

}
