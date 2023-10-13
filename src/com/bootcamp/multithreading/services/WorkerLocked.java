package com.bootcamp.multithreading.services;

import com.bootcamp.multithreading.utils.ConsoleColors;

import java.util.ArrayList;
import java.util.List;

import static com.bootcamp.multithreading.utils.Utils.showFormattedTime;

public class WorkerLocked {

    String color = ConsoleColors.RED_FONT;
    String background = ConsoleColors.BLACK_BACKGROUND;

    private Object lock1 = new Object();
    private Object lock2 = new Object();

    private List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();

    public void stageOneLocked() {
        synchronized (lock1) {
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list1.add(1);
        }
    }

    public void stageTwoLocked() {
        synchronized (lock2) {
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list2.add(1);
        }
    }

    public void processLocked() {
        for (int i = 0; i < 1000; i++) {
            stageOneLocked();
            stageTwoLocked();
        }
    }

    public void mainLocked() {
        System.out.println("Starting ...");

        long start = System.currentTimeMillis();

        Thread t1 = new Thread(() -> { processLocked(); });
        Thread t2 = new Thread(() -> { processLocked(); });

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
        System.out.printf(ConsoleColors.colorize("[Locked] " + text, color, background));
    }

}
