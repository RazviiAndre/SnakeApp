package gui;

import controller.ControllerDB;
import interfaces.Initiable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame implements ActionListener, Initiable {
//    FRAME VARIABLES
    private static final String JFRAME_TITLE = "LOGIN";
    private static final ImageIcon JFRAME_ICON = new ImageIcon("src/main/resources/login_gui_icon.png");
    private static final Color JFRAME_BACKGROUND_COLOR = Color.decode( "#87BFFF");
    private static final short JFRAME_WIDTH = 600;
    private static final short JFRAME_HEIGHT = 400;
//    BUTTONS AND LABELS TEXT VARIABLES
    private static final String BUTTON_BACK_TEXT = "BACK";
    private static final String BUTTON_LOGIN_TEXT = "LOGIN";
    private static final String LABEL_USER_TEXT = "User";
    private static final String LABEL_PASSWORD_TEXT = "Password";
//    BUTTON,TEXTFIELDS AND LABEL LOCATIONS VARIABLES
    private static final short BUTTON_BACK_LOCATION_X = 0;
    private static final short BUTTON_BACK_LOCATION_Y = 0;
    private static final short BUTTON_LOGIN_LOCATION_X = 247;
    private static final short BUTTON_LOGIN_LOCATION_Y = 300;
    private static final short LABEL_LOCATION_X = 150;
    private static final short LABEL_USER_LOCATION_Y = 120;
    private static final short LABEL_PASSWORD_LOCATION_Y = 160;
    private static final short TEXTFIELD_USER_LOCATION_X = 250;
    private static final short TEXTFIELD_USER_LOCATION_Y = 115;
    private static final short PASSWORDFIELD_LOCATION_X = 250;
    private static final short PASSWORDFIELD_LOCATION_Y = 155;
//    BUTTON,TEXTFIELDS AND LABELS SIZES VARIABLES
    private static final short BUTTON_SIZE_VGAP  = 30;
    private static final short BUTTON_SIZE_HGAP  = 85;
    private static final short LABEL_SIZE_HGAP = 70;
    private static final short LABEL_SIZE_VGAP = 15;
    private static final short TEXTFIELD_SIZE_VGAP = 22;
    private static final short TEXTFIELD_SIZE_HGAP = 150;
//    BUTTON AND LABEL FONTS VARIABLES
    private static final String BUTTON_FONT_TYPE = "Arial";
    private static final String LABEL_FONT_TYPE = "Arial";
    private static final byte BUTTON_FONT_SIZE = 11;
    private static final byte LABEL_FONT_SIZE = 16;
    private static final byte BUTTON_FONT_STYLE = Font.PLAIN;
    private static final byte LABEL_FONT_STYLE = Font.PLAIN;
//    ICON VARIABLES
    private static final String ICON_DENIED = "src/main/resources/icons/denied.png";
    private static final String ICON_SUCCES = "src/main/resources/icons/succes.png";
//    ALERTS VARIABLES
    private static final String ALERT_LOGIN_SUCCES_TITLE = "LOGGED IN!";
    private static final String ALERT_LOGIN_DENIED_TITLE = "DENIED!";
    private static final String ALERT_LOGIN_SUCCES_TEXT = "You have successfully logged in!";
    private static final String ALERT_LOGIN_DENIED_TEXT = "User or password does not match!";
    private static final String ALERT_CANNOT_CONNECT_TO_DATABASE_TITLE = "Server down!";
    private static final String ALERT_CANNOT_CONNECT_TO_DATABASE_TEXT = "There is no connection to the database!";


    JButton jBack,jLogin;
    JPanel jPanel;
    JLabel jLabel_User,jLabel_Password;
    JTextField jText_User;
    JPasswordField jPasswordField;
    ControllerDB controllerDB = new ControllerDB();

    public LoginGUI(){
        initFrame();
        initButtons();
        initLabels();
        initTextFields();
        initPanel();
        endInit();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        new MainGUI();
        this.dispose();
    }
    public void loginPerformed(ActionEvent actionEvent){
        if(controllerDB.getStatus()) {
            controllerDB.init();
            if (controllerDB.tryLogin(jText_User.getText(), jPasswordField.getText())) {
                JOptionPane.showMessageDialog(jPanel, ALERT_LOGIN_SUCCES_TEXT, ALERT_LOGIN_SUCCES_TITLE, JOptionPane.PLAIN_MESSAGE, new ImageIcon(ICON_SUCCES));
                MainGUI.setLoggedIn(true);
                new MainGUI();
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(jPanel, ALERT_LOGIN_DENIED_TEXT, ALERT_LOGIN_DENIED_TITLE, JOptionPane.PLAIN_MESSAGE, new ImageIcon(ICON_DENIED));
                jText_User.setText("");
                jPasswordField.setText("");
            }
        } else {
            JOptionPane.showMessageDialog(jPanel,ALERT_CANNOT_CONNECT_TO_DATABASE_TEXT,ALERT_CANNOT_CONNECT_TO_DATABASE_TITLE,JOptionPane.OK_OPTION);
        }
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
    public void initButtons() {
        jBack = new JButton(BUTTON_BACK_TEXT);
        jLogin = new JButton(BUTTON_LOGIN_TEXT);

        jBack.setBounds(BUTTON_BACK_LOCATION_X,BUTTON_BACK_LOCATION_Y,BUTTON_SIZE_HGAP,BUTTON_SIZE_VGAP);
        jBack.setOpaque(false);
        jBack.setContentAreaFilled(false);
        jBack.setFocusable(false);
        jBack.setFont(new Font(BUTTON_FONT_TYPE,BUTTON_FONT_STYLE,BUTTON_FONT_SIZE));

        jLogin.setBounds(BUTTON_LOGIN_LOCATION_X,BUTTON_LOGIN_LOCATION_Y,BUTTON_SIZE_HGAP,BUTTON_SIZE_VGAP);
        jLogin.setOpaque(false);
        jLogin.setContentAreaFilled(false);
        jLogin.setFocusable(false);
        jLogin.setFont(new Font(BUTTON_FONT_TYPE,BUTTON_FONT_STYLE,BUTTON_FONT_SIZE));

        jBack.addActionListener(this);
        jLogin.addActionListener(this::loginPerformed);
    }

    @Override
    public void initPanel() {
        jPanel = new JPanel();
        jPanel.setLayout(null);
        jPanel.setBackground(JFRAME_BACKGROUND_COLOR);
        jPanel.add(jBack);
        jPanel.add(jLogin);
        jPanel.add(jLabel_User);
        jPanel.add(jLabel_Password);
        jPanel.add(jPasswordField);
        jPanel.add(jText_User);
    }

    @Override
    public void initLabels() {
        jLabel_User = new JLabel(LABEL_USER_TEXT);
        jLabel_Password = new JLabel(LABEL_PASSWORD_TEXT);

        jLabel_User.setLocation(LABEL_LOCATION_X,LABEL_USER_LOCATION_Y);
        jLabel_User.setFont(new Font(LABEL_FONT_TYPE,LABEL_FONT_STYLE,LABEL_FONT_SIZE));
        jLabel_User.setSize(LABEL_SIZE_HGAP,LABEL_SIZE_VGAP);

        jLabel_Password.setLocation(LABEL_LOCATION_X,LABEL_PASSWORD_LOCATION_Y);
        jLabel_Password.setFont(new Font(LABEL_FONT_TYPE,LABEL_FONT_STYLE,LABEL_FONT_SIZE));
        jLabel_Password.setSize(LABEL_SIZE_HGAP,LABEL_SIZE_VGAP);
    }

    @Override
    public void initTextFields() {
        jText_User = new JTextField();
        jPasswordField = new JPasswordField();

        jText_User.setBounds(TEXTFIELD_USER_LOCATION_X,TEXTFIELD_USER_LOCATION_Y,TEXTFIELD_SIZE_HGAP,TEXTFIELD_SIZE_VGAP);
        jText_User.setFont(new Font(null,Font.PLAIN,14));
        jPasswordField.setBounds(PASSWORDFIELD_LOCATION_X, PASSWORDFIELD_LOCATION_Y,TEXTFIELD_SIZE_HGAP,TEXTFIELD_SIZE_VGAP);
        jPasswordField.setFont(new Font(null,Font.PLAIN,20));
    }

    @Override
    public void endInit() {
        add(jPanel);
        setVisible(true);
    }
}
