/*#############################################################################
 # Copyright (c) rzvdev                                                       #
 # Created in 11/17/20, 7:45 PM                                               #
 # Author : Razvi Andre                                                       #
 # Linkedin : https://www.linkedin.com/in/lungu-razvan-andre-4858a417b        #
 # Github : https://github.com/razviiandre                                    #
 #                                                                            #
 #############################################################################*/
package gui;

import DAO.Account;
import controller.ControllerDB;
import interfaces.Initiable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterGUI extends JFrame implements ActionListener, Initiable,Runnable {
    //    FRAME VARIABLES
    private static final String JFRAME_TITLE = "REGISTER";
    private static final ImageIcon JRAME_ICON = new ImageIcon("src/main/resources/register_gui_icon.png");
    private static final Color JFRAME_BACKGROUND_COLOR = Color.decode("#87BFFF");
    private static final short JFRAME_WIDTH = 600;
    private static final short JFRAME_HEIGHT = 400;
    //    BUTONS,LABELS AND TEXXTFIELDS SIZ1E
    private static final short BUTTON_SIZE_VGAP = 30;
    private static final short BUTTON_SIZE_HGAP = 85;
    private static final short LABEL_SIZE_HGAP = 70;
    private static final short LABEL_SIZE_VGAP = 15;
    private static final short TEXTFIELD_SIZE_VGAP = 22;
    private static final short TEXTFIELD_SIZE_HGAP = 150;
    //    BUTTONS,LABELS AND TEXTFIELDS LOCATIONS VARIABLES
    private static final short BUTTON_BACK_LOCATION_X = 0;
    private static final short BUTTON_REGISTER_LOCATION_X = 247;
    private static final short TEXTFIELD_USER_LOCATION_X = 250;
    private static final short LABEL_LOCATION_X = 150;
    private static final short BUTTON_BACK_LOCATION_Y = 0;
    private static final short BUTTON_REGISTER_LOCATION_Y = 300;
    private static final short LABEL_USER_LOCATION_Y = 70;
    private static final short LABEL_PASSWORD_LOCATION_Y = 110;
    private static final short LABEL_RETYPE_PASSWORD_LOCATION_Y = 150;
    private static final short LABEL_EMAIL_LOCATION_Y = 190;
    private static final short LABEL_PHONE_LOCATION_Y = 230;
    private static final short TEXTFIELD_USER_LOCATION_Y = 65;
    private static final short PASSWORDFIELD_LOCATION_Y = 145;
    private static final short RETYPEPASSWORD_LOCATION_Y = 105;
    private static final short TEXTFIELD_EMAIL_LOCATION_Y = 185;
    private static final short TEXTFIELD_PHONE_LOCATION_Y = 225;
    //    BUTTONS AND LABELS TEXT VARIABLES
    private static final String BUTTON_BACK_TEXT = "BACK";
    private static final String BUTTON_REGISTER_TEXT = "REGISTER";
    private static final String LABEL_USER_TEXT = "User";
    private static final String LABEL_PASSWORD_TEXT = "Password";
    private static final String LABEL_RETYPE_PASSWORD_TEXT = "Retype Password";
    private static final String LABEL_EMAIL_TEXT = "Email";
    private static final String LABEL_PHONE_TEXT = "Phone";
    //    FONT VARIABLES
    private static final String BUTTON_FONT_TYPE = "Arial";
    private static final String LABEL_FONT_TYPE = "Arial";
    private static final byte BUTTON_FONT_SIZE = 11;
    private static final byte LABEL_FONT_SIZE = 16;
    private static final byte BUTTON_FONT_STYLE = Font.PLAIN;
    private static final byte LABEL_FONT_STYLE = Font.PLAIN;
    //    ICON VARIABLES
    private static final String ICON_SUCCES = "src/main/resources/icons/succes.png";
    private static final String ICON_DENIED = "src/main/resources/icons/denied.png";
    //    ALERTS VARIABLES
    private static final String ALERT_REGISTER_TITLE = "Successful";
    private static final String ALERT_REGISTER_TEXT = "Account successfully created!";
    private static final String ALERT_DENIED_TITLE = "DENIED";
    private static final String ALERT_DENIED_TEXT = "The account cannot be registered!";
    private static final String ALERT_CANNOT_CONNECT_TO_DATABASE_TITLE = "Server down!";
    private static final String ALERT_CANNOT_CONNECT_TO_DATABASE_TEXT = "There is no connection to the database!";
    //    CONDITIONS VARIABLES
    private static final short TEXT_CONDITIONS_LOCATION_X = 420;
    private static final byte CONDITIONS_FONT_SIZE = 10;
    private static final short CONDITIONS_SIZE_VGAP = 115;
    private static final short CONDITIONS_SIZE_HGAP = 150;
    private static final short TEXT_CONDITIONS_USER_LOCATION_Y = 18;
    private static final short TEXT_CONDITIONS_PASSWORD_LOCATION_Y = 58;
    private static final short TEXT_CONDITIONS_EMAIL_LOCATION_Y = 138;
    private static final short TEXT_CONDITIONS_PHONE_LOCATION_Y = 178;
    private static final String CONDITIONS_USER_TEXT_1 = "User cannot be empty!";
    private static final String CONDITIONS_USER_TEXT_2 = "Needs more than 5 characters!";
    private static final String CONDITIONS_PASSWORD_TEXT_1 = "You need a password!";
    private static final String CONDITIONS_PASSWORD_TEXT_2 = "Needs more than 5 characters!";
    private static final String CONDITIONS_PASSWORD_TEXT_3 = "Passwords don't match!";
    private static final String CONDITIONS_EMAIL_TEXT_1 = "Email cannot be empty!";
    private static final String CONDITIONS_EMAIL_TEXT_2 = "The email seems to be incorrect!";
    private static final String CONDITIONS_PHONE_TEXT_1 = "Phone cannot be empty!";
    private static final String CONDITIONS_PHONE_TEXT_2 = "Enter a valid number!";

    JButton jBack, jRegister;
    JPanel jPanel;
    JLabel jLabel_User, jLabel_Password, jLabel_retypePassword, jLabel_Email, jLabel_Phone;
    JLabel jLabel_User_Conditions, jLabel_Password_Conditions, jLabel_Email_Conditions, jLabel_Phone_Conditions;
    JTextField jText_User, jText_Email, jText_Phone;
    JPasswordField jPasswordField, jRetypePasswordField;
    ControllerDB controllerDB = new ControllerDB();
    Account account;

    public RegisterGUI() {
        initFrame();
        initButtons();
        initLabels();
        initTextFields();
        initPanel();
        endInit();
    }

    //    BUTTON METHODS
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        new MainGUI();
        this.dispose();
    }

    public void registerPerformed(ActionEvent actionEvent) {
        if(controllerDB.getStatus()){
        boolean textFieldPassConditions = true;
        if (!checkUser(jText_User)) {
            textFieldPassConditions = false;
        }
        if (!checkPassword(jPasswordField, jRetypePasswordField)) {
            textFieldPassConditions = false;
        }
        if (!checkEmail(jText_Email)) {
            textFieldPassConditions = false;
        }
        if (!checkPhone(jText_Phone)) {
            textFieldPassConditions = false;
        }

        if (textFieldPassConditions) {
            account = new Account(jText_User.getText(), String.valueOf(jPasswordField.getPassword()), String.valueOf(jRetypePasswordField.getPassword()),
                    jText_Email.getText(), jText_Phone.getText());
            controllerDB.init();
            if (controllerDB.canAdd(account)) {
                controllerDB.add(account);
                JOptionPane.showMessageDialog(jPanel, ALERT_REGISTER_TEXT, ALERT_REGISTER_TITLE, JOptionPane.INFORMATION_MESSAGE, new ImageIcon(ICON_SUCCES));
            } else {
                JOptionPane.showMessageDialog(jPanel, ALERT_DENIED_TEXT + "\n" + controllerDB.whyDenied(), ALERT_DENIED_TITLE, JOptionPane.INFORMATION_MESSAGE, new ImageIcon(ICON_DENIED));
            }
            jText_User.setText("");
            jText_Phone.setText("");
            jText_Email.setText("");
            jPasswordField.setText("");
            jRetypePasswordField.setText("");
            }
        } else {
            JOptionPane.showMessageDialog(jPanel,ALERT_CANNOT_CONNECT_TO_DATABASE_TEXT,ALERT_CANNOT_CONNECT_TO_DATABASE_TITLE,JOptionPane.OK_OPTION);
        }
    }

    //    CHECK CONDITIONS METHODS
    public boolean checkUser(JTextField jText_User) {
        if (jText_User.getText().equals("")) {
            System.out.println(CONDITIONS_USER_TEXT_1);
            jLabel_User_Conditions.setText(CONDITIONS_USER_TEXT_1);
            jLabel_User_Conditions.setVisible(true);
            return false;
        } else if (jText_User.getText().length() <= 5) {
            System.out.println(CONDITIONS_USER_TEXT_2);
            jLabel_User_Conditions.setText(CONDITIONS_USER_TEXT_2);
            jLabel_User_Conditions.setVisible(true);
            return false;
        } else {
            jLabel_User_Conditions.setText("");
            jLabel_User_Conditions.setVisible(false);
            return true;
        }
    }

    public boolean checkPassword(JPasswordField jPasswordField, JPasswordField jRetypePasswordField) {
        StringBuilder stringBuilder_password = new StringBuilder();
        StringBuilder stringBuilder_retypePassword = new StringBuilder();
        for (char c : jPasswordField.getPassword()) {
            stringBuilder_password.append(c);
        }
        for (char c : jRetypePasswordField.getPassword()) {
            stringBuilder_retypePassword.append(c);
        }

        if (String.valueOf(jPasswordField.getPassword()).equals("")) {
            System.out.println(CONDITIONS_PASSWORD_TEXT_1);
            jLabel_Password_Conditions.setText(CONDITIONS_PASSWORD_TEXT_1);
            jLabel_Password_Conditions.setVisible(true);
            return false;
        } else if (String.valueOf(jPasswordField.getPassword()).length() <= 5) {
            System.out.println(CONDITIONS_PASSWORD_TEXT_2);
            jLabel_Password_Conditions.setText(CONDITIONS_PASSWORD_TEXT_2);
            jLabel_Password_Conditions.setVisible(true);
            return false;
        } else if (!stringBuilder_password.toString().equals(stringBuilder_retypePassword.toString())) {
            System.out.println(CONDITIONS_PASSWORD_TEXT_3 + " " + stringBuilder_password + " = " + stringBuilder_retypePassword);
            jLabel_Password_Conditions.setText(CONDITIONS_PASSWORD_TEXT_3);
            jLabel_Password_Conditions.setVisible(true);
            return false;
        } else {
            jLabel_Password_Conditions.setText("");
            jLabel_Password_Conditions.setVisible(false);
            return true;
        }
    }

    public boolean checkEmail(JTextField jText_Email) {
        if (jText_Email.getText().equals("")) {
            System.out.println(CONDITIONS_EMAIL_TEXT_1);
            jLabel_Email_Conditions.setText(CONDITIONS_EMAIL_TEXT_1);
            jLabel_Email_Conditions.setVisible(true);
            return false;
        } else if (!jText_Email.getText().contains("@")) {
            System.out.println(CONDITIONS_EMAIL_TEXT_2);
            jLabel_Email_Conditions.setText(CONDITIONS_EMAIL_TEXT_2);
            jLabel_Email_Conditions.setVisible(true);
            return false;
        } else {
            jLabel_Email_Conditions.setText("");
            jLabel_Email_Conditions.setVisible(false);
            return true;
        }
    }

    public boolean checkPhone(JTextField jText_Phone) {
        if (jText_Phone.getText().equals("")) {
            System.out.println(CONDITIONS_PHONE_TEXT_1);
            jLabel_Phone_Conditions.setText(CONDITIONS_PHONE_TEXT_1);
            jLabel_Phone_Conditions.setVisible(true);
            return false;
        } else if (!jText_Phone.getText().equals("")) {
            try {
                Integer.parseInt(jText_Phone.getText());
                jLabel_Phone_Conditions.setText("");
                jLabel_Phone_Conditions.setVisible(false);
                return true;
            } catch (NumberFormatException e) {
                jLabel_Phone_Conditions.setText(CONDITIONS_PHONE_TEXT_2);
                jLabel_Phone_Conditions.setVisible(true);
                return false;
            }
        }
        return true;
    }
    @Override
    public void initFrame() {
        setTitle(JFRAME_TITLE);
        setSize(JFRAME_WIDTH, JFRAME_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(JRAME_ICON.getImage());
    }
    @Override
    public void initButtons() {
        jBack = new JButton(BUTTON_BACK_TEXT);
        jRegister = new JButton(BUTTON_REGISTER_TEXT);

        jBack.setBounds(BUTTON_BACK_LOCATION_X,BUTTON_BACK_LOCATION_Y,BUTTON_SIZE_HGAP, BUTTON_SIZE_VGAP);
        jBack.setOpaque(false);
        jBack.setFont(new Font(BUTTON_FONT_TYPE,BUTTON_FONT_STYLE,BUTTON_FONT_SIZE));
        jBack.setContentAreaFilled(false);
        jBack.setFocusable(false);

        jRegister.setBounds(BUTTON_REGISTER_LOCATION_X,BUTTON_REGISTER_LOCATION_Y,BUTTON_SIZE_HGAP,BUTTON_SIZE_VGAP);
        jRegister.setOpaque(false);
        jRegister.setFont(new Font(BUTTON_FONT_TYPE,BUTTON_FONT_STYLE,BUTTON_FONT_SIZE));
        jRegister.setContentAreaFilled(false);
        jRegister.setFocusable(false);

        jBack.addActionListener(this);
        jRegister.addActionListener(this::registerPerformed);
    }
    @Override
    public void initPanel() {
        jPanel = new JPanel();
        jPanel.setBackground(JFRAME_BACKGROUND_COLOR);
        jPanel.setLayout(null);
        jPanel.add(jBack);
        jPanel.add(jRegister);
        jPanel.add(jLabel_Password);
        jPanel.add(jLabel_User);
        jPanel.add(jLabel_retypePassword);
        jPanel.add(jLabel_Email);
        jPanel.add(jLabel_Phone);
        jPanel.add(jText_Email);
        jPanel.add(jText_User);
        jPanel.add(jPasswordField);
        jPanel.add(jRetypePasswordField);
        jPanel.add(jText_Phone);
        jPanel.add(jLabel_User_Conditions);
        jPanel.add(jLabel_Password_Conditions);
        jPanel.add(jLabel_Email_Conditions);
        jPanel.add(jLabel_Phone_Conditions);
    }
    @Override
    public void initLabels() {
        jLabel_User = new JLabel(LABEL_USER_TEXT);
        jLabel_Password = new JLabel(LABEL_PASSWORD_TEXT);
        jLabel_retypePassword = new JLabel(LABEL_RETYPE_PASSWORD_TEXT);
        jLabel_Email = new JLabel(LABEL_EMAIL_TEXT);
        jLabel_Phone = new JLabel(LABEL_PHONE_TEXT);
        jLabel_User_Conditions = new JLabel();
        jLabel_Password_Conditions = new JLabel();
        jLabel_Email_Conditions = new JLabel();
        jLabel_Phone_Conditions = new JLabel();

        jLabel_User.setLocation(LABEL_LOCATION_X,LABEL_USER_LOCATION_Y);
        jLabel_User.setFont(new Font(LABEL_FONT_TYPE,LABEL_FONT_STYLE,LABEL_FONT_SIZE));
        jLabel_User.setSize(LABEL_SIZE_HGAP,LABEL_SIZE_VGAP);

        jLabel_User_Conditions.setLocation(TEXT_CONDITIONS_LOCATION_X,TEXT_CONDITIONS_USER_LOCATION_Y);
        jLabel_User_Conditions.setFont(new Font(LABEL_FONT_TYPE,LABEL_FONT_STYLE,CONDITIONS_FONT_SIZE));
        jLabel_User_Conditions.setSize(CONDITIONS_SIZE_HGAP,CONDITIONS_SIZE_VGAP);
        jLabel_User_Conditions.setForeground(Color.RED);
        jLabel_User_Conditions.setVisible(false);

        jLabel_Password.setLocation(LABEL_LOCATION_X,LABEL_PASSWORD_LOCATION_Y);
        jLabel_Password.setFont(new Font(LABEL_FONT_TYPE,LABEL_FONT_STYLE,LABEL_FONT_SIZE));
        jLabel_Password.setSize(LABEL_SIZE_HGAP,LABEL_SIZE_VGAP);

        jLabel_Password_Conditions.setLocation(TEXT_CONDITIONS_LOCATION_X,TEXT_CONDITIONS_PASSWORD_LOCATION_Y);
        jLabel_Password_Conditions.setFont(new Font(LABEL_FONT_TYPE,LABEL_FONT_STYLE,CONDITIONS_FONT_SIZE));
        jLabel_Password_Conditions.setSize(CONDITIONS_SIZE_HGAP,CONDITIONS_SIZE_VGAP);
        jLabel_Password_Conditions.setForeground(Color.RED);
        jLabel_Password_Conditions.setVisible(false);

        jLabel_retypePassword.setLocation(LABEL_LOCATION_X-53,LABEL_RETYPE_PASSWORD_LOCATION_Y);
        jLabel_retypePassword.setFont(new Font(LABEL_FONT_TYPE,LABEL_FONT_STYLE,LABEL_FONT_SIZE));
        jLabel_retypePassword.setSize(LABEL_SIZE_HGAP+55,LABEL_SIZE_VGAP);

        jLabel_Email.setLocation(LABEL_LOCATION_X,LABEL_EMAIL_LOCATION_Y);
        jLabel_Email.setFont(new Font(LABEL_FONT_TYPE,LABEL_FONT_STYLE,LABEL_FONT_SIZE));
        jLabel_Email.setSize(LABEL_SIZE_HGAP,LABEL_SIZE_VGAP);

        jLabel_Email_Conditions.setLocation(TEXT_CONDITIONS_LOCATION_X, TEXT_CONDITIONS_EMAIL_LOCATION_Y);
        jLabel_Email_Conditions.setFont(new Font(LABEL_FONT_TYPE,LABEL_FONT_STYLE,CONDITIONS_FONT_SIZE));
        jLabel_Email_Conditions.setSize(CONDITIONS_SIZE_HGAP, CONDITIONS_SIZE_VGAP);
        jLabel_Email_Conditions.setForeground(Color.RED);
        jLabel_Email_Conditions.setVisible(false);

        jLabel_Phone.setLocation(LABEL_LOCATION_X,LABEL_PHONE_LOCATION_Y);
        jLabel_Phone.setFont(new Font(LABEL_FONT_TYPE,LABEL_FONT_STYLE,LABEL_FONT_SIZE));
        jLabel_Phone.setSize(LABEL_SIZE_HGAP,LABEL_SIZE_VGAP);

        jLabel_Phone_Conditions.setLocation(TEXT_CONDITIONS_LOCATION_X,TEXT_CONDITIONS_PHONE_LOCATION_Y);
        jLabel_Phone_Conditions.setFont(new Font(LABEL_FONT_TYPE,LABEL_FONT_STYLE,CONDITIONS_FONT_SIZE));
        jLabel_Phone_Conditions.setSize(CONDITIONS_SIZE_HGAP,CONDITIONS_SIZE_VGAP);
        jLabel_Phone_Conditions.setForeground(Color.RED);
        jLabel_Phone_Conditions.setVisible(false);
    }
    @Override
    public void initTextFields() {
        jText_User = new JTextField();
        jText_Email = new JTextField();
        jPasswordField = new JPasswordField();
        jRetypePasswordField = new JPasswordField();
        jText_Phone = new JTextField();


        jText_User.setBounds(TEXTFIELD_USER_LOCATION_X,TEXTFIELD_USER_LOCATION_Y,TEXTFIELD_SIZE_HGAP,TEXTFIELD_SIZE_VGAP);
        jText_User.setFont(new Font(null,Font.PLAIN,14));

        jPasswordField.setBounds(TEXTFIELD_USER_LOCATION_X,RETYPEPASSWORD_LOCATION_Y,TEXTFIELD_SIZE_HGAP,TEXTFIELD_SIZE_VGAP);
        jPasswordField.setFont(new Font(null,Font.PLAIN,20));
        jRetypePasswordField.setBounds(TEXTFIELD_USER_LOCATION_X,PASSWORDFIELD_LOCATION_Y,TEXTFIELD_SIZE_HGAP,TEXTFIELD_SIZE_VGAP);
        jRetypePasswordField.setFont(new Font(null,Font.PLAIN,20));

        jText_Email.setBounds(TEXTFIELD_USER_LOCATION_X,TEXTFIELD_EMAIL_LOCATION_Y,TEXTFIELD_SIZE_HGAP,TEXTFIELD_SIZE_VGAP);
        jText_Email.setFont(new Font(null,Font.PLAIN,14));

        jText_Phone.setBounds(TEXTFIELD_USER_LOCATION_X,TEXTFIELD_PHONE_LOCATION_Y,TEXTFIELD_SIZE_HGAP,TEXTFIELD_SIZE_VGAP);
        jText_Phone.setFont(new Font(null,Font.PLAIN,14));


    }
    @Override
    public void endInit() {
        add(jPanel);
        setVisible(true);
    }

    @Override
    public void run() {
    }
}
