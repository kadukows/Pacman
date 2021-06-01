package com.company.entities;

import com.company.Board;
import com.company.Direction;
import com.company.Vector2d.ConstVector2d;

public class Pinky extends Ghost implements Runnable {
    /**
     * Default constructor for Ghost class.
     *
     * @param x     starting x-coordinate for ghost
     * @param y     starting y-coordinate for ghost
     * @param red   is color RGB for ghost
     * @param green is color RGB for ghost
     * @param blue  is color RGB for ghost
     * @param board board this ghost belongs to
     */
    public Pinky(int x, int y, int red, int green, int blue, Board board) {
        super(x, y, red, green, blue, board);
    }


    public static double roundTo2DecimalPlace(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    private void avoidObstacle(double dt, Direction direction, ConstVector2d Pinky_position, ConstVector2d Pacman_position) {


        switch (direction) {
            case down:
                if (Pacman_position.getX() < Pinky_position.getX() && !couldMoveToDirection(Direction.left, dt)) {
                    if (couldMoveToDirection(Direction.right, dt)) {
                        while (!couldMoveToDirection(Direction.down, dt)) {
                            localCenter_.add(Direction.toVector2d(Direction.right).copy().times(0.1*dt * GHOST_SPEED));
                            board_.repaint();
                            System.out.println("cofam 1");
                        }
                        while (!couldMoveToDirection(Direction.left, dt) || couldMoveToDirection(Direction.down, dt) ) {
                            localCenter_.add(Direction.toVector2d(Direction.down).copy().times(0.1*dt * GHOST_SPEED));
                            board_.repaint();
                            System.out.println("cofam 2");
                        }
                        System.out.println("cofniete");
                    } else {
                        while (!couldMoveToDirection(Direction.left, dt)) {
                            localCenter_.add(Direction.toVector2d(Direction.up).copy().times(0.1* dt * GHOST_SPEED));
                            board_.repaint();
                        }
                    }
                } else if (Pacman_position.getX() > Pinky_position.getX() && !couldMoveToDirection(Direction.right, dt)) {
                    if (couldMoveToDirection(Direction.left, dt)) {
                        while (!couldMoveToDirection(Direction.down, dt)) {
                            localCenter_.add(Direction.toVector2d(Direction.left).copy().times(0.1*dt * GHOST_SPEED));
                            board_.repaint();
                        }
                    } else {
                        while (!couldMoveToDirection(Direction.right, dt)) {
                            localCenter_.add(Direction.toVector2d(Direction.up).copy().times(0.1*dt * GHOST_SPEED));
                            board_.repaint();
                        }
                    }
                } else if (Pacman_position.getX() < Pinky_position.getX() && couldMoveToDirection(Direction.left, dt)) {
                    localCenter_.add(Direction.toVector2d(Direction.left).copy().times(0.5*dt * GHOST_SPEED));
                } else {
                    localCenter_.add(Direction.toVector2d(Direction.right).copy().times(0.5*dt * GHOST_SPEED));
                }
            case up:
                if (Pacman_position.getX() < Pinky_position.getX() && !couldMoveToDirection(Direction.left, dt)) {
                    if (couldMoveToDirection(Direction.right, dt)) {
                        while (!couldMoveToDirection(Direction.up, dt)) {
                            localCenter_.add(Direction.toVector2d(Direction.right).copy().times(0.1*dt * GHOST_SPEED));
                            board_.repaint();
                        }
                    } else {
                        while (!couldMoveToDirection(Direction.left, dt)) {
                            localCenter_.add(Direction.toVector2d(Direction.down).copy().times(0.1*dt * GHOST_SPEED));
                            board_.repaint();
                        }
                    }
                } else if (Pacman_position.getX() > Pinky_position.getX() && !couldMoveToDirection(Direction.right, dt)) {
                    if (couldMoveToDirection(Direction.left, dt)) {
                        while (!couldMoveToDirection(Direction.up, dt)) {
                            localCenter_.add(Direction.toVector2d(Direction.left).copy().times(0.1*dt * GHOST_SPEED));
                            board_.repaint();
                        }
                    } else {
                        while (!couldMoveToDirection(Direction.right, dt)) {
                            localCenter_.add(Direction.toVector2d(Direction.down).copy().times(0.1*dt * GHOST_SPEED));
                            board_.repaint();
                        }
                    }
                } else if (Pacman_position.getX() < Pinky_position.getX() && couldMoveToDirection(Direction.left, dt)) {
                    localCenter_.add(Direction.toVector2d(Direction.left).copy().times(0.5*dt * GHOST_SPEED));
                } else {
                    localCenter_.add(Direction.toVector2d(Direction.right).copy().times(0.5*dt * GHOST_SPEED));
                }

        }

    }


    public void update(double dt, Player player) {

        Direction direction;

        ConstVector2d Pacman_position;
        ConstVector2d Pinky_position;

        Pacman_position = player.getLocalCenter();
        Pinky_position = getLocalCenter();


        if (Pacman_position.getY() < Pinky_position.getY()) {
            direction = Direction.up;
            if (couldMoveToDirection(direction, dt)) {
                localCenter_.add(Direction.toVector2d(direction).copy().times(0.5*dt * GHOST_SPEED));
            } else {
                avoidObstacle(dt, direction, Pinky_position, Pacman_position);
            }
        } else {
            direction = Direction.down;
            if (couldMoveToDirection(direction, dt)) {
                localCenter_.add(Direction.toVector2d(direction).copy().times(0.5*dt * GHOST_SPEED));
            } else {
                avoidObstacle(dt, direction, Pinky_position, Pacman_position);
            }
        }


    }


    @Override
    public void run() {

    }
}
