package com.book.jcip.examplestudy.c15;

import com.book.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

//Michael-Scott(Michael and Scott, 1996) 非阻塞算法中的插入算法
@ThreadSafe
public class LinkedQueue <E>{

    private static class Node <E> {
        final E item;
        final AtomicReference<Node<E>> next;

        public Node(E item, Node<E> next) {
            this.item = item;
            this.next = new AtomicReference<Node<E>>(next);
        }
    }

    private final Node<E> dummy = new Node<E>(null, null);
    private final AtomicReference<Node<E>> head = new AtomicReference<>(dummy);
    private final AtomicReference<Node<E>> tail = new AtomicReference<>(dummy);

    public boolean put(E item) {
        Node<E> newNode = new Node<>(item, null);
        while (true) {
            Node<E> curTail = tail.get();
            Node<E> tailNext = curTail.next.get();
            if (curTail == tail.get()) {
                if (tailNext != null) {     //A
                    //队列处于中间状态,推进尾节点
                    tail.compareAndSet(curTail, tailNext);    //B
                } else {
                    //处于稳定状态,尝试插入新节点
                    if (curTail.next.compareAndSet(null, newNode)) {  //C
                        //插入操作成功, 尝试推进尾节点
                        tail.compareAndSet(curTail, newNode);   //D
                        return true;
                    }
                }
            }
        }
    }
}
