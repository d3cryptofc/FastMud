package com.github.d3cryptofc.fastmud.utils;

import com.github.d3cryptofc.fastmud.constant.AnsiColors;

public class HumanUtils {

    public static String booleanToHumanLikeOnOff(boolean value) {
        return String.format(
            "%s%s%s",
            value ? AnsiColors.GREEN : AnsiColors.DARK_RED,
            value ? "on" : "off",
            AnsiColors.RESET
        );
    }
}
