package com.company;

import java.util.Arrays;
import java.util.Iterator;

public class Matrix<T> implements Iterable<T> {
    private final T[] data_;
    private final int width_;
    private final int height_;

    public Matrix(int width, int height) {
        assert width > 0;
        assert height > 0;

        width_ = width;
        height_ = height;

        data_ =  (T[]) new Object[width * height];
    }

    public T get(int i, int j) {
        assert i < width_;
        assert j < height_;

        return data_[i * width_ + j];
    }

    public void set(int i, int j, T t) {
        assert i < width_;
        assert j < height_;

        data_[i * width_ + j] = t;
    }

    public int getWidth() {
        return width_;
    }

    public int getHeight() {
        return height_;
    }

    @Override
    public Iterator<T> iterator() {
        return Arrays.stream(data_).iterator();
    }
}
