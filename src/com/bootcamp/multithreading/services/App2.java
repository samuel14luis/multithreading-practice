package com.bootcamp.multithreading.services;

import java.security.SecureRandom;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static com.bootcamp.multithreading.utils.Utils.formattedList;
import static com.bootcamp.multithreading.utils.Utils.getRepeatedPattern;

public class App2 {

    private volatile static BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
    private static final int QUEUE_MAX_SIZE = 10;

    private static synchronized void putQueue(Integer number) throws InterruptedException {
        App2.queue.put(number);
    }

    private static void producer() throws InterruptedException {
        SecureRandom random = new SecureRandom();

        while(true) {
            putQueue(random.nextInt(100));
            Thread.sleep(100); //simulate a slower producer
        }
    }

    private static void consumer() throws InterruptedException {
        SecureRandom random = new SecureRandom();
        while(true) {
            Thread.sleep(62); //simulate a faster consumer

            if(random.nextInt(10) % 2 == 0) {
                Integer value = queue.take();
                String show = formattedList(queue, QUEUE_MAX_SIZE) + " Taken value: " + value + "; Queue size is: " + queue.size();
                System.out.print(getRepeatedPattern(show.length() + 5, "\b") + show);
            }
        }
    }

    public static void exec() throws InterruptedException {
        Thread producer = new Thread(() -> {
            try {
                producer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

    }

}
