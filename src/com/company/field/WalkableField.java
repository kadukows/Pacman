package com.company.field;

import com.company.Board;
import com.company.entities.Player;
import com.sun.istack.internal.NotNull;

import java.awt.*;
import java.awt.geom.Rectangle2D;


/**
 * Class representing walkable field with optional consumable on it.
 */
public class WalkableField extends AbstractField {
    private static final Color COLOR = new Color(255, 255, 0);
    private static Rectangle2D.Double RECT = new Rectangle2D.Double(0, 0, 0.4, 0.4);
    private boolean consumable_;

    /**
     * Default constructor.
     *
     * @param board Board this field is assigned to
     * @param consumable If this field contains consumable
     */
    public WalkableField(@NotNull Board board, boolean consumable) {
        super(board);
        consumable_ = consumable;
    }

    /**
     * Observer for consuamble property.
     *
     * @return true if field has consumable, false otherwise
     */
    public boolean hasConsumable() {
        return consumable_;
    }

    /**
     * Allows player to walk to this field.
     *
     * @return true
     */
    @Override
    public boolean isWalkable() {
        return true;
    }

    /**
     * Allows Ghost from walking onto this field.
     *
     * @return true
     */
    @Override
    public boolean isWalkableForGhost() {
        return true;
    }

    /**
     * Draw this field onto frame.
     *
     * @param g Graphics2D object used to draw
     * @param x x position of field in a fieldMatrix
     * @param y y position of field in a fieldMatrix
     */
    @Override
    public void draw(Graphics2D g, int x, int y) {
        if (consumable_) {
            g.setColor(COLOR);
            RECT.x = x + 0.3;
            RECT.y = y + 0.3;
            g.fill(RECT);
        }
    }

    /**
     * Indicate to the board that consumable was consumed, if any.
     *
     * @param player Player that is on this specific field
     */
    @Override
    public void onPlayerEnter(Player player) {
        if (consumable_) {
            consumable_ = false;
            board_.playerConsumed(this);
        }
    }
}
