package sample.model;

public class Customer {
    private int idCustomer;
    private String type;
    private String firstName;
    private String secondName;
    private String nameP;
    private String region;
    private String street;
    private int house;
    private int bankN;

    public Customer(int idCustomer, String type, String firstName, String secondName, String nameP, String region, String street, int house, int bankN) {
        this.idCustomer = idCustomer;
        this.type = type;
        this.firstName = firstName;
        this.secondName = secondName;
        this.nameP = nameP;
        this.region = region;
        this.street = street;
        this.house = house;
        this.bankN = bankN;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getNameP() {
        return nameP;
    }

    public void setNameP(String nameP) {
        this.nameP = nameP;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouse() {
        return house;
    }

    public void setHouse(int house) {
        this.house = house;
    }

    public int getBankN() {
        return bankN;
    }

    public void setBankN(int bankN) {
        this.bankN = bankN;
    }
}
