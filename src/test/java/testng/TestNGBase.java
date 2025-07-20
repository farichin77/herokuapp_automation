package testng;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestNGBase {
    protected WebDriver driver;
    protected final String BASE_URL = "https://the-internet.herokuapp.com";

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        
        // Setup Chrome options untuk stabilitas
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--disable-extensions");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        
        System.out.println("Opening The Internet Herokuapp...");
        driver.get(BASE_URL + "/login");
        System.out.println("URL loaded: " + driver.getCurrentUrl());
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            System.out.println("Closing browser...");
            driver.quit();
        }
    }
}
