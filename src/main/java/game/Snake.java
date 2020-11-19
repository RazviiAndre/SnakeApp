/*#############################################################################
 # Copyright (c) rzvdev                                                       #
 # Created in 11/17/20, 7:45 PM                                               #
 # Author : Razvi Andre                                                       #
 # Linkedin : https://www.linkedin.com/in/lungu-razvan-andre-4858a417b        #
 # Github : https://github.com/razviiandre                                    #
 #                                                                            #
 #############################################################################*/
package game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Snake{
    private static final Image SNAKE_HEAD_ICON_DOWN = new ImageIcon("src/main/resources/icons/snake_down.png").getImage();
    private static final Image SNAKE_HEAD_ICON_UP = new ImageIcon("src/main/resources/icons/snake_up.png").getImage();
    private static final Image SNAKE_HEAD_ICON_LEFT = new ImageIcon("src/main/resources/icons/snake_left.png").getImage();
    private static final Image SNAKE_HEAD_ICON_RIGHT = new ImageIcon("src/main/resources/icons/snake_right.png").getImage();
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
        for(Point p : snakeLocation){
            if(p.x == jframe_width - 40 || p.y == jframe_height - 80 || p.x <= -20 || p.y <= -20){
                return false;
            }
        }
        for(int i = 1 ; i < snakeLocation.size() ; i++){
            int snakeHeadX = snakeLocation.get(0).x;
            int snakeHeadY = snakeLocation.get(0).y;
            if(snakeHeadX == snakeLocation.get(i).x && snakeHeadY == snakeLocation.get(i).y){
                return false;
            }
        }
        return true;
    }

    public boolean eatApple(){
        return apple.getApple().x == snakeLocation.get(0).x && apple.getApple().y == snakeLocation.get(0).y;
    }

    public void removeLastSnakePart(){
        snakeLocation.remove(snakeLocation.size() - 1);
    }

    public void paintSnake(Graphics g, int scale){
        paintSnakeHead(g,scale);
        paintSnakeParts(g,scale);
    }

    public void paintSnakeParts(Graphics g, int scale){
        for(int i = 1 ; i < snakeLocation.size() ; i++){
        g.setColor(Color.black);
        g.fillRect(snakeLocation.get(i).x, snakeLocation.get(i).y,scale,scale);
        }
    }

    public void paintSnakeHead(Graphics g,int scale){
        Image imageIcon;
        switch (direction){
            case UP:
                imageIcon = SNAKE_HEAD_ICON_UP;
                break;
            case DOWN:
                imageIcon = SNAKE_HEAD_ICON_DOWN;
                break;
            case LEFT:
                imageIcon = SNAKE_HEAD_ICON_LEFT;
                break;
            case RIGHT:
                imageIcon = SNAKE_HEAD_ICON_RIGHT;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + direction);
        }
        g.drawImage(imageIcon,snakeLocation.get(0).x,snakeLocation.get(0).y,scale,scale,null);
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
