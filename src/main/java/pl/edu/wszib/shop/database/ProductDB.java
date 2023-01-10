package pl.edu.wszib.shop.database;

import pl.edu.wszib.shop.model.Laptop;
import pl.edu.wszib.shop.model.Product;


import java.util.LinkedList;

public class ProductDB {
    private static final ProductDB instance = new ProductDB();

    final LinkedList<Product> products = new LinkedList<>();

    private ProductDB(){
        products.add(new Laptop("Lenovo", "Y500", 3700.0, 10,
                15.6, "1920x1080",
                "Intel Core i5-11400h", "Geforce RTX 2070", 16, false));
        products.add(new Laptop("MSI", "KATANA", 5599.0, 3,
                17.2, "2560x1440",
                "Intel Core i5-11400h", "Geforce RTX 3080", 32, true));
        //products.add();
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

    public Product findProduct(Product enteredProduct){
        for(Product product : this.products){
            if(product.getBrand().equals(enteredProduct.getBrand())
                    && product.getModel().equals(enteredProduct.getModel())) {
                return product;
            }
        }
        return null;
    }


    public LinkedList<Product> getProducts() {
        return products;
    }

    public static ProductDB getInstance() {
        return instance;
    }
}
