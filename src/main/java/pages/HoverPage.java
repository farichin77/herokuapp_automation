package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.interactions.Actions;
import java.time.Duration;
import java.util.List;

public class HoverPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    // Selectors untuk Hovers page
    private By figureImages = By.cssSelector(".figure img");
    private By figureCaption = By.cssSelector(".figcaption");
    private By userLinks = By.cssSelector(".figcaption a");

    public HoverPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
    }

    public void navigateToHovers() {
        driver.get("https://the-internet.herokuapp.com/hovers");
    }

    public void hoverOverImage(int imageIndex) {
        List<WebElement> images = driver.findElements(figureImages);
        if (imageIndex < images.size()) {
            WebElement image = images.get(imageIndex);
            actions.moveToElement(image).perform();
        }
    }

    public boolean isCaptionVisible(int imageIndex) {
        try {
            hoverOverImage(imageIndex);
            Thread.sleep(500); // Small wait for hover effect
            List<WebElement> captions = driver.findElements(figureCaption);
            return imageIndex < captions.size() && captions.get(imageIndex).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getCaptionText(int imageIndex) {
        hoverOverImage(imageIndex);
        try {
            Thread.sleep(500); // Small wait for hover effect
            List<WebElement> captions = driver.findElements(figureCaption);
            if (imageIndex < captions.size()) {
                return captions.get(imageIndex).getText();
            }
        } catch (Exception e) {
            // Handle exception
        }
        return "";
    }

    public void clickUserLink(int imageIndex) {
        hoverOverImage(imageIndex);
        try {
            Thread.sleep(500); // Small wait for hover effect
            List<WebElement> links = driver.findElements(userLinks);
            if (imageIndex < links.size()) {
                links.get(imageIndex).click();
            }
        } catch (Exception e) {
            // Handle exception
        }
    }

    public int getTotalImages() {
        List<WebElement> images = driver.findElements(figureImages);
        return images.size();
    }

    public boolean isUserProfilePageLoaded() {
        return driver.getCurrentUrl().contains("/users/");
    }
}
