/*#############################################################################
 # Copyright (c) rzvdev                                                       #
 # Created in 11/17/20, 7:45 PM                                               #
 # Author : Razvi Andre                                                       #
 # Linkedin : https://www.linkedin.com/in/lungu-razvan-andre-4858a417b        #
 # Github : https://github.com/razviiandre                                    #
 #                                                                            #
 #############################################################################*/

import controller.ControllerDB;
import gui.MainGUI;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        MainGUI mainGUI = new MainGUI();
        mainGUI.run();
        ControllerDB controllerDB = ControllerDB.getInstance();


        Scanner IN = new Scanner(System.in);
        String input = "";
        while(!input.equals("stop")){
            input = IN.nextLine();
            System.out.println(controllerDB.getAccount().toString());
        }
    }
}
