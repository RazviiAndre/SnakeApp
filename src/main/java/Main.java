import controller.ControllerDB;
import gui.MainGUI;


import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        MainGUI mainGUI = new MainGUI();
        mainGUI.run();
        ControllerDB controllerDB = new ControllerDB();


        Scanner IN = new Scanner(System.in);
        String input = "";
        while(!input.equals("stop")){
            input = IN.nextLine();
            System.out.println(controllerDB.getAccount().toString());
        }
    }
}
