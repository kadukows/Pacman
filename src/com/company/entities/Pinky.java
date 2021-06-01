package com.company.entities;

import com.company.Board;

public class Pinky extends Ghost implements Runnable {
    /**
     * Default constructor for Ghost class.
     *
     * @param x     starting x-coordinate for ghost
     * @param y     starting y-coordinate for ghost
     * @param red   is color RGB for ghost
     * @param green is color RGB for ghost
     * @param blue  is color RGB for ghost
     * @param board board this ghost belongs to
     */
    public Pinky(int x, int y, int red, int green, int blue, Board board) {
        super(x, y, red, green, blue, board);
    }

    @Override
    public void run() {

    }
}
