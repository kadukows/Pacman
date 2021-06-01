package com.company.field;

import com.company.Board;
import com.company.entities.Player;
import com.sun.istack.internal.NotNull;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class WallField extends AbstractField {
    private static final Color COLOR = new Color(0, 0, 255);
    private static Rectangle2D.Double RECT = new Rectangle2D.Double(0, 0, 1, 1);

    /**
     * Default constructor.
     *
     * @param board Board this field is assigned to
     */
    public WallField(@NotNull Board board) {
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
     * Forbids Ghost from walking onto this field.
     *
     * @return false
     */
    @Override
    public boolean isWalkableForGhost() {
        return false;
    }

    /**
     * Draws wall onto a frame.
     *
     * @param g Graphics2D object used to draw
     * @param x x position of field in a fieldMatrix
     * @param y y position of field in a fieldMatrix
     */
    @Override
    public void draw(Graphics2D g, int x, int y) {
        g.setColor(COLOR);
        RECT.x = x;
        RECT.y = y;
        g.fill(RECT);
    }

    /**
     * @throws  RuntimeException when player enters
     * @param player Player that is on this specific field
     */
    @Override
    public void onPlayerEnter(Player player) {
        throw new RuntimeException("Player can't enter walls");
    }
}
