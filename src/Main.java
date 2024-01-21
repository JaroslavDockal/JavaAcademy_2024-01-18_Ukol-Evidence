/*
V metodě main svého projektu připrav proměnné pro uložení informací o prodejcích kvalitní biomrkve.
Pokud to umíš, můžeš založit novou třídu a údaje o prodejcích zapsat jako atributy této třídy.

O každém prodejci potřebujeme evidovat tyto údaje:

Jméno a příjmení prodejce
Datum narození prodejce.
Počet dosud sjednaných smluv.
Celkové množství prodané mrkve v tunách.
Název města, kde prodejce sídlí.
Registrační značka vozidla („SPZ“).
Spotřeba firemního vozidla v litrech na 100 km.
IP adresa verze 4 firemního počítače.
Více o formátu IP adresy verze 4 viz například IP adresa verze 4

Do proměnných ulož vhodné hodnoty (hodnoty si vymysli tak, aby dávaly smysl).

Na základě zadaných údajů vypiš v metodě main na obrazovku průměrné množství prodané mrkve na jednu smlouvu.
*/
// Class Declaration

import java.time.LocalDate;
import java.net.InetAddress;

public class Main {
    public static void main(String[] args){
        String divider = "\n--------------------------------------------------------------------------------------------";
        int sumOfContract;
        double sumOfSoldAmount;

        Seller seller01
                = new Seller("Tomáš Fuk", LocalDate.of(2002,07,01) , 5,
                2.6, "Stará při Čopni", "2P4 3376", 8.3, "68.5.2.12" );

        Seller seller02
                = new Seller("František Skočdopole", LocalDate.of(1932,01,16) , 7,
                3.1, "Malá Velká", "4S4 2132", 4.6, "13.37.55.63" );

        Seller seller03
                = new Seller("Kleofáš Zajíc", LocalDate.of(1984,04,01) , 2,
                1.33, "Rychlá", "5U0 2736", 12.7, "138.241.4.31" );

        System.out.println("Data ke dni: " + LocalDate.now() + "." + divider);
        System.out.println(seller01.toString() + divider);
        System.out.println(seller02.toString() + divider);
        System.out.println(seller03.toString() + divider);

        sumOfContract = seller01.noOfContracts + seller02.noOfContracts + seller03.noOfContracts;
        sumOfSoldAmount = seller01.soldAmount + seller02.soldAmount + seller03.soldAmount;

        System.out.println("Celkem dodáno " + sumOfSoldAmount + " tun zboží, navázáno na " + sumOfContract + " smluv.");
        System.out.println("Průměrné množství zboží na 1 smlouvu je " + String.format("%.0f", 1000*sumOfSoldAmount/sumOfContract) + " kg.");
    }
}