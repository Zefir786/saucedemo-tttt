package com.example.tests;

import pages.CartPage;
import pages.InventoryPage;
import pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTests extends BaseTest {

    @Test(description = "Add first item to cart and verify cart count")
    public void addItemToCart() {
        LoginPage login = new LoginPage(driver);
        login.open();
        login.login("standard_user", "secret_sauce");

        InventoryPage inv = new InventoryPage(driver);
        Assert.assertTrue(inv.isOpen());

        inv.addFirstItemToCart();
        Assert.assertEquals(inv.getCartCount(), 1, "Cart count should be 1 after adding one item");
    }

    @Test(description = "Remove item from cart")
    public void removeItemFromCart() {
        LoginPage login = new LoginPage(driver);
        login.open();
        login.login("standard_user", "secret_sauce");

        InventoryPage inv = new InventoryPage(driver);
        inv.addFirstItemToCart();
        driver.get("https://www.saucedemo.com/cart.html");
        CartPage cart = new CartPage(driver);
        Assert.assertTrue(cart.hasItems(), "Cart should contain items");
        cart.removeFirstFromCart();
        Assert.assertFalse(cart.hasItems(), "Cart should be empty after removing the item");
    }
}
