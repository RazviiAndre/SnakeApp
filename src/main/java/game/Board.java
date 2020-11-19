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

// TODO: 19-Nov-20 Improve LIMIT SCREEN FOR GAMEOVER

public class Board extends JPanel implements ActionListener {
    private final static String JFRAME_TITLE = "Snake";
    private static final ImageIcon JFRAME_ICON = new ImageIcon("src/main/resources/icons/snake.png");
    public final static int JFRAME_WIDTH = SettingsGUI.getGameResolutionWidth();
    public final static int JFRAME_HEIGHT = SettingsGUI.getGameResolutionHeight();
    private static final short OBJECT_SCALE = 35;

    public Timer timer;

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
        timer = new Timer(300, this);
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
        snake.paintSnake(g,OBJECT_SCALE);
        apple.paintApple(g,OBJECT_SCALE);
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
            } else {
                snake.removeLastSnakePart();
            }
            repaint();

        }
    }
}
