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

    public Account(){

    }

    public Account(String user,String password,String retypedPassword,String email, String phone){
        this.user = user;
        this.password = password;
        this.retypedPassword = retypedPassword;
        this.email = email;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
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
}
