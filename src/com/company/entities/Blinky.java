package com.company.entities;

import com.company.Board;
import com.company.Direction;
import com.company.Vector2d.ConstVector2d;

public class Blinky extends Ghost implements Runnable{

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
     */
    public Blinky(int x, int y, int red, int green, int blue, Board board, Direction direction) {
        super(x, y, red, green, blue, board, direction);
    }



    public void algorithmBlinky(double dt, Player player) {
        time = time + dt;
        if (time > 5){
            time = 0;
            if (GHOST_SPEED < 8.25){
            setGHOST_SPEED(0.25);
            }
        }
        Direction direction;
        ConstVector2d Pacman_position;
        ConstVector2d Pinky_position;

        Pacman_position = player.getLocalCenter();
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
    public void run() {

    }
}
