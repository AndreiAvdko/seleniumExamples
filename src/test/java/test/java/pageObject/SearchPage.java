package test.java.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends BasePage {
    // Вместо этого
    //private By searhFileldBy = By.id("1st-ib");
    // Это
    @FindBy(id = "1st-ib")
    private WebElement searchField;

    public SearchPage() {
        super();
    }

    public void fillTheSearchField(String keyword) {
        // Это не нужно после добавления аннотации
        // WebElement searchField = driver.findElement(searhFileldBy);

        searchField.sendKeys(keyword);
    }

    public void pressEnter() {
        // Это не нужно после добавления аннотации
        // WebElement searchField = driver.findElement(searhFileldBy);
        searchField.sendKeys(Keys.RETURN);
    }
}
