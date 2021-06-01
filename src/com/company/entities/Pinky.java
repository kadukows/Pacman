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


    public void update(double dt, Player player) {

        Direction direction;

        ConstVector2d player_position;
        ConstVector2d Blinky_position;

        player_position = player.getLocalCenter();
        Blinky_position = getLocalCenter();


          if (couldMoveToDirection(Direction.up, dt)){
              System.out.println(" GÓRA ");

              if( player_position.getY() < Blinky_position.getY()){
                  System.out.println("pierwszy if");
                  System.out.println(player_position.getY());
                  System.out.println(Blinky_position.getY());
                  localCenter_.add(Direction.toVector2d(Direction.up).copy().times(dt * GHOST_SPEED));
              }
              else if(((player_position.getX() - Blinky_position.getX()) > 0.1) && (couldMoveToDirection(Direction.right, dt))  ){
                  System.out.println("drugi if");
                  System.out.println(player_position.getX());
                  System.out.println(Blinky_position.getX());
                  System.out.println(player_position.getX() - Blinky_position.getX() );
                  System.out.println((player_position.getX() - Blinky_position.getX()) > 0.1 );
                  localCenter_.add(Direction.toVector2d(Direction.right).copy().times(dt * GHOST_SPEED));

              }
              else if((player_position.getX() < Blinky_position.getX()) && (couldMoveToDirection(Direction.left, dt))){
                  System.out.println("trzeci if");
                  System.out.println(player_position.getX());
                  System.out.println(Blinky_position.getX());
                  System.out.println(player_position.getX() - Blinky_position.getX() );
                  System.out.println((player_position.getX() - Blinky_position.getX()) < 0.1 );
                  localCenter_.add(Direction.toVector2d(Direction.left).copy().times(dt * GHOST_SPEED));
              }
              else if( ((player_position.getY() - Blinky_position.getY()) > 0.1) && couldMoveToDirection(Direction.down, dt)){
                  System.out.println("czwarty if");
                  System.out.println(player_position.getY());
                  System.out.println(Blinky_position.getY());
                  localCenter_.add(Direction.toVector2d(Direction.down).copy().times(dt * GHOST_SPEED));
              }
              else {
                  System.out.println("else");
                  System.out.println(player_position);
                  localCenter_.add(Direction.toVector2d(Direction.up).copy().times( dt * GHOST_SPEED));
              }
          }
          else if (couldMoveToDirection(Direction.down, dt)){

              double Blinky_X = roundTo2DecimalPlace(Blinky_position.getX());
              double Blinky_Y = roundTo2DecimalPlace(Blinky_position.getY());

              if( ((player_position.getY() > Blinky_position.getY()))){
                  localCenter_.add(Direction.toVector2d(Direction.down).copy().times(dt * GHOST_SPEED));
              }
              else if(((player_position.getX() > Blinky_position.getX())) && (couldMoveToDirection(Direction.right, dt))  ){
                  localCenter_.add(Direction.toVector2d(Direction.right).copy().times(dt * GHOST_SPEED));
              }
              else if((player_position.getX() < Blinky_X) && (couldMoveToDirection(Direction.left, dt))){
                  localCenter_.add(Direction.toVector2d(Direction.left).copy().times(dt * GHOST_SPEED));
              }
              else {
                  localCenter_.add(Direction.toVector2d(Direction.down).copy().times(dt * GHOST_SPEED));
              }
          }
          else if (couldMoveToDirection(Direction.left, dt)) {

              double Blinky_X = roundTo2DecimalPlace(Blinky_position.getX());


              if (player_position.getX() < Blinky_X) {
                  localCenter_.add(Direction.toVector2d(Direction.left).copy().times(dt * GHOST_SPEED));
              } else if (((player_position.getX() - Blinky_position.getX()) > 0.1 )&& (couldMoveToDirection(Direction.right, dt))) {
                  localCenter_.add(Direction.toVector2d(Direction.right).copy().times(dt * GHOST_SPEED));
              } else {
                  localCenter_.add(Direction.toVector2d(Direction.left).copy().times(dt * GHOST_SPEED));
              }
          }
            else if (couldMoveToDirection(Direction.right, dt)) {

                  localCenter_.add(Direction.toVector2d(Direction.right).copy().times(dt * GHOST_SPEED));
            }






    }


    @Override
    public void run() {

    }
}
