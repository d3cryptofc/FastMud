package br.minilambda;

public class Constants {
    public static final int[][] AROUND_BLOCK_OFFSETS = {
        {0, -1, 0}, // Bottom.
        {-1, 0, 0}, // X negative.
        {+1, 0, 0}, // X positive.
        {0, 0, -1}, // Z negative.
        {0, 0, +1}  // Z positive.
    };
}