package com.company.field;

import com.company.Board;
import com.company.entities.Player;
import com.sun.istack.internal.NotNull;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class WallField extends AbstractField {
    private static final Color COLOR = new Color(0, 0, 255);
    private static Rectangle2D.Double RECT = new Rectangle2D.Double(0, 0, 1, 1);

    public WallField(@NotNull Board board) {
        super(board);
    }

    @Override
    public boolean isWalkable() {
        return false;
    }

    @Override
    public void draw(Graphics2D g, int x, int y) {
        g.setColor(COLOR);
        RECT.x = x;
        RECT.y = y;
        g.fill(RECT);
    }

    @Override
    public void onPlayerEnter(Player player) {
        throw new RuntimeException("Player can't enter walls");
    }
}
