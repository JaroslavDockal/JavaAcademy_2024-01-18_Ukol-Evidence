import java.time.LocalDate;
//import java.text.DecimalFormat;
import java.net.InetAddress;

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

        if (this.getNoOfContracts() == 1) {
            smlouvaStr = " smlouva";
        } else if (this.getNoOfContracts() == 2) {
            smlouvaStr = " smlouvy";
        } else {
            smlouvaStr = " smluv";
        }

        return (String.format(this.getName() + " dodal celkem " + this.getSoldAmount() + " tun zboží, navázáno na " +
                        this.getNoOfContracts() + smlouvaStr + " (průměrně " + "%.0f" + " kg/smlouva).",
                (1000*this.getSoldAmount()/this.getNoOfContracts()))
        );
    }
}