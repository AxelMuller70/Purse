package com.company;

import java.net.SocketOption;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Création d'un purse standard, PIN user = 1234, PIN admin = 123456
        Purse purse = new Purse(new int[] {1, 2, 3, 4}, new int[] {1, 2, 3, 4, 5, 6});
        Scanner scs = new Scanner(System.in);
        char rep;
        do{
            Portail GAB = new Portail();
            GAB.ecranAccueil();
            int choix = GAB.choixOperation();


            switch (choix){
                case 1 : //créditer sa carte
                    //identification du client
                    Scanner sc = new Scanner(System.in);
                    System.out.print("Votre montant : ");
                    int montant = sc.nextInt();
                    System.out.print("Votre code pin: ");
                    purse.beginTransactionCredit(montant);
                    purse.commitTransactionCredit();
                    break;
                case 2 : // débiter sa carte
                    Scanner scd = new Scanner(System.in);
                    System.out.print("Montant à débiter : ");
                    int montantd = scd.nextInt();
                    purse.beginTransactionDebit(montantd);
                    purse.commitTransactionCredit();
                    break;
                case 3 : // débloquer la carte
                   // purse.PINChangeUnblock();
                    break;
                case 4 :// état de la carte
                    break;
                case 5 :// Mon solde
                    System.out.println("Mon solde est : "+ purse.getData() + " euro(s)");
                    break;
                default:
                    break;
            }
            System.out.print("Voulez vous faire autre opération ? O/N: ");
            rep = scs.next().charAt(0);
        }while(rep == 'o' || rep == 'O');



























        // Création d'un purse standard, PIN user = 1234, PIN admin = 123456

//        Purse purse = new Purse(new int[] {1, 2, 3, 4}, new int[] {1, 2, 3, 4, 5, 6});
//
//
//
//        System.out.println("Opération de crédit : 30 euros");
//        purse.beginTransactionCredit(30);
//        purse.commitTransactionCredit();
//
//        System.out.println("Affichage du solde : "+purse.getData());
//
//        System.out.println("Opération de débit : 20 euros");
//        purse.beginTransactionDebit(20);
//        purse.commitTransactionDebit();
//
//        System.out.println("Affichage du solde : "+purse.getData());
//
//        System.out.println("Déblocage de la carte");
//        purse.PINChangeUnblock();
//
//        System.out.println("Opération de crédit : 15 euros");
//        purse.beginTransactionCredit(15);
//        purse.commitTransactionCredit();
//
//        System.out.println("Opération de débit : 10 euros");
//        purse.beginTransactionDebit(10);
//        purse.commitTransactionDebit();
    }
}
