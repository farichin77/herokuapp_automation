package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.Alert;
import java.time.Duration;

public class AlertsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Selectors untuk JavaScript Alerts page
    private By jsAlertButton = By.xpath("//button[text()='Click for JS Alert']");
    private By jsConfirmButton = By.xpath("//button[text()='Click for JS Confirm']");
    private By jsPromptButton = By.xpath("//button[text()='Click for JS Prompt']");
    private By resultText = By.id("result");

    public AlertsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateToAlerts() {
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
    }

    public void clickJSAlert() {
        WebElement alertBtn = wait.until(ExpectedConditions.elementToBeClickable(jsAlertButton));
        alertBtn.click();
    }

    public void clickJSConfirm() {
        WebElement confirmBtn = wait.until(ExpectedConditions.elementToBeClickable(jsConfirmButton));
        confirmBtn.click();
    }

    public void clickJSPrompt() {
        WebElement promptBtn = wait.until(ExpectedConditions.elementToBeClickable(jsPromptButton));
        promptBtn.click();
    }

    public String getAlertText() {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        return alert.getText();
    }

    public void acceptAlert() {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
    }

    public void dismissAlert() {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.dismiss();
    }

    public void sendTextToAlert(String text) {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.sendKeys(text);
        alert.accept();
    }

    public String getResultText() {
        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(resultText));
        return result.getText();
    }

    public boolean isAlertPresent() {
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
