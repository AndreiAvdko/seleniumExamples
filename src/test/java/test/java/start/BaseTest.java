package test.java.start;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import java.io.File;

public abstract class BaseTest {
    protected WebDriver driver;

    @BeforeTest
    public void setUp() {
        File file = new File("src/test/resources/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());

        /*switch(browser) {
            case CHROME: driver = new ChromeDriver(); break;
            case FIREFOX: driver = new FirefoxDriver(); break;
            case INTERNET_EXPLORER: driver = new InternetExplorerDriver(); break;
        }*/

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.navigate().to("https://www.google.com/");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
