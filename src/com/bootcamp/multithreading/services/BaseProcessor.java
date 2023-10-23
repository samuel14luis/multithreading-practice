package com.bootcamp.multithreading.services;

import com.bootcamp.multithreading.Main;
import com.bootcamp.multithreading.models.ColorPack;
import com.bootcamp.multithreading.utils.ConsoleColors;
import com.bootcamp.multithreading.utils.Utils;

import static com.bootcamp.multithreading.utils.ConsoleColors.generateRandomColorPack;
import static com.bootcamp.multithreading.utils.Utils.getRepeatedPattern;

public class BaseProcessor {

    private int id;
    private ColorPack colors;
    private final String pattern = "| ";
    private boolean watchTimeSpace = false;

    public BaseProcessor(int id) {
        this.id = id;
        this.colors = generateRandomColorPack();
    }

    public BaseProcessor(int id, boolean watchTimeSpace) {
        this.id = id;
        this.colors = generateRandomColorPack();
        this.watchTimeSpace = watchTimeSpace;
    }

    public void sout(String text) {
        if (watchTimeSpace) {
            soutTime(text);
        } else {
            soutNormal(text);
        }
    }

    public void soutNormal(String text) {
        String formattedTime = Utils.showFormattedTime(Main.getStartTime(), System.currentTimeMillis());
        System.out.printf(ConsoleColors.colorize("[Thread-" + id + "] " + text + " ... " + formattedTime, colors));
    }


    public void soutTime(String text) {
        long seconds = Utils.getSecondsOfTime(Main.getStartTime(), System.currentTimeMillis());
        String formattedTime = Utils.showFormattedTime(Main.getStartTime(), System.currentTimeMillis());
        System.out.printf(getRepeatedPattern(seconds, pattern) + ConsoleColors.colorize("[Thread-" + id + "] " + text + " ... " + formattedTime, colors));
    }

}
