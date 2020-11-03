package gui;

import controller.ControllerDB;
import interfaces.Initiable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainGUI extends JFrame implements ActionListener, Initiable, Runnable {
//    FRAME VARIABLES
    private static final String JFRAME_TITLE = "Snake Swing";
    private static final ImageIcon JFRAME_ICON = new ImageIcon("src/main/resources/main_gui_icon.png");
    private static final Color JFRAME_BACKGROUND_COLOR = Color.decode( "#87BFFF");
    private static final short JFRAME_WIDTH = 600;
    private static final short JFRAME_HEIGHT = 400;
//    BUTTON SIZES VARIABLES
    private static final short BUTTON_SIZE_VGAP  = 30;
    private static final short BUTTON_SIZE_HGAP  = 85;
//    BUTTON TEXT VARIABLES
    private static final String BUTTON_PLAY_TEXT = "PLAY";
    private static final String BUTTON_LOGIN_TEXT = "LOGIN";
    private static final String BUTTON_LOGOUT_TEXT = "LOGOUT";
    private static final String BUTTON_REGISTER_TEXT = "REGISTER";
    private static final String BUTTON_SETTINGS_TEXT = "SETTINGS";
//    BUTTON FONT VARIABLES
    private static final String BUTTON_FONT_TYPE = "Arial";
    private static final byte BUTTON_FONT_SIZE = 11;
    private static final byte BUTTON_FONT_STYLE = Font.PLAIN;
//    BUTTONS LOCATIONS VARIABLES
    private static final short BUTTON_LOGOUT_LOCATION_X = 0;
    private static final short BUTTON_PLAY_LOCATION_X = 0;
    private static final short BUTTON_LOGIN_LOCATION_X = 0;
    private static final short BUTTON_REGISTER_LOCATION_X = 0;
    private static final short BUTTON_LOGOUT_LOCATION_Y = 0;
    private static final short BUTTON_REGISTER_LOCATION_Y = 33;
    private static final short BUTTON_PLAY_LOCATION_Y = 66;
    private static final short BUTTON_LOGIN_LOCATION_Y = 0;
//    STATUS ICON LOCATIONS VARIABLES
    private static final short STATUS_LOCATION_X = 555;
    private static final short STATUS_LOCATION_Y = 5;
    private static final short STATUS_VGAP = 16;
    private static final short STATUS_HGAP = 16;
//    STATUS ICON VARIBALES
    private static final String ICON_CONNECTED = "src/main/resources/icons/connected.png";
    private static final String ICON_REFUSED = "src/main/resources/icons/cannotConnect.png";


    ControllerDB controllerDB = new ControllerDB();
    JButton jPlay,jLogin,jRegister,jLoggout,jSettings;
    JPanel jPanel;
    JLabel jStatus = new JLabel();

    private static boolean loggedIn = false;

    public MainGUI(){
        if(!loggedIn) {
            initFrame();
            initButtons();
            initStatus(controllerDB.getStatus());
            initPanel();
            endInit();
        } else {
            initFrame();
            initLoggedButtons();
            initStatus(controllerDB.getStatus());
            initLoggedPanel();
            endInit();
        }

    }

    @Override
    public void run() {
        controllerDB.init();
        initStatus(controllerDB.getStatus());
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        new RegisterGUI();
        this.dispose();
    }
    public void actionLoggoutButton(ActionEvent av){
        MainGUI.setLoggedIn(false);
        controllerDB.tryLoggout();
        new MainGUI();
        this.dispose();
    }
    public void actionLoginButton(ActionEvent av){
        new LoginGUI();
        this.dispose();
    }

    public void initLoggedButtons(){
        jLoggout = new JButton(BUTTON_LOGOUT_TEXT);
        jPlay = new JButton(BUTTON_PLAY_TEXT);
        jSettings = new JButton(BUTTON_SETTINGS_TEXT);

        jLoggout.setBounds(BUTTON_LOGOUT_LOCATION_X,BUTTON_LOGOUT_LOCATION_Y,BUTTON_SIZE_HGAP,BUTTON_SIZE_VGAP);
        jLoggout.setOpaque(false);
        jLoggout.setContentAreaFilled(false);
        jLoggout.setFocusable(false);
        jLoggout.setFont(new Font(BUTTON_FONT_TYPE,BUTTON_FONT_STYLE,BUTTON_FONT_SIZE));

        jSettings.setBounds(BUTTON_REGISTER_LOCATION_X,BUTTON_REGISTER_LOCATION_Y,BUTTON_SIZE_HGAP,BUTTON_SIZE_VGAP);
        jSettings.setFocusable(false);
        jSettings.setContentAreaFilled(false);
        jSettings.setFont(new Font(BUTTON_FONT_TYPE,BUTTON_FONT_STYLE,BUTTON_FONT_SIZE));
        jSettings.setOpaque(false);

        jPlay.setBounds(BUTTON_PLAY_LOCATION_X,BUTTON_PLAY_LOCATION_Y,BUTTON_SIZE_HGAP,BUTTON_SIZE_VGAP);
        jPlay.setFocusable(false);
        jPlay.setContentAreaFilled(false);
        jPlay.setFont(new Font(BUTTON_FONT_TYPE,BUTTON_FONT_STYLE,BUTTON_FONT_SIZE));
        jPlay.setOpaque(false);

        jLoggout.addActionListener(this::actionLoggoutButton);
    }
    public void initLoggedPanel(){
        jPanel = new JPanel();
        jPanel.setLayout(null);
        jPanel.setBackground(JFRAME_BACKGROUND_COLOR);
        jPanel.add(jPlay);
        jPanel.add(jLoggout);
        jPanel.add(jSettings);
        jPanel.add(jStatus);
    }

    public void initStatus(boolean status){
        if(status) {
            jStatus.setIcon(new ImageIcon(ICON_CONNECTED));
        } else {
            jStatus.setIcon(new ImageIcon(ICON_REFUSED));
        }
        jStatus.setBounds(STATUS_LOCATION_X,STATUS_LOCATION_Y,STATUS_HGAP,STATUS_VGAP);
    }

    @Override
    public void initPanel() {
        jPanel = new JPanel();
        jPanel.setLayout(null);
        jPanel.setBackground(JFRAME_BACKGROUND_COLOR);
        jPanel.add(jLogin);
        jPanel.add(jRegister);
        jPanel.add(jPlay);
        jPanel.add(jStatus);
    }
    @Override
    public void initButtons() {
        jPlay = new JButton(BUTTON_PLAY_TEXT);
        jLogin = new JButton(BUTTON_LOGIN_TEXT);
        jRegister = new JButton(BUTTON_REGISTER_TEXT);

        jLogin.setBounds(BUTTON_LOGIN_LOCATION_X,BUTTON_LOGIN_LOCATION_Y,BUTTON_SIZE_HGAP,BUTTON_SIZE_VGAP);
        jLogin.setOpaque(false);
        jLogin.setContentAreaFilled(false);
        jLogin.setFocusable(false);
        jLogin.setFont(new Font(BUTTON_FONT_TYPE,BUTTON_FONT_STYLE,BUTTON_FONT_SIZE));

        jRegister.setBounds(BUTTON_REGISTER_LOCATION_X,BUTTON_REGISTER_LOCATION_Y,BUTTON_SIZE_HGAP,BUTTON_SIZE_VGAP);
        jRegister.setOpaque(false);
        jRegister.setContentAreaFilled(false);
        jRegister.setFont(new Font(BUTTON_FONT_TYPE,BUTTON_FONT_STYLE,BUTTON_FONT_SIZE));
        jRegister.setFocusable(false);

        jPlay.setBounds(BUTTON_PLAY_LOCATION_X,BUTTON_PLAY_LOCATION_Y,BUTTON_SIZE_HGAP,BUTTON_SIZE_VGAP);
        jPlay.setFocusable(false);
        jPlay.setContentAreaFilled(false);
        jPlay.setFont(new Font(BUTTON_FONT_TYPE,BUTTON_FONT_STYLE,BUTTON_FONT_SIZE));
        jPlay.setOpaque(false);
        jPlay.setEnabled(false);

        jRegister.addActionListener(this);
        jLogin.addActionListener(this::actionLoginButton);
    }

    @Override
    public void initFrame() {
        setTitle(JFRAME_TITLE);
        setSize(JFRAME_WIDTH, JFRAME_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(JFRAME_ICON.getImage());
    }
    @Override
    public void endInit() {
        add(jPanel);
        setVisible(true);
    }
    public static void setLoggedIn(boolean loggedIn) {
        MainGUI.loggedIn = loggedIn;
    }
    @Override
    public void initTextFields() {

    }
    @Override
    public void initLabels() {
    }
}
