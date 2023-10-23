package com.bootcamp.multithreading.services;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.concurrent.*;

public class App13CallableAndFuture {

    public static void run() {
        ExecutorService executor = Executors.newCachedThreadPool();

        // Future<Integer> future = executor.submit(new Callable<Integer>() {
        Future<Integer> future = executor.submit(() -> {
            SecureRandom random = new SecureRandom();
            int duration = random.nextInt(4000);

            if (duration > 2000) {
                throw new IOException("Sleeping for too long.");
            }

            System.out.println("Starting...");
            Thread.sleep(duration);
            System.out.println("Finished.");
            return duration;
        });

        executor.shutdown();

        try {
            System.out.println("Result is: " + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.out.println(e.getMessage());
        }
    }

}
