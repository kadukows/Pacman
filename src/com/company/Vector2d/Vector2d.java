package com.company.Vector2d;

import com.sun.istack.internal.NotNull;

/**
 * Class that represents mutable Vector2d object.
 */
public class Vector2d extends ConstVector2d {
    /**
     * Default constructor for a class.
     *
     * @param x x coordinate
     * @param y y coordinate
     */
    public Vector2d(double x, double y) {
        super(x, y);
    }

    /**
     * Sets x property of a class.
     *
     * @param n new x value
     */
    public void setX(double n) {
        x_ = n;
    }

    /**
     * Sets y property of a class.
     *
     * @param n new y value
     */
    public void setY(double n) {
        y_ = n;
    }

    /**
     * Sets both x and y values of a class.
     *
     * @param x new x value
     * @param y new y value
     */
    public void set(double x, double y) {
        x_ = x;
        y_ = y;
    }

    /**
     * Adds other vector to itself.
     *
     * @param other vector to add
     * @return itself
     */
    public Vector2d add(@NotNull ConstVector2d other) {
        x_ += other.x_;
        y_ += other.y_;
        return this;
    }

    /**
     * Adds two doubles to itself.
     *
     * @param x value to add to x coordinate
     * @param y value to add to y coordinate
     * @return itself
     */
    public Vector2d add(double x, double y) {
        x_ += x;
        y_ += y;
        return this;
    }

    /**
     * Multiplies Vector2d by a scalar.
     *
     * @param scalar scalar to multiply by
     * @return itself
     */
    public Vector2d times(double scalar) {
        x_ *= scalar;
        y_ *= scalar;
        return this;
    }

    /**
     * Rotates Vector2d by 90 or -90 degrees.
     *
     * @param inverted if true, rotates by -90 degrees
     * @return itself
     */
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
