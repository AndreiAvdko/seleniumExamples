package test.java.start;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestWithParameters extends BaseTest {


    @Test(dataProvider = "dataProvider")
    public void openGoogleComInChromeTest(String text) throws InterruptedException {
        System.out.println(driver.getTitle() + " page has been opened");
        WebElement searchField = driver.findElement(By.name("q"));
        searchField.click();
        searchField.sendKeys(text);
        searchField.sendKeys(Keys.ENTER);
        Thread.sleep(3000);
        WebElement resultRow = driver.findElement(By.xpath("(//h3)[2]"));
        assertThat(resultRow.isDisplayed()).as("Element has not been displayed!").isTrue();
        assertThat(resultRow.getText()).as("Wrong text has been displayed!").isEqualTo("The Selenium Browser Automation Project");
        assertThat(resultRow.getAttribute("class")).as("Wrong attribute text!").contains("LC20lb MBeuO DKV0Md");
    }


    @DataProvider(name = "dataProvider")
    public Object [][] dataProviderMethod() {
        return new Object[][]{{"selenium java"},{"selenium javascript"}};
    }
}
