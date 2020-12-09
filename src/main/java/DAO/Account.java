/*#############################################################################
 # Copyright (c) rzvdev                                                       #
 # Created in 11/17/20, 7:45 PM                                               #
 # Author : Razvi Andre                                                       #
 # Linkedin : https://www.linkedin.com/in/lungu-razvan-andre-4858a417b        #
 # Github : https://github.com/razviiandre                                    #
 #                                                                            #
 #############################################################################*/
package DAO;

import javax.persistence.*;

@Entity(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String user;
    private String password;
    @Transient
    private String retypedPassword;
    private String email;
    private String phone;
    private int score;

    public Account(){

    }

    public Account(String user,String password,String retypedPassword,String email, String phone){
        this.user = user;
        this.password = password;
        this.retypedPassword = retypedPassword;
        this.email = email;
        this.phone = phone;
        this.score = 0;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' + ", score='" + score + "]" +
                '}';
    }

    public String getUser() {
        return user;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }
    public String getPhone() {
        return phone;
    }
    public int getScore(){
        return score;
    }
    public void setScore(int newScore){
        this.score = newScore;
    }
   //TODO UPDATE SCORE TO DATABASE , SHOW NEW SCORE IN MAINGUI WHILE CLICK BACK BUTTON
}
