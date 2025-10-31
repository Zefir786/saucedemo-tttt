package com.example.tests;

import pages.LoginPage;
import pages.InventoryPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @Test(description = "Positive: standard_user can login")
    public void successfulLogin() {
        LoginPage login = new LoginPage(driver);
        login.open();
        login.login("standard_user", "secret_sauce");
        InventoryPage inv = new InventoryPage(driver);
        Assert.assertTrue(inv.isOpen(), "Inventory page should be open after successful login");
    }

    @Test(description = "Negative: locked_out_user cannot login and sees error")
    public void lockedOutUserCannotLogin() {
        LoginPage login = new LoginPage(driver);
        login.open();
        login.login("locked_out_user", "secret_sauce");
        String err = login.getError();
        Assert.assertTrue(err.length() > 0, "Error message should be visible for locked out user");
    }

    @Test(description = "Negative: invalid credentials show error")
    public void invalidCredentials() {
        LoginPage login = new LoginPage(driver);
        login.open();
        login.login("invalid_user", "wrong");
        String err = login.getError();
        Assert.assertTrue(err.length() > 0, "Error message should appear for invalid credentials");
    }
}
