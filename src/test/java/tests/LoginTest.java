package tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import pages.LoginPage;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest extends BaseTest {

    @Test
    @DisplayName("Test Login dengan Username dan Password Valid")
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        
        // Credentials valid untuk The Internet Herokuapp
        loginPage.login("tomsmith", "SuperSecretPassword!");
        
        // Verifikasi login berhasil
        assertTrue(loginPage.isLoginSuccessful(), "Login should be successful with valid credentials");
        assertTrue(loginPage.isLogoutButtonVisible(), "Logout button should be visible after successful login");
        
        String flashMessage = loginPage.getFlashMessage();
        assertTrue(flashMessage.contains("You logged into a secure area!"), 
                  "Flash message should indicate successful login");
        
        System.out.println("✅ Valid login test passed!");
        System.out.println("Flash message: " + flashMessage);
    }

    @Test
    @DisplayName("Test Login dengan Username Invalid")
    public void testInvalidUsername() {
        LoginPage loginPage = new LoginPage(driver);
        
        // Username invalid, password valid
        loginPage.login("invaliduser", "SuperSecretPassword!");
        
        // Verifikasi login gagal
        assertTrue(loginPage.isLoginFailed(), "Login should fail with invalid username");
        assertFalse(loginPage.isLogoutButtonVisible(), "Logout button should not be visible after failed login");
        
        String flashMessage = loginPage.getFlashMessage();
        assertTrue(flashMessage.contains("Your username is invalid!"), 
                  "Flash message should indicate invalid username");
        
        System.out.println("✅ Invalid username test passed!");
        System.out.println("Flash message: " + flashMessage);
    }

    @Test
    @DisplayName("Test Login dengan Password Invalid")
    public void testInvalidPassword() {
        LoginPage loginPage = new LoginPage(driver);
        
        // Username valid, password invalid
        loginPage.login("tomsmith", "wrongpassword");
        
        // Verifikasi login gagal
        assertTrue(loginPage.isLoginFailed(), "Login should fail with invalid password");
        assertFalse(loginPage.isLogoutButtonVisible(), "Logout button should not be visible after failed login");
        
        String flashMessage = loginPage.getFlashMessage();
        assertTrue(flashMessage.contains("Your password is invalid!"), 
                  "Flash message should indicate invalid password");
        
        System.out.println("✅ Invalid password test passed!");
        System.out.println("Flash message: " + flashMessage);
    }

    @Test
    @DisplayName("Test Login dan Logout")
    public void testLoginAndLogout() {
        LoginPage loginPage = new LoginPage(driver);
        
        // Login dengan credentials valid
        loginPage.login("tomsmith", "SuperSecretPassword!");
        assertTrue(loginPage.isLoginSuccessful(), "Login should be successful");
        
        // Logout
        loginPage.clickLogoutButton();
        
        // Verifikasi kembali ke halaman login
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/login"), "Should be redirected to login page after logout");
        
        String flashMessage = loginPage.getFlashMessage();
        assertTrue(flashMessage.contains("You logged out of the secure area!"), 
                  "Flash message should indicate successful logout");
        
        System.out.println("✅ Login and logout test passed!");
        System.out.println("Flash message: " + flashMessage);
    }
}
