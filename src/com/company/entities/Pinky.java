package com.company.entities;

import com.company.Board;
import com.company.Direction;
import com.company.Vector2d.ConstVector2d;

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
    public Pinky(int x, int y, int red, int green, int blue, Board board, Direction direction) {
        super(x, y, red, green, blue, board, direction);
    }

    /**
     * The pink ghost "Pinky" movement rules
     * @param dt delta time
     * @param player pacman controlled by the player
     */
    public void algorithmPinky(double dt, Player player) {

        Direction direction;
        ConstVector2d Pacman_position;
        ConstVector2d Pinky_position;

        Pacman_position = player.getLocalCenter();
        Pinky_position = getLocalCenter();
        direction = getCurrentDirection();

        if (direction == Direction.up){
            if (Double.compare(Pacman_position.getY(), Pinky_position.getY()) < 0 && couldMoveToDirection(Direction.up,dt) ){
                setCurrentDirection(Direction.up);
                localCenter_.add(Direction.toVector2d(Direction.up).copy().times(dt * GHOST_SPEED));
            }
            else if( Double.compare(Pacman_position.getX(), Pinky_position.getX()) > 0 && couldMoveToDirection(Direction.right,dt)){
                setCurrentDirection(Direction.right);
                localCenter_.add(Direction.toVector2d(Direction.right).copy().times(dt * GHOST_SPEED));
            }
            else if ( Double.compare(Pacman_position.getX(), Pinky_position.getX()) < 0 && couldMoveToDirection(Direction.left,dt) ){
                setCurrentDirection(Direction.left);
                localCenter_.add(Direction.toVector2d(Direction.left).copy().times(dt * GHOST_SPEED));
            }
            else if (couldMoveToDirection(Direction.up,dt)){
                setCurrentDirection(Direction.up);
                localCenter_.add(Direction.toVector2d(Direction.up).copy().times(dt * GHOST_SPEED));
            }
            else if (couldMoveToDirection(Direction.right,dt)){
                setCurrentDirection(Direction.right);
                localCenter_.add(Direction.toVector2d(Direction.right).copy().times(dt * GHOST_SPEED));
            }
            else {
                setCurrentDirection(Direction.left);
                localCenter_.add(Direction.toVector2d(Direction.left).copy().times(dt * GHOST_SPEED));
            }
        }
        else if ( direction == Direction.down ){
            if (Double.compare(Pacman_position.getY(), Pinky_position.getY()) > 0 && couldMoveToDirection(Direction.down,dt) ){
                setCurrentDirection(Direction.down);
                localCenter_.add(Direction.toVector2d(Direction.down).copy().times(dt * GHOST_SPEED));
            }
            else if( Double.compare(Pacman_position.getX(), Pinky_position.getX()) > 0 && couldMoveToDirection(Direction.right,dt)){
                setCurrentDirection(Direction.right);
                localCenter_.add(Direction.toVector2d(Direction.right).copy().times(dt * GHOST_SPEED));
            }
            else if ( Double.compare(Pacman_position.getX(), Pinky_position.getX()) < 0 && couldMoveToDirection(Direction.left,dt) ){
                setCurrentDirection(Direction.left);
                localCenter_.add(Direction.toVector2d(Direction.left).copy().times(dt * GHOST_SPEED));
            }
            else if (couldMoveToDirection(Direction.down,dt)){
                setCurrentDirection(Direction.down);
                localCenter_.add(Direction.toVector2d(Direction.down).copy().times(dt * GHOST_SPEED));
            }
            else if (couldMoveToDirection(Direction.right,dt)){
                setCurrentDirection(Direction.right);
                localCenter_.add(Direction.toVector2d(Direction.right).copy().times(dt * GHOST_SPEED));
            }
            else {
                setCurrentDirection(Direction.left);
                localCenter_.add(Direction.toVector2d(Direction.left).copy().times(dt * GHOST_SPEED));
            }
        }
        else if ( direction == Direction.left ){
            if ( Double.compare(Pacman_position.getX(), Pinky_position.getX()) < 0 && couldMoveToDirection(Direction.left,dt) ){
                setCurrentDirection(Direction.left);
                localCenter_.add(Direction.toVector2d(Direction.left).copy().times(dt * GHOST_SPEED));
            }
            else if( Double.compare(Pacman_position.getY(), Pinky_position.getY()) < 0 && couldMoveToDirection(Direction.up,dt) ){
                setCurrentDirection(Direction.up);
                localCenter_.add(Direction.toVector2d(Direction.up).copy().times(dt * GHOST_SPEED));
            }
            else if( Double.compare(Pacman_position.getY(), Pinky_position.getY()) > 0 && couldMoveToDirection(Direction.down,dt) ){
                setCurrentDirection(Direction.down);
                localCenter_.add(Direction.toVector2d(Direction.down).copy().times(dt * GHOST_SPEED));
            }
            else if (couldMoveToDirection(Direction.left,dt)){
                setCurrentDirection(Direction.left);
                localCenter_.add(Direction.toVector2d(Direction.left).copy().times(dt * GHOST_SPEED));
            }
            else if (couldMoveToDirection(Direction.up,dt)){
                setCurrentDirection(Direction.up);
                localCenter_.add(Direction.toVector2d(Direction.up).copy().times(dt * GHOST_SPEED));
            }
            else {
                setCurrentDirection(Direction.down);
                localCenter_.add(Direction.toVector2d(Direction.down).copy().times(dt * GHOST_SPEED));
            }
        }
        else {
            if ( Double.compare(Pacman_position.getX(), Pinky_position.getX()) > 0 && couldMoveToDirection(Direction.right,dt) ){
                setCurrentDirection(Direction.right);
                localCenter_.add(Direction.toVector2d(Direction.right).copy().times(dt * GHOST_SPEED));
            }
            else if( Double.compare(Pacman_position.getY(), Pinky_position.getY()) < 0 && couldMoveToDirection(Direction.up,dt) ){
                setCurrentDirection(Direction.up);
                localCenter_.add(Direction.toVector2d(Direction.up).copy().times(dt * GHOST_SPEED));
            }
            else if( Double.compare(Pacman_position.getY(), Pinky_position.getY()) > 0 && couldMoveToDirection(Direction.down,dt) ){
                setCurrentDirection(Direction.down);
                localCenter_.add(Direction.toVector2d(Direction.down).copy().times(dt * GHOST_SPEED));
            }
            else if (couldMoveToDirection(Direction.right,dt)){
                setCurrentDirection(Direction.right);
                localCenter_.add(Direction.toVector2d(Direction.right).copy().times(dt * GHOST_SPEED));
            }
            else if (couldMoveToDirection(Direction.up,dt)){
                setCurrentDirection(Direction.up);
                localCenter_.add(Direction.toVector2d(Direction.up).copy().times(dt * GHOST_SPEED));
            }
            else {
                setCurrentDirection(Direction.down);
                localCenter_.add(Direction.toVector2d(Direction.down).copy().times(dt * GHOST_SPEED));
            }
        }


    }

    @Override
    public void run() {

    }
}
