/*#############################################################################
 # Copyright (c) rzvdev                                                       #
 # Created in 11/17/20, 7:45 PM                                               #
 # Author : Razvi Andre                                                       #
 # Linkedin : https://www.linkedin.com/in/lungu-razvan-andre-4858a417b        #
 # Github : https://github.com/razviiandre                                    #
 #                                                                            #
 #############################################################################*/
package interfaces;

public interface Initiable {
     void initFrame();
     void initButtons();
     void initPanel();
     void initLabels() throws InterruptedException;
     void initTextFields();
     void endInit();

}
