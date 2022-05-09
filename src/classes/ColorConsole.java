package classes;

import interfaces.Colorable;

public enum ColorConsole implements Colorable {
    ANSI_RESET("\u001B[0m"),
    ANSI_RED("\u001B[31m"),
    ANSI_GREEN("\u001B[32m"),
    ANSI_YELLOW("\u001B[33m");

    private String color;

    ColorConsole(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String color() {
        return getColor();
    }
}
