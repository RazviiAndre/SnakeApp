package gui;

import interfaces.Initiable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class SettingsGUI extends JFrame implements Initiable, ActionListener, ItemListener {
    //    FRAME VARIABLES
    private static final String JFRAME_TITLE = "Settings";
    private static final ImageIcon JFRAME_ICON = new ImageIcon("src/main/resources/main_gui_icon.png");
    private static final Color JFRAME_BACKGROUND_COLOR = Color.decode( "#87BFFF");
    private static final short JFRAME_WIDTH = 600;
    private static final short JFRAME_HEIGHT = 400;

    private static final short BUTTON_SIZE_VGAP = 30;
    private static final short BUTTON_SIZE_HGAP = 85;
    private static final short BUTTON_BACK_LOCATION_X = 0;
    private static final short BUTTON_BACK_LOCATION_Y = 0;
    private static final String BUTTON_BACK_TEXT = "BACK";
    private static final byte BUTTON_FONT_STYLE = Font.PLAIN;
    private static final byte BUTTON_FONT_SIZE = 11;
    private static final String BUTTON_FONT_TYPE = "Arial";

    private static final String LABEL_RESOLUTION_TEXT = "GAME RESOLUTION";
    private static final short LABEL_RESOLUTION_LOCATION_X = 245;
    private static final short LABEL_RESOLUTION_LOCATION_Y = 50;
    private static final short LABEL_RESOLUTION_HGAP = 150;
    private static final short LABEL_RESOLUTION_VGAP = 50;

    private static int GAME_RESOLUTION_WIDTH = 600;
    private static int GAME_RESOLUTION_HEIGHT = 400;

    public static int getGameResolutionWidth() {
        return GAME_RESOLUTION_WIDTH;
    }

    public static int getGameResolutionHeight() {
        return GAME_RESOLUTION_HEIGHT;
    }

    JPanel jPanel;
    JButton jBack;
    JLabel jResolution;
    JCheckBox jCheckBox_600x400,jCheckBox_800x600,jCheckBox_fullscreen;

    public SettingsGUI(){
        initFrame();
        initButtons();
        initLabels();
        initPanel();
        endInit();
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
        jCheckBox_600x400 = new JCheckBox("600x400",true);
        jCheckBox_800x600 = new JCheckBox("800x600");
        jCheckBox_fullscreen = new JCheckBox("Fullscreen");

        jCheckBox_600x400.setBackground(JFRAME_BACKGROUND_COLOR);
        jCheckBox_600x400.setBounds(260,90,90,15);
        jCheckBox_600x400.setFocusable(false);
        jCheckBox_600x400.addItemListener(this);

        jCheckBox_800x600.setBackground(JFRAME_BACKGROUND_COLOR);
        jCheckBox_800x600.setBounds(260,110,90,15);
        jCheckBox_800x600.setFocusable(false);
        jCheckBox_800x600.addItemListener(this::itemStateChanged2);

        jCheckBox_fullscreen.setBackground(JFRAME_BACKGROUND_COLOR);
        jCheckBox_fullscreen.setBounds(260,130,90,15);
        jCheckBox_fullscreen.setFocusable(false);
        jCheckBox_fullscreen.addItemListener(this::itemStateChanged3);





        jBack.setBounds(BUTTON_BACK_LOCATION_X,BUTTON_BACK_LOCATION_Y,BUTTON_SIZE_HGAP, BUTTON_SIZE_VGAP);
        jBack.setOpaque(false);
        jBack.setFont(new Font(BUTTON_FONT_TYPE,BUTTON_FONT_STYLE,BUTTON_FONT_SIZE));
        jBack.setContentAreaFilled(false);
        jBack.setFocusable(false);

        jBack.addActionListener(this);
    }

    @Override
    public void initPanel() {
        jPanel = new JPanel();
        jPanel.setLayout(null);
        jPanel.setBackground(JFRAME_BACKGROUND_COLOR);
        jPanel.add(jBack);
        jPanel.add(jResolution);
        jPanel.add(jCheckBox_600x400);
        jPanel.add(jCheckBox_800x600);
        jPanel.add(jCheckBox_fullscreen);
    }

    @Override
    public void initLabels() {
        jResolution = new JLabel(LABEL_RESOLUTION_TEXT);
        jResolution.setBounds(LABEL_RESOLUTION_LOCATION_X,LABEL_RESOLUTION_LOCATION_Y ,LABEL_RESOLUTION_HGAP,LABEL_RESOLUTION_VGAP);
    }

    @Override
    public void initTextFields() {

    }

    @Override
    public void endInit() {
        add(jPanel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
            new MainGUI();
            this.dispose();
    }

    public void itemStateChanged3(ItemEvent itemEvent){
        if(jCheckBox_fullscreen.isSelected()){
            jCheckBox_600x400.setSelected(false);
            jCheckBox_800x600.setSelected(false);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            GAME_RESOLUTION_HEIGHT = (int)screenSize.getHeight();
            GAME_RESOLUTION_WIDTH = (int)screenSize.getWidth();
        }
    }

    public void itemStateChanged2(ItemEvent itemEvent){
        if(jCheckBox_800x600.isSelected()){
            jCheckBox_600x400.setSelected(false);
            jCheckBox_fullscreen.setSelected(false);
            GAME_RESOLUTION_WIDTH = 800;
            GAME_RESOLUTION_HEIGHT = 400;
        }
    }

    @Override
    public void itemStateChanged(ItemEvent itemEvent) {
        if(jCheckBox_600x400.isSelected()){
            jCheckBox_800x600.setSelected(false);
            jCheckBox_fullscreen.setSelected(false);
            GAME_RESOLUTION_WIDTH = 600;
            GAME_RESOLUTION_HEIGHT = 400;
        }
    }
}
