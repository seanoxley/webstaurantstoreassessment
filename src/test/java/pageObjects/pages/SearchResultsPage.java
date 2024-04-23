package pageObjects.pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultsPage extends TestPageBase {

    @FindBy(css = "[data-testid = 'productBoxContainer']")
    private List<WebElement> _productListings;

    @FindBy(css = ".inline-block.leading-4.align-top.rounded-l-md [aria-label *= 'go to page']")
    private WebElement _previousPageButton;

    @FindBy(css = ".inline-block.leading-4.align-top.rounded-r-md [aria-label *= 'go to page']")
    private WebElement _nextPageButton;

    @FindBy(css = "[aria-label *= 'last page']")
    private WebElement _lastPageButton;

    public SearchResultsPage() {
        PageFactory.initElements(Driver, this);
    }

    public List<WebElement> getProducts() {
        if (waitUntilWebElementisDisplayed(_productListings.get(0))) {
            return _productListings;
        }
        throw new TimeoutException("Products are not available.");
    }

    public List<String> getProductsDesciptions() {
        // Grabbing innerText specifically as the native .getText method returns the
        // descriptions as well as sub-components.
        return getProducts().stream().map(e -> e.getText()).collect(Collectors.toList());
    }

    public boolean isPreviousPageAvailable() {
        try {
            return isWebElementDisplayed(_previousPageButton);
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isNextPageAvailable() {
        try {
            return isWebElementDisplayed(_nextPageButton);
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void navigateToPreviousPage() {
        waitUntilWebElementisDisplayed(_previousPageButton);
        _previousPageButton.click();
    }

    public void navigateToNextPage() {
        waitUntilWebElementisDisplayed(_nextPageButton);
        _nextPageButton.click();
    }

    public void navigateToLastPast() {
        waitUntilWebElementisDisplayed(_lastPageButton);
        _lastPageButton.click();
    }
}
