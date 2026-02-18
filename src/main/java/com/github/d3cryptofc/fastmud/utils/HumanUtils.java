package com.github.d3cryptofc.fastmud.utils;

public class HumanUtils {

    public static String booleanToHumanLikeOnOff(boolean value) {
        return String.format(
            "%s%s%s",
            value ? AnsiColor.GREEN : AnsiColor.DARK_RED,
            value ? "on" : "off",
            AnsiColor.RESET
        );
    }
}
