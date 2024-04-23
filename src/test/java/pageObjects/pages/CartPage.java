package pageObjects.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends TestPageBase {

    @FindBy(css = ".emptyCartButton.btn.btn-mini.btn-ui.pull-right")
    private WebElement _emptyCartButton;

    @FindBy(css = "[data-testid='modal-footer'] button")
    private WebElement _emptyCartModalEmptyCartButton;

    @FindBy(css = ".btn.btn-primary")
    private WebElement _continueShoppingButton;

    public CartPage() {
        PageFactory.initElements(Driver, this);
    }

    public void emptyCart() {
        waitUntilWebElementisDisplayed(_emptyCartButton);
        _emptyCartButton.click();
    }

    public void confirmEmptyCart() {
        waitUntilWebElementisDisplayed(_emptyCartModalEmptyCartButton);
        _emptyCartModalEmptyCartButton.click();
    }

    public boolean isCartPopulated() {
        try {
            return waitUntilWebElementisDisplayed(_emptyCartButton);
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isCartEmpty() {
        try {
            return waitUntilWebElementisDisplayed(_continueShoppingButton);
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
