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
    public Blinky(int x, int y, int red, int green, int blue, Board board, Direction direction) {
        super(x, y, red, green, blue, board, direction);
    }


    /**
     * Manhattan metric calculation
     * @param Pacman_position pacman position on the bord
     * @param Clyde_position Clyde position on the bord
     * @return distance between Pacman and Clyde
     */
    private double manhattanMetric(ConstVector2d Pacman_position, ConstVector2d Clyde_position ){
        double length;

        length = Math.abs(Pacman_position.getX()-Clyde_position.getX()) + Math.abs(Pacman_position.getY()-Clyde_position.getY());
        return length;
    }



    public void algorithmBlinky(double dt, Player player) {



        Direction direction;

        ConstVector2d Pacman_position;
        ConstVector2d Blinky_position;

        Pacman_position = player.getLocalCenter();
        Blinky_position = getLocalCenter();

        double length = manhattanMetric(Pacman_position, Blinky_position);


    }

    @Override
    public void run() {

    }
}
