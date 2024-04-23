package tests;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.pages.CartPage;
import pageObjects.pages.ProductPage;
import pageObjects.pages.SearchResultsPage;
import pageObjects.pages.TestPageBase;
import pageObjects.widgets.SearchBarWidget;

public class AssessmentTests extends TestPageBase {

    private String _websiteUrl = "https://www.webstaurantstore.com/";
    private String _browser = "chrome";

    @BeforeTest
    public void beforeTest() {
        _browser = System.getProperty("browser", "chrome");
    }

    @BeforeMethod
    public void beforeMethod() {
        SetupDriver(_browser);
        OpenUrl(_websiteUrl);
    }

    @AfterMethod
    public void afterMethod() {
        TearDownDriver();
    }

    @Test
    public void storeMainTitleTest() {
        String websiteTitle = "WebstaurantStore: Restaurant Supplies & Foodservice Equipment";

        // Validate website title.
        Assert.assertEquals(Driver.getTitle(), websiteTitle);
    }

    @Test
    public void storeSearchTest() throws InterruptedException {
        String searchText = "stainless work table";
        String validationString = "Table";

        // Perform the initial product search.
        SearchBarWidget searchBarWidget = new SearchBarWidget();
        searchBarWidget.EnterSearchTerm(searchText);
        searchBarWidget.PerformSearch();

        // Add the initial product descriptions to the list.
        SearchResultsPage searchResultsPage = new SearchResultsPage();
        List<String> productionDescriptions = new ArrayList<>();
        productionDescriptions.addAll(searchResultsPage.getProductsDesciptions());

        // Iterate through all pages and add each product description to the list.
        do {
            // Navigate to the next page.
            searchResultsPage.navigateToNextPage();

            // Add the product descriptions to the list.
            productionDescriptions.addAll(searchResultsPage.getProductsDesciptions());
        } while (searchResultsPage.isNextPageAvailable());

        // Check if any product descriptions do not contain the intended validation
        // string.
        // If so, add to an errors list for later validation.
        List<String> errorList = new ArrayList<>();
        for (String thisProductDescription : productionDescriptions) {
            if (!thisProductDescription.contains(validationString)) {
                errorList.add(thisProductDescription);
            }
        }

        // Assert there are no entries in the list which do not contain the intended
        // validation string.
        if (errorList.size() > 0) {
            System.out.println(errorList);
        }
        Assert.assertEquals(errorList.size(), 0);
    }

    @Test
    public void cartTest() throws InterruptedException {
        String searchText = "stainless work table";

        // Perform the initial product search.
        SearchBarWidget searchBarWidget = new SearchBarWidget();
        searchBarWidget.EnterSearchTerm(searchText);
        searchBarWidget.PerformSearch();

        // Add the initial product descriptions to the list.
        SearchResultsPage searchResultsPage = new SearchResultsPage();

        // Navigate to the last product page.
        searchResultsPage.navigateToLastPast();

        // Get the list of products then click on the last one.
        List<WebElement> productElements = searchResultsPage.getProducts();
        productElements.get(productElements.size() - 1).click();

        // Add the product the the cart then open the product page.
        ProductPage productPage = new ProductPage();
        productPage.addProductToCart();
        productPage.openProductCart();

        // Validate that the cart is not empty.
        CartPage cartPage = new CartPage();
        Assert.assertTrue(cartPage.isCartPopulated());

        // Empty the cart via button click then confirm via a modal button click.
        cartPage.emptyCart();
        cartPage.confirmEmptyCart();

        // Validate that the cart is empty.
        Assert.assertTrue(cartPage.isCartEmpty());
    }
}
