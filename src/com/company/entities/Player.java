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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.awt.event.KeyEvent.*;

public class Player implements Runnable{
    private static final double PLAYER_SPEED = 8.5;
    private static final Color COLOR = new Color(255, 255, 0);
    private static final Rectangle2D.Double RECT = new Rectangle2D.Double(0, 0, 0.6, 0.6);
    private static final Map<Integer, Direction> KEY_TO_DIRECTION = Stream.of(new Object[][] {
            {Integer.valueOf(VK_UP), Direction.up},
            {Integer.valueOf(VK_DOWN), Direction.down},
            {Integer.valueOf(VK_LEFT), Direction.left},
            {Integer.valueOf(VK_RIGHT), Direction.right}
    }).collect(Collectors.toMap(data -> (Integer) data[0], data -> (Direction)data[1]));

    private final Vector2d localCenter_;
    private final Board board_;
    private final List<Runnable> onPlayerMoveListeners_;
    private Direction direction_;
    private Direction nextDirection_;
    private double dt;

    /**
     * Default constructor for Player class.
     *
     * @param x starting x-coordinate for player
     * @param y starting y-coordinate for player
     * @param board board this player belongs to
     */
    public Player(int x, int y, @NotNull Board board, double dT) {
        localCenter_ = new Vector2d(x + 0.5, y + 0.5);
        board_ = board;
        onPlayerMoveListeners_ = new ArrayList<>();

        direction_ = Direction.up;
        nextDirection_ = null;
        dt = dT;
    }


    /**
     * Adds a listener that is called every time player makes new move.
     *
     * @param runnable lambda/function to be called
     */
    public void addPlayerMoveListener(Runnable runnable) {
        onPlayerMoveListeners_.add(runnable);
    }

    /**
     * Returns current local center of player.
     *
     * @return local center
     */
    public ConstVector2d getLocalCenter() {
        return localCenter_;
    }


    /**
     * Checks if player can move to specified direction.
     *
     * @param direction direction for which to check possible move
     * @param dt delta time, indicates how much should player be moved in this frame
     * @return true if move possible, false otherwise
     */
    private boolean couldMoveToDirection(Direction direction, double dt) {
        ArrayList<ConstVector2d> pointsToCheck = new ArrayList<>();

        ConstVector2d middlePointDelta = Direction.toVector2d(direction).copy().times(0.5);
        ConstVector2d middlePoint = localCenter_.copy()
                .add(middlePointDelta)
                .add(Direction.toVector2d(direction).copy().times(dt * PLAYER_SPEED));

        boolean[] invertedArray = new boolean[] {false, true};
        for (boolean inverted : invertedArray) {
            pointsToCheck.add(middlePoint.copy()
                    .add(middlePointDelta.copy()
                            .rot90(inverted).times(0.8)));
        }

        for (ConstVector2d point : pointsToCheck) {
            AbstractField field = board_.getField(point);
            if (field == null || !field.isWalkable()) {
                return false;
            }
        }

        return true;
    }

    /**
     * Notifies all listeners (registered + current field) of player.
     */
    private void notifyListeners() {
        AbstractField field = board_.getField(localCenter_);
        if (field != null) {
            field.onPlayerEnter(this);
        }

        for (Runnable listener : onPlayerMoveListeners_) {
            listener.run();
        }
    }

    /**
     * Moves to specified direction without checking if it is possible.
     *
     * @param direction direction to which move
     * @param dt delta time, indicates how much to move
     */
    private void moveToDirection(Direction direction, double dt) {
        localCenter_.add(Direction.toVector2d(direction).copy().times(dt * PLAYER_SPEED));

        // centering
        switch (direction) {
            case up:
            case down: localCenter_.setX(Math.floor(localCenter_.getX()) + 0.5); break;
            case left:
            case right: localCenter_.setY(Math.floor(localCenter_.getY()) + 0.5); break;
        }

        notifyListeners();
    }

    /**
     * Gets amount that is needed to make player exactly centered (last move).
     *
     * @param direction direction to which player is currently moving
     * @param middlePoint mid point of edge of direction
     * @return amount to shove
     */
    private double getAmountToShove(Direction direction, ConstVector2d middlePoint) {
        switch (direction) {
            case up:
                return middlePoint.getY() - Math.floor(middlePoint.getY());
            case down:
                return Math.ceil(middlePoint.getY()) - middlePoint.getY();
            case left:
                return middlePoint.getX() - Math.floor(middlePoint.getX());
            case right:
                return Math.ceil(middlePoint.getX()) - middlePoint.getX();
        }

        return 0.0;
    }

    /**
     * Shoves player to wall (last move).
     *
     * @param direction direction
     */
    private void shoveToWall(Direction direction) {
        ConstVector2d middlePoint = localCenter_.copy().add(Direction.toVector2d(direction).copy().times(0.5));
        localCenter_.add(Direction.toVector2d(direction).copy().times(getAmountToShove(direction, middlePoint)));
        notifyListeners();
    }

    /**
     * Main function that processes player update.
     */
    public void update() {
        KEY_TO_DIRECTION.forEach((Integer key_code, Direction direction) -> {
            if (board_.getKeyboardManager().isDown(key_code)) {
                nextDirection_ = direction;
            }
        });

        if (nextDirection_ != null && couldMoveToDirection(nextDirection_, dt)) {
            direction_ = nextDirection_;
            nextDirection_ = null;
            moveToDirection(direction_, dt);
        }
        else if (couldMoveToDirection(direction_, dt)) {
            moveToDirection(direction_, dt);
        }
        else {
            shoveToWall(direction_);
        }
    }

    /**
     * Function that draws player onto the current frame.
     *
     * @param g Graphics object used to draw
     */
    public void draw(Graphics2D g) {
        ConstVector2d upperLeftCorner = localCenter_.copy().add(-0.5, -0.5);

        g.setColor(COLOR);
        RECT.x = upperLeftCorner.getX() + 0.2;
        RECT.y = upperLeftCorner.getY() + 0.2;
        g.fill(RECT);
    }


    /**
     * Sets player coordinates.
     *
     * @param new_pos new position of player
     */
    public void setLocalCenter(ConstVector2d new_pos) {
        localCenter_.set(new_pos.getX(), new_pos.getY());
        notifyListeners();
    }

    @Override
    public void run() {
        update();
    }
}
