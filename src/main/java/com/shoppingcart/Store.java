package com.shoppingcart;

import java.util.ArrayList;

public class Store {
    private List<Product> productsAvailables;

    public Store() {
        this.productsAvailables = new ArrayList<>();
    }

    public Store(ArrayList<Product> products) {
        this.productsAvailables = products;
    }

    public List<Product> getProductsAvailables() {
        return productsAvailables;
    }

    public void setProductsAvailables(ArrayList<Product> productsAvailables) {
        this.productsAvailables = productsAvailables;
    }

    public Product searchProductInInventory(String prdName) {
        for (int i = 0; i < productsAvailables.size(); i++) {
            if (productsAvailables.get(i).getProductName().equals(prdName)) {
                return productsAvailables.get(i);
            }
        }
        return null;
    }

    public void showProductsInInventory() {
        System.out.println("Products in Invetory:");
        for (int i = 0; i < productsAvailables.size(); i++) {
            System.out.println((i + 1) + ". " + productsAvailables.get(i));
        }
    }

}
