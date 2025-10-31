package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class InventoryPage extends BasePage {
    private final By inventoryTitle = By.cssSelector("span.title");
    private final By inventoryItems = By.cssSelector(".inventory_item");
    private final By addToCartButtons = By.cssSelector("button.btn_inventory");
    private final By shoppingCartBadge = By.cssSelector(".shopping_cart_badge");
    private final By menuButton = By.id("react-burger-menu-btn");
    private final By logoutLink = By.id("logout_sidebar_link");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public boolean isOpen() {
        return isDisplayed(inventoryTitle) && getText(inventoryTitle).contains("Products");
    }

    public List<String> listItems() {
        List<WebElement> items = driver.findElements(inventoryItems);
        return items.stream()
                .map(i -> i.findElement(By.cssSelector(".inventory_item_name")).getText())
                .collect(Collectors.toList());
    }

    public void addFirstItemToCart() {
        waitClickable(addToCartButtons).click();
    }

    public int getCartCount() {
        try {
            String txt = getText(shoppingCartBadge);
            return Integer.parseInt(txt);
        } catch (Exception e) {
            return 0;
        }
    }

    public void openMenu() {
        click(menuButton);
    }

    public void logout() {
        click(logoutLink);
    }
}
