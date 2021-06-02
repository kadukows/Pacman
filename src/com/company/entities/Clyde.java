package com.company.entities;

import com.company.Board;
import com.company.Direction;
import com.company.Vector2d.ConstVector2d;

import java.util.ArrayList;
import java.util.Collections;

public class Clyde extends Ghost implements Runnable{
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
    public Clyde(int x, int y, int red, int green, int blue, Board board, Direction direction) {
        super(x, y, red, green, blue, board, direction);
    }


    /**
     * Manhattan metric calculation
     * @param Pacman_position pacman position on the bord
     * @param Clyde_position Clyde position on the bord
     * @return distance between Pacman and Clyde
     */
    private double manhattanMetric(ConstVector2d Pacman_position, ConstVector2d Clyde_position ){
        double length;

        length = Math.abs(Pacman_position.getX()-Clyde_position.getX()) + Math.abs(Pacman_position.getY()-Clyde_position.getY());
        return length;
    }



    private Direction searchForbiddenDirection(Direction direction){
        Direction forbiddenDirection;

        if ( direction == Direction.down){
            forbiddenDirection = Direction.up;
        }
        else if ( direction == Direction.up){
            forbiddenDirection = Direction.down;
        }
        else if ( direction == Direction.left){
            forbiddenDirection = Direction.right;
        }
        else {
            forbiddenDirection = Direction.left;
        }

        return forbiddenDirection;
    }

    private void  MoveToAvailableDirection(Direction forbiddenDirection, double dt){

        System.out.print( " Problem w funkcji MoveToAvailableDirection : ");

        System.out.println("UP: ");
        System.out.print(Direction.up != forbiddenDirection);
        System.out.print("  ");
        System.out.print(localCenter_);
        System.out.print("  ");
        System.out.println(couldMoveToDirection(Direction.up, dt));


        System.out.print("DOWN: ");
        System.out.print(Direction.down != forbiddenDirection);
        System.out.print("  ");
        System.out.println(couldMoveToDirection(Direction.down, dt));

        System.out.print("LEFT: ");
        System.out.print(Direction.left != forbiddenDirection);
        System.out.print("  ");
        System.out.println(couldMoveToDirection(Direction.left, dt));

        System.out.print("RIGTH: ");
        System.out.print(Direction.right != forbiddenDirection);
        System.out.print("  ");
        System.out.println(couldMoveToDirection(Direction.right, dt));


        if (Direction.up != forbiddenDirection && couldMoveToDirection(Direction.up, dt)){
            System.out.println(" dla up");
            setCurrentDirection(Direction.up);
            moveToDirection(Direction.up, dt);
        }

        else if (Direction.down != forbiddenDirection && couldMoveToDirection(Direction.down, dt)){
            System.out.println(" dla down");
            setCurrentDirection(Direction.down);
            moveToDirection(Direction.down, dt);
        }
        else if (Direction.right != forbiddenDirection && couldMoveToDirection(Direction.right, dt)){
            System.out.println(" dla right");
            setCurrentDirection(Direction.right);
            moveToDirection(Direction.right, dt);
        }
        else if (Direction.left != forbiddenDirection && couldMoveToDirection(Direction.left, dt)){
            System.out.println(" dla left");
            setCurrentDirection(Direction.left);
            moveToDirection(Direction.left, dt);
        }
    }






    /**
     * The orange ghost "Clyde" movement rules
     * @param dt delta time
     * @param player pacman controlled by the player
     */
    public void algorithmClyde(double dt, Player player) {

        Direction direction;
        Direction forbiddenDirection;
        ConstVector2d Pacman_position;
        ConstVector2d Clyde_position;
        double length;
        boolean flag;

        flag = false;
        Pacman_position = player.getLocalCenter();
        Clyde_position = getLocalCenter();

        length = manhattanMetric(Pacman_position, Clyde_position);

        ArrayList<Direction> availableDirections = new ArrayList<>();
        availableDirections.add(Direction.up);
        availableDirections.add(Direction.down);
        availableDirections.add(Direction.left);
        availableDirections.add(Direction.right);

        direction = getCurrentDirection();
        forbiddenDirection = searchForbiddenDirection(direction);
        availableDirections.remove(forbiddenDirection);
        Collections.shuffle(availableDirections);


        if ( couldMoveToDirection(currentDirection_, dt) && length > 5){
            System.out.println("Kulturalnie ide wzdłuż mojego kierunku");
            setCurrentDirection(currentDirection_);
            moveToDirection(currentDirection_, dt);
            flag = true;
        }

        if ( length < 5 && !flag){
            System.out.println("UPS jestem za blisko");
            if (availableDirections.contains(Direction.down)){
                System.out.println("Uciekam w dół");
                if (Pacman_position.getY() < Clyde_position.getY() && couldMoveToDirection(Direction.down, dt)){
                    setCurrentDirection(Direction.down);
                    moveToDirection(Direction.down, dt);
                    flag = true;
                }
            }
           if (availableDirections.contains(Direction.up) && !flag){
               System.out.println("Uciekam w górę");
               if (Pacman_position.getY() > Clyde_position.getY() && couldMoveToDirection(Direction.up, dt)){
                    setCurrentDirection(Direction.up);
                    moveToDirection(Direction.up, dt);
                    flag = true;
                }
            }

           if (availableDirections.contains(Direction.right) && !flag){
               System.out.println("Uciekam w prawo");
               if (Pacman_position.getX() < Clyde_position.getX() && couldMoveToDirection(Direction.right, dt)){
                    setCurrentDirection(Direction.right);
                    moveToDirection(Direction.right, dt);
                    flag = true;
                }
            }

           if (availableDirections.contains(Direction.left)  && !flag){
               System.out.println("Uciekam w lewo");
               if (Pacman_position.getX() > Clyde_position.getX() && couldMoveToDirection(Direction.left, dt)){
                    setCurrentDirection(Direction.left);
                   moveToDirection(Direction.left, dt);
                    flag = true;
                }
            }
           if (!flag){
               System.out.println("Uciekam gdziekolwiek");
               MoveToAvailableDirection(forbiddenDirection, dt);
               flag = true;
           }
        }
        if (!flag) {
            System.out.println("Poruszam sie w randomowym kierunku ");
            MoveToAvailableDirection(forbiddenDirection, dt);
            }

    }


    @Override
    public void run() {

    }
}
