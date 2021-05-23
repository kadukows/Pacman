package com.company.field;

import com.company.Board;
import com.company.entities.Player;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class EnemyField extends AbstractField {
    private static final Color COLOR = new Color(87, 0, 0);

    public EnemyField(Board board) {
        super(board);
    }

    @Override
    public boolean isWalkable() {
        return false;
    }

    @Override
    public void draw(Graphics2D g, int x, int y) {
        g.setColor(COLOR);
        g.fillRect(x, y, 1, 1);
    }

    @Override
    public void onPlayerEnter(Player player) {
        // pass
    }
}
