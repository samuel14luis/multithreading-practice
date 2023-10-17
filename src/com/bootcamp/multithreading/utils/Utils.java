package com.bootcamp.multithreading.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.bootcamp.multithreading.utils.ConsoleColors.RED_FONT;
import static com.bootcamp.multithreading.utils.ConsoleColors.RESET;

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
                .concat(RESET);
        return result;
    }

    public static String formattedList(BlockingQueue<Integer> list, int queueSize) {
        //complete one list with zeros to show a fixed size
        List<Integer> copy = new ArrayList<>(list);
        try {
            while(copy.size() < queueSize) {
                copy.add(-1);
            }
        } catch (Exception e) { }

        return copy
                .stream()
                .map(n -> n < 0 ? RED_FONT + "-" + RESET : String.valueOf(n))
                .collect(Collectors.joining(", ", "[", "]"));
    }

}
