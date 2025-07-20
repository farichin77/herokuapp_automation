package tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import pages.LoginPage;
import pages.CheckboxPage;
import pages.DropdownPage;
import utils.BugReporter;
import static org.junit.jupiter.api.Assertions.*;

public class BugDemoTest extends BaseTest {

    @Test
    @DisplayName("Demo: Manual Bug Report - Critical Bug")
    public void testManualCriticalBugReport() {
        LoginPage loginPage = new LoginPage(driver);
        
        // Simulate a critical bug scenario
        loginPage.login("tomsmith", "SuperSecretPassword!");
        
        // Manual bug report for demonstration
        BugReporter.reportCriticalBug(
            "Login Functionality",
            "User cannot login with valid credentials - system shows error message",
            "User should be able to login successfully with valid credentials",
            "Login fails with error: 'Invalid credentials' even with correct username/password",
            "1. Navigate to login page\n2. Enter username: tomsmith\n3. Enter password: SuperSecretPassword!\n4. Click login button\n5. Observe error message",
            driver
        );
        
        System.out.println("✅ Manual critical bug report demo completed!");
    }

    @Test
    @DisplayName("Demo: Manual Bug Report - High Priority Bug")
    public void testManualHighBugReport() {
        CheckboxPage checkboxPage = new CheckboxPage(driver);
        checkboxPage.navigateToCheckboxes();
        
        // Manual bug report for UI issue
        BugReporter.reportHighBug(
            "Checkbox UI Issue",
            "Checkbox state is not visually updated after clicking",
            "Checkbox should show checked/unchecked state visually",
            "Checkbox appears unchecked even when it's actually checked in DOM",
            "1. Navigate to checkboxes page\n2. Click on checkbox 1\n3. Observe visual state vs actual state",
            driver
        );
        
        System.out.println("✅ Manual high priority bug report demo completed!");
    }

    @Test
    @DisplayName("Demo: Manual Bug Report - Medium Priority Bug")
    public void testManualMediumBugReport() {
        DropdownPage dropdownPage = new DropdownPage(driver);
        dropdownPage.navigateToDropdown();
        
        // Manual bug report for dropdown issue
        BugReporter.reportMediumBug(
            "Dropdown Selection Issue",
            "Dropdown does not retain selected value after page refresh",
            "Selected dropdown value should persist after page refresh",
            "Dropdown resets to default value after page refresh",
            "1. Navigate to dropdown page\n2. Select 'Option 1'\n3. Refresh page\n4. Observe dropdown value resets",
            driver
        );
        
        System.out.println("✅ Manual medium priority bug report demo completed!");
    }

    @Test
    @DisplayName("Demo: Manual Bug Report - Low Priority Bug")
    public void testManualLowBugReport() {
        // Manual bug report for cosmetic issue
        BugReporter.reportLowBug(
            "Page Title Formatting",
            "Page title has inconsistent capitalization",
            "Page title should follow consistent capitalization rules",
            "Some pages use 'Title Case' while others use 'sentence case'",
            "1. Navigate through different pages\n2. Observe page titles\n3. Note inconsistent capitalization",
            driver
        );
        
        System.out.println("✅ Manual low priority bug report demo completed!");
    }

    @Test
    @DisplayName("Demo: Bug Reporting System (Configurable Demo)")
    public void testIntentionalFailureForBugReport() {
        LoginPage loginPage = new LoginPage(driver);
        
        // Check if we should demonstrate failure or success
        String demoMode = System.getProperty("bug.demo.mode", "success");
        
        loginPage.login("tomsmith", "SuperSecretPassword!");
        String flashMessage = loginPage.getFlashMessage();
        
        if ("failure".equals(demoMode)) {
            // DEMO MODE: Intentional failure to show auto bug reporting
            System.out.println("🐛 DEMO MODE: Demonstrating automatic bug reporting on test failure");
            assertEquals("This will fail intentionally", flashMessage, 
                        "This test is designed to fail to demonstrate auto bug reporting");
        } else {
            // SUCCESS MODE: Manual bug report demo without test failure
            System.out.println("✅ SUCCESS MODE: Demonstrating manual bug reporting");
            
            // Create a manual bug report to demonstrate the system
            BugReporter.reportMediumBug(
                "Demo Bug Report",
                "This is a demonstration of the bug reporting system",
                "Bug reporting system should generate reports automatically",
                "Manual bug report created for demonstration purposes",
                "1. Login with valid credentials\n2. Trigger bug reporting system\n3. Verify report generation",
                driver
            );
            
            // Make a successful assertion
            assertTrue(flashMessage.contains("You logged into a secure area"), 
                      "Login should be successful and show success message");
            
            System.out.println("✅ Bug reporting system demo completed successfully!");
        }
    }

    @Test
    @DisplayName("Demo: Complex Scenario Bug Report")
    public void testComplexScenarioBugReport() {
        LoginPage loginPage = new LoginPage(driver);
        CheckboxPage checkboxPage = new CheckboxPage(driver);
        
        // Complex scenario with multiple steps
        loginPage.login("tomsmith", "SuperSecretPassword!");
        
        if (loginPage.isLoginSuccessful()) {
            // Navigate to checkboxes after login
            checkboxPage.navigateToCheckboxes();
            
            // Report a bug about post-login navigation
            BugReporter.reportMediumBug(
                "Post-Login Navigation Issue",
                "After successful login, navigating to other pages loses session",
                "User should remain logged in when navigating to other pages",
                "User session is lost when navigating from secure area to checkboxes page",
                "1. Login with valid credentials\n2. Verify login success\n3. Navigate to checkboxes page\n4. Observe session is lost",
                driver
            );
        }
        
        System.out.println("✅ Complex scenario bug report demo completed!");
    }

    @Test
    @DisplayName("Demo: Performance Issue Bug Report")
    public void testPerformanceBugReport() {
        long startTime = System.currentTimeMillis();
        
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("tomsmith", "SuperSecretPassword!");
        
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        
        // Report performance bug if login takes too long
        if (duration > 5000) { // 5 seconds threshold
            BugReporter.reportHighBug(
                "Login Performance Issue",
                "Login process takes longer than expected",
                "Login should complete within 3 seconds",
                "Login took " + duration + "ms to complete",
                "1. Navigate to login page\n2. Enter credentials\n3. Click login\n4. Measure time to completion",
                driver
            );
        }
        
        System.out.println("✅ Performance bug report demo completed! Login took: " + duration + "ms");
    }
}
