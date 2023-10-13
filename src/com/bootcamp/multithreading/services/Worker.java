package com.bootcamp.multithreading.services;

import com.bootcamp.multithreading.utils.ConsoleColors;

import java.util.ArrayList;
import java.util.List;

import static com.bootcamp.multithreading.utils.Utils.showFormattedTime;

public class Worker {

    String color = ConsoleColors.BLUE_FONT;
    String background = ConsoleColors.BLACK_BACKGROUND;

    private List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();

    public synchronized void stageOne() {
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        list1.add(1);
    }

    public synchronized void stageTwo() {
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        list2.add(1);
    }

    public void process() {
        for (int i = 0; i < 1000; i++) {
            stageOne();
            stageTwo();
        }
    }

    public void main() {
        System.out.println("Starting ...");

        long start = System.currentTimeMillis();

        Thread t1 = new Thread(() -> { process(); });
        Thread t2 = new Thread(() -> { process(); });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();

        sout("Time taken: " + showFormattedTime(start, end));
        sout("List1: " + list1.size() + "; List2: " + list2.size());
    }

    private void sout(String text) {
        System.out.printf(ConsoleColors.colorize("[Sync] " + text, color, background));
    }

}
