package inventory_app;

public class Product {

    // Instance variables with private access modifier for encapsulation
    private int product;
    private String description;
    private String unit;
    private String unitQty;
    private double price;
    private String date;
    private int lastQty;

    // Product Constructor fully qualified
    public Product(int product, String description, String unit, String unitQty, double price, String date, int lastQty) {
        this.product = product;
        this.description = description;
        this.unit = unit;
        this.unitQty = unitQty;
        this.price = price;
        this.date = date;
        this.lastQty = lastQty;
    }

    // Product Constructor
    public Product() {
    }

    // Getter & Setter methods
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

    public String getUnitQty() {
        return unitQty;
    }

    public void setUnitQty(String unitQty) {
        this.unitQty = unitQty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getLastQty() {
        return lastQty;
    }

    public void setLastQty(int lastQty) {
        this.lastQty = lastQty;
    }

    // ToString method
    @Override
    public String toString() {
        return
                "product = " + product +
                        ", description = " + description +
                        ", unit = " + unit +
                        ", unitQty = " + unitQty +
                        ", price = " + price +
                        ", date = " + date +
                        ", lastQty = " + lastQty;
    }
}
