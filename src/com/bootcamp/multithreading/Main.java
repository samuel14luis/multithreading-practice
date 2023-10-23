package com.bootcamp.multithreading;

import com.bootcamp.multithreading.services.*;
import com.bootcamp.multithreading.utils.ConsoleColors;

import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.bootcamp.multithreading.services.App2.*;

public class Main {

    private static final Long startTime = System.currentTimeMillis();

    public static void main(String[] args) {
        //test1();
        //test2();
        //test3();
        //test4();
        //test5();
        //test6();
        //test7();
        //test8();
        //test9();
        test10();
    }

    public static synchronized Long getStartTime() {
        return startTime;
    }

    private static void test1() {
        Thread t1 = new Thread(() -> {

            long startTime = System.currentTimeMillis();
            int threadNumber = 10;

            for (int i = 0; i < threadNumber; i++) {
                Thread thread = createThread(String.valueOf(i));
                thread.start();
            }

            long endTime = System.currentTimeMillis();

            // Calcula la diferencia de tiempo en milisegundos
            long totalTime = endTime - startTime;

            // Convierte los milisegundos a minutos y segundos
            long segundos = (totalTime / 1000) % 60;
            long minutos = (totalTime / (1000 * 60)) % 60;

            System.out.println("Tiempo transcurrido: " + minutos + "m " + segundos + "s.");

        });

        t1.start();
    }

    private static void test2() {

        Processor proc1 = new Processor();
        proc1.start();

        System.out.println("Press return to stop...");
        Scanner scan = new Scanner(System.in);
        scan.nextLine();

        proc1.shutdown();
    }

    private static void test3() {
        new ThreadSync().doWorkAsync();
        new ThreadSync().doWorkHalfSync();
        new ThreadSync().doWorkSync();
    }

    /**
     * Shared variables.
     */
    private static void test4() {
        new Worker().main();
        new WorkerLocked().mainLocked();
    }

    /**
     * Pool of threads.
     */
    private static void test5() {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            executor.submit(new ThreadForPool(i));
        }

        executor.shutdown();

        System.out.println(ConsoleColors.GREEN_FONT + "All tasks submitted." + ConsoleColors.RESET);

        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(ConsoleColors.GREEN_FONT + "All tasks completed." + ConsoleColors.RESET);
    }

    /**
     * CountDownLatch.
     */
    private static void test6() {

        CountDownLatch latch = new CountDownLatch(3);

        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 5; i++) {
            executor.submit(new LatchProcessor(latch, i));
        }

        System.out.println(ConsoleColors.GREEN_FONT + "All tasks submitted." + ConsoleColors.RESET);

        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(ConsoleColors.GREEN_FONT + "All tasks completed." + ConsoleColors.RESET);

        executor.shutdown();

        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * Using threads as producer and consumer.
     */
    private static void test7() {
        try {
            exec();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Using threads as producer and consumer with wait and notify.
     * In short, produce() will wait until consume() calls notify(),
     * while consume() will wait for user input before calling notify().
     * This coordinates execution between the two threads using wait/notify for synchronization.
     */
    private static void test8() {
        final App3Notify processor = new App3Notify();

        Thread t1 = new Thread(() -> {
            try {
                processor.produce();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                processor.consume();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Synchronization on the common lock object ensures that only one thread can access the list at a time.
     * wait() and notify() are used to coordinate them and avoid race conditions.
     *
     * In short, this produces and consumes values from a shared list synchronously using low-level wait/notify.
     */
    private static void test9() {
        final App9LowLevelNotify processor = new App9LowLevelNotify();

        Thread t1 = new Thread(() -> {
            try {
                processor.produce();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                processor.consume();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * ReentrantLocks.
     * The App10ReentrantLocks class uses a ReentrantLock and its associated Conditions
     * for synchronization and communication between threads.
     *
     * It has an increment() method that simply increments a counter count.
     * The firstThread() and secondThread() methods run in separate threads.
     *
     * By using ReentrantLock and Condition, synchronization between threads is more flexible.
     * It allows you to suspend a thread until some event (the signal) occurs,
     * without having to block the entire object with synchronized.
     *
     * The finished() method at the end simply prints the final value of the counter after both threads finish.
     *
     * In summary, this illustrates the use of reentrant locks and conditions
     * for more advanced synchronization between threads.
     */
    private static void test10() {
        final App10ReentrantLocks processor = new App10ReentrantLocks();

        Thread t1 = new Thread(() -> {
            try {
                processor.firstThread(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                processor.secondThread(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        processor.finished();
    }

    private static Thread createThread(String number) {
        int randomThread = (int) (Math.random() * 3);
        switch (randomThread) {
            case 1: return new Thread(new App1("Thread " + number,
                    ConsoleColors.BLUE_FONT,
                    ConsoleColors.BLACK_BACKGROUND, 10));
            case 2: return new Thread(new App1("Thread " + number,
                    ConsoleColors.BLACK_FONT,
                    ConsoleColors.RED_BACKGROUND, 15));
            case 3: return new Thread(new App1("Thread " + number,
                    ConsoleColors.BLACK_GREEN_FONT,
                    ConsoleColors.YELLOW_BACKGROUND, 5));
            default: return new Thread(new App1("Thread " + number,
                    ConsoleColors.BLUE_FONT,
                    ConsoleColors.BLACK_BACKGROUND, 3));
        }
    }

}