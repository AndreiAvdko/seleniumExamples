package test.java.pageObject;

import org.openqa.selenium.WebDriver;

public class BaseTest {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }
}
