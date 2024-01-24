import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Seller {
    String name;
    LocalDate dateOfBirth;
    int noOfContracts;
    double soldAmount;
    String city;
    SPZ spz;
    double consumption;
    String ipStr;
    InetAddress ip = null;
    String ipMsg;
    int id = 0;

    private boolean isValidIpAddress(String ip) {
        return ip != null && !ip.isEmpty();
    }

    public Seller(int id, String name, LocalDate dateOfBirth, int noOfContracts, double soldAmount,
                  String city, String SPZ, double consumption, String ip)
    {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.noOfContracts = noOfContracts;
        this.soldAmount = soldAmount;
        this.city = city;
        this.spz = new SPZ(SPZ);
        this.consumption = consumption;
        this.ipStr = ip;
        if (isValidIpAddress(this.ipStr)) {
            try {
                this.ip = InetAddress.getByName(this.ipStr);
            } catch (UnknownHostException e) {
                this.ip = null;
            }
        } else {
            this.ip = null;
        }
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public int getNoOfContracts() { return noOfContracts; }
    public double getSoldAmount() { return soldAmount; }
    public String getCity() { return city; }
    public String getSPZ() { return spz.getSPZ(); }
    public double getConsumption() { return consumption; }
    public String getIP() {
        InetAddress tempIp = ip;
        if (tempIp != null) {
            return tempIp.getHostAddress();
        } else {
            return "Neznámá IP adresa";
        }
    }

    @Override public String toString()
    {
        String smlouvaMsg;
        String sjendanoMsg;

        if (this.getNoOfContracts() == 1) {
            sjendanoMsg = "\nSjednána ";
            smlouvaMsg = " smlouva";
        } else if (this.getNoOfContracts() < 5) {
            sjendanoMsg = "\nSjednány ";
            smlouvaMsg = " smlouvy";
        } else {
            sjendanoMsg = "\nSjednáno ";
            smlouvaMsg = " smluv";
        }

        if (ip != null) {
            ipMsg = ip.getHostAddress();
        }else{
            ipMsg = "Neznámá IP adresa";
        };

        return ("Jméno prodejce: " + name +
                "\nDatum narození: " + dateOfBirth.format(DateTimeFormatter.ofPattern("d.M.y")) +
                "\nSídlo prodejce: " + city +
                "\nSPZ vozidla: " + spz.getSPZ() +
                "\nSpotřeba vozidla: " + consumption + " l/100km" +
                "\nIP adresa firemního počítače: " + ipMsg +
                sjendanoMsg + noOfContracts + smlouvaMsg +
                "\nCelkové množství prodané mrkve: " + soldAmount + " tun" +
                "\nPruměrné množství mrkve na smlouvu: " + String.format("%.0f",(1000*this.getSoldAmount()/this.getNoOfContracts())) +" kg"
        );

    }
}