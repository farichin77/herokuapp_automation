package testng;

import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
import org.testng.Assert;
import pages.LoginPage;
import pages.CheckboxPage;
import pages.DropdownPage;
import utils.BugReporter;

@Listeners(TestListener.class)
public class BugDemoTestNG extends TestNGBase {

    @Test(priority = 1, description = "Demo: Manual Bug Report - Critical Bug")
    public void testManualCriticalBugReport() {
        LoginPage loginPage = new LoginPage(driver);
        
        // Simulate a critical bug scenario
        loginPage.login("tomsmith", "SuperSecretPassword!");
        
        // Manual bug report for demonstration
        BugReporter.reportCriticalBug(
            "Login System Crash",
            "Application crashes when user attempts to login with special characters in password",
            "System should handle special characters gracefully",
            "Application throws NullPointerException and crashes",
            "1. Navigate to login page\n2. Enter username: tomsmith\n3. Enter password with special chars: P@ssw0rd!\n4. Click login button\n5. Observe system crash",
            driver
        );
        
        System.out.println("✅ Manual critical bug report demo completed!");
    }

    @Test(priority = 2, description = "Demo: Manual Bug Report - High Priority Bug")
    public void testManualHighBugReport() {
        CheckboxPage checkboxPage = new CheckboxPage(driver);
        checkboxPage.navigateToCheckboxes();
        
        // Manual bug report for functionality issue
        BugReporter.reportHighBug(
            "Checkbox Functionality Broken",
            "Multiple checkboxes can be selected when only one should be allowed",
            "Only one checkbox should be selectable at a time (radio button behavior)",
            "Multiple checkboxes can be selected simultaneously",
            "1. Navigate to checkboxes page\n2. Click checkbox 1\n3. Click checkbox 2\n4. Observe both are selected",
            driver
        );
        
        System.out.println("✅ Manual high priority bug report demo completed!");
    }

    @Test(priority = 3, description = "Demo: Intentional Test Failure for Auto Bug Report")
    public void testIntentionalFailureForBugReport() {
        LoginPage loginPage = new LoginPage(driver);
        
        // This test will intentionally fail to demonstrate auto bug reporting
        loginPage.login("tomsmith", "SuperSecretPassword!");
        
        // Intentional assertion failure
        String flashMessage = loginPage.getFlashMessage();
        
        // This assertion will fail intentionally to trigger bug report
        Assert.assertEquals(flashMessage, "This will fail intentionally", 
                           "This test is designed to fail to demonstrate auto bug reporting");
        
        System.out.println("This line should not be reached due to assertion failure");
    }

    @Test(priority = 4, description = "Demo: Data Validation Bug Report")
    public void testDataValidationBugReport() {
        LoginPage loginPage = new LoginPage(driver);
        
        // Test with invalid data
        loginPage.login("", ""); // Empty credentials
        
        // Manual bug report for data validation
        BugReporter.reportMediumBug(
            "Missing Input Validation",
            "System accepts empty username and password fields",
            "System should show validation error for empty required fields",
            "No validation error shown for empty username/password",
            "1. Navigate to login page\n2. Leave username field empty\n3. Leave password field empty\n4. Click login button\n5. Observe no validation error",
            driver
        );
        
        System.out.println("✅ Data validation bug report demo completed!");
    }

    @Test(priority = 5, description = "Demo: Security Bug Report")
    public void testSecurityBugReport() {
        LoginPage loginPage = new LoginPage(driver);
        
        // Test security scenario
        loginPage.login("admin", "admin");
        
        // Manual bug report for security issue
        BugReporter.reportCriticalBug(
            "Default Admin Credentials Active",
            "System allows login with default admin/admin credentials",
            "Default credentials should be disabled in production",
            "System accepts default admin/admin login credentials",
            "1. Navigate to login page\n2. Enter username: admin\n3. Enter password: admin\n4. Click login button\n5. Observe successful login with default credentials",
            driver
        );
        
        System.out.println("✅ Security bug report demo completed!");
    }

    @Test(priority = 6, description = "Demo: UI/UX Bug Report")
    public void testUIUXBugReport() {
        DropdownPage dropdownPage = new DropdownPage(driver);
        dropdownPage.navigateToDropdown();
        
        // Manual bug report for UI/UX issue
        BugReporter.reportLowBug(
            "Dropdown UI Inconsistency",
            "Dropdown arrow icon is misaligned and inconsistent with design guidelines",
            "Dropdown arrow should be properly aligned and follow design standards",
            "Arrow icon appears 2px off-center and uses wrong color (#666 instead of #333)",
            "1. Navigate to dropdown page\n2. Observe dropdown arrow alignment\n3. Compare with design mockups\n4. Note misalignment and color difference",
            driver
        );
        
        System.out.println("✅ UI/UX bug report demo completed!");
    }

    @Test(priority = 7, description = "Demo: Browser Compatibility Bug Report")
    public void testBrowserCompatibilityBugReport() {
        // Manual bug report for browser compatibility
        BugReporter.reportMediumBug(
            "Chrome Browser Compatibility Issue",
            "Hover effects don't work properly in Chrome version 138+",
            "Hover effects should work consistently across all supported browsers",
            "Hover effects are delayed by 2-3 seconds in Chrome 138+, work fine in Firefox",
            "1. Open application in Chrome 138+\n2. Navigate to hovers page\n3. Hover over images\n4. Observe delayed hover effect\n5. Compare with Firefox behavior",
            driver
        );
        
        System.out.println("✅ Browser compatibility bug report demo completed!");
    }
}
