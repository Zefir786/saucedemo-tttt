package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutStepOnePage extends BasePage {
    private final By firstName = By.id("first-name");
    private final By lastName = By.id("last-name");
    private final By postalCode = By.id("postal-code");
    private final By continueBtn = By.id("continue");
    private final By errorContainer = By.cssSelector("h3[data-test='error']");

    public CheckoutStepOnePage(WebDriver driver) {
        super(driver);
    }

    public void fillInformation(String fn, String ln, String postal) {
        type(firstName, fn);
        type(lastName, ln);
        type(postalCode, postal);
    }

    public void clickContinue() {
        click(continueBtn);
    }

    public String getError() {
        return isDisplayed(errorContainer) ? getText(errorContainer) : "";
    }
}
