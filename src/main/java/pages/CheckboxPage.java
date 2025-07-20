package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.List;

public class CheckboxPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Selectors untuk Checkboxes page
    private By checkbox1 = By.xpath("//input[@type='checkbox'][1]");
    private By checkbox2 = By.xpath("//input[@type='checkbox'][2]");
    private By allCheckboxes = By.xpath("//input[@type='checkbox']");

    public CheckboxPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateToCheckboxes() {
        driver.get("https://the-internet.herokuapp.com/checkboxes");
    }

    public void clickCheckbox1() {
        WebElement cb1 = wait.until(ExpectedConditions.elementToBeClickable(checkbox1));
        cb1.click();
    }

    public void clickCheckbox2() {
        WebElement cb2 = wait.until(ExpectedConditions.elementToBeClickable(checkbox2));
        cb2.click();
    }

    public boolean isCheckbox1Selected() {
        WebElement cb1 = driver.findElement(checkbox1);
        return cb1.isSelected();
    }

    public boolean isCheckbox2Selected() {
        WebElement cb2 = driver.findElement(checkbox2);
        return cb2.isSelected();
    }

    public int getTotalCheckboxes() {
        List<WebElement> checkboxes = driver.findElements(allCheckboxes);
        return checkboxes.size();
    }

    public void selectAllCheckboxes() {
        List<WebElement> checkboxes = driver.findElements(allCheckboxes);
        for (WebElement checkbox : checkboxes) {
            if (!checkbox.isSelected()) {
                checkbox.click();
            }
        }
    }

    public void unselectAllCheckboxes() {
        List<WebElement> checkboxes = driver.findElements(allCheckboxes);
        for (WebElement checkbox : checkboxes) {
            if (checkbox.isSelected()) {
                checkbox.click();
            }
        }
    }

    public boolean areAllCheckboxesSelected() {
        List<WebElement> checkboxes = driver.findElements(allCheckboxes);
        for (WebElement checkbox : checkboxes) {
            if (!checkbox.isSelected()) {
                return false;
            }
        }
        return true;
    }
}
