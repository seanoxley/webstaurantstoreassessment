package pageObjects.widgets;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pageObjects.pages.TestPageBase;

public class SearchBarWidget extends TestPageBase{
    
    @FindBy(id = "searchval")
    private WebElement _searchField;

    @FindBy(css = "#searchForm > div > button")
    private WebElement _searchButton;

    public SearchBarWidget() {
        PageFactory.initElements(Driver, this);
    }

    public void EnterSearchTerm( String searchTerm ) {
        ClearSearchField();
        _searchField.sendKeys(searchTerm); 
    }

    public void PerformSearch() {
        _searchButton.click(); 
    }

    public void ClearSearchField() {
        _searchField.clear();
    }
}
