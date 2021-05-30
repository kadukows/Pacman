package com.company.field;

import com.company.Board;
import com.company.entities.Player;
import com.sun.istack.internal.NotNull;

import java.awt.*;


public abstract class AbstractField {
    protected Board board_;

    public AbstractField(@NotNull Board board) {
        board_ = board;
    }

    public abstract boolean isWalkable();
    public abstract void draw(Graphics2D g, int x, int y);
    public abstract void onPlayerEnter(@NotNull Player player);
}
