package com.bootcamp.multithreading.services;

import java.util.Scanner;

public class App3Notify {

    /**
     * The produce() method is synchronized on the App3Notify object,
     * which means that only one thread at a time can execute this method.
     *
     * Inside the produce() method, first print "Running the produce thread...". It then calls wait(),
     * which suspends execution of the current thread until another thread calls notify() on the same object.
     *
     * When it resumes, it prints "Again in the produce thread...".
     *
     * @throws InterruptedException
     */
    public void produce() throws InterruptedException {
        synchronized (this) {
            System.out.println("Running the produce thread...");
            wait();
            System.out.println("Again in the produce thread...");
        }
    }

    /**
     * The consume() method first sleeps the main thread for 2 seconds.
     * It then syncs to the App3Notify object and prints "Running the consume thread, waiting for return key...".
     * Then wait for the user to press Enter using Scanner.
     *
     * When Enter is pressed, it prints "Return key pressed."
     * and calls notify() to notify the waiting thread in produce() that it can resume.
     * It then sleeps the main thread for 5 more seconds before exiting the consume() method.
     *
     * @throws InterruptedException
     */
    public void consume() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Thread.sleep(2000);

        synchronized (this) {
            System.out.println("Running the consume thread, waiting for return key...");
            scanner.nextLine();
            System.out.println("Return key pressed.");
            notify();
            Thread.sleep(5000);
        }
    }
}
