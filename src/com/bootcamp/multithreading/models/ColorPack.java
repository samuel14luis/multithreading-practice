package com.bootcamp.multithreading.models;

public class ColorPack {

    private ColorModel fontColor;

    private ColorModel backgroundColor;

    public ColorPack(ColorModel fontColor, ColorModel backgroundColor) {
        this.fontColor = fontColor;
        this.backgroundColor = backgroundColor;
    }

    public ColorModel getFontColor() {
        return fontColor;
    }

    public ColorModel getBackgroundColor() {
        return backgroundColor;
    }

}
