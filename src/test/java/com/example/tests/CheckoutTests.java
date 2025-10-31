package com.example.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class CheckoutTests extends BaseTest {

    @Test(description = "Happy path: complete checkout")
    public void completeCheckout() {
        LoginPage login = new LoginPage(driver);
        login.open();
        login.login("standard_user", "secret_sauce");

        InventoryPage inv = new InventoryPage(driver);
        inv.addFirstItemToCart();
        driver.get("https://www.saucedemo.com/cart.html");

        CartPage cart = new CartPage(driver);
        Assert.assertTrue(cart.hasItems());

        cart.goToCheckout();
        CheckoutStepOnePage stepOne = new CheckoutStepOnePage(driver);
        stepOne.fillInformation("John", "Doe", "12345");
        stepOne.clickContinue();

        CheckoutStepTwoPage stepTwo = new CheckoutStepTwoPage(driver);
        Assert.assertTrue(stepTwo.hasSummary());
        stepTwo.finish();

        CheckoutCompletePage complete = new CheckoutCompletePage(driver);
        Assert.assertTrue(complete.isComplete(), "Checkout should complete successfully");
    }

    @Test(description = "Negative: checkout with missing postal code shows error")
    public void checkoutMissingPostalCode() {
        LoginPage login = new LoginPage(driver);
        login.open();
        login.login("standard_user", "secret_sauce");

        InventoryPage inv = new InventoryPage(driver);
        inv.addFirstItemToCart();
        driver.get("https://www.saucedemo.com/cart.html");

        CartPage cart = new CartPage(driver);
        cart.goToCheckout();

        CheckoutStepOnePage stepOne = new CheckoutStepOnePage(driver);
        stepOne.fillInformation("Jane", "Smith", "");
        stepOne.clickContinue();

        String err = stepOne.getError();
        Assert.assertTrue(err.toLowerCase().contains("postal"), "Error should indicate missing postal code");
    }
}
