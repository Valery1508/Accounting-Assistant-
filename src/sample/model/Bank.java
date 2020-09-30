package sample.model;

public class Bank {
    private int bankN;
    private String bankName;

    public Bank(int bankN, String bankName) {
        this.bankN = bankN;
        this.bankName = bankName;
    }

    public int getBankN() {
        return bankN;
    }

    public void setBankN(int bankN) {
        this.bankN = bankN;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
