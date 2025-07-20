package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import pages.*;
import utils.ScreenshotUtils;
import utils.AllureBugReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Comprehensive Test Suite for The Internet Herokuapp (JUnit 5)
 * Covers multiple UI components and interactions
 * 
 * @author QA Automation Engineer
 */
@Epic("The Internet Herokuapp Automation")
@Feature("Comprehensive UI Testing - JUnit")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ComprehensiveTest {
    
    private static WebDriver driver;
    private static DropdownPage dropdownPage;
    private static CheckboxPage checkboxPage;
    private static FileUploadPage fileUploadPage;
    private static AlertsPage alertsPage;
    private static HoverPage hoverPage;
    
    @BeforeAll
    @Step("Setup WebDriver and initialize pages")
    static void setUp() {
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
    
    @AfterAll
    @Step("Close WebDriver")
    static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    
    @AfterEach
    void afterEach(TestInfo testInfo) {
        // Take screenshot on failure (JUnit 5 doesn't have built-in failure detection like TestNG)
        // This is a simplified version - in real scenarios you'd use TestWatcher extension
        System.out.println("Completed test: " + testInfo.getDisplayName());
    }
    
    // ==================== DROPDOWN TESTS ====================
    
    @Test
    @Order(1)
    @Story("Dropdown Selection")
    @Description("Test dropdown selection functionality")
    @Severity(SeverityLevel.NORMAL)
    void testDropdownSelection() {
        dropdownPage.navigateToDropdown();
        
        // Test selecting Option 1
        dropdownPage.selectByVisibleText("Option 1");
        String selectedOption = dropdownPage.getSelectedOptionText();
        Assertions.assertEquals("Option 1", selectedOption, "Option 1 should be selected");
        
        // Test selecting Option 2
        dropdownPage.selectByVisibleText("Option 2");
        selectedOption = dropdownPage.getSelectedOptionText();
        Assertions.assertEquals("Option 2", selectedOption, "Option 2 should be selected");
        
        System.out.println("✅ Dropdown selection test passed!");
    }
    
    @Test
    @Order(2)
    @Story("Dropdown Validation")
    @Description("Test dropdown options availability")
    @Severity(SeverityLevel.MINOR)
    void testDropdownOptions() {
        dropdownPage.navigateToDropdown();
        
        // Verify dropdown has expected options
        Assertions.assertTrue(dropdownPage.isOptionAvailable("Option 1"), "Option 1 should be available");
        Assertions.assertTrue(dropdownPage.isOptionAvailable("Option 2"), "Option 2 should be available");
        
        System.out.println("✅ Dropdown options validation test passed!");
    }
    
    // ==================== CHECKBOX TESTS ====================
    
    @Test
    @Order(3)
    @Story("Checkbox Interaction")
    @Description("Test checkbox check/uncheck functionality")
    @Severity(SeverityLevel.NORMAL)
    void testCheckboxInteraction() {
        checkboxPage.navigateToCheckboxes();
        
        // Test checking checkbox 1
        if (!checkboxPage.isCheckbox1Selected()) {
            checkboxPage.clickCheckbox1();
        }
        Assertions.assertTrue(checkboxPage.isCheckbox1Selected(), "Checkbox 1 should be checked");
        
        // Test unchecking checkbox 1
        checkboxPage.clickCheckbox1();
        Assertions.assertFalse(checkboxPage.isCheckbox1Selected(), "Checkbox 1 should be unchecked");
        
        // Test checking checkbox 2
        if (!checkboxPage.isCheckbox2Selected()) {
            checkboxPage.clickCheckbox2();
        }
        Assertions.assertTrue(checkboxPage.isCheckbox2Selected(), "Checkbox 2 should be checked");
        
        System.out.println("✅ Checkbox interaction test passed!");
    }
    
    @Test
    @Order(4)
    @Story("Multiple Checkbox Selection")
    @Description("Test multiple checkbox selection")
    @Severity(SeverityLevel.NORMAL)
    void testMultipleCheckboxSelection() {
        checkboxPage.navigateToCheckboxes();
        
        // Check both checkboxes
        checkboxPage.selectAllCheckboxes();
        
        Assertions.assertTrue(checkboxPage.isCheckbox1Selected(), "Checkbox 1 should be checked");
        Assertions.assertTrue(checkboxPage.isCheckbox2Selected(), "Checkbox 2 should be checked");
        
        // Uncheck all checkboxes
        checkboxPage.unselectAllCheckboxes();
        
        Assertions.assertFalse(checkboxPage.isCheckbox1Selected(), "Checkbox 1 should be unchecked");
        Assertions.assertFalse(checkboxPage.isCheckbox2Selected(), "Checkbox 2 should be unchecked");
        
        System.out.println("✅ Multiple checkbox selection test passed!");
    }
    
    // ==================== FILE UPLOAD TESTS ====================
    
    @Test
    @Order(5)
    @Story("File Upload")
    @Description("Test file upload functionality")
    @Severity(SeverityLevel.CRITICAL)
    void testFileUpload() {
        fileUploadPage.navigateToFileUpload();
        
        // Create a test file
        String testFileName = "test-upload.txt";
        String testFilePath = fileUploadPage.createTestFile(testFileName, "This is a test file for upload automation.");
        
        // Upload the file
        fileUploadPage.uploadFile(testFilePath);
        
        // Verify upload success
        Assertions.assertTrue(fileUploadPage.isUploadSuccessful(), "File upload should be successful");
        Assertions.assertTrue(fileUploadPage.getUploadedFileName().contains(testFileName), 
                             "Uploaded file name should contain: " + testFileName);
        
        System.out.println("✅ File upload test passed!");
        System.out.println("Uploaded file: " + fileUploadPage.getUploadedFileName());
    }
    
    @Test
    @Order(6)
    @Story("File Upload Validation")
    @Description("Test file upload with different file types")
    @Severity(SeverityLevel.NORMAL)
    void testFileUploadValidation() {
        fileUploadPage.navigateToFileUpload();
        
        // Test with image file
        String imageFileName = "test-image.png";
        String imageFilePath = fileUploadPage.createTestFile(imageFileName, "PNG image content simulation");
        
        fileUploadPage.uploadFile(imageFilePath);
        
        Assertions.assertTrue(fileUploadPage.isUploadSuccessful(), "Image file upload should be successful");
        
        System.out.println("✅ File upload validation test passed!");
    }
    
    // ==================== JAVASCRIPT ALERTS TESTS ====================
    
    @Test
    @Order(7)
    @Story("JavaScript Alerts")
    @Description("Test JavaScript alert handling")
    @Severity(SeverityLevel.NORMAL)
    void testJavaScriptAlert() {
        alertsPage.navigateToAlerts();
        
        // Test JS Alert
        alertsPage.clickJSAlert();
        String alertText = alertsPage.getAlertText();
        Assertions.assertEquals("I am a JS Alert", alertText, "Alert text should match expected");
        
        alertsPage.acceptAlert();
        String resultText = alertsPage.getResultText();
        Assertions.assertEquals("You successfully clicked an alert", resultText, "Result text should confirm alert was clicked");
        
        System.out.println("✅ JavaScript Alert test passed!");
        System.out.println("Alert text: " + alertText);
    }
    
    @Test
    @Order(8)
    @Story("JavaScript Confirm")
    @Description("Test JavaScript confirm dialog handling")
    @Severity(SeverityLevel.NORMAL)
    void testJavaScriptConfirm() {
        alertsPage.navigateToAlerts();
        
        // Test JS Confirm - Accept
        alertsPage.clickJSConfirm();
        String confirmText = alertsPage.getAlertText();
        Assertions.assertEquals("I am a JS Confirm", confirmText, "Confirm text should match expected");
        
        alertsPage.acceptAlert();
        String resultText = alertsPage.getResultText();
        Assertions.assertEquals("You clicked: Ok", resultText, "Result should show OK was clicked");
        
        // Test JS Confirm - Dismiss
        alertsPage.clickJSConfirm();
        alertsPage.dismissAlert();
        resultText = alertsPage.getResultText();
        Assertions.assertEquals("You clicked: Cancel", resultText, "Result should show Cancel was clicked");
        
        System.out.println("✅ JavaScript Confirm test passed!");
    }
    
    @Test
    @Order(9)
    @Story("JavaScript Prompt")
    @Description("Test JavaScript prompt dialog handling")
    @Severity(SeverityLevel.NORMAL)
    void testJavaScriptPrompt() {
        alertsPage.navigateToAlerts();
        
        String testInput = "Hello Automation!";
        
        // Test JS Prompt with input
        alertsPage.clickJSPrompt();
        String promptText = alertsPage.getAlertText();
        Assertions.assertEquals("I am a JS prompt", promptText, "Prompt text should match expected");
        
        alertsPage.sendTextToAlert(testInput);
        
        String resultText = alertsPage.getResultText();
        Assertions.assertEquals("You entered: " + testInput, resultText, "Result should show entered text");
        
        System.out.println("✅ JavaScript Prompt test passed!");
        System.out.println("Entered text: " + testInput);
    }
    
    // ==================== HOVER TESTS ====================
    
    @Test
    @Order(10)
    @Story("Hover Actions")
    @Description("Test mouse hover functionality")
    @Severity(SeverityLevel.NORMAL)
    void testHoverActions() {
        hoverPage.navigateToHovers();
        
        // Test hover on first figure (index 0)
        hoverPage.hoverOverImage(0);
        Assertions.assertTrue(hoverPage.isCaptionVisible(0), "Figure 1 caption should be visible on hover");
        String caption1 = hoverPage.getCaptionText(0);
        Assertions.assertTrue(caption1.contains("user1"), "Caption should contain user1");
        
        // Test hover on second figure (index 1)
        hoverPage.hoverOverImage(1);
        Assertions.assertTrue(hoverPage.isCaptionVisible(1), "Figure 2 caption should be visible on hover");
        String caption2 = hoverPage.getCaptionText(1);
        Assertions.assertTrue(caption2.contains("user2"), "Caption should contain user2");
        
        // Test hover on third figure (index 2)
        hoverPage.hoverOverImage(2);
        Assertions.assertTrue(hoverPage.isCaptionVisible(2), "Figure 3 caption should be visible on hover");
        String caption3 = hoverPage.getCaptionText(2);
        Assertions.assertTrue(caption3.contains("user3"), "Caption should contain user3");
        
        System.out.println("✅ Hover actions test passed!");
        System.out.println("Figure captions: " + caption1 + ", " + caption2 + ", " + caption3);
    }
    
    @Test
    @Order(11)
    @Story("Hover Profile Links")
    @Description("Test hover profile links functionality")
    @Severity(SeverityLevel.MINOR)
    void testHoverProfileLinks() {
        hoverPage.navigateToHovers();
        
        // Test clicking profile links after hover
        hoverPage.hoverOverImage(0);
        Assertions.assertTrue(hoverPage.isCaptionVisible(0), "Caption should be visible after hover");
        
        // Click profile link and verify navigation
        hoverPage.clickUserLink(0);
        Assertions.assertTrue(hoverPage.isUserProfilePageLoaded(), "Should navigate to user profile page");
        
        System.out.println("✅ Hover profile links test passed!");
        System.out.println("Profile URL: " + driver.getCurrentUrl());
    }
}
