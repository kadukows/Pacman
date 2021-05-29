package com.company;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Class that models Matrix.
 *
 * @param <T> type that Matrix holds
 */
public class Matrix<T> implements Iterable<T> {
    private final T[] data_;
    private final int width_;
    private final int height_;

    /**
     * Default constructor of a class.
     *
     * @param width width of a matrix
     * @param height height of a matrix
     */
    public Matrix(int width, int height) {
        assert width > 0;
        assert height > 0;

        width_ = width;
        height_ = height;

        data_ =  (T[]) new Object[width * height];
    }

    /**
     * Getter for object at given row and columns.
     *
     * @param i row
     * @param j column
     * @return object stored at given coordinates
     */
    public T get(int i, int j) {
        assert i < width_;
        assert j < height_;

        return data_[i * width_ + j];
    }

    /**
     * Setter for object at given row and columns.
     *
     * @param i row
     * @param j column
     * @param t object to be stored at given coordinates
     */
    public void set(int i, int j, T t) {
        assert i < width_;
        assert j < height_;

        data_[i * width_ + j] = t;
    }

    /**
     * Getter for number of columns
     *
     * @return number of columns
     */
    public int getWidth() {
        return width_;
    }

    /**
     * Getter for number of rows.
     *
     * @return number of rows
     */
    public int getHeight() {
        return height_;
    }

    /**
     * Implements Iterable interface
     *
     * @return iterator of underlying memory
     */
    @Override
    public Iterator<T> iterator() {
        return Arrays.stream(data_).iterator();
    }
}
