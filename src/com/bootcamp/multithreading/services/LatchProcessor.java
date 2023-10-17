package com.bootcamp.multithreading.services;

import com.bootcamp.multithreading.models.ColorPack;

import java.util.concurrent.CountDownLatch;

import static com.bootcamp.multithreading.utils.ConsoleColors.generateRandomColorPack;

public class LatchProcessor implements Runnable {

    private CountDownLatch latch;
    private int id;
    private ColorPack colors;
    private String pattern = "| ";

    public LatchProcessor(CountDownLatch latch, int id) {
        this.latch = latch;
        this.id = id;
        this.colors = generateRandomColorPack();
    }

    public void run() {
        System.out.println("Started.");

        try {
            Thread.sleep(4600);
            System.out.println("Thread " + id + " is waiting for the latch.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        latch.countDown();
    }

}
