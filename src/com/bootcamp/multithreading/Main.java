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
        test7();
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