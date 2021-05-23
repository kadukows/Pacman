package com.company.Vector2d;

public class ConstVector2d {
    private static final double epsilon = 0.005;

    protected double x_;
    protected double y_;

    public ConstVector2d(double x, double y) {
        x_ = x;
        y_ = y;
    }

    public double getX() {
        return x_;
    }

    public double getY() {
        return y_;
    }

    public Vector2d copy() {
        return new Vector2d(x_, y_);
    }

    @Override
    public String toString() {
        return "(" + x_ + "," + y_ + ")";
    }

    private static boolean DoubleEqualWithEpsilon(double lhs, double rhs) {
        return lhs - epsilon <= rhs && rhs <= lhs + epsilon;
    }

    public boolean equals(ConstVector2d other) {
        return DoubleEqualWithEpsilon(x_, other.x_) && DoubleEqualWithEpsilon(y_, other.y_);
    }
}
