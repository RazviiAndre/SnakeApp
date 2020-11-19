/*#############################################################################
 # Copyright (c) rzvdev                                                       #
 # Created in 11/17/20, 7:45 PM                                               #
 # Author : Razvi Andre                                                       #
 # Linkedin : https://www.linkedin.com/in/lungu-razvan-andre-4858a417b        #
 # Github : https://github.com/razviiandre                                    #
 #                                                                            #
 #############################################################################*/
package game;

import java.awt.*;
import java.util.ArrayList;

public class Snake{
    private final ArrayList<Point> snakeLocation = new ArrayList<>();
    private static Direction direction = Direction.DOWN;
    private final Apple apple = new Apple();


    public Snake(){

    }

    public void move() {
        switch (direction){
            case UP:
                snakeLocation.add(0, new Point(snakeLocation.get(0).x, snakeLocation.get(0).y - 20));
                break;
            case DOWN:
                snakeLocation.add(0, new Point(snakeLocation.get(0).x, snakeLocation.get(0).y + 20));
                break;
            case LEFT:
                snakeLocation.add(0, new Point(snakeLocation.get(0).x - 20, snakeLocation.get(0).y));
                break;
            case RIGHT:
                snakeLocation.add(0, new Point(snakeLocation.get(0).x + 20, snakeLocation.get(0).y));
                break;
        }
    }

    public boolean checkPosition(int jframe_height, int jframe_width){
        // TODO: 19-Nov-20 - IMPROVE CHECK POSITION SYSTEM. IF SNAKE LOCATION IS EQUALS SNAKEPARTS LOCATIONS THEN GAMEOVER
        for(Point p : snakeLocation){
            if(p.x == jframe_width - 40 || p.y == jframe_height - 80 || p.x <= -20 || p.y <= -20){
                return false;
            }
        }
        return true;
    }

    public boolean eatApple(){
        return apple.getApple().x == snakeLocation.get(0).x && apple.getApple().y == snakeLocation.get(0).y;
    }

    public void setDirection(Direction direction){
        Snake.direction = direction;
    }
    public ArrayList<Point> getSnakeLocation() {
        return snakeLocation;
    }

    public Direction getDirection(){
        return direction;
    }
}
