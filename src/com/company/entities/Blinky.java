package com.company.entities;

import com.company.Board;
import com.company.Direction;
import com.company.Vector2d.ConstVector2d;

public class Blinky extends Ghost {

    private double time = 0;
    /**
     * Default constructor for Ghost class.
     *
     * @param x     starting x-coordinate for ghost
     * @param y     starting y-coordinate for ghost
     * @param red   is color RGB for ghost
     * @param green is color RGB for ghost
     * @param blue  is color RGB for ghost
     * @param board board this ghost belongs to
     * @param direction Blinky direction
     * @param player Pacman
     */
    public Blinky(int x, int y, int red, int green, int blue, Board board, Direction direction, Player player) {
        super(x, y, red, green, blue, board, direction, player);
    }


    /**
     * The red ghost "Blinky" movement rules
     *
     * @param dt delta time
     */
    public void algorithmBlinky(double dt) {
        time = time + dt;
        if (time > 5){
            time = 0;
            if (speed_ < 8.25){
            setSpeed(0.25);
            }
        }
        Direction direction;
        ConstVector2d Pacman_position;
        ConstVector2d Pinky_position;

        Pacman_position = player_.getLocalCenter();
        Pinky_position = getLocalCenter();
        direction = getCurrentDirection();

        double PARAM  = 0;

        if (direction == Direction.up){
            if (Double.compare(Pacman_position.getY() - PARAM, Pinky_position.getY()) < 0 && couldMoveToDirection(Direction.up,dt) ){
                setCurrentDirection(Direction.up);
                moveToDirection(Direction.up,dt);
            }
            else if( Double.compare(Pacman_position.getX()+PARAM, Pinky_position.getX()) > 0 && couldMoveToDirection(Direction.right,dt)){
                setCurrentDirection(Direction.right);
                moveToDirection(Direction.right,dt);
            }
            else if ( Double.compare(Pacman_position.getX()-PARAM, Pinky_position.getX()) < 0 && couldMoveToDirection(Direction.left,dt) ){
                setCurrentDirection(Direction.left);
                moveToDirection(Direction.left,dt);
            }
            else if (couldMoveToDirection(Direction.up,dt)){
                setCurrentDirection(Direction.up);
                moveToDirection(Direction.up,dt);
            }
            else if (couldMoveToDirection(Direction.right,dt)){
                setCurrentDirection(Direction.right);
                moveToDirection(Direction.right,dt);
            }
            else {
                setCurrentDirection(Direction.left);
                moveToDirection(Direction.left,dt);
            }
        }
        else if ( direction == Direction.down ){
            if (Double.compare(Pacman_position.getY()+PARAM, Pinky_position.getY()) > 0 && couldMoveToDirection(Direction.down,dt) ){
                setCurrentDirection(Direction.down);
                moveToDirection(Direction.down,dt);
            }
            else if( Double.compare(Pacman_position.getX()+PARAM, Pinky_position.getX()) > 0 && couldMoveToDirection(Direction.right,dt)){
                setCurrentDirection(Direction.right);
                moveToDirection(Direction.right,dt);
            }
            else if ( Double.compare(Pacman_position.getX()-PARAM, Pinky_position.getX()) < 0 && couldMoveToDirection(Direction.left,dt) ){
                setCurrentDirection(Direction.left);
                moveToDirection(Direction.left,dt);
            }
            else if (couldMoveToDirection(Direction.down,dt)){
                setCurrentDirection(Direction.down);
                moveToDirection(Direction.down,dt);
            }
            else if (couldMoveToDirection(Direction.right,dt)){
                setCurrentDirection(Direction.right);
                moveToDirection(Direction.right,dt);
            }
            else {
                setCurrentDirection(Direction.left);
                moveToDirection(Direction.left,dt);
            }
        }
        else if ( direction == Direction.left ){
            if ( Double.compare(Pacman_position.getX()-PARAM, Pinky_position.getX()) < 0 && couldMoveToDirection(Direction.left,dt) ){
                setCurrentDirection(Direction.left);
                moveToDirection(Direction.left,dt);
            }
            else if( Double.compare(Pacman_position.getY()-PARAM, Pinky_position.getY()) < 0 && couldMoveToDirection(Direction.up,dt) ){
                setCurrentDirection(Direction.up);
                moveToDirection(Direction.up,dt);
            }
            else if( Double.compare(Pacman_position.getY()+PARAM, Pinky_position.getY()) > 0 && couldMoveToDirection(Direction.down,dt) ){
                setCurrentDirection(Direction.down);
                moveToDirection(Direction.down,dt);
            }
            else if (couldMoveToDirection(Direction.left,dt)){
                setCurrentDirection(Direction.left);
                moveToDirection(Direction.left,dt);
            }
            else if (couldMoveToDirection(Direction.up,dt)){
                setCurrentDirection(Direction.up);
                moveToDirection(Direction.up,dt);
            }
            else if  (couldMoveToDirection(Direction.down,dt)) {
                setCurrentDirection(Direction.down);
                moveToDirection(Direction.down,dt);
            }
            else if (!couldMoveToDirection(Direction.down,dt) && !couldMoveToDirection(Direction.up,dt) && !couldMoveToDirection(Direction.left,dt)){
                setCurrentDirection(Direction.right);
                moveToDirection(Direction.right, dt);
            }
        }
        else if ( direction == Direction.right ){

            if ( Double.compare(Pacman_position.getX()+PARAM, Pinky_position.getX()) > 0 && couldMoveToDirection(Direction.right,dt) ){
                setCurrentDirection(Direction.right);
                moveToDirection(Direction.right,dt);
            }
            else if( Double.compare(Pacman_position.getY()-PARAM, Pinky_position.getY()) < 0 && couldMoveToDirection(Direction.up,dt) ){
                setCurrentDirection(Direction.up);
                moveToDirection(Direction.up,dt);
            }
            else  if( Double.compare(Pacman_position.getY()+PARAM, Pinky_position.getY()) > 0 && couldMoveToDirection(Direction.down,dt) ){
                setCurrentDirection(Direction.down);
                moveToDirection(Direction.down,dt);
            }
            else if (couldMoveToDirection(Direction.right,dt)){
                setCurrentDirection(Direction.right);
                moveToDirection(Direction.right,dt);
            }
            else if (couldMoveToDirection(Direction.up,dt)){
                setCurrentDirection(Direction.up);
                moveToDirection(Direction.up,dt);
            }
            else  if  (couldMoveToDirection(Direction.down,dt)) {
                setCurrentDirection(Direction.down);
                moveToDirection(Direction.down,dt);
            }
            else if (!couldMoveToDirection(Direction.down,dt) && !couldMoveToDirection(Direction.up,dt) && !couldMoveToDirection(Direction.right,dt)){
                setCurrentDirection(Direction.left);
                moveToDirection(Direction.left, dt);
            }
        }
    }

    @Override
    public void update(double dt)
    {
        algorithmBlinky(dt);
    }
}
