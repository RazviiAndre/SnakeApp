/*#############################################################################
 # Copyright (c) rzvdev                                                       #
 # Created in 11/17/20, 7:45 PM                                               #
 # Author : Razvi Andre                                                       #
 # Linkedin : https://www.linkedin.com/in/lungu-razvan-andre-4858a417b        #
 # Github : https://github.com/razviiandre                                    #
 #                                                                            #
 #############################################################################*/
package game;

import gui.SettingsGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// TODO: 24-Nov-20 ADDING A SCORE LABEL , MAYBE MAXIMUM SPEED and MOVES ? MAYBE A TOP WITH ALL PLAYERS
public class Board extends JPanel implements ActionListener {
    private final static String JFRAME_TITLE = "SNAKE";
    private static final ImageIcon JFRAME_ICON = new ImageIcon("src/main/resources/icons/snakeapp_icon.png");
    public final static int JFRAME_WIDTH = SettingsGUI.getGameResolutionWidth();
    public final static int JFRAME_HEIGHT = SettingsGUI.getGameResolutionHeight();

    public static Timer timer;
    public static int snakeSpeed = 300;
    public static int increaseSpeedValue = 50;
    public static int increaseSpeedAfterEatApple = 3;
    public static int countEatedApple = 0;

    public static JPanel jPanel;
    public Snake snake = new Snake();
    public Apple apple = new Apple();
    public boolean gameOver = false;

    public Board(){
        initFrame();
        initPanel();
        startGame();
        endInit();
    }

    public static void main(String[] args) {
        Board board = new Board();
    }

    public void startGame(){
        timer = new Timer(snakeSpeed, this);
        snake.getSnakeLocation().add(new Point(100,100));
        timer.start();
    }

    public void initFrame(){
        JFrame jFrame = new JFrame(JFRAME_TITLE);
        jFrame.setSize(JFRAME_WIDTH, JFRAME_HEIGHT);
        jFrame.setVisible(true);
        jFrame.setLocationRelativeTo(null);
        jFrame.getContentPane().add(this);
        jFrame.setIconImage(JFRAME_ICON.getImage());
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.addKeyListener(new MovementController());
    }

    public void initPanel(){
        jPanel = new JPanel();
        jPanel.setLayout(null);
    }

    public void endInit() {
        add(jPanel);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        snake.paintSnake(g);
        apple.paintApple(g);
    }

    public void controlSpeed(int speed){
            timer.stop();
            timer = new Timer(speed, this);
            timer.start();
    }
    public void increaseSpeed(){
        countEatedApple++;
        if(countEatedApple == increaseSpeedAfterEatApple) {
            snakeSpeed -= increaseSpeedValue;
            controlSpeed(snakeSpeed);
            countEatedApple = 0;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!gameOver) {
            snake.move();
            if (!snake.checkPosition(JFRAME_HEIGHT, JFRAME_WIDTH)) {
                gameOver = true;
            }
            if(snake.eatApple()){
                apple.newApple(JFRAME_HEIGHT,JFRAME_WIDTH);
                increaseSpeed();
            } else {
                snake.removeLastSnakePart();
            }
            repaint();
//            apple.newApple(JFRAME_HEIGHT,JFRAME_WIDTH);
//            System.out.println("Apple x:" + apple.getApple().x + " y: " + apple.getApple().y);
//            System.out.println("Snake x:" + snake.getSnakeLocation().get(0).x + " y: " + snake.getSnakeLocation().get(0).y );
//            System.out.println("Current speed: " + snakeSpeed);
//            System.out.println("CountEatedApple: " + countEatedApple);
//            System.out.println("Score: " + apple.getScore());
        }
    }
}
