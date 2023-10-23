package com.bootcamp.multithreading.services;

import java.security.SecureRandom;

public class App14InterruptingThreads {

    public static void run() {
        System.out.println("Starting ...");

        Thread t1 = new Thread(() -> {
            SecureRandom random = new SecureRandom();
            for(int i = 0; i < 1E8; i++) {
                /*if(Thread.currentThread().isInterrupted()) {
                    System.out.println("Interrupted!");
                    break;
                }*/

                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    System.out.println("Interrupted!");
                    break;
                }

                Math.sin(random.nextDouble());
            }
        });

        t1.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t1.interrupt();

        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Finished.");
    }
}
