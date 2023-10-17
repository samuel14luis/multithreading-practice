package com.bootcamp.multithreading.utils;

import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public static String getRepeatedPattern(long repetition, String pattern) {
        String result = Stream.generate(() -> pattern)
                //.limit(id * 5 + 5)
                .limit(repetition * 2 + 5)
                .collect(Collectors.joining())
                .concat(ConsoleColors.RESET);
        return result;
    }

}
