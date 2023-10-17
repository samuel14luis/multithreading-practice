package com.bootcamp.multithreading.utils;

import com.bootcamp.multithreading.models.ColorModel;
import com.bootcamp.multithreading.models.ColorPack;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConsoleColors {

    private static final int CONTRAST_THRESHOLD = 50;
    private static SecureRandom random = new SecureRandom();

    private static List<ColorModel> fontColors = new ArrayList<>() {{
        add(new ColorModel("BLACK_FONT", "\u001b[30m", 0));
        add(new ColorModel("RED_FONT", "\u001b[91m", 50));
        add(new ColorModel("GREEN_FONT", "\u001b[92m", 65));
        add(new ColorModel("BLACK_GREEN_FONT", "\033[0;32m", 32));
        add(new ColorModel("YELLOW_FONT", "\u001b[93m", 90));
        add(new ColorModel("BLUE_FONT", "\u001b[34m", 55));
        add(new ColorModel("PURPLE_FONT", "\u001b[35m", 55));
        add(new ColorModel("CYAN_FONT", "\u001b[36m", 35));
        add(new ColorModel("GRAY_FONT", "\u001b[37m", 50));
        add(new ColorModel("WHITE_FONT", "\u001b[97m", 100));
    }};

    private static List<ColorModel> bgColors = new ArrayList<>() {{
        add(new ColorModel("BLACK_BACKGROUND", "\u001b[40m", 0));
        add(new ColorModel("RED_BACKGROUND", "\u001b[41m", 45));
        add(new ColorModel("GREEN_BACKGROUND", "\u001b[42m", 50));
        add(new ColorModel("YELLOW_BACKGROUND", "\u001b[103m", 65));
        add(new ColorModel("BLUE_BACKGROUND", "\u001b[44m", 30));
        add(new ColorModel("PURPLE_BACKGROUND", "\u001b[45m", 50));
        add(new ColorModel("CYAN_BACKGROUND", "\u001b[46m", 40));
        add(new ColorModel("GRAY_BACKGROUND", "\u001b[47m", 50));
        add(new ColorModel("WHITE_BACKGROUND", "\u001b[107m", 100));
    }};

    private static Map<String, ColorModel> mapFonts = fontColors.stream()
            .collect(Collectors.toMap(font -> font.getName(), color -> color));

    private static Map<String, ColorModel> mapBg = bgColors.stream()
            .collect(Collectors.toMap(bg -> bg.getName(), color -> color));

    public static final String RESET = "\u001b[0m";
    public static final String NEW_LINE = "%n";
    public static final String BLACK_FONT = mapFonts.get("BLACK_FONT").getColor();
    public static final String RED_FONT = mapFonts.get("RED_FONT").getColor();
    public static final String GREEN_FONT = mapFonts.get("GREEN_FONT").getColor();
    public static final String BLACK_GREEN_FONT = mapFonts.get("BLACK_GREEN_FONT").getColor();
    public static final String YELLOW_FONT = mapFonts.get("YELLOW_FONT").getColor();
    public static final String BLUE_FONT = mapFonts.get("BLUE_FONT").getColor();
    public static final String PURPLE_FONT = mapFonts.get("PURPLE_FONT").getColor();
    public static final String CYAN_FONT = mapFonts.get("CYAN_FONT").getColor();
    public static final String WHITE_FONT = mapFonts.get("WHITE_FONT").getColor();
    public static final String BLACK_BACKGROUND = mapBg.get("BLACK_BACKGROUND").getColor();
    public static final String RED_BACKGROUND = mapBg.get("RED_BACKGROUND").getColor();
    public static final String GREEN_BACKGROUND = mapBg.get("GREEN_BACKGROUND").getColor();
    public static final String YELLOW_BACKGROUND = mapBg.get("YELLOW_BACKGROUND").getColor();
    public static final String BLUE_BACKGROUND = mapBg.get("BLUE_BACKGROUND").getColor();
    public static final String PURPLE_BACKGROUND = mapBg.get("PURPLE_BACKGROUND").getColor();
    public static final String CYAN_BACKGROUND = mapBg.get("CYAN_BACKGROUND").getColor();
    public static final String WHITE_BACKGROUND = mapBg.get("WHITE_BACKGROUND").getColor();

    public static String colorize(String text, String color) {
        return color + text + RESET + NEW_LINE;
    }

    public static String colorize(String text, ColorPack colorPack) {
        return colorize(text, colorPack.getFontColor().getColor(), colorPack.getBackgroundColor().getColor());
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


    public static ColorModel getRandomBackgroundColor() {
        return bgColors.get(random.nextInt(bgColors.size()));
    }

    public static ColorModel getRandomFontColor() {
        return fontColors.get(random.nextInt(fontColors.size()));
    }

    public static ColorPack generateRandomColorPack(ColorModel font, ColorModel background) {
        //validar contraste entre colores
        ColorModel fontColor = font == null ? getRandomFontColor() : font;
        ColorModel bgColor = background == null ? getRandomBackgroundColor() : background;

        int diff = Math.abs(fontColor.getBrightness() - bgColor.getBrightness());

        while (diff < CONTRAST_THRESHOLD) {
            fontColor = font == null ? getRandomFontColor() : font;
            bgColor = background == null ? getRandomBackgroundColor() : background;
            diff = Math.abs(fontColor.getBrightness() - bgColor.getBrightness());
        }
        //System.out.println("final diff: " + diff); TODO: DELETE LOGS

        return new ColorPack(fontColor, bgColor);
    }

    public static ColorPack generateRandomColorPack() {
        return generateRandomColorPack(null, null);
    }

    /**
     * Algunos buenos criterios para asignar valores de luminosidad a colores al validar contraste son:
     * - Utilizar un modelo de color estándar como HSL o HSV, y
     *   tomar el valor de "brillo" (lightness en inglés) como luminosidad.
     * - Utilizar la fórmula de luminosidad relativa: 0.2126 * Rojo + 0.7152 * Verde + 0.0722 * Azul.
     *   Esto da un valor 0-255 proporcional a la luminosidad percibida.
     * - Tomar el mayor valor entre el Rojo, Verde y Azul como luminosidad aproximada.
     * - Utilizar bibliotecas de procesamiento de imágenes que calculen luminosidad a partir de RGB.
     * - Definir rangos de 0-100 basados en la luminosidad relativa de los colores. Por ejemplo:
     *   Negro: 0
     *   Gris medio: 50
     *   Blanco: 100
     *   Rojo: 45
     *   Azul: 25
     * - Hacer pruebas visuales mostrando texto de colores sobre fondos y ajustar valores de luminosidad
     *   para lograr suficiente contraste.
     *
     * En resumen, las mejores opciones son usar un modelo de color estándar o la fórmula de luminosidad relativa.
     * Rangos de 0-100 basados en pruebas visuales también funcionan bien en la práctica.
     *
     * Lo importante es asignar valores que reflejen razonablemente la luminosidad relativa percibida entre colores.
     */
    public static void showColors() {
        System.out.println("Font Colors");
        fontColors.forEach(color -> System.out.println(
                color.getColor() + color.getName() + "::" + color.getBrightness() + RESET));
        System.out.println("Background Colors");
        bgColors.forEach(color -> System.out.println(
                color.getColor() + color.getName() + "::" + color.getBrightness() + RESET));

        //generar 5 colores aleatorios
        for (int i = 0; i < 10; i++) {
            ColorPack colorPack = generateRandomColorPack();
            //ColorPack colorPack = generateRandomColorPack(null, mapBg.get("GREEN_BACKGROUND"));
            System.out.printf(colorize(
                    "Color: " +
                            colorPack.getFontColor().getName() + "::" + colorPack.getFontColor().getBrightness() + " - " +
                            colorPack.getBackgroundColor().getName() + "::" + colorPack.getBackgroundColor().getBrightness(),
                    colorPack.getFontColor().getColor(),
                    colorPack.getBackgroundColor().getColor()
            ));
        }
    }
}
