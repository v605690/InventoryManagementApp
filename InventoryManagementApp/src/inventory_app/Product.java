package inventory_app;

import java.util.Date;

public class Product {
    private int product;
    private String description;
    private String unit;
    private int unitQty;
    private double price;
    private String category;
    private Date date;
    private int lastQty;

    public Product(int product, String description, String unit, int unitQty, double price, String category, Date date, int lastQty) {
        this.product = product;
        this.description = description;
        this.unit = unit;
        this.unitQty = unitQty;
        this.price = price;
        this.category = category;
        this.date = date;
        this.lastQty = lastQty;
    }

    public Product() {
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getUnitQty() {
        return unitQty;
    }

    public void setUnitQty(int unitQty) {
        this.unitQty = unitQty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public java.sql.Date getDate() {
        return (java.sql.Date) date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getLastQty() {
        return lastQty;
    }

    public void setLastQty(int lastQty) {
        this.lastQty = lastQty;
    }

    @Override
    public String toString() {
        return
                "product = " + product +
                ", description = " + description +
                ", unit = " + unit +
                ", unitQty = " + unitQty +
                ", price = " + price +
                ", category = " + category +
                ", date = " + date +
                ", lastQty = " + lastQty;
    }
}
