package pl.edu.wszib.shop.database;

import pl.edu.wszib.shop.model.Keyboard;
import pl.edu.wszib.shop.model.Laptop;
import pl.edu.wszib.shop.model.Monitor;
import pl.edu.wszib.shop.model.Product;


import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ProductDB {
    private static final ProductDB instance = new ProductDB();

    final private List<Product> products = new LinkedList<>();

    private ProductDB(){
        products.add(new Laptop("Asus", "ROG", 5599.0, 3,
                17.2, "2560x1440",
                "Intel Core i5-11400h", "Geforce RTX 3080", 32, true));
        products.add(new Laptop("Lenovo", "Y500", 3700.0, 10,
                15.6, "1920x1080",
                "Intel Core i5-11400h", "Geforce RTX 2070", 16, false));
        products.add(new Monitor("HP", "X27", 1200.0, 20,
                27, "1920x1080", 144, "IPS"));
        products.add(new Monitor("Asus", "Z34", 1899.0, 7,
                31, "2560x1440", 144, "VA"));
        products.add(new Keyboard("Logitech", "MX", 499, 5,
                Keyboard.KeyboardType.MEMBRANE, true, "black"));
        products.add(new Keyboard("Logitech", "G413", 279, 10,
                Keyboard.KeyboardType.MECHANICAL, false, "black"));
    }

    public void buyProducts(Product product, int units){
        if(product.getNumberOfUnits() >= units){
            product.setNumberOfUnits(product.getNumberOfUnits() - units);
        } else {
            System.out.println("Not enough units in stock !!!");
        }
    }

    public void addUnits(Product product, int units){
        product.setNumberOfUnits(product.getNumberOfUnits() + units);
    }

    public Optional<Product> findProduct(Product enteredProduct){
        for(Product product : this.products){
            if(product.getBrand().equals(enteredProduct.getBrand())
                    && product.getModel().equals(enteredProduct.getModel())) {
                return Optional.of(product);
            }
        }
        return Optional.empty();
    }


    public List<Product> getProducts() {
        return products;
    }

    public static ProductDB getInstance() {
        return instance;
    }
}
