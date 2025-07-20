package testng;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;
import utils.ScreenshotUtils;
import utils.AllureBugReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Comprehensive Test Suite for The Internet Herokuapp
 * Covers multiple UI components and interactions
 * 
 * @author QA Automation Engineer
 */
@Epic("The Internet Herokuapp Automation")
@Feature("Comprehensive UI Testing")
public class ComprehensiveTestNG {
    
    private WebDriver driver;
    private DropdownPage dropdownPage;
    private CheckboxPage checkboxPage;
    private FileUploadPage fileUploadPage;
    private AlertsPage alertsPage;
    private HoverPage hoverPage;
    
    @BeforeClass
    @Step("Setup WebDriver and initialize pages")
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--start-maximized");
        
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        
        // Initialize page objects
        dropdownPage = new DropdownPage(driver);
        checkboxPage = new CheckboxPage(driver);
        fileUploadPage = new FileUploadPage(driver);
        alertsPage = new AlertsPage(driver);
        hoverPage = new HoverPage(driver);
    }
    
    @AfterClass
    @Step("Close WebDriver")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    
    @AfterMethod
    public void afterMethod(org.testng.ITestResult result) {
        if (result.getStatus() == org.testng.ITestResult.FAILURE) {
            String testName = result.getMethod().getMethodName();
            ScreenshotUtils.takeScreenshot(driver, testName);
            
            // Auto bug reporting for failed tests
            AllureBugReporter.reportHighBug(
                testName + " - Test Failure",
                "Test failed during execution: " + result.getThrowable().getMessage(),
                "Test should pass successfully",
                "Test failed with exception: " + result.getThrowable().getClass().getSimpleName(),
                "1. Execute test: " + testName + "\n2. Observe failure\n3. Check logs for details",
                driver
            );
        }
    }
    
    // ==================== DROPDOWN TESTS ====================
    
    @Test(priority = 1)
    @Story("Dropdown Selection")
    @Description("Test dropdown selection functionality")
    @Severity(SeverityLevel.NORMAL)
    public void testDropdownSelection() {
        dropdownPage.navigateToDropdown();
        
        // Test selecting Option 1
        dropdownPage.selectByVisibleText("Option 1");
        String selectedOption = dropdownPage.getSelectedOptionText();
        Assert.assertEquals(selectedOption, "Option 1", "Option 1 should be selected");
        
        // Test selecting Option 2
        dropdownPage.selectByVisibleText("Option 2");
        selectedOption = dropdownPage.getSelectedOptionText();
        Assert.assertEquals(selectedOption, "Option 2", "Option 2 should be selected");
        
        System.out.println("✅ Dropdown selection test passed!");
    }
    
    @Test(priority = 2)
    @Story("Dropdown Validation")
    @Description("Test dropdown options availability")
    @Severity(SeverityLevel.MINOR)
    public void testDropdownOptions() {
        dropdownPage.navigateToDropdown();
        
        // Verify dropdown has expected options
        Assert.assertTrue(dropdownPage.isOptionAvailable("Option 1"), "Option 1 should be available");
        Assert.assertTrue(dropdownPage.isOptionAvailable("Option 2"), "Option 2 should be available");
        
        System.out.println("✅ Dropdown options validation test passed!");
    }
    
    // ==================== CHECKBOX TESTS ====================
    
    @Test(priority = 3)
    @Story("Checkbox Interaction")
    @Description("Test checkbox check/uncheck functionality")
    @Severity(SeverityLevel.NORMAL)
    public void testCheckboxInteraction() {
        checkboxPage.navigateToCheckboxes();
        
        // Test checking checkbox 1
        if (!checkboxPage.isCheckbox1Selected()) {
            checkboxPage.clickCheckbox1();
        }
        Assert.assertTrue(checkboxPage.isCheckbox1Selected(), "Checkbox 1 should be checked");
        
        // Test unchecking checkbox 1
        checkboxPage.clickCheckbox1();
        Assert.assertFalse(checkboxPage.isCheckbox1Selected(), "Checkbox 1 should be unchecked");
        
        // Test checking checkbox 2
        if (!checkboxPage.isCheckbox2Selected()) {
            checkboxPage.clickCheckbox2();
        }
        Assert.assertTrue(checkboxPage.isCheckbox2Selected(), "Checkbox 2 should be checked");
        
        System.out.println("✅ Checkbox interaction test passed!");
    }
    
    @Test(priority = 4)
    @Story("Multiple Checkbox Selection")
    @Description("Test multiple checkbox selection")
    @Severity(SeverityLevel.NORMAL)
    public void testMultipleCheckboxSelection() {
        checkboxPage.navigateToCheckboxes();
        
        // Check both checkboxes
        checkboxPage.selectAllCheckboxes();
        
        Assert.assertTrue(checkboxPage.isCheckbox1Selected(), "Checkbox 1 should be checked");
        Assert.assertTrue(checkboxPage.isCheckbox2Selected(), "Checkbox 2 should be checked");
        
        // Uncheck all checkboxes
        checkboxPage.unselectAllCheckboxes();
        
        Assert.assertFalse(checkboxPage.isCheckbox1Selected(), "Checkbox 1 should be unchecked");
        Assert.assertFalse(checkboxPage.isCheckbox2Selected(), "Checkbox 2 should be unchecked");
        
        System.out.println("✅ Multiple checkbox selection test passed!");
    }
    
    // ==================== FILE UPLOAD TESTS ====================
    
    @Test(priority = 5)
    @Story("File Upload")
    @Description("Test file upload functionality")
    @Severity(SeverityLevel.CRITICAL)
    public void testFileUpload() {
        fileUploadPage.navigateToFileUpload();
        
        // Create a test file
        String testFileName = "test-upload.txt";
        String testFilePath = fileUploadPage.createTestFile(testFileName, "This is a test file for upload automation.");
        
        // Upload the file
        fileUploadPage.uploadFile(testFilePath);
        
        // Verify upload success
        Assert.assertTrue(fileUploadPage.isUploadSuccessful(), "File upload should be successful");
        Assert.assertTrue(fileUploadPage.getUploadedFileName().contains(testFileName), 
                         "Uploaded file name should contain: " + testFileName);
        
        System.out.println("✅ File upload test passed!");
        System.out.println("Uploaded file: " + fileUploadPage.getUploadedFileName());
    }
    
    @Test(priority = 6)
    @Story("File Upload Validation")
    @Description("Test file upload with different file types")
    @Severity(SeverityLevel.NORMAL)
    public void testFileUploadValidation() {
        fileUploadPage.navigateToFileUpload();
        
        // Test with image file
        String imageFileName = "test-image.png";
        String imageFilePath = fileUploadPage.createTestFile(imageFileName, "PNG image content simulation");
        
        fileUploadPage.uploadFile(imageFilePath);
        
        Assert.assertTrue(fileUploadPage.isUploadSuccessful(), "Image file upload should be successful");
        
        System.out.println("✅ File upload validation test passed!");
    }
    
    // ==================== JAVASCRIPT ALERTS TESTS ====================
    
    @Test(priority = 7)
    @Story("JavaScript Alerts")
    @Description("Test JavaScript alert handling")
    @Severity(SeverityLevel.NORMAL)
    public void testJavaScriptAlert() {
        alertsPage.navigateToAlerts();
        
        // Test JS Alert
        alertsPage.clickJSAlert();
        String alertText = alertsPage.getAlertText();
        Assert.assertEquals(alertText, "I am a JS Alert", "Alert text should match expected");
        
        alertsPage.acceptAlert();
        String resultText = alertsPage.getResultText();
        Assert.assertEquals(resultText, "You successfully clicked an alert", "Result text should confirm alert was clicked");
        
        System.out.println("✅ JavaScript Alert test passed!");
        System.out.println("Alert text: " + alertText);
    }
    
    @Test(priority = 8)
    @Story("JavaScript Confirm")
    @Description("Test JavaScript confirm dialog handling")
    @Severity(SeverityLevel.NORMAL)
    public void testJavaScriptConfirm() {
        alertsPage.navigateToAlerts();
        
        // Test JS Confirm - Accept
        alertsPage.clickJSConfirm();
        String confirmText = alertsPage.getAlertText();
        Assert.assertEquals(confirmText, "I am a JS Confirm", "Confirm text should match expected");
        
        alertsPage.acceptAlert();
        String resultText = alertsPage.getResultText();
        Assert.assertEquals(resultText, "You clicked: Ok", "Result should show OK was clicked");
        
        // Test JS Confirm - Dismiss
        alertsPage.clickJSConfirm();
        alertsPage.dismissAlert();
        resultText = alertsPage.getResultText();
        Assert.assertEquals(resultText, "You clicked: Cancel", "Result should show Cancel was clicked");
        
        System.out.println("✅ JavaScript Confirm test passed!");
    }
    
    @Test(priority = 9)
    @Story("JavaScript Prompt")
    @Description("Test JavaScript prompt dialog handling")
    @Severity(SeverityLevel.NORMAL)
    public void testJavaScriptPrompt() {
        alertsPage.navigateToAlerts();
        
        String testInput = "Hello Automation!";
        
        // Test JS Prompt with input
        alertsPage.clickJSPrompt();
        String promptText = alertsPage.getAlertText();
        Assert.assertEquals(promptText, "I am a JS prompt", "Prompt text should match expected");
        
        alertsPage.sendTextToAlert(testInput);
        
        String resultText = alertsPage.getResultText();
        Assert.assertEquals(resultText, "You entered: " + testInput, "Result should show entered text");
        
        System.out.println("✅ JavaScript Prompt test passed!");
        System.out.println("Entered text: " + testInput);
    }
    
    // ==================== HOVER TESTS ====================
    
    @Test(priority = 10)
    @Story("Hover Actions")
    @Description("Test mouse hover functionality")
    @Severity(SeverityLevel.NORMAL)
    public void testHoverActions() {
        hoverPage.navigateToHovers();
        
        // Test hover on first figure (index 0)
        hoverPage.hoverOverImage(0);
        Assert.assertTrue(hoverPage.isCaptionVisible(0), "Figure 1 caption should be visible on hover");
        String caption1 = hoverPage.getCaptionText(0);
        Assert.assertTrue(caption1.contains("user1"), "Caption should contain user1");
        
        // Test hover on second figure (index 1)
        hoverPage.hoverOverImage(1);
        Assert.assertTrue(hoverPage.isCaptionVisible(1), "Figure 2 caption should be visible on hover");
        String caption2 = hoverPage.getCaptionText(1);
        Assert.assertTrue(caption2.contains("user2"), "Caption should contain user2");
        
        // Test hover on third figure (index 2)
        hoverPage.hoverOverImage(2);
        Assert.assertTrue(hoverPage.isCaptionVisible(2), "Figure 3 caption should be visible on hover");
        String caption3 = hoverPage.getCaptionText(2);
        Assert.assertTrue(caption3.contains("user3"), "Caption should contain user3");
        
        System.out.println("✅ Hover actions test passed!");
        System.out.println("Figure captions: " + caption1 + ", " + caption2 + ", " + caption3);
    }
    
    @Test(priority = 11)
    @Story("Hover Profile Links")
    @Description("Test hover profile links functionality")
    @Severity(SeverityLevel.MINOR)
    public void testHoverProfileLinks() {
        hoverPage.navigateToHovers();
        
        // Test clicking profile links after hover
        hoverPage.hoverOverImage(0);
        Assert.assertTrue(hoverPage.isCaptionVisible(0), "Caption should be visible after hover");
        
        // Click profile link and verify navigation
        hoverPage.clickUserLink(0);
        Assert.assertTrue(hoverPage.isUserProfilePageLoaded(), "Should navigate to user profile page");
        
        System.out.println("✅ Hover profile links test passed!");
        System.out.println("Profile URL: " + driver.getCurrentUrl());
    }
}
