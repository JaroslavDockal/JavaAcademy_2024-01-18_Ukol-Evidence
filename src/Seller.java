import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
//import java.net.InetAddress;

public class Seller {
    String name;
    LocalDate dateOfBirth;
    int noOfContracts;
    double soldAmount;
    String city;
    String spz;
    double consumption;
    String ip;

    public Seller(String name, LocalDate dateOfBirth, int noOfContracts, double soldAmount,
                  String city, String spz, double consumption, String ip)
    {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.noOfContracts = noOfContracts;
        this.soldAmount = soldAmount;
        this.city = city;
        this.spz = spz;
        this.consumption = consumption;
        this.ip = ip;
    }

    public String getName() { return name; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public int getNoOfContracts() { return noOfContracts; }
    public double getSoldAmount() { return soldAmount; }
    public String getCity() { return city; }
    public String getSPZ() { return spz; }
    public double getConsumption() { return consumption; }
    public String getIP() { return ip; }

    @Override public String toString()
    {
        String smlouvaStr;
        String sjendanoStr;

        if (this.getNoOfContracts() == 1) {
            smlouvaStr = " smlouva";
            sjendanoStr = "\nSjednána ";
        } else if (this.getNoOfContracts() < 5 ) {
            smlouvaStr = " smlouvy";
            sjendanoStr = "\nSjednány ";
        } else {
            smlouvaStr = " smluv";
            sjendanoStr = "\nSjednáno ";
        }

        return ("Jméno prodejce: " + name +
                "\nDatum narození: " + dateOfBirth.format(DateTimeFormatter.ofPattern("d.M.y")) +
                "\nSídlo prodejce: " + city +
                "\nSPZ vozidla: " + spz +
                "\nSpotřeba vozidla: " + consumption + " l/100km" +
                "\nIP adresa firemního počítače: " + ip +
                sjendanoStr + noOfContracts + smlouvaStr +
                "\nCelkové množství prodané mrkve: " + soldAmount + " tun" +
                "\nPruměrné množství mrkve na smlouvu: " + String.format("%.0f",(1000*this.getSoldAmount()/this.getNoOfContracts())) +" kg");
    }
}