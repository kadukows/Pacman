package com.company.entities;

import com.company.Board;
import com.company.Direction;
import com.company.Vector2d.ConstVector2d;

import java.util.ArrayList;
import java.util.Collections;

public class Clyde extends Ghost {
    /**
     * Default constructor for Ghost class.
     *
     * @param x     starting x-coordinate for ghost
     * @param y     starting y-coordinate for ghost
     * @param red   is color RGB for ghost
     * @param green is color RGB for ghost
     * @param blue  is color RGB for ghost
     * @param board board this ghost belongs to
     * @param direction starting direction
     * @param player Pacman
     */
    public Clyde(int x, int y, int red, int green, int blue, Board board, Direction direction, Player player) {
        super(x, y, red, green, blue, board, direction, player);
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


    /**
     * The orange ghost "Clyde" movement rules
     *
     * @param dt delta time
     */
    public void algorithmClyde(double dt) {

        Direction direction;
        Direction forbiddenDirection;
        ConstVector2d Pacman_position;
        ConstVector2d Clyde_position;
        double length;
        boolean flag;

        flag = false;
        Pacman_position = player_.getLocalCenter();
        Clyde_position = getLocalCenter();

        length = manhattanMetric(Pacman_position, Clyde_position);

        ArrayList<Direction> availableDirections = new ArrayList<>();
        availableDirections.add(Direction.up);
        availableDirections.add(Direction.down);
        availableDirections.add(Direction.left);
        availableDirections.add(Direction.right);

        direction = getCurrentDirection();
        forbiddenDirection = searchForbiddenDirection(direction);
        availableDirections.remove(forbiddenDirection);
        Collections.shuffle(availableDirections);


        if ( couldMoveToDirection(currentDirection_, dt) && length > 5){
            setCurrentDirection(currentDirection_);
            moveToDirection(currentDirection_, dt);
            flag = true;
        }

        if ( length < 5 && !flag){
            if (availableDirections.contains(Direction.down)){
                if (Pacman_position.getY() < Clyde_position.getY() && couldMoveToDirection(Direction.down, dt)){
                    setCurrentDirection(Direction.down);
                    moveToDirection(Direction.down, dt);
                    flag = true;
                }
            }
           if (availableDirections.contains(Direction.up) && !flag){
               if (Pacman_position.getY() > Clyde_position.getY() && couldMoveToDirection(Direction.up, dt)){
                    setCurrentDirection(Direction.up);
                    moveToDirection(Direction.up, dt);
                    flag = true;
                }
            }

           if (availableDirections.contains(Direction.right) && !flag){
               if (Pacman_position.getX() < Clyde_position.getX() && couldMoveToDirection(Direction.right, dt)){
                    setCurrentDirection(Direction.right);
                    moveToDirection(Direction.right, dt);
                    flag = true;
                }
            }

           if (availableDirections.contains(Direction.left)  && !flag){
               if (Pacman_position.getX() > Clyde_position.getX() && couldMoveToDirection(Direction.left, dt)){
                    setCurrentDirection(Direction.left);
                   moveToDirection(Direction.left, dt);
                    flag = true;
                }
            }
           if (!flag){
               moveToAvailableDirection(forbiddenDirection, dt);
               flag = true;
           }
        }
        if (!flag) {
            moveToAvailableDirection(forbiddenDirection, dt);
            }

    }

    @Override
    public void update(double dt) {
        algorithmClyde(dt);
    }
}
