package com.bootcamp.multithreading.utils;

public class Utils {

    public static String showFormattedTime(long start, long end) {
        long totalTime = end - start;
        long seconds = (totalTime / 1000) % 60;
        long minutes = (totalTime / (1000 * 60)) % 60;
        return minutes + "m " + seconds + "s.";
    }

    public static long getSecondsOfTime(long start, long end) {
        long totalTime = end - start;
        long seconds = (totalTime / 1000) % 60;
        return seconds;
    }

}
