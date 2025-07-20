package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.List;

public class DropdownPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Selectors untuk Dropdown page
    private By dropdownElement = By.id("dropdown");

    public DropdownPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateToDropdown() {
        driver.get("https://the-internet.herokuapp.com/dropdown");
    }

    public void selectByValue(String value) {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(dropdownElement));
        Select select = new Select(dropdown);
        select.selectByValue(value);
    }

    public void selectByVisibleText(String text) {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(dropdownElement));
        Select select = new Select(dropdown);
        select.selectByVisibleText(text);
    }

    public void selectByIndex(int index) {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(dropdownElement));
        Select select = new Select(dropdown);
        select.selectByIndex(index);
    }

    public String getSelectedOptionText() {
        WebElement dropdown = driver.findElement(dropdownElement);
        Select select = new Select(dropdown);
        return select.getFirstSelectedOption().getText();
    }

    public String getSelectedOptionValue() {
        WebElement dropdown = driver.findElement(dropdownElement);
        Select select = new Select(dropdown);
        return select.getFirstSelectedOption().getAttribute("value");
    }

    public List<String> getAllOptionTexts() {
        WebElement dropdown = driver.findElement(dropdownElement);
        Select select = new Select(dropdown);
        List<WebElement> options = select.getOptions();
        return options.stream().map(WebElement::getText).toList();
    }

    public int getTotalOptions() {
        WebElement dropdown = driver.findElement(dropdownElement);
        Select select = new Select(dropdown);
        return select.getOptions().size();
    }

    public boolean isOptionAvailable(String optionText) {
        List<String> allOptions = getAllOptionTexts();
        return allOptions.contains(optionText);
    }
}
