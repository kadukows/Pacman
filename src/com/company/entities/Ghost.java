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

public class Ghost {
    public static final double GHOST_SPEED = 4.5;
    private final Color COLOR;
    private static final Rectangle2D.Double RECT = new Rectangle2D.Double(0, 0, 0.6, 0.6);
    public final Vector2d localCenter_;
    private final Board board_;

    /**
     * Default constructor for Ghost class.
     *
     * @param x starting x-coordinate for ghost
     * @param y starting y-coordinate for ghost
     * @param red is color RGB for ghost
     * @param green is color RGB for ghost
     * @param blue  is color RGB for ghost
     * @param board board this ghost belongs to
     */

    public Ghost(int x, int y, int red, int green, int blue, @NotNull Board board) {
        localCenter_ = new Vector2d(x + 0.5, y + 0.5);
        board_ = board;
        COLOR = new Color(red, green, blue);
        Direction direction_ = Direction.up;
        Direction nextDirection_ = null;
    }

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
     * Function that draws ghost onto the current frame.
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
}
