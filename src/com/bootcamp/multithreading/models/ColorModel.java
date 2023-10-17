package com.bootcamp.multithreading.models;

public class ColorModel {

    private String name;
    private String color;
    private int brightness;

    public ColorModel(String name, String color, int brightness) {
        this.name = name;
        this.color = color;
        this.brightness = brightness;
    }

    private ColorModel() {}

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public int getBrightness() {
        return brightness;
    }

    public String toString() {
        return "ColorModel{" + "name=" + name + ", color=" + color + ", brightness=" + brightness + '}';
    }

}
