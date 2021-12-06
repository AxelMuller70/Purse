package com.company;

import com.sun.nio.sctp.SctpSocketOption;

import java.util.Scanner;

public class Portail {

    public void presentation(){
        System.out.println("***********************************************************");
        System.out.println("               GUICHET D' OPERATION DE PURSE               ");
        System.out.println("***********************************************************");
    }
    public void ecranAccueil(){
        this.presentation();
        System.out.println("\t 1- Créditer mon purse");
        System.out.println("\t 2- Débiter mon purse");
        System.out.println("\t 3- Débloquer le purse");
        System.out.println("\t 4- Etat purse");
        System.out.println("\t 5- Mon solde");
    }
    public int choixOperation(){
        Scanner sc = new Scanner(System.in);
        int op;
        do{
            System.out.print("Veuillez choisir le numéro de votre opération : ");
            op = sc.nextInt();
        }while (op != 1 && op != 2 && op != 3 && op != 4 && op !=5);
        return op;
    }
}
