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
import java.util.Random;

public class Apple{
    private static final Image APPLE_ICON = new ImageIcon("src/main/resources/icons/apple.png").getImage();
    private static Point apple;
    private static short score = 0;
    private static final short APPLE_SCALE = 25;

    Apple(){
        apple = new Point(100,100);
    }

    public void newApple(int jframe_height, int jframe_width){
        Random random = new Random();
        int y = random.ints(0,jframe_height-100).filter(value -> value % 20 == 0).findFirst().getAsInt();
        int x = random.ints(0,jframe_width-100).filter(value -> value % 20 == 0).findFirst().getAsInt();
        apple = new Point(x, y);
        score++;
    }

    public void paintApple(Graphics g){
        g.drawImage(APPLE_ICON,apple.x,apple.y,APPLE_SCALE,APPLE_SCALE,null);
    }

    public Point getApple() {
        return apple;
    }

    public short getScore() {
        return score;
    }

    public void setApple(Point apple) {
        Apple.apple = apple;
    }

}
