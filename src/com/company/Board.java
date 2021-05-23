package com.company;

import com.company.Vector2d.ConstVector2d;
import com.company.entities.Player;
import com.company.field.AbstractField;
import com.company.fieldmatrixfactory.IFieldMatrixFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.AffineTransform;

public class Board extends JPanel {
    private static final int BLOCK_SIZE = 50;

    private final Matrix<AbstractField> fieldMatrix_;
    private final Player player_;
    private final KeyboardManager keyboardManager_;

    public Board(IFieldMatrixFactory fieldMatrixFactory) {
        fieldMatrix_ = fieldMatrixFactory.create(this);
        keyboardManager_ = new KeyboardManager();
        player_ = new Player(2, 2, this);
        player_.addPlayerMoveListener(this::onPacmanMoved);

        /*
        Matrix<AbstractField> fieldMatrix = new Matrix<AbstractField>(4, 4);
        AbstractField wall = new WallField(this);
        AbstractField walk = new WalkableField(this, false);

        fieldMatrix.set(0, 0, wall);
        fieldMatrix.set(0, 1, wall);
        fieldMatrix.set(0, 2, wall);
        fieldMatrix.set(0, 3, wall);

        fieldMatrix.set(1, 0, wall);
        fieldMatrix.set(2, 0, wall);
        fieldMatrix.set(3, 0, wall);

        fieldMatrix.set(1, 3, walk);
        fieldMatrix.set(1, 2, walk);
        fieldMatrix.set(1, 1, walk);
        fieldMatrix.set(2, 1, walk);
        fieldMatrix.set(3, 1, walk);

        fieldMatrix.set(2, 2, wall);
        fieldMatrix.set(2, 3, wall);
        fieldMatrix.set(3, 2, wall);

        fieldMatrix.set(3, 3, walk);

        fieldMatrix_ = fieldMatrix;
         */

        setPreferredSize(new Dimension(
                BLOCK_SIZE * (fieldMatrix_.getWidth() - 2),
                BLOCK_SIZE * (fieldMatrix_.getHeight() - 2)));
        addKeyListener(keyboardManager_);
        setFocusable(true);

        new Timer(1000 / 60, (ActionEvent avt) -> this.update(1 / 60.0)).start();
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0,
                BLOCK_SIZE * fieldMatrix_.getWidth(),
                BLOCK_SIZE * fieldMatrix_.getHeight());

        AffineTransform oldTransform = g2d.getTransform();
        g2d.scale(BLOCK_SIZE, BLOCK_SIZE);
        g2d.translate(-1, -1);

        for (int y = 0; y < fieldMatrix_.getHeight(); ++y) {
            for (int x = 0; x < fieldMatrix_.getWidth(); ++x) {
                fieldMatrix_.get(y, x).draw(g2d, x, y);
            }
        }

        player_.draw(g2d);

        g2d.setTransform(oldTransform);
    }

    private boolean isInBoundaries(ConstVector2d point) {
        return 0 <= point.getX() && point.getX() < fieldMatrix_.getWidth()
                && 0 <= point.getY() && point.getY() < fieldMatrix_.getHeight();
    }

    public AbstractField getField(ConstVector2d point) {
        if (!isInBoundaries(point)) {
            return null;
        }

        return fieldMatrix_.get((int) Math.round(Math.floor(point.getY())), (int) Math.round(Math.floor(point.getX())));
    }

    public void playerConsumed(AbstractField field) {

    }

    public void onPacmanMoved() {

    }

    public void update(double dt) {
        player_.update(dt);
        repaint();
    }

    public KeyboardManager getKeyboardManager() {
        return keyboardManager_;
    }
}
