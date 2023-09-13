package test.java.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;;import static org.testng.AssertJUnit.assertEquals;

public class SearchResultPage extends BasePage {
    // Вместо этого
    //private By searchResultURLsBy = By.xpath("//cite[@class='iUh30']");
    // Это
    @FindBy(xpath = "//cite[@class='iUh30']")
    private List<WebElement> searchResultURLs;


    public SearchResultPage() {
        super();
    }

    public void assertThatExpectedValueIsOnSearchTop(String expectedValue) {
        // Это не нужно
        // List<WebElement> searchResultURLs = driver.findElements(searchResultURLsBy);
    assertEquals(searchResultURLs.get(0).getText(), "expectedValue",
              expectedValue + " is not the first result!");
    }

}
