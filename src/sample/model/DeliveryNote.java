package sample.model;

public class DeliveryNote {
    private int id;
    private int idCustomer;
    private int codeP;
    private String date;
    private double price;
    private int quantity;
    //private double totalPrice;

    public DeliveryNote(int id, int idCustomer, int codeP, String date, double price, int quantity) {
        this.id = id;
        this.idCustomer = idCustomer;
        this.codeP = codeP;
        this.date = date;
        this.price = price;
        this.quantity = quantity;
    }

    public DeliveryNote(int idCustomer, int codeP, String date, double price, int quantity) {
        this.idCustomer = idCustomer;
        this.codeP = codeP;
        this.date = date;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public int getCodeP() {
        return codeP;
    }

    public void setCodeP(int codeP) {
        this.codeP = codeP;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
