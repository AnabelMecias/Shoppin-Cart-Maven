package com.shoppingcart;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class App {
    public static void main(String[] args) {
        Store store = new Store();

        Product product1 = new Product(1001, "Smartphone", 600.0f, 0.1f, "High-end smartphone with advanced features.",
                0, 5);
        Product product2 = new Product(2002, "Laptop", 900.0f, 0.15f, "Powerful laptop for work and gaming.", 0, 100);
        Product product3 = new Product(3003, "Headphones", 150.0f, 0.05f,
                "Wireless headphones with noise-cancellation.", 0, 200);
        Product product4 = new Product(4004, "Tablet", 250.0f, 0.2f, "Compact tablet with a high-resolution display.",
                0, 3);
        Product product5 = new Product(5005, "Smartwatch", 200.0f, 0.05f, "Fitness tracker with heart rate monitor.", 0,
                20);

        store.getProductsAvailables().add(product1);
        store.getProductsAvailables().add(product2);
        store.getProductsAvailables().add(product3);
        store.getProductsAvailables().add(product4);
        store.getProductsAvailables().add(product5);

        ShoppingCart cart = new ShoppingCart("Anabel", store);
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("==== Shopping Cart Menu ====");
            System.out.println("1. Show Subtotal Balance");
            System.out.println("2. Add Product");
            System.out.println("3. Delete Product");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    cart.showShoppingCartDetails();
                    break;
                case 2:
                    System.out.println("==== Products Availables ====");
                    store.showProductsInInventory();
                    String prdName = getValidProductName(scanner, "add");
                    int quantity = getValidQuantity(scanner, "add", prdName);
                    cart.addProduct(prdName, quantity);
                    cart.showProductsInCart();
                    break;
                case 3:
                    if (cart.getProductsInCart() > 0) {
                        cart.showProductsInCart();
                        String prdNameDelete = getValidProductName(scanner, "delete");
                        int quantityDelete = getValidQuantity(scanner, "delete", prdNameDelete);
                        cart.deleteProduct(prdNameDelete, quantityDelete);
                        cart.showProductsInCart();
                    } else {
                        System.out.println("There is nothing in your cart");
                    }
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Try again!");
                    break;
            }
        }
        System.out.println("Thank you for using the shopping cart!");
        scanner.close();
    }

    private static int getValidQuantity(Scanner scanner, String action, String prdName) {
        int quantity = 0;
        while (quantity <= 0) {
            System.out.print("Enter the quantity of the product " + prdName + " you want to " + action + ": ");
            quantity = scanner.nextInt();
            scanner.nextLine();
            if (quantity <= 0) {
                System.out.println("Invalid input. Quantity must be greater than zero.");
            }
        }
        return quantity;
    }

    private static String getValidProductName(Scanner scanner, String action) {
        String prdName = "";
        do  {
            System.out.print("Enter the name of the product you want to " + action + ": ");
            prdName = scanner.nextLine();
        } while (prdName.isEmpty());
        return prdName;
    }
}
