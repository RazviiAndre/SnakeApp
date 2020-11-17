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

public class InputController extends KeyAdapter {
    public Snake snake = new Snake();
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
            snake.setDirection(Direction.LEFT);
        }
        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D && snake.getDirection() != Direction.LEFT) {
            snake.setDirection(Direction.RIGHT);
        }
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W && snake.getDirection() != Direction.DOWN) {
            snake.setDirection(Direction.UP);
        }
        if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S && snake.getDirection() != Direction.UP) {
            snake.setDirection(Direction.DOWN);
        }
    }
}
