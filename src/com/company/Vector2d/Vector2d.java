package com.company.Vector2d;

import com.sun.istack.internal.NotNull;

public class Vector2d extends ConstVector2d {
    public Vector2d(double x, double y) {
        super(x, y);
    }

    public void setX(double n) {
        x_ = n;
    }

    public void setY(double n) {
        y_ = n;
    }

    public void set(double x, double y) {
        x_ = x;
        y_ = y;
    }

    public Vector2d add(@NotNull ConstVector2d other) {
        x_ += other.x_;
        y_ += other.y_;
        return this;
    }

    public Vector2d add(double x, double y) {
        x_ += x;
        y_ += y;
        return this;
    }

    public Vector2d times(double scalar) {
        x_ *= scalar;
        y_ *= scalar;
        return this;
    }

    public Vector2d rot90(boolean inverted) {
        double old_x = x_;
        if (!inverted) {
            x_ = -y_;
            y_ = old_x;
        }
        else {
            x_ = y_;
            y_ = -old_x;
        }

        return this;
    }
}
