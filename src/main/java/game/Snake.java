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

// wTODO: 24-Nov-20 Improve functionality for increaseSpeed function.
    public class Snake{
    private static final Image SNAKE_HEAD_ICON_DOWN = new ImageIcon("src/main/resources/icons/snake_down.png").getImage();
    private static final Image SNAKE_HEAD_ICON_UP = new ImageIcon("src/main/resources/icons/snake_up.png").getImage();
    private static final Image SNAKE_HEAD_ICON_LEFT = new ImageIcon("src/main/resources/icons/snake_left.png").getImage();
    private static final Image SNAKE_HEAD_ICON_RIGHT = new ImageIcon("src/main/resources/icons/snake_right.png").getImage();
    private static final Image SNAKE_PARTS = new ImageIcon("src/main/resources/icons/snakepart.png").getImage();
    private final ArrayList<Point> snakeLocation = new ArrayList<>();
    private static Direction direction = Direction.DOWN;
    private final Apple apple = new Apple();
    private static final short DISTANCE = 20;
    private static final short SNAKE_PARTS_SCALE = 20;
    private static final short SNAKE_HEAD_SCALE = 35;
    private static final short SNAKE_PART_LOCATION_X = 7;
    private static final short SNAKE_PART_LOCATION_Y = 15;

    public Snake(){

    }

    public void move() {
        switch (direction){
            case UP:
                snakeLocation.add(0, new Point(snakeLocation.get(0).x, snakeLocation.get(0).y - DISTANCE));
                break;
            case DOWN:
                snakeLocation.add(0, new Point(snakeLocation.get(0).x, snakeLocation.get(0).y + DISTANCE));
                break;
            case LEFT:
                snakeLocation.add(0, new Point(snakeLocation.get(0).x - DISTANCE, snakeLocation.get(0).y));
                break;
            case RIGHT:
                snakeLocation.add(0, new Point(snakeLocation.get(0).x + DISTANCE, snakeLocation.get(0).y));
                break;
        }
    }

    public boolean checkPosition(int jframe_height, int jframe_width){
        for(Point p : snakeLocation){
            if(p.x == jframe_width - 40 || p.y == jframe_height - 60 || p.x <= -20 || p.y <= -20){
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

    public void paintSnake(Graphics g){
        paintSnakeHead(g);
        paintSnakeParts(g);
    }

    public void paintSnakeParts(Graphics g) {
        for (int i = 1; i < snakeLocation.size(); i++) {
            switch (direction) {
                case DOWN:
                    g.drawImage(SNAKE_PARTS,snakeLocation.get(i).x + SNAKE_PART_LOCATION_X,snakeLocation.get(i).y ,SNAKE_PARTS_SCALE,SNAKE_PARTS_SCALE,null);
//                    g.setColor(Color.green);
//                    g.fillRect(snakeLocation.get(i).x + 10, snakeLocation.get(i).y + 3, 15, 15);
                    break;
                case UP:
                    g.drawImage(SNAKE_PARTS,snakeLocation.get(i).x + SNAKE_PART_LOCATION_X, snakeLocation.get(i).y + SNAKE_PART_LOCATION_Y,SNAKE_PARTS_SCALE,SNAKE_PARTS_SCALE,null);
//                    g.setColor(Color.green);
//                    g.fillRect(snakeLocation.get(i).x + 10, snakeLocation.get(i).y + 15, 15, 15);
                    break;
                case LEFT:
                    g.drawImage(SNAKE_PARTS,snakeLocation.get(i).x + SNAKE_PART_LOCATION_Y, snakeLocation.get(i).y + SNAKE_PART_LOCATION_X,SNAKE_PARTS_SCALE,SNAKE_PARTS_SCALE,null);
//                    g.setColor(Color.green);
//                    g.fillRect(snakeLocation.get(i).x + 15, snakeLocation.get(i).y + 10, 15, 15);
                    break;
                case RIGHT:
                    g.drawImage(SNAKE_PARTS,snakeLocation.get(i).x, snakeLocation.get(i).y + SNAKE_PART_LOCATION_X,SNAKE_PARTS_SCALE,SNAKE_PARTS_SCALE,null );
//                    g.setColor(Color.green);
//                    g.fillRect(snakeLocation.get(i).x + 3, snakeLocation.get(i).y + 10, 15, 15);
                    break;
            }
        }
    }
    public void paintSnakeHead(Graphics g){
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
        g.drawImage(imageIcon,snakeLocation.get(0).x,snakeLocation.get(0).y,SNAKE_HEAD_SCALE,SNAKE_HEAD_SCALE,null);
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
