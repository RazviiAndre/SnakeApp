/*#############################################################################
 # Copyright (c) rzvdev                                                       #
 # Created in 11/17/20, 7:45 PM                                               #
 # Author : Razvi Andre                                                       #
 # Linkedin : https://www.linkedin.com/in/lungu-razvan-andre-4858a417b        #
 # Github : https://github.com/razviiandre                                    #
 #                                                                            #
 #############################################################################*/
package interfaces;

import java.awt.*;

public interface Playable {

    void initBoard();
    void loadImages();
    void initGame();
    void doDrawing(Graphics graphics);
    void gameOver(Graphics graphics);
    void checkApple(Graphics graphics);
    void move();
    void checkCollision();
    void locateApple();
}
