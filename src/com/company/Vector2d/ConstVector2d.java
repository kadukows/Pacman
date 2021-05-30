package com.company.Vector2d;

/**
 * Base const class for Vector2d.
 * Encompasses all const-like operations (observers, copying, equality, string conversion) of Vector2d object.
 */
public class ConstVector2d {
    private static final double EPSILON = 0.005;

    protected double x_;
    protected double y_;

    /**
     * Default constructor of a class.
     *
     * @param x x coordinate
     * @param y y coordinate
     */
    public ConstVector2d(double x, double y) {
        x_ = x;
        y_ = y;
    }

    /**
     * Observer for x property.
     *
     * @return x coordinate
     */
    public double getX() {
        return x_;
    }

    /**
     * Observer for y property.
     *
     * @return y coordinate
     */
    public double getY() {
        return y_;
    }

    /**
     * Copies const vector to mutable, non const version.
     *
     * @return new mutable Vector2d
     */
    public Vector2d copy() {
        return new Vector2d(x_, y_);
    }

    /**
     * Converts Vector2d to string.
     *
     * @return string representation of vector2d
     */
    @Override
    public String toString() {
        return "("  + x_ + ", " + y_ +  ")";
    }

    /**
     * Compares two double precision floats with set epsilon.
     *
     * @param lhs first double to compare
     * @param rhs second double to compare
     * @return true if clos-ish, false otherwise
     */
    private static boolean DoubleEqualWithEpsilon(double lhs, double rhs) {
        return lhs - EPSILON <= rhs && rhs <= lhs + EPSILON;
    }

    /**
     * Compares two vectors for equality.
     *
     * @param other other vector
     * @return true if equal, false otherwise
     */
    public boolean equals(ConstVector2d other) {
        return DoubleEqualWithEpsilon(x_, other.x_) && DoubleEqualWithEpsilon(y_, other.y_);
    }
}
