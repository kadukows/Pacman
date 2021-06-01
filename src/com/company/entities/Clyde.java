package com.company.entities;

import com.company.Board;
import com.company.Direction;

public class Clyde extends Ghost implements Runnable{
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
    public Clyde(int x, int y, int red, int green, int blue, Board board, Direction direction) {
        super(x, y, red, green, blue, board, direction);
    }

    public void update(double dt, Player player) {

        Direction direction;
        direction = Direction.right;
        if (couldMoveToDirection(direction, dt)) {
            localCenter_.add(Direction.toVector2d(direction).copy().times(dt * GHOST_SPEED));

        }


    }


    @Override
    public void run() {

    }
}
