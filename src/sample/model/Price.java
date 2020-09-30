package sample.model;

public class Price {
    private int codeP;
    private String date;
    private double price;

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

    public Price(int codeP, String date, double price) {
        this.codeP = codeP;
        this.date = date;
        this.price = price;
    }
}
