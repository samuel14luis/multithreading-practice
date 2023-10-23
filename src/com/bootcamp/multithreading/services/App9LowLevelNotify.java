package com.bootcamp.multithreading.services;

import java.security.SecureRandom;
import java.util.LinkedList;

public class App9LowLevelNotify {

    private LinkedList<Integer> list = new LinkedList<>();
    private final int LIMIT = 10;
    private final Object lock = new Object();

    /**
     * The produce() method runs in an infinite loop where it generates incremental values and adds them to the list.
     * Before adding a value, it checks if the list size is equal to the limit (10) and if so,
     * calls wait() on the lock object to suspend the thread.
     *
     * Once there is space in the list, it adds the value and then calls notify()
     * on the lock object to wake up the consumer.
     * @throws InterruptedException
     */
    public void produce() throws InterruptedException {
        int value = 0;

        while (true) {
            synchronized (lock) {
                while (list.size() == LIMIT) {
                    lock.wait();
                }

                list.add(value++);
                lock.notify();
            }
        }
    }

    /**
     * The consume() method also runs in an infinite loop where it removes values from the list.
     * Before deleting, it checks if the list is empty and if so, calls wait() to suspend.
     *
     * Once there are elements in the list, it prints the size, removes an element and prints its value.
     * Then call notify() to wake up the producer again.
     * @throws InterruptedException
     */
    public void consume() throws InterruptedException {
        SecureRandom random = new SecureRandom();
        while (true) {
            synchronized (lock) {
                while (list.size() == 0) {
                    lock.wait();
                }

                System.out.print("List size is: " + list.size());
                int value = list.removeFirst();
                System.out.println("; value is: " + value);
                lock.notify();
            }

            Thread.sleep(random.nextInt(1000));
        }
    }

}
