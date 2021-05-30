package com.company.entities;


import com.company.Board;
import com.company.Direction;
import com.company.Vector2d.ConstVector2d;
import com.company.Vector2d.Vector2d;
import com.sun.istack.internal.NotNull;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Blinky {
    private static final double BLINKY_SPEED = 8.5;
    private static final Color COLOR= new Color(255, 0, 0);
    private static final Rectangle2D.Double RECT = new Rectangle2D.Double(0, 0, 0.6, 0.6);

    private final Vector2d localCenter_;
    private final Board board_;
    private Direction direction_;

    public Blinky(int x, int y, @NotNull Board board){
        localCenter_ = new Vector2d(x + 0.5, y + 0.5);
        board_ = board;
        direction_ = Direction.left;

    }

    public void moveToBlinky(){
        for(int i=0; i < 5; i++)
            localCenter_.copy().add(0.5, 0.5);
    }




    public void draw(Graphics2D g) {
        ConstVector2d upperLeftCorner = localCenter_.copy().add(-0.5, -0.5);

        g.setColor(COLOR);
        RECT.x = upperLeftCorner.getX() + 0.2;
        RECT.y = upperLeftCorner.getY() + 0.2;
        g.fill(RECT);
    }

}
