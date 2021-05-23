package com.company.entities;

import com.company.Board;
import com.company.Direction;
import com.company.Vector2d.ConstVector2d;
import com.company.Vector2d.Vector2d;
import com.company.field.AbstractField;
import com.sun.istack.internal.NotNull;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.awt.event.KeyEvent.*;

public class Player {
    private static final double PLAYER_SPEED = 5.5;
    private static final Color COLOR = new Color(255, 255, 0);
    private static final Rectangle2D.Double RECT = new Rectangle2D.Double(0, 0, 0.6, 0.6);
    private static final Map<Integer, Direction> keyToDirection_ = Stream.of(new Object[][] {
            {Integer.valueOf(VK_UP), Direction.up},
            {Integer.valueOf(VK_DOWN), Direction.down},
            {Integer.valueOf(VK_LEFT), Direction.left},
            {Integer.valueOf(VK_RIGHT), Direction.right}
    }).collect(Collectors.toMap(data -> (Integer) data[0], data -> (Direction)data[1]));

    private final Vector2d localCenter_;
    private final Board board_;
    private final List<Runnable> onPlayerMoveListeners_;
    private Direction direction_;
    private Direction nextDirection_;

    public Player(int x, int y, @NotNull Board board) {
        localCenter_ = new Vector2d(x + 0.5, y + 0.5);
        board_ = board;
        onPlayerMoveListeners_ = new ArrayList<Runnable>();

        direction_ = Direction.up;
        nextDirection_ = null;
    }

    public void addPlayerMoveListener(Runnable runnable) {
        onPlayerMoveListeners_.add(runnable);
    }

    public ConstVector2d getLocalCenter() {
        return localCenter_;
    }

    private boolean couldMoveToDirection(Direction direction, double dt) {
        ArrayList<ConstVector2d> pointsToCheck = new ArrayList<ConstVector2d>();

        ConstVector2d middlePointDelta = Direction.toVector2d(direction).copy().times(0.5);
        ConstVector2d middlePoint = localCenter_.copy()
                .add(middlePointDelta)
                .add(Direction.toVector2d(direction).copy().times(dt * PLAYER_SPEED));

        boolean[] invertedArray = new boolean[] {false, true};
        for (boolean inverted : invertedArray) {
            pointsToCheck.add(middlePoint.copy()
                    .add(middlePointDelta.copy()
                            .rot90(inverted).times(0.9)));
        }

        for (ConstVector2d point : pointsToCheck) {
            AbstractField field = board_.getField(point);
            if (field == null || !field.isWalkable()) {
                return false;
            }
        }

        return true;
    }

    private void notifyListeners() {
        AbstractField field = board_.getField(localCenter_);
        if (field != null) {
            field.onPlayerEnter(this);
        }

        for (Runnable listener : onPlayerMoveListeners_) {
            listener.run();
        }
    }

    private void moveToDirection(Direction direction, double dt) {
        localCenter_.add(Direction.toVector2d(direction).copy().times(dt * PLAYER_SPEED));

        // centering
        switch (direction) {
            case up:
            case down: localCenter_.setX(Math.floor(localCenter_.getX()) + 0.5); break;
            case left:
            case right: localCenter_.setY(Math.floor(localCenter_.getY()) + 0.5); break;
        }

        notifyListeners();
    }

    private double getAmountToShove(Direction direction, ConstVector2d middlePoint) {
        switch (direction) {
            case up:
                return middlePoint.getY() - Math.floor(middlePoint.getY());
            case down:
                return Math.ceil(middlePoint.getY()) - middlePoint.getY();
            case left:
                return middlePoint.getX() - Math.floor(middlePoint.getX());
            case right:
                return Math.ceil(middlePoint.getX()) - middlePoint.getX();
        }

        return 0.0;
    }

    private void shoveToWall(Direction direction) {
        ConstVector2d middlePoint = localCenter_.copy().add(Direction.toVector2d(direction).copy().times(0.5));
        localCenter_.add(Direction.toVector2d(direction).copy().times(getAmountToShove(direction, middlePoint)));
        notifyListeners();
    }

    public void update(double dt) {
        keyToDirection_.forEach((Integer key_code, Direction direction) -> {
            if (board_.getKeyboardManager().isDown(key_code)) {
                nextDirection_ = direction;
            }
        });

        if (nextDirection_ != null && couldMoveToDirection(nextDirection_, dt)) {
            direction_ = nextDirection_;
            nextDirection_ = null;
            moveToDirection(direction_, dt);
        }
        else if (couldMoveToDirection(direction_, dt)) {
            moveToDirection(direction_, dt);
        }
        else {
            shoveToWall(direction_);
        }
    }

    public void draw(Graphics2D g) {
        ConstVector2d upperLeftCorner = localCenter_.copy().add(-0.5, -0.5);

        g.setColor(COLOR);
        RECT.x = upperLeftCorner.getX() + 0.2;
        RECT.y = upperLeftCorner.getY() + 0.2;
        g.fill(RECT);
    }

    public void setLocalCenter(ConstVector2d new_pos) {
        localCenter_.set(new_pos.getX(), new_pos.getY());
    }
}
