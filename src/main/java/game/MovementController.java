/*#############################################################################
 # Copyright (c) rzvdev                                                       #
 # Created in 11/17/20, 7:45 PM                                               #
 # Author : Razvi Andre                                                       #
 # Linkedin : https://www.linkedin.com/in/lungu-razvan-andre-4858a417b        #
 # Github : https://github.com/razviiandre                                    #
 #                                                                            #
 #############################################################################*/
package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MovementController extends KeyAdapter {
    public Snake snake = new Snake();
    Apple apple = new Apple();

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        Direction previousDirection = snake.getDirection();
            if (key == KeyEvent.VK_A && previousDirection != Direction.RIGHT ) {
                snake.setDirection(Direction.LEFT);
            }
            if (key == KeyEvent.VK_D && previousDirection != Direction.LEFT) {
                snake.setDirection(Direction.RIGHT);
            }
            if (key == KeyEvent.VK_W && previousDirection != Direction.DOWN) {
                snake.setDirection(Direction.UP);
            }
            if (key == KeyEvent.VK_S && previousDirection != Direction.UP) {
                snake.setDirection(Direction.DOWN);
            }
    }
}
