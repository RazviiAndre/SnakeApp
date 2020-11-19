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

    Apple(){
        apple = new Point(300,200);
    }

    public void newApple(int jframe_height, int jframe_width){
        Random random = new Random();
        int x = random.ints(0,jframe_height-50).filter(value -> value % 20 == 0).findAny().getAsInt();
        int y = random.ints(0,jframe_width-50).filter(value -> value % 20 == 0).findAny().getAsInt();
        apple = new Point(x, y);
    }

    public void paintApple(Graphics g,int scale){
        g.drawImage(APPLE_ICON,apple.x,apple.y,scale,scale,null);
    }

    public Point getApple() {
        return apple;
    }

    public void setApple(Point apple) {
        Apple.apple = apple;
    }
}
