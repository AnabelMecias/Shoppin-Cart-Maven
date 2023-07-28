package test.java.com.shoppingcart;

import com.shoppingcart.Product;
import com.shoppingcart.ShoppingCart;
import com.shoppingcart.Store;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;

public class ShoppingCartTest {
    private ShoppingCart shoppingCart;

    @BeforeEach
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
        Assertions.assertEquals(0.0f, this.shoppingCart.getSubTotal());
    }

    @Test
    public void getSubTotalSingleProductTest(){
        this.shoppingCart.addProduct("UltraPhone", 1);
        Assertions.assertEquals(630.0f, this.shoppingCart.getSubTotal());
    }

    @Test
    public void getSubTotalMultipleProductTest(){
        this.shoppingCart.addProduct("UltraPhone", 2);
        this.shoppingCart.addProduct("SuperBook", 3);
        this.shoppingCart.addProduct("NoiseBuster", 1);
        this.shoppingCart.addProduct("TabX", 2);
        Assertions.assertEquals(4844.0f, this.shoppingCart.getSubTotal());
    }

}
