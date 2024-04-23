package pageObjects.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage extends TestPageBase {

    @FindBy(css = ".btn.btn-cart.btn-large.z-0")
    private WebElement _addToCartButton;

    @FindBy(css = "a.btn.btn-primary .icon-shopping-cart.pr-1")
    private WebElement _viewCartButton;

    public ProductPage() {
        PageFactory.initElements(Driver, this);
    }

    public void addProductToCart() {
        waitUntilWebElementisDisplayed(_addToCartButton);
        _addToCartButton.click();
    }

    public void openProductCart() {
        waitUntilWebElementisDisplayed(_viewCartButton);
        _viewCartButton.click();
    }
}
