package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutStepTwoPage extends BasePage {
    private final By finishBtn = By.id("finish");
    private final By itemTotal = By.cssSelector(".summary_subtotal_label");
    private final By taxLabel = By.cssSelector(".summary_tax_label");

    public CheckoutStepTwoPage(WebDriver driver) {
        super(driver);
    }

    public boolean hasSummary() {
        return isDisplayed(itemTotal);
    }

    public void finish() {
        click(finishBtn);
    }
}
