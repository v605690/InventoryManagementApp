package inventory_app;

import java.util.Date;

public class Product {
    private int productId;
    private String description;
    private String unit;
    private int unitQuantity;
    private double price;
    private String category;
    private Date date;
    private int lastQuantity;

    public Product(int productId, String description, String unit, int unitQuantity, double price, String category, Date date, int lastQuantity) {
        this.productId = productId;
        this.description = description;
        this.unit = unit;
        this.unitQuantity = unitQuantity;
        this.price = price;
        this.category = category;
        this.date = date;
    }

    public Product() {
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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

    public int getUnitQuantity() {
        return unitQuantity;
    }

    public void setUnitQuantity(int unitQuantity) {
        this.unitQuantity = unitQuantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

//    public double setPrice() {
//        return price;
//    }

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

    public int getLastQuantity() {
        return lastQuantity;
    }

//    public int setLastQuantity() {
//        return lastQuantity;
//    }

    public int setLastQuantity(int lastQuantity) {
        this.lastQuantity = lastQuantity;
        return lastQuantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", description='" + description + '\'' +
                ", unit=" + unit +
                ", unitQuantity=" + unitQuantity +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", date=" + date +
                ", lastQuantity=" + lastQuantity +
                '}';
    }
}
