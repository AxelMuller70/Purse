package com.company;

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

    boolean verifyPINUser(int[] PINCode){return false;}
    boolean verifyPINAdmin(int[] PINCode){return false;}
    private boolean getIdentificationAdmin(){return false;}
    private boolean getIdentificationUser(){return false;}
    void PINChangeUnblock(){}
    void beginTransactionDebit(int amount){}
    void beginTransactionCredit(int amount){}
    void commitTransactionDebit(){}
    void commitTransactionCredit(){}
    int getData(){return 0;}
}
