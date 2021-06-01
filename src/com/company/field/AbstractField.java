package com.company.field;

import com.company.Board;
import com.company.entities.Player;
import com.sun.istack.internal.NotNull;

import java.awt.*;

/**
 * Base class for all fields.
 */
public abstract class AbstractField {
    protected Board board_;

    /**
     *  Class constructor specifying "parent" board.
     *
     * @param board Board this field is assigned to
     */
    public AbstractField(@NotNull Board board) {
        board_ = board;
    }

    /**
     * Returns boolean that indicates whether this field is walkable.
     *
     * @return true if field is walkable
     */
    public abstract boolean isWalkable();

    /**
     * Allows Ghost from walking onto this field.
     *
     * @return true
     */
    public abstract boolean isWalkableForGhost();

    /**
     * Method used to draw field on to current frame.
     *
     * @param g Graphics2D object used to draw
     * @param x x position of field in a fieldMatrix
     * @param y y position of field in a fieldMatrix
     */
    public abstract void draw(Graphics2D g, int x, int y);

    /**
     * Callback, player calls this each frame it is on that specific field.
     *
     * @param player Player that is on this specific field
     * @see Player
     */
    public abstract void onPlayerEnter(@NotNull Player player);
}