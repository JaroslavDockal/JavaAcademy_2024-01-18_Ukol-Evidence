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
//import java.net.InetAddress;

public class Main {
    public static void main(String[] args){
        String divider = "\n-------------------------------------------";
        int sumOfContracts = 0;
        double sumOfSoldAmount = 0;
        double avgPerContract;
        String avgPerContractMsg;

        Seller[] sellers = new Seller[]{
                new Seller(1, "Tomáš Fuk", LocalDate.of(2002, 07, 01), 5,
                        2.6, "Stará při Čopni", "F1FINKA", 8.3, "68.5.2.12"),

                new Seller(2, "František Skočdopole", LocalDate.of(1932, 01, 16), 7,
                        3.1, "Lhota", "4S4 2132", 4.6, "13.37.55.63"),

                new Seller(3, "Kleofáš Zajíc", LocalDate.of(1984, 04, 01), 2,
                        4.33, "Nová Ves u Nového Města na Moravě", "5U02736", 12.7, "338.241.4.31"),

                new Seller(4, "Lucie Nováková", LocalDate.of(1990, 5, 12), 3,
                        2.9, "Hradec Králové", "1B98765", 9.8, "112.45.78.99"),

                new Seller(5, "Pavel Veselý", LocalDate.of(1975, 8, 23), 6,
                        3.5, "Brno", "8X65432", 6.2, "76.34.22.11"),

                new Seller(6, "Jana Vlková", LocalDate.of(1988, 11, 8), 1,
                        4.7, "Ostrava", "046 CD93", 11.5, "189.67.23.45"),

                new Seller(7, "Josef Marek", LocalDate.of(1965, 2, 18), 4,
                        3.2, "Plzeň", "018 XX98", 5.9, "23.56.78.90"),

                new Seller(8, "Alena Pospíchalová", LocalDate.of(1995, 9, 30), 5,
                        4.1, "Ústí nad Labem", "011HC01", 10.2, "45.78.91.23"),

                new Seller(9, "Martina Novotná", LocalDate.of(1980, 3, 14), 2,
                        3.8, "Pardubice", "F 3297", 7.5, "112.45.78.99"),

                new Seller(10, "Adam Malý", LocalDate.of(1992, 6, 27), 6,
                        4.5, "Bratislava", "01V 0911", 8.2, "76.34.22.11"),

                new Seller(11, "Eva Procházková", LocalDate.of(1978, 9, 8), 3,
                        3.2, "Olomouc", "05R 1953", 9.1, "189.67.23.45"),

                new Seller(12, "Petr Vaněk", LocalDate.of(1985, 12, 21), 5,
                        2.9, "Zlín", "01S 2025", 11.7, "23.56.78.90"),

                new Seller(13, "Karolína Svobodová", LocalDate.of(1993, 2, 3), 1,
                        3.7, "České Budějovice", "EL9 8383", 10.3, "45.78.91.23"),

                new Seller(14, "Miroslav Kovařík", LocalDate.of(1970, 5, 16), 4,
                        2.5, "Havlíčkův Brod", "EL000AA", 12.9, "68.5.2.12"),

                new Seller(15, "Zuzana Kratochvílová", LocalDate.of(1987, 8, 29), 7,
                        4.1, "Kladno", "A35 7327", 6.8, "13.37.55.63"),

                new Seller(16, "Radek Novák", LocalDate.of(1994, 11, 10), 2,
                        3.6, "Ústí nad Orlicí", "T2462", 8.5, "338.241.4.31"),

                new Seller(17, "Tomáš Válek", LocalDate.of(1982, 2, 7), 5,
                        3.4, "Plzeň", "1P98765", 9.6, "112.45.78.99"),

                new Seller(18, "Lucie Kovaříková", LocalDate.of(1991, 5, 20), 7,
                        4.0, "Praha", "5A76543", 8.8, "76.34.22.11"),

                new Seller(19, "Marek Novák", LocalDate.of(1988, 8, 2), 3,
                        3.1, "Liberec", "6L65432", 7.2, "189.67.23.45"),

                new Seller(20, "Anna Pospíšilová", LocalDate.of(1975, 11, 15), 1,
                        2.8, "Brno", "2.34567", 11.4, "23.56.78.90"),

                new Seller(21, "Jan Vávra", LocalDate.of(1996, 1, 27), 6,
                        4.2, "Ostrava", "3O87654", 6.9, "45.78.91.23"),

                new Seller(22, "Veronika Procházková", LocalDate.of(1983, 4, 10), 4,
                        2.7, "Jihlava", "7J76543", 10.1, "68.5.2.12"),

                new Seller(23, "Pavel Malý", LocalDate.of(1972, 7, 23), 2,
                        3.5, "Znojmo", "4Z65432", 8.7, "13.37.55.63"),

                new Seller(24, "Jana Švábíková", LocalDate.of(1989, 10, 5), 5,
                        2.2, "Ústí nad Labem", "8U34567", 12.2, "338.241.4.31"),

                new Seller(25, "Ondřej Novotný", LocalDate.of(1997, 12, 18), 7,
                        3.9, "Tábor", "9T87654", 9.3, "112.45.78.99"),

                new Seller(26, "Barbora Procházková", LocalDate.of(1986, 3, 3), 1,
                        3.3, "Kolin", "1K76543", 8.1, "76.34.22.11"),

                new Seller(27, "Štěpán Malý", LocalDate.of(1973, 6, 16), 6,
                        4.3, "Český Těšín", "6C65432", 7.5, "189.67.23.45"),

                new Seller(28, "Michaela Nováková", LocalDate.of(1990, 9, 29), 3,
                        3.0, "Hradec Králové", "7H34567", 10.5, "23.56.78.90"),

                new Seller(29, "Filip Vávra", LocalDate.of(1977, 1, 11), 5,
                        2.6, "Karviná", "5K87654", 11.0, "45.78.91.23"),

                new Seller(30, "Eliška Procházková", LocalDate.of(1995, 4, 24), 4,
                        3.6, "Prostějov", "023 6589", 7.8, "68.5.2.12"),
        };

        System.out.println("Data ke dni: " + LocalDate.now() + "." + divider);
        for (Seller seller : sellers) {
            if (seller.getId() >= 1 && seller.getId() <= 99) {
                //System.out.println(seller.toString() + divider);
                sumOfContracts += seller.noOfContracts;
                sumOfSoldAmount += seller.soldAmount;
            }
        }

        avgPerContract = sumOfSoldAmount/sumOfContracts;

        if (avgPerContract < 1){
            avgPerContractMsg = String.format("%.0f" + " kg", 1000*avgPerContract);
        } else {
            avgPerContractMsg = String.format("%.0f" + " tun", avgPerContract);
        }

        System.out.println("Celkem sjednáno " + sumOfContracts + " smluv");
        System.out.println("Celkem dodáno "+ String.format("%.1f",sumOfSoldAmount) + " tun mrkve");
        System.out.println("Průměrné množství mrkve na smlouvu: " + avgPerContractMsg);
    }
}