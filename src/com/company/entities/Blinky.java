package com.company.entities;


import com.company.Board;
import com.company.Direction;
import com.company.Vector2d.ConstVector2d;
import com.company.Vector2d.Vector2d;
import com.company.field.AbstractField;
import com.sun.istack.internal.NotNull;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class Blinky {
    private static final double BLINKY_SPEED = 4.5;
    private static final Color COLOR= new Color(255, 0, 0);
    private static final Ellipse2D.Double ELIP = new Ellipse2D.Double(0, 0, 0.6, 0.6);


    private final Vector2d localCenter_;
    private final Board board_;
    private Direction direction_;

    public Blinky(int x, int y, @NotNull Board board){
        localCenter_ = new Vector2d(x + 0.5, y + 0.5);
        board_ = board;
        direction_ = Direction.left;

    }

    public ConstVector2d getLocalCenter(){ return localCenter_; }

    private boolean couldMoveToDirection(Direction direction, double dt) {
        ArrayList<ConstVector2d> pointsToCheck = new ArrayList<>();

        ConstVector2d middlePointDelta = Direction.toVector2d(direction).copy().times(0.5);
        ConstVector2d middlePoint = localCenter_.copy()
                .add(middlePointDelta)
                .add(Direction.toVector2d(direction).copy().times(dt * BLINKY_SPEED));

        boolean[] invertedArray = new boolean[] {false, true};
        for (boolean inverted : invertedArray) {
            pointsToCheck.add(middlePoint.copy()
                    .add(middlePointDelta.copy()
                            .rot90(inverted).times(0.8)));
        }

        for (ConstVector2d point : pointsToCheck) {
            AbstractField field = board_.getField(point);
            if (field == null || !field.isWalkable()) {
                return false;
            }
        }

        return true;
    }

    public static double roundTo2DecimalPlace(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    public void update(double dt, Player player) {

        Direction direction;

        ConstVector2d player_position;
        ConstVector2d Blinky_position;


        player_position = player.getLocalCenter();
        Blinky_position = getLocalCenter();




      if (couldMoveToDirection(Direction.up, dt)){
          System.out.println(" GÓRA ");

          double Blinky_X = roundTo2DecimalPlace(Blinky_position.getX());
          double Blinky_Y = roundTo2DecimalPlace(Blinky_position.getY());

          if( player_position.getY() < Blinky_Y){
              System.out.println("pierwszy if");
              localCenter_.add(Direction.toVector2d(Direction.up).copy().times(dt * BLINKY_SPEED));
          }
          else if((player_position.getX() > Blinky_Y) && (couldMoveToDirection(Direction.right, dt))  ){
              System.out.println("drugi if");
//              System.out.println(player_position);
//              System.out.println(Blinky_Y);
              localCenter_.add(Direction.toVector2d(Direction.right).copy().times(dt * BLINKY_SPEED));

          }
          else if((player_position.getX() < Blinky_X) && (couldMoveToDirection(Direction.left, dt))){
              System.out.println("trzeci if");
//              System.out.println(player_position);
//              System.out.println(Blinky_X);
              localCenter_.add(Direction.toVector2d(Direction.left).copy().times(dt * BLINKY_SPEED));
          }
          else if( (player_position.getY() > Blinky_Y) && couldMoveToDirection(Direction.down, dt)){
              System.out.println("czwarty if");
              System.out.println(player_position);
           System.out.println(Blinky_X);
              localCenter_.add(Direction.toVector2d(Direction.down).copy().times(dt * BLINKY_SPEED));
          }
          else {
              System.out.println("else");
              System.out.println(player_position);
              System.out.println(Blinky_X);
              System.out.println(Blinky_Y);
              localCenter_.add(Direction.toVector2d(Direction.up).copy().times(dt * BLINKY_SPEED));
          }
      }
      else if (couldMoveToDirection(Direction.down, dt)){

          double Blinky_X = roundTo2DecimalPlace(Blinky_position.getX());
          double Blinky_Y = roundTo2DecimalPlace(Blinky_position.getY());

          if( player_position.getY() > Blinky_Y){
              localCenter_.add(Direction.toVector2d(Direction.down).copy().times(dt * BLINKY_SPEED));
          }
          else if((player_position.getX() > Blinky_X) && (couldMoveToDirection(Direction.right, dt))  ){
              localCenter_.add(Direction.toVector2d(Direction.right).copy().times(dt * BLINKY_SPEED));
          }
          else if((player_position.getX() < Blinky_X) && (couldMoveToDirection(Direction.left, dt))){
              localCenter_.add(Direction.toVector2d(Direction.left).copy().times(dt * BLINKY_SPEED));
          }
          else {
              localCenter_.add(Direction.toVector2d(Direction.down).copy().times(dt * BLINKY_SPEED));
          }
      }
      else if (couldMoveToDirection(Direction.left, dt)) {

          double Blinky_X = roundTo2DecimalPlace(Blinky_position.getX());


          if (player_position.getX() < Blinky_X) {
              localCenter_.add(Direction.toVector2d(Direction.left).copy().times(dt * BLINKY_SPEED));
          } else if ((player_position.getX() > Blinky_X) && (couldMoveToDirection(Direction.right, dt))) {
              localCenter_.add(Direction.toVector2d(Direction.right).copy().times(dt * BLINKY_SPEED));
          } else {
              localCenter_.add(Direction.toVector2d(Direction.left).copy().times(dt * BLINKY_SPEED));
          }
      }
        else if (couldMoveToDirection(Direction.right, dt)) {

              localCenter_.add(Direction.toVector2d(Direction.right).copy().times(dt * BLINKY_SPEED));
        }

//        try {
//            TimeUnit.MILLISECONDS.sleep(200);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        if (player_position.getY() < Blinky_position.getY()) {
//            System.out.println(" W góre ");
//            direction = Direction.up;
//            if (couldMoveToDirection(direction, dt)) {
//                localCenter_.add(Direction.toVector2d(direction).copy().times(dt * BLINKY_SPEED));
//
//            } else {
//                System.out.println(" W dół ");
//                direction = Direction.down;
//                if (couldMoveToDirection(direction, dt)) {
//                    localCenter_.add(Direction.toVector2d(direction).copy().times(dt * BLINKY_SPEED));
//                }
//            }
//
//            if (player_position.getX() > Blinky_position.getX()) {
//                direction = Direction.right;
//                System.out.println(" W prawo ");
//                if (couldMoveToDirection(direction, dt)) {
//                    localCenter_.add(Direction.toVector2d(direction).copy().times(dt * BLINKY_SPEED));
//                }
//            } else {
//                direction = Direction.left;
//                System.out.println(" lewo ");
//                if (couldMoveToDirection(direction, dt)) {
//                    localCenter_.add(Direction.toVector2d(direction).copy().times(dt * BLINKY_SPEED));
//                }
//            }
//
//        }
    }

    public void draw(Graphics2D g) {
        ConstVector2d upperLeftCorner = localCenter_.copy().add(-0.5, -0.5);
        g.setColor(COLOR);
        ELIP.x = upperLeftCorner.getX() + 0.2;
        ELIP.y = upperLeftCorner.getY() + 0.2;
        g.fill(ELIP);
    }

}
