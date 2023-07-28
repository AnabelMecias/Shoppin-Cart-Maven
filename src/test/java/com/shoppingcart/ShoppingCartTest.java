package com.shoppingcart;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ShoppingCartTest {
    private ShoppingCart shoppingCart;

    @Before
    public void setUp() {
        List<Product> productsAvailables = new ArrayList<>();
        Product product1 = new Product(1001, "UltraPhone", 700.0f, 0.1f, "Premium smartphone with AI capabilities.",
                0, 8);
        Product product2 = new Product(2002, "SuperBook", 1200.0f, 0.2f,
                "High-performance laptop for professionals and gamers.", 0, 50);
        Product product3 = new Product(3003, "NoiseBuster", 120.0f, 0.05f,
                "Wireless headphones with advanced noise-cancellation technology.", 0, 150);
        Product product4 = new Product(4004, "TabX", 300.0f, 0.15f, "Versatile tablet with a large display.",
                0, 5);
        Product product5 = new Product(5005, "FitTracker Pro", 250.0f, 0.1f,
                "Smartwatch with comprehensive fitness tracking features.", 0,
                30);
        productsAvailables.add(product1);
        productsAvailables.add(product2);
        productsAvailables.add(product3);
        productsAvailables.add(product4);
        productsAvailables.add(product5);
        this.shoppingCart = new ShoppingCart("Anabel", new Store(productsAvailables));
    }

    @Test
    public void getSubTotalNoProductsTest(){
        Assert.assertEquals(0.0f, this.shoppingCart.getSubTotal(), 0.001f);
    }

    @Test
    public void getSubTotalSingleProductTest(){
        this.shoppingCart.addProduct("UltraPhone", 1);
        Assert.assertEquals(630.0f, this.shoppingCart.getSubTotal(), 1.0f);
    }

    @Test
    public void testGetSubTotalMultipleProduct(){
        this.shoppingCart.addProduct("UltraPhone", 2);
        this.shoppingCart.addProduct("SuperBook", 3);

        Assert.assertEquals(4140.0f, this.shoppingCart.getSubTotal(), 2.0f);
    }

    @Test
    public void testAddAProduct() {
        this.shoppingCart.addProduct("UltraPhone", 2);
        Assert.assertEquals(2, this.shoppingCart.getProductsInCart());
    }

    public void testAddExistingProductInCart() {
        Product product1 = new Product(1001, "UltraPhone", 700.0f, 0.1f, "Premium smartphone with AI capabilities.",
                7, 8);
        this.shoppingCart.addProduct("UltraPhone", 2);
        this.shoppingCart.addProduct("UltraPhone", 5);
        Assert.assertEquals(7, this.shoppingCart.getProductsInCart());
        Assert.assertEquals(product1, this.shoppingCart.getProducts().get(0));
    }

    public void testAddProductWithQuantityAboveLimit() {
        Product product1 = new Product(1001, "UltraPhone", 700.0f, 0.1f, "Premium smartphone with AI capabilities.",
                5, 8);
        this.shoppingCart.addProduct("UltraPhone", 5);
        this.shoppingCart.addProduct("UltraPhone", 5);
        Assert.assertEquals(5, this.shoppingCart.getProductsInCart());
        Assert.assertEquals(product1, this.shoppingCart.getProducts().get(0));
    }

    public void testAddNonExistingProductToShoppingCart() {
        this.shoppingCart.addProduct("Belt", 5);
        Assert.assertEquals(0, this.shoppingCart.getProductsInCart());
    }

    @Test
    public void testEditProductQuantity() {
        
    }
}
