package test.java.pageObject;

import org.openqa.selenium.WebDriver;

public abstract class BasePage {

    protected  WebDriver driver;

    public BasePage() {
        this.driver = BaseTest.getDriver();
    }


}
