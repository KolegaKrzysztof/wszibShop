package pl.edu.wszib.shop.model;

sealed public class Product permits Laptop {
    private String brand;
    private String model;
    private double price;
    private int numberOfUnits;

    public Product(){
    }

    public Product(String brand, String model, double price, int numberOfUnits) {
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.numberOfUnits = numberOfUnits;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumberOfUnits() {
        return numberOfUnits;
    }

    public void setNumberOfUnits(int numberOfUnits) {
        this.numberOfUnits = numberOfUnits;
    }

    @Override
    public String toString() {

        return new StringBuilder("brand: '").append(this.brand)
                .append("' model: '").append(model)
                .append("' price: ").append(price)
                .append(" numberOfUnits: ").append(numberOfUnits)
                .toString();
    }
}
