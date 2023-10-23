package com.bootcamp.multithreading.services;

import com.bootcamp.multithreading.models.Connection;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App12Semaphore {

    public static void run() throws InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 200; i++) {
            executor.submit(() -> {
                Connection.getInstance().connect();
            });
        }
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);
    }
}
