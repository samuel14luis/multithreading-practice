package com.bootcamp.multithreading.services;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class App10ReentrantLocks {

    private int count = 0;
    private void increment() {
        for (int i = 0; i < 10000; i++) {
            count++;
        }
    }
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    /**
     * firstThread() acquires the ReentrantLock lock, prints "Waiting...",
     * calls await() on the Condition to suspend, and then increments the counter when it is woken up.
     * @param id of the thread. For display purposes only.
     * @throws InterruptedException
     */
    public void firstThread(int id) throws InterruptedException {
        BaseProcessor processor = new BaseProcessor(id, true);
        lock.lock();

        processor.sout("Waiting...");
        condition.await();
        processor.sout("Woken up!");

        try {
            increment();
        } finally {
            lock.unlock();
        }
    }

    /**
     * secondThread() sleeps 1 second, acquires the lock, prints a message,
     * waits for user input, calls signal() in the Condition to wake up firstThread(), and increments the counter.
     *
     * @param id of the thread. For display purposes only.
     * @throws InterruptedException
     */
    public void secondThread(int id) throws InterruptedException {
        BaseProcessor processor = new BaseProcessor(id, true);
        Thread.sleep(1000);
        lock.lock();

        processor.sout("Press the return key!");
        new Scanner(System.in).nextLine();
        processor.sout("Got return key!");

        condition.signal();

        try {
            increment();
        } finally {
            lock.unlock();
        }
    }

    public void finished() {
        System.out.println("Count is: " + count);
    }
}
