package com.bootcamp.multithreading.services;

public class ThreadSync {

    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    /**
     * Async method, because execute async the threads and sout show count in 0.
     */
    public void doWorkAsync() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i <= 100000; i++) {
                count++;
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i <= 100000; i++) {
                count++;
            }
        });

        t1.start();
        t2.start();

        System.out.println("Count is: " + count);
    }

    /**
     * Synchronized method, but sometimes get 20k and sometimes 14k, etc.
     * @throws InterruptedException
     */
    public void doWorkHalfSync() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                count++;
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                count++;
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Count is: " + count);
    }

    /**
     * Synchronized method, always get 20k. Because the threads are sync, using synchronized method.
     */
    public void doWorkSync() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                increment();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Count is: " + count);
    }
}
