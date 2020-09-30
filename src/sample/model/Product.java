package sample.model;

import java.util.Objects;

public class Product {
    private int codeP;
    private String nameP;
    private String category;

    public Product(int codeP, String nameP, String category) {
        this.codeP = codeP;
        this.nameP = nameP;
        this.category = category;
    }

    public Product() {
    }

    public int getCodeP() {
        return codeP;
    }

    public void setCodeP(int codeP) {
        this.codeP = codeP;
    }

    public String getNameP() {
        return nameP;
    }

    public void setNameP(String nameP) {
        this.nameP = nameP;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return codeP == product.codeP &&
                Objects.equals(nameP, product.nameP) &&
                Objects.equals(category, product.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeP, nameP, category);
    }
}
