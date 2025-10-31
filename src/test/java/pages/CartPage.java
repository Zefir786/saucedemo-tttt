package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {
    private final By cartItem = By.cssSelector(".cart_item");
    private final By checkoutBtn = By.id("checkout");
    private final By continueShopping = By.id("continue-shopping");
    private final By removeBtn = By.cssSelector("button.cart_button");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean hasItems() {
        return isDisplayed(cartItem);
    }

    public void goToCheckout() {
        click(checkoutBtn);
    }

    public void removeFirstFromCart() {
        click(removeBtn);
    }

    public void continueShopping() {
        click(continueShopping);
    }
}
