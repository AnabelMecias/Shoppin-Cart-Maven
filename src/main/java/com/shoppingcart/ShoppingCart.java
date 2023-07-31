package com.shoppingcart;

import java.util.LinkedList;
import java.util.Collections;
import java.util.Comparator;

public class ShoppingCart {
    private String shoppingCartName;
    private LinkedList<Product> products;
    private int productsInCart;
    private int productsQuantityLimit;
    private double salesTax;
    private Store store;

    public int getProductsQuantityLimit() {
        return productsQuantityLimit;
    }

    public ShoppingCart(String shoppingCartName, Store store) {
        this.shoppingCartName = shoppingCartName;
        products = new LinkedList<>();
        this.salesTax = 0.1;
        this.productsQuantityLimit = 100;
        this.store = store;
        this.productsInCart = 0;
    }

    public int getProductQuantity() {
        return products.size();
    }

    public String getShoppingCartName() {
        return shoppingCartName;
    }

    public void setShoppingCartName(String shoppingCartName) {
        this.shoppingCartName = shoppingCartName;
    }

    public LinkedList<Product> getProducts() {
        return products;
    }

    public void setProducts(LinkedList<Product> products) {
        this.products = products;
    }

    public double getSubTotal() {
        int count = 0; 
        for (Product product : products) {
            double price = product.getPrice()*product.getProductQuantity();
            count += price-(price*product.getDiscount());
        }
        return count;
    }

    public int getproductsQuantityLimit() {
        return productsQuantityLimit;
    }

    public double getTotalTax() {
        return getSubTotal() * salesTax;
    }

    public double getSalesTax() {
        return salesTax;
    }

    public void setSalesTax(double salesTax) {
        this.salesTax = salesTax;
    }

    public int getProductsInCart() {
        return productsInCart;
    }

    public void setProductsInCart(int productsInCart) {
        this.productsInCart = productsInCart;
    }

    public void setProductsQuantityLimit(int productsQuantityLimit) {
        this.productsQuantityLimit = productsQuantityLimit;
    }

    public void addProduct(String prdName, int quantity) {
        if ((productsInCart + quantity) <= productsQuantityLimit) {
            Product prd = searchProductInShoppingCart(prdName);
            if (prd != null) {
                int pos = products.indexOf(prd);
                Product product = products.get(pos);
                if ((product.getProductQuantity() + quantity) <= product.getProductQuantityLimit()) {
                    product.setProductQuantity(product.getProductQuantity() + quantity);
                    System.out.println("Product added: " + quantity + " " + prdName);
                    productsInCart += quantity;
                } else {
                    System.out.println("You reached the limit of " + product.getProductQuantityLimit()
                            + " of the product " + prdName + " in your cart");
                }
            } else {
                Product product = store.searchProductInInventory(prdName);
                if (product != null) {
                    if ((product.getProductQuantity() + quantity) <= product.getProductQuantityLimit()) {
                        product.setProductQuantity(quantity);
                        products.add(product);
                        System.out.println("Product added: " + quantity + " " + prdName);
                        productsInCart += quantity;
                    } else {
                        System.out.println("You reached the limit of " + product.getProductQuantityLimit()
                                + " of the product " + prdName + " in your cart");
                    }
                } else {
                    System.out.println("The product you entered " + prdName
                            + " does not exist in the store's inventory. Please check the product name and try again.");
                }
            }
        } else {
            System.out.println("You reached the limit of " + productsQuantityLimit + " products in your cart");
        }
    }

    public void deleteProduct(String prdName, int quantity) {
        if (products.isEmpty()) {
            System.out.println("The shopping cart is empty");
        } else {
            Product prd = searchProductInShoppingCart(prdName);
            if (prd != null) {
                if ((prd.getProductQuantity() - quantity) <= 0) {
                    products.remove(prd);
                    if ((prd.getProductQuantity() - quantity) < 0) {
                        System.out.println("The quantity of " + quantity + " of product " + prdName
                                + " is more than the amount in the cart " + prd.getProductQuantity()
                                + " . The product is going to be remove completly");
                        System.out.println("Deleted product: " + prd.getProductQuantity() + " of " + prdName
                                + " from shopping cart");
                        productsInCart -= prd.getProductQuantity();
                    } else {
                        System.out.println("Deleted product: " + quantity + " " + prdName + " from shopping cart");
                        productsInCart -= quantity;
                    }
                } else if ((prd.getProductQuantity() - quantity) > 0) {
                    prd.setProductQuantity(prd.getProductQuantity() - quantity);
                    System.out.println("Deleted product: " + quantity + " " + prdName + " from shopping cart");
                    productsInCart -= quantity;
                }
            } else {
                System.out.println("The product is not in the shopping cart");
            }
        }
        System.out.println("Shopping cart: " + shoppingCartName + " - Products quantity: " + productsInCart);
    }

    public Product searchProductInShoppingCart(String prdName) {
        for (Product product : products) {
            if (product.getProductName().equals(prdName)) {
                return product;
            }
        }
        return null;
    }    

    public void editProductQuantity(String prdName, int quantity) {
        if (products.isEmpty()) {
            System.out.println("The shopping cart is empty");
            System.out.println("Shopping cart: " + shoppingCartName + " - Products quantity: " + productsInCart);
        } else {
            Product prd = searchProductInShoppingCart(prdName);
            if (prd != null) {
                int pos = products.indexOf(prd);
                Product product = products.get(pos);
                if (quantity <= product.getProductQuantityLimit()) {
                    productsInCart -= product.getProductQuantity();
                    product.setProductQuantity(quantity);
                    System.out.println("Product quantity updated: " + quantity + " " + prdName);
                    productsInCart += quantity;
                }else {
                    System.out.println("You reached the limit of " + product.getProductQuantityLimit()
                            + " of the product " + prdName + " in your cart");
                }
            }else{
                 System.out.println("The product is not in the shopping cart");
            }
        }
    }

    public void showProductsAlphabetical() {
        System.out.println("Products in Shopping Cart alphabetical:");
        LinkedList<Product> productsList = new LinkedList<>(products);
        Collections.sort(productsList, Comparator.comparing(Product::getProductName));
        for (int i = 0; i < productsList.size(); i++) {
            System.out.println(productsList.get(i));
        }
    }

    public void showProductsInCart() {
        System.out.println("Products in Shopping Cart:");

        for (Product product : products) {
            System.out.println(product);
        }
    }

    public void showShoppingCartDetails() {
        System.out.println("Shopping Cart \"" + shoppingCartName + "\" details: Subtotal= " + getSubTotal()
                + " Products Quantity= " + productsInCart + " Sales Tax= " + getTotalTax());
    }

    // Attributes or Variables
    // Total of Cart
    // List of all items in the cart -- Data Structure (Array, LinkedList..)
    // Name of Cart
    // Order ID
    // Sales Tax

    // ---- Methods
    // Add a product to shopping cart -- Create --> complete
    // Show all products in shopping cart -- Read
    // Show total of shopping cart -- Read
    // Update quantity of products -- Update
    // Delete product from shopping cart -- Delete a product
    // Empty cart -- Delete
}
