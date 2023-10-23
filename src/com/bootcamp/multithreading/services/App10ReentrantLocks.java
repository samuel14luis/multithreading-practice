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
