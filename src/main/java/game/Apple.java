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
import java.util.Random;

public class Apple{
    private static Point apple;

    Apple(){
        apple = new Point(300,200);
    }

    public void newApple(int newX, int newY){
        // TODO: 19-Nov-20 Improve function,  apple spawned off-screen
        Random random = new Random();
        int x = random.ints(0,newX).filter(value -> value % 20 == 0).findAny().getAsInt();
        int y = random.ints(0,newY).filter(value -> value % 20 == 0).findAny().getAsInt();

        apple = new Point(x, y);
    }

    public Point getApple() {
        return apple;
    }

    public void setApple(Point apple) {
        Apple.apple = apple;
    }
}
