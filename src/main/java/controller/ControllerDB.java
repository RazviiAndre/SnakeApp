/*#############################################################################
 # Copyright (c) rzvdev                                                       #
 # Created in 11/17/20, 7:45 PM                                               #
 # Author : Razvi Andre                                                       #
 # Linkedin : https://www.linkedin.com/in/lungu-razvan-andre-4858a417b        #
 # Github : https://github.com/razviiandre                                    #
 #                                                                            #
 #############################################################################*/
package controller;

import DAO.Account;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.NoResultException;
import javax.persistence.Query;

public class ControllerDB {
    Configuration config;
    SessionFactory sessionFactory;
    Session session;
    Transaction transaction;

    private static ControllerDB instance;
    private ControllerDB(){}

    public static ControllerDB getInstance(){
        if(instance == null){
            instance = new ControllerDB();
        }
        return instance;
    }

//    STRING COLORS
    static String green = "\033[32m";
    static String normalColor = "\033[0m";

    private static Account account = new Account();
    private static boolean status;
    boolean userExist;
    boolean emailExist;


    public void init(){
        try {
            config = new Configuration().configure().addAnnotatedClass(DAO.Account.class);
            sessionFactory = config.buildSessionFactory();
            session = sessionFactory.openSession();
            status = true;
        } catch (SessionException e){
            System.out.println("Cannot connect to db! Check sessions");
            e.printStackTrace();
            status = false;
        }
    }

    public String whyDenied(){
        if(userExist){
            return "The user already exists!";
        }  else if(emailExist){
            return "Email is already in use";
        }
        return "";

    }
    public boolean tryLogin(String user,String password){
        try{
            Query queryUser = session.createQuery("FROM account a WHERE a.user = :accountUser");
            queryUser.setParameter("accountUser",user);
            Object obj = queryUser.getSingleResult();
            if(obj.toString().contains(password)){
                account = (Account)obj;
                System.out.println("LOGGED IN! " + account.toString());
                return true;
            } else {
                account = new Account();
                return false;
            }
        } catch (NoResultException e){
            account = new Account();
            System.out.println("NOT LOGGED!" + account.toString());
            return false;
        }
    }

    public void tryLoggout(){
        account = new Account();
        System.out.println("LOGGED OUT ! " + account.toString());
    }

    public void updateAccount(Account account){
        try {
            transaction = session.beginTransaction();
            session.update(account);
            transaction.commit();
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("upateAccount error method");
        }
    }


    public boolean canAdd(Account account){
        boolean accountHaveThisUser = true;
        boolean accountHaveThisEmail = true;

        try {
            Query queryUser = session.createQuery("SELECT a.user FROM account a WHERE a.user = :accountUser");
            queryUser.setParameter("accountUser", account.getUser());
            queryUser.getSingleResult();
        } catch (NoResultException e){
            accountHaveThisUser = false;
        }

        try {
            Query queryEmail = session.createQuery("SELECT a.email FROM account a WHERE a.email = :accountEmail");
            queryEmail.setParameter("accountEmail",account.getEmail());
            queryEmail.getSingleResult();
        } catch (NoResultException e){
            accountHaveThisEmail = false;
        }

        if(!accountHaveThisUser && !accountHaveThisEmail){
            System.out.println(green+"ACCOUNT CREATED SUCCESFULLY ! " +
                    "User: " + account.getUser() + " , Password: " + account.getPassword() +
                    " , Email: " + account.getEmail() + " , Phone: " + account.getPhone() + normalColor);
            return true;
        }
        userExist = accountHaveThisUser;
        emailExist = accountHaveThisEmail;

        return false;
    }
    public void add(Object o){
        transaction = session.beginTransaction();
        session.save(o);
        transaction.commit();
    }

    public Account getAccount() {
        return account;
    }
    public boolean getStatus(){
        return status;
    }
}
