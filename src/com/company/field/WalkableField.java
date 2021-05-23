package com.company.field;

import com.company.Board;
import com.company.entities.Player;
import com.sun.istack.internal.NotNull;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class WalkableField extends AbstractField {
    private static final Color COLOR = new Color(255, 255, 0);
    private static Rectangle2D.Double RECT = new Rectangle2D.Double(0, 0, 0.4, 0.4);
    private boolean consumable_;

    public WalkableField(@NotNull Board board, boolean consumable) {
        super(board);
        consumable_ = consumable;
    }

    @Override
    public boolean isWalkable() {
        return true;
    }

    @Override
    public void draw(Graphics2D g, int x, int y) {
        if (consumable_) {
            g.setColor(COLOR);
            RECT.x = x + 0.3;
            RECT.y = y + 0.3;
            g.fill(RECT);
        }
    }

    @Override
    public void onPlayerEnter(Player player) {
        if (consumable_) {
            board_.playerConsumed(this);
        }
    }
}
