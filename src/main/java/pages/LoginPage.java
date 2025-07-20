package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Selectors untuk The Internet Herokuapp login page
    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By loginButton = By.cssSelector("button[type='submit']");
    private By flashMessage = By.id("flash");
    private By logoutButton = By.cssSelector("a[href='/logout']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void enterUsername(String username) {
        WebElement usernameElement = wait.until(ExpectedConditions.elementToBeClickable(usernameField));
        usernameElement.clear();
        usernameElement.sendKeys(username);
    }

    public void enterPassword(String password) {
        WebElement passwordElement = wait.until(ExpectedConditions.elementToBeClickable(passwordField));
        passwordElement.clear();
        passwordElement.sendKeys(password);
    }

    public void clickLoginButton() {
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginBtn.click();
    }

    public String getFlashMessage() {
        WebElement flashElement = wait.until(ExpectedConditions.visibilityOfElementLocated(flashMessage));
        return flashElement.getText();
    }

    public boolean isLogoutButtonVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(logoutButton)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickLogoutButton() {
        if (isLogoutButtonVisible()) {
            WebElement logoutBtn = driver.findElement(logoutButton);
            logoutBtn.click();
        }
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    public boolean isLoginSuccessful() {
        return isLogoutButtonVisible() && getFlashMessage().contains("You logged into a secure area!");
    }

    public boolean isLoginFailed() {
        return getFlashMessage().contains("Your username is invalid!") || 
               getFlashMessage().contains("Your password is invalid!");
    }
}
