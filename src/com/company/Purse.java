package com.company;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class Purse {

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

    public Purse(int[] userPIN, int[] adminPIN, int MAX_USER_TRIES, int MAX_ADMIN_TRIES, int MAX_TRANS, int MAX_BALANCE, int MAX_CREDIT_AMOUNT, int MAX_DEBIT_AMOUNT) {
        this.userPIN = userPIN;
        this.adminPIN = adminPIN;
        this.maxUserTries = MAX_USER_TRIES;
        this.maxAdminTries = MAX_ADMIN_TRIES;
        this.maxTrans = MAX_TRANS;
        this.maxBalance = MAX_BALANCE;
        this.maxCreditAmount = MAX_CREDIT_AMOUNT;
        this.maxDebitAmount = MAX_DEBIT_AMOUNT;
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

        boolean good = verifyPINUser(askCode());

        if(!good)userTriesLeft--;
        else userTriesLeft = MAX_USER_TRIES;

        if(userTriesLeft == 0)lifeCycleState = LCS.BLOCKED;

        return good;
    }

    private boolean getIdentificationAdmin(){

        boolean good = verifyPINAdmin(askCode());

        if(good){
            PINChangeUnblock();
        }else{
            adminTriesLeft--;
        }
        if(adminTriesLeft == 0)lifeCycleState = LCS.DEAD;

        return good;
    }

    void PINChangeUnblock(){
        lifeCycleState = LCS.USE;
        adminTriesLeft = MAX_ADMIN_TRIES;
        userTriesLeft = MAX_USER_TRIES;
    }
    void beginTransactionDebit(int amount){
        if(amount > maxDebitAmount || balance - amount < 0)return;
        balance -= amount;
    }
    void beginTransactionCredit(int amount){
        if(amount > maxCreditAmount || balance + amount > maxBalance)return;
        balance += amount;
    }
    void commitTransactionDebit(){
        transLeft--;
    }
    void commitTransactionCredit(){
        transLeft--;
    }
    int getData(){
        return balance;
    }
}
