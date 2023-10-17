package com.bootcamp.multithreading.services;

import com.bootcamp.multithreading.Main;
import com.bootcamp.multithreading.models.ColorPack;
import com.bootcamp.multithreading.utils.ConsoleColors;
import com.bootcamp.multithreading.utils.Utils;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.bootcamp.multithreading.utils.ConsoleColors.generateRandomColorPack;

public class ThreadForPool implements Runnable {

    private int id;

    private ColorPack colors;

    public ThreadForPool(int id) {
        this.id = id;
        this.colors = generateRandomColorPack();
    }

    @Override
    public void run() {
        long seconds = Utils.getSecondsOfTime(Main.getStartTime(), System.currentTimeMillis());
        String formattedTime = Utils.showFormattedTime(Main.getStartTime(), System.currentTimeMillis());
        System.out.printf(getRepeatedPattern(seconds) + ConsoleColors.colorize("[Thread-" + id + "] Starting... " + formattedTime, colors));

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        seconds = Utils.getSecondsOfTime(Main.getStartTime(), System.currentTimeMillis());
        formattedTime = Utils.showFormattedTime(Main.getStartTime(), System.currentTimeMillis());
        System.out.printf(getRepeatedPattern(seconds) + ConsoleColors.colorize(
                "[Thread-" + id + "]" + ConsoleColors.RESET + " Completed. " + formattedTime, colors));
    }

    private String getRepeatedPattern(long repetition) {
        String pattern = Stream.generate(() -> "| ")
                //.limit(id * 5 + 5)
                .limit(repetition * 2 + 5)
                .collect(Collectors.joining())
                .concat(ConsoleColors.RESET);
        return pattern;
    }
}
