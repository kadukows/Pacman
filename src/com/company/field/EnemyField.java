package com.company.field;

import com.company.Board;
import com.company.entities.Player;

import java.awt.*;

/**
 * Class representing enemy spawning grounds.
 */
public class EnemyField extends AbstractField {
    private static final Color COLOR = new Color(87, 0, 0);

    /**
     * Default constructor.
     *
     * @param board Board this field belongs to
     */
    public EnemyField(Board board) {
        super(board);
    }

    /**
     * Forbids player from walking onto this field.
     *
     * @return false
     */
    @Override
    public boolean isWalkable() {
        return false;
    }

    /**
     * Draws this field.
     *
     * @param g Graphics2D object used to draw
     * @param x x position of field in a fieldMatrix
     * @param y y position of field in a fieldMatrix
     */
    @Override
    public void draw(Graphics2D g, int x, int y) {
        g.setColor(COLOR);
        g.fillRect(x, y, 1, 1);
    }

    /**
     * This does nothing.
     *
     * @param player Player that is on this specific field
     */
    @Override
    public void onPlayerEnter(Player player) {
        // pass
    }
}
