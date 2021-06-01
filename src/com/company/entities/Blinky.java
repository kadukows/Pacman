package com.company.entities;

import com.company.Board;
import com.company.Direction;
import com.company.Vector2d.ConstVector2d;

public class Blinky extends Ghost implements Runnable{

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
    public Blinky(int x, int y, int red, int green, int blue, Board board) {
        super(x, y, red, green, blue, board);
    }

    public void update(double dt, Player player) {



        Direction direction;

        ConstVector2d player_position;
        ConstVector2d Blinky_position;

        player_position = player.getLocalCenter();
        Blinky_position = getLocalCenter();

            direction = Direction.left;
            if (couldMoveToDirection(direction, dt)) {
                localCenter_.add(Direction.toVector2d(direction).copy().times(dt * GHOST_SPEED));

            }


    }

    @Override
    public void run() {

    }
}
