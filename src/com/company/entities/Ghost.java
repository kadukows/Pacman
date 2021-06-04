package com.company.entities;

import com.company.Board;
import com.company.Direction;
import com.company.Vector2d.ConstVector2d;
import com.company.Vector2d.Vector2d;
import com.company.field.AbstractField;
import com.sun.istack.internal.NotNull;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collections;

public class Ghost {
    protected final Player player_;
    protected double GHOST_SPEED = 4.5;
    private final Color COLOR;
    private static final Rectangle2D.Double RECT = new Rectangle2D.Double(0, 0, 0.6, 0.6);
    public final Vector2d localCenter_;
    protected final Board board_;
    protected Direction currentDirection_;
    protected double dt;


    /**
     * Default constructor for Ghost class.
     *
     * @param x starting x-coordinate for ghost
     * @param y starting y-coordinate for ghost
     * @param red is color RGB for ghost
     * @param green is color RGB for ghost
     * @param blue  is color RGB for ghost
     * @param board board this ghost belongs to
     * @param direction Ghost direction
     * @param player Pacman
     * @param dT delta time
     */

    public Ghost(int x, int y, int red, int green, int blue, @NotNull Board board, Direction direction, Player player, Double dT) {
        localCenter_ = new Vector2d(x + 0.5, y + 0.5);
        board_ = board;
        COLOR = new Color(red, green, blue);
        currentDirection_ = direction;
        player_ = player;
        dt = dT;
    }

    /**
     * Set current speed of ghost.
     * @param speed speed of ghost
     */
    public void setGHOST_SPEED(double speed) { GHOST_SPEED = GHOST_SPEED+speed; }


    /**
     * Returns current direction of ghost.
     *
     * @return current Direction
     */
    public Direction getCurrentDirection() { return currentDirection_ ; }


    /**
     * Set current direction of ghost.
     */
    public void setCurrentDirection(Direction direction) { currentDirection_ = direction; }

    /**
     * Returns current local center of ghost.
     *
     * @return local center
     */
    public ConstVector2d getLocalCenter() {
        return localCenter_;
    }


    /**
     * Checks if ghost can move to specified direction.
     *
     * @param direction direction for which to check possible move
     * @param dt delta time, indicates how much should player be moved in this frame
     * @return true if move possible, false otherwise
     */
    boolean couldMoveToDirection(Direction direction, double dt) {
        ArrayList<ConstVector2d> pointsToCheck = new ArrayList<>();

        ConstVector2d middlePointDelta = Direction.toVector2d(direction).copy().times(0.5);
        ConstVector2d middlePoint = localCenter_.copy()
                .add(middlePointDelta)
                .add(Direction.toVector2d(direction).copy().times(dt * GHOST_SPEED));

        boolean[] invertedArray = new boolean[] {false, true};
        for (boolean inverted : invertedArray) {
            pointsToCheck.add(middlePoint.copy()
                    .add(middlePointDelta.copy()
                            .rot90(inverted).times(0.7)));
        }

        for (ConstVector2d point : pointsToCheck) {
            AbstractField field = board_.getField(point);
            if (field == null || !field.isWalkableForGhost()) {
                return false;
            }
        }

        return true;
    }

    /**
     * Moves to specified direction without checking if it is possible.
     *
     * @param direction direction to which move
     * @param dt delta time, indicates how much to move
     */
    protected void moveToDirection(Direction direction, double dt) {
        if (!couldMoveToDirection(direction, dt)) throw new RuntimeException();
        localCenter_.add(Direction.toVector2d(direction).copy().times(dt * GHOST_SPEED));

        // centering
        switch (direction) {
            case up:
            case down: localCenter_.setX(Math.floor(localCenter_.getX()) + 0.5); break;
            case left:
            case right: localCenter_.setY(Math.floor(localCenter_.getY()) + 0.5); break;
        }
    }


    /**
     * Search in which direction the ghost cannot move
     * @param direction the direction in which the ghost moves
     * @return the direction the ghost cannot move
     */

    protected Direction searchForbiddenDirection(Direction direction){
        Direction forbiddenDirection;

        if ( direction == Direction.down){
            forbiddenDirection = Direction.up;
        }
        else if ( direction == Direction.up){
            forbiddenDirection = Direction.down;
        }
        else if ( direction == Direction.left){
            forbiddenDirection = Direction.right;
        }
        else {
            forbiddenDirection = Direction.left;
        }

        return forbiddenDirection;
    }

    /**
     * Movement of the  ghost in the available direction
     * @param forbiddenDirection the direction the ghost cannot move
     * @param dt delta time
     */

    protected void  MoveToAvailableDirection(Direction forbiddenDirection, double dt){

        ArrayList<Direction> availableDirections = new ArrayList<>();
        availableDirections.add(Direction.up);
        availableDirections.add(Direction.down);
        availableDirections.add(Direction.left);
        availableDirections.add(Direction.right);

        availableDirections.remove(forbiddenDirection);
        Collections.shuffle(availableDirections);

        if (Direction.up != forbiddenDirection && couldMoveToDirection(Direction.up, dt)){
            setCurrentDirection(Direction.up);
            moveToDirection(Direction.up, dt);
        }

        else if (Direction.down != forbiddenDirection && couldMoveToDirection(Direction.down, dt)){
            setCurrentDirection(Direction.down);
            moveToDirection(Direction.down, dt);
        }
        else if (Direction.right != forbiddenDirection && couldMoveToDirection(Direction.right, dt)){
            setCurrentDirection(Direction.right);
            moveToDirection(Direction.right, dt);
        }
        else if (Direction.left != forbiddenDirection && couldMoveToDirection(Direction.left, dt)){
            setCurrentDirection(Direction.left);
            moveToDirection(Direction.left, dt);
        }
    }


    /**
     * Function that draws ghost onto the current frame.
     *
     * @param g Graphics object used to draw
     */
    public void draw(Graphics2D g) {
        ConstVector2d upperLeftCorner = localCenter_.copy().add(-0.5, -0.5);

        g.setColor(COLOR);
        RECT.x = upperLeftCorner.getX() + 0.3;
        RECT.y = upperLeftCorner.getY() + 0.3;
        g.fill(RECT);
    }
}
