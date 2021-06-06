package com.company.entities;

import com.company.Board;
import com.company.Direction;
import com.company.Vector2d.ConstVector2d;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Inky  extends Ghost {
    private double time = 0;
    private int idBehaviours;
    /**
     * Default constructor for Ghost class.
     *
     * @param x         starting x-coordinate for ghost
     * @param y         starting y-coordinate for ghost
     * @param red       is color RGB for ghost
     * @param green     is color RGB for ghost
     * @param blue      is color RGB for ghost
     * @param board     board this ghost belongs to
     * @param direction Inky direction
     * @param player Pacman
     */
    public Inky(int x, int y, int red, int green, int blue, Board board, Direction direction, Player player) {
        super(x, y, red, green, blue, board, direction, player);
    }

    /**
     * The blue ghost "Inky" movement rules
     *
     * @param dt delta time
     */
    public void algorithmInky(double dt){
        time = time + dt;
        Random r = new Random();

        if( time == 0 ){
            idBehaviours = r.nextInt(3);
        }
        else if ( time >= 5){
            time = 0;
        }else{
            if( idBehaviours==0){
                PinkyBehaviours(dt, player_);
            }
            else if ( idBehaviours == 1){
                ClydeBehaviours(dt, player_);
            }
            else if ( idBehaviours == 2) {
                BlinkyBehaviours(dt, player_);
            }else{
                try {
                    throw new Exception();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
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
     * The ghost "Clyde" behaviors
     * @param dt delta time
     * @param player Pacman controlled by the player
     */

    private void ClydeBehaviours(double dt, Player player){
        Direction direction;
        Direction forbiddenDirection;
        ConstVector2d Pacman_position;
        ConstVector2d Clyde_position;
        double length;
        boolean flag;

        flag = false;
        Pacman_position = player.getLocalCenter();
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

    /**
     * The ghost "Blinky" behaviors
     * @param dt delta time
     * @param player Pacman controlled by the player
     */
    private void BlinkyBehaviours(double dt, Player player){

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
    /**
     * The ghost "Pinky" behaviors
     * @param dt delta time
     * @param player Pacman controlled by the player
     */
    private void PinkyBehaviours(double dt, Player player){
        Direction direction;
        ConstVector2d Pacman_position;
        ConstVector2d Pinky_position;

        Pacman_position = player.getLocalCenter();
        Pinky_position = getLocalCenter();
        direction = getCurrentDirection();

        double PARAM  = -1;

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
    public void update(double dt) {
        algorithmInky(dt);
    }
}
