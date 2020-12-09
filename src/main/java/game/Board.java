/*#############################################################################
 # Copyright (c) rzvdev                                                       #
 # Created in 11/17/20, 7:45 PM                                               #
 # Author : Razvi Andre                                                       #
 # Linkedin : https://www.linkedin.com/in/lungu-razvan-andre-4858a417b        #
 # Github : https://github.com/razviiandre                                    #
 #                                                                            #
 #############################################################################*/
package game;

import gui.MainGUI;
import gui.SettingsGUI;
import interfaces.Initiable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board extends JPanel implements ActionListener, Initiable {
    private final static String JFRAME_TITLE = "SNAKE";
    private static final ImageIcon JFRAME_ICON = new ImageIcon("src/main/resources/icons/snakeapp_icon.png");
    public final static int JFRAME_WIDTH = SettingsGUI.getGameResolutionWidth();
    public final static int JFRAME_HEIGHT = SettingsGUI.getGameResolutionHeight();
    private static final String LABEL_FONT_TYPE = "Arial";
    private static final byte LABEL_FONT_SIZE = 14;
    private static final byte LABEL_FONT_STYLE = Font.PLAIN;
    private static final Color backgroundColor = new Color(179, 255, 179);

    public Apple apple = new Apple();
    public Snake snake = new Snake();
    public boolean gameOver = false;
    public static Timer timer;
    public static int snakeSpeed = 300;
    public static int increaseSpeedValue = 10;
    public static int increaseSpeedAfterEatApple = 3;
    public static int countEatedApple = 0;

    JPanel jPanel;
    JLabel jScore_Text,jScore;
    JFrame jFrame;

    public Board(){
        initFrame();
        initPanel();
        initLabels();
        startGame();
        endInit();
        setBackground(backgroundColor);
    }

    public void startGame(){
        timer = new Timer(snakeSpeed, this);
        snake.getSnakeLocation().add(new Point(100,100));
        timer.start();
    }

    public void initFrame(){
        jFrame = new JFrame(JFRAME_TITLE);
        jFrame.setSize(JFRAME_WIDTH, JFRAME_HEIGHT);
        jFrame.setVisible(true);
        jFrame.setLocationRelativeTo(null);
        jFrame.getContentPane().add(this);
        jFrame.setIconImage(JFRAME_ICON.getImage());
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.addKeyListener(new MovementController());
    }

    @Override
    public void initButtons() {

    }

    public void initPanel(){
        jPanel = new JPanel();
        jPanel.setLayout(null);
    }

    @Override
    public void initLabels() {
        jScore_Text = new JLabel("Score: ");
        jScore = new JLabel("0");

        jScore_Text.setBounds(JFRAME_WIDTH/2-35,5,45,10);
        jScore_Text.setFont(new Font(LABEL_FONT_TYPE,LABEL_FONT_STYLE,LABEL_FONT_SIZE));
        jScore_Text.setForeground(Color.RED);

        jScore.setBounds(JFRAME_WIDTH/2+15,5,35,10);
        jScore.setFont(new Font(LABEL_FONT_TYPE,LABEL_FONT_STYLE,LABEL_FONT_SIZE));

    }

    @Override
    public void initTextFields() {

    }

    public void endInit() {
        add(jPanel);
        add(jScore_Text);
        add(jScore);
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
            increaseSpeedAfterEatApple++;
            if(snakeSpeed == 100){
                increaseSpeedValue = 0;
            }
        }
    }

    public void updateScore(){
        jScore.setText(String.valueOf(apple.getScore()));
    }

    boolean u = true;
    public void gameOver(){
        Object[] options = {"Try again.","Back"};
        if(u) {
            int response = JOptionPane.showOptionDialog(null, "You died..", "Gameover",
                    JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, options, options[0]);
            if (response == 0) {
                 jFrame.dispose();
                 new Board();
            } else if (response == 1) {
                jFrame.dispose();
                MainGUI mainGUI = new MainGUI();
                mainGUI.setHighscore(Integer.parseInt(jScore.getText()));
            }
            u = false;
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
                updateScore();
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
        } else {
            gameOver();
        }
    }
}
