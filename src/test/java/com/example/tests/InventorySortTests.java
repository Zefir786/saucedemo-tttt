package com.example.tests;

import pages.InventoryPage;
import pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InventorySortTests extends BaseTest {

    @Test(description = "Sort inventory by name (ZA) - smoke")
    public void sortInventory() {
        LoginPage login = new LoginPage(driver);
        login.open();
        login.login("standard_user", "secret_sauce");

        InventoryPage inv = new InventoryPage(driver);
        Assert.assertTrue(inv.isOpen());

        Select sort = new Select(driver.findElement(By.cssSelector(".product_sort_container")));
        sort.selectByValue("za");
        Assert.assertTrue(inv.listItems().size() > 0, "Inventory should list items after sorting");
    }
}
