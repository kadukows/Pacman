package com.company;

import com.company.Vector2d.ConstVector2d;

public enum Direction {
    up,
    down,
    left,
    right;

    private static final ConstVector2d VECTOR_UP = new ConstVector2d(0, -1);
    private static final ConstVector2d VECTOR_DOWN = new ConstVector2d(0, 1);
    private static final ConstVector2d VECTOR_LEFT = new ConstVector2d(-1, 0);
    private static final ConstVector2d VECTOR_RIGHT = new ConstVector2d(1, 0);


    public static ConstVector2d toVector2d(Direction direction) {
        switch (direction) {
            case up: return VECTOR_UP;
            case down: return VECTOR_DOWN;
            case left: return VECTOR_LEFT;
            case right: return VECTOR_RIGHT;
        }

        throw new RuntimeException("Wrong enum value");
    }
}


