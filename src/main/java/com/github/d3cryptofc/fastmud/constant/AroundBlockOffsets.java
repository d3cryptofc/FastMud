package com.github.d3cryptofc.fastmud.constant;

public class AroundBlockOffsets {

    public static final int[][] X = {
        { -1, 0, 0 }, // X negative.
        { +1, 0, 0 }, // X positive.
    };

    public static final int[][] Y = {
        { 0, -1, 0 }, // Y negative.
        { 0, +1, 0 }, // Y positive.
    };

    public static final int[][] Z = {
        { 0, 0, -1 }, // Z negative.
        { 0, 0, +1 }, // Z positive.
    };

    // X and Z offsets.
    public static final int[][] XZ = { X[0], X[1], Z[0], Z[1] };

    // X, Z and Y BOTTOM offsets.
    public static final int[][] XZB = { X[0], X[1], Y[0], Z[0], Z[1] };

    // X, Z and Y TOP offsets.
    public static final int[][] XZT = { X[0], X[1], Y[1], Z[0], Z[1] };

    // X, Z and Y offsets.
    public static final int[][] XYZ = { X[0], X[1], Y[0], Y[1], Z[0], Z[1] };
}
