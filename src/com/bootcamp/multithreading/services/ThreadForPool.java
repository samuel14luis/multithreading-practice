package com.bootcamp.multithreading.services;

import com.bootcamp.multithreading.Main;
import com.bootcamp.multithreading.models.ColorPack;
import com.bootcamp.multithreading.utils.ConsoleColors;
import com.bootcamp.multithreading.utils.Utils;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.bootcamp.multithreading.utils.ConsoleColors.generateRandomColorPack;
import static com.bootcamp.multithreading.utils.Utils.getRepeatedPattern;

public class ThreadForPool implements Runnable {

    private int id;

    private ColorPack colors;
    private String pattern = "| ";

    public ThreadForPool(int id) {
        this.id = id;
        this.colors = generateRandomColorPack();
    }

    @Override
    public void run() {
        long seconds = Utils.getSecondsOfTime(Main.getStartTime(), System.currentTimeMillis());
        String formattedTime = Utils.showFormattedTime(Main.getStartTime(), System.currentTimeMillis());
        System.out.printf(getRepeatedPattern(seconds, pattern) + ConsoleColors.colorize("[Thread-" + id + "] Starting... " + formattedTime, colors));

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        seconds = Utils.getSecondsOfTime(Main.getStartTime(), System.currentTimeMillis());
        formattedTime = Utils.showFormattedTime(Main.getStartTime(), System.currentTimeMillis());
        System.out.printf(getRepeatedPattern(seconds, pattern) + ConsoleColors.colorize(
                "[Thread-" + id + "]" + ConsoleColors.RESET + " Completed. " + formattedTime, colors));
    }
}
