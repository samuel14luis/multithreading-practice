package utils;

public class ConsoleColors {
    public static final String RESET = "\u001b[0m";
    public static final String NEW_LINE = "%n";
    public static final String BLACK_FONT = "\u001b[30m";
    public static final String RED_FONT = "\u001b[31m";
    public static final String GREEN_FONT = "\u001b[32m";
    public static final String BLACK_GREEN_FONT = "\033[0;32m";
    public static final String YELLOW_FONT = "\u001b[33m";
    public static final String BLUE_FONT = "\u001b[34m";
    public static final String PURPLE_FONT = "\u001b[35m";
    public static final String CYAN_FONT = "\u001b[36m";
    public static final String WHITE_FONT = "\u001b[37m";
    public static final String BLACK_BACKGROUND = "\u001b[40m";
    public static final String RED_BACKGROUND = "\u001b[41m";
    public static final String GREEN_BACKGROUND = "\u001b[42m";
    public static final String YELLOW_BACKGROUND = "\u001b[43m";
    public static final String BLUE_BACKGROUND = "\u001b[44m";
    public static final String PURPLE_BACKGROUND = "\u001b[45m";
    public static final String CYAN_BACKGROUND = "\u001b[46m";
    public static final String WHITE_BACKGROUND = "\u001b[47m";

    public static String colorize(String text, String color) {
        return color + text + RESET + NEW_LINE;
    }

    public static String colorize(String text, String color, String background) {
        return color + background + text + RESET + NEW_LINE;
    }

    public static String colorize(String text, String color, String background, boolean bold) {
        return (bold ? "\u001b[1m" : "") + color + background + text + RESET + NEW_LINE;
    }

    public static String blackTag(String text) {
        return ConsoleColors.colorize(text,
                ConsoleColors.BLUE_FONT,
                ConsoleColors.BLACK_BACKGROUND);

    }

    public static String redTag(String text) {
        return ConsoleColors.colorize(text,
                ConsoleColors.BLACK_FONT,
                ConsoleColors.RED_BACKGROUND);
    }

    public static String yellowTag(String text) {
        return ConsoleColors.colorize(text,
                ConsoleColors.BLACK_GREEN_FONT,
                ConsoleColors.YELLOW_BACKGROUND, true);
    }


}
