package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Purse {

    public enum LCS {
        PRE_PERSO, USE, BLOCKED, DEAD
    }

    private int[] userPIN;
    private int[] adminPIN;
    private final static int MAX_USER_TRIES = 3;
    private final static int MAX_ADMIN_TRIES = 4;
    private final static int MAX_TRANS = 500;
    private final static int MAX_BALANCE = 100;
    private final static int MAX_CREDIT_AMOUNT = 50;
    private final static int MAX_DEBIT_AMOUNT = 30;

    private int maxUserTries;
    private int maxAdminTries;
    private int maxTrans;
    private int maxBalance;
    private int maxCreditAmount;
    private int maxDebitAmount;
    private int userTriesLeft;
    private int adminTriesLeft;
    private int balance;
    private int transLeft;
    private boolean userAuthenticate;
    private boolean adminAuthenticate;
    private LCS lifeCycleState;
    private boolean inTransaction;

    public Purse(int[] userPIN, int[] adminPIN, int MAX_USER_TRIES, int MAX_ADMIN_TRIES, int MAX_TRANS, int MAX_BALANCE, int MAX_CREDIT_AMOUNT, int MAX_DEBIT_AMOUNT) {
        this.userPIN = userPIN;
        this.adminPIN = adminPIN;
        this.maxUserTries = MAX_USER_TRIES;
        this.maxAdminTries = MAX_ADMIN_TRIES;
        this.maxTrans = MAX_TRANS;
        this.maxBalance = MAX_BALANCE;
        this.maxCreditAmount = MAX_CREDIT_AMOUNT;
        this.maxDebitAmount = MAX_DEBIT_AMOUNT;
        this.lifeCycleState = LCS.USE;
        this.balance = 0;
        this.userTriesLeft = MAX_USER_TRIES;
        this.adminTriesLeft = MAX_ADMIN_TRIES;
        this.inTransaction = false;
    }

    public Purse( int[] userPIN, int[] adminPIN){
        this(userPIN,adminPIN,MAX_USER_TRIES,MAX_ADMIN_TRIES,MAX_TRANS,MAX_BALANCE,MAX_CREDIT_AMOUNT,MAX_DEBIT_AMOUNT);
    }

    boolean verifyPINUser(int[] PINCode){
        return Arrays.equals(userPIN,PINCode);
    }
    boolean verifyPINAdmin(int[] PINCode){
        return Arrays.equals(adminPIN,PINCode);
    }

    private int[] askCode(){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next("\\d+");
        return input.chars().map(a -> a-((int)'0')).toArray();
    }

    private boolean getIdentificationUser(){

        System.out.print("Votre code pin: ");
        userAuthenticate = verifyPINUser(askCode());

        if(userAuthenticate){
            userTriesLeft = maxUserTries;
        }else{
            userTriesLeft--;
        }

        if(userTriesLeft == 0){
            lifeCycleState = LCS.BLOCKED;
        }

        //if(lifeCycleState != LCS.DEAD && !userAuthenticate && getIdentificationAdmin())getIdentificationUser();

        return userAuthenticate;
    }

    private boolean getIdentificationAdmin(){

        System.out.print("Code PIN admin : ");
        adminAuthenticate = verifyPINAdmin(askCode());

        if(adminAuthenticate){
            adminTriesLeft = maxAdminTries;
        }else{
            adminTriesLeft--;
        }
        if(adminTriesLeft == 0)lifeCycleState = LCS.DEAD;

        return adminAuthenticate;
    }
    // Pas fonctionnel
    void PINChangeUnblock(){
        boolean auth;
        if(lifeCycleState == LCS.BLOCKED){
            do{
                if(adminTriesLeft < MAX_ADMIN_TRIES){
                    System.out.println("Mot de passe erroné");
                }
                auth = this.getIdentificationAdmin();

            }while(!(auth && adminTriesLeft > 0) && adminTriesLeft > 0);
            if(auth && adminTriesLeft > 0){
                lifeCycleState = LCS.USE;
                adminTriesLeft = MAX_ADMIN_TRIES;
                userTriesLeft = MAX_USER_TRIES;
                System.out.println("Carte débloquée");
            }else {
                lifeCycleState = LCS.DEAD;
                System.out.println("Purse DEAD");
            }
        }else if(lifeCycleState == LCS.USE){
            System.out.println("Carte débloquée");
        }else if(lifeCycleState == LCS.DEAD){
            System.out.println("Carte débloquée");
        }
    }

    void beginTransactionDebit(int amount){

        if(amount <= 0){
            System.out.println("Pas de montant nul ou négatif");return;}
        if(balance - amount < 0){
            System.out.println("Montant trop élevé");return;}
        if(amount > maxDebitAmount){
            System.out.println("Plafond dépassé");return;}
        if(lifeCycleState != LCS.USE){
            System.out.println("Purse INVALIDE");
        }else{
            balance -= amount;
            inTransaction = true;
        }
    }
    void beginTransactionCredit(int amount){

        if(amount <= 0){
            System.out.println("Pas de montant nul ou négatif");return;}
        if(amount > maxCreditAmount){
            System.out.println("montant trop élevé");return;}
        if(balance + amount > maxBalance) {
            System.out.println("Plafond dépassé");return;}

        boolean auth;
        if(lifeCycleState != LCS.USE){
            System.out.println("");
            System.out.println("Purse INVALIDE");
        }else{
            do{
                if(userTriesLeft < MAX_USER_TRIES){
                    System.out.println("Mot de passe erroné");
                }
                auth = this.getIdentificationUser();

            }while(!(auth && userTriesLeft > 0) && userTriesLeft > 0);
            if(auth && userTriesLeft > 0){
               balance += amount;
               inTransaction = true;
            }else {
                System.out.println("Nombre d'essai dépassé. Purse BLOQUE (débloquer dans le menu principal) ");
            }
        }
    }
    void commitTransactionDebit(){
        transLeft--;
        inTransaction = false;
        if(transLeft == 0)lifeCycleState = LCS.DEAD;
    }
    void commitTransactionCredit(){
        transLeft--;
        inTransaction = false;
        if(transLeft == 0)lifeCycleState = LCS.DEAD;
    }

    int getData(){
        return balance;
    }

    void reset(){
        if(inTransaction)abortTransaction();
    }

    void abortTransaction(){
        //TODO ...
    }
}
