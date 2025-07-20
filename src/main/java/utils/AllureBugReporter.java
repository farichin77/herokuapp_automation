package utils;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Issue;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * AllureBugReporter - Advanced bug reporting system integrated with Allure
 * Provides comprehensive bug reporting with screenshots, severity levels, and detailed information
 */
public class AllureBugReporter {
    
    private static int bugCounter = 0;
    
    /**
     * Report a critical bug with Allure integration
     */
    @Step("🔴 CRITICAL BUG: {testName}")
    @Severity(SeverityLevel.CRITICAL)
    public static void reportCriticalBug(String testName, String description, 
                                       String expectedResult, String actualResult, 
                                       String stepsToReproduce, WebDriver driver) {
        bugCounter++;
        
        // Add bug details to Allure report
        Allure.parameter("Bug ID", "BUG-" + String.format("%03d", bugCounter));
        Allure.parameter("Severity", "CRITICAL");
        Allure.parameter("Test Name", testName);
        Allure.parameter("Description", description);
        Allure.parameter("Expected Result", expectedResult);
        Allure.parameter("Actual Result", actualResult);
        Allure.parameter("Timestamp", getCurrentTimestamp());
        
        // Add steps to reproduce
        Allure.addAttachment("Steps to Reproduce", "text/plain", stepsToReproduce);
        
        // Add environment info
        if (driver != null) {
            Allure.parameter("Current URL", driver.getCurrentUrl());
            Allure.parameter("Browser", driver.getClass().getSimpleName());
            
            // Take and attach screenshot
            attachScreenshot(driver, testName);
        }
        
        // Add to Allure description
        String bugDescription = String.format(
            "🔴 **CRITICAL BUG DETECTED**\n\n" +
            "**Description:** %s\n\n" +
            "**Expected:** %s\n\n" +
            "**Actual:** %s\n\n" +
            "**Impact:** Critical system functionality affected",
            description, expectedResult, actualResult
        );
        
        Allure.description(bugDescription);
        Allure.issue("CRITICAL-" + bugCounter, testName);
        
        System.out.println("🔴 CRITICAL BUG REPORTED: " + testName);
    }
    
    /**
     * Report a high priority bug with Allure integration
     */
    @Step("🟠 HIGH PRIORITY BUG: {testName}")
    @Severity(SeverityLevel.NORMAL)
    public static void reportHighBug(String testName, String description, 
                                   String expectedResult, String actualResult, 
                                   String stepsToReproduce, WebDriver driver) {
        bugCounter++;
        
        Allure.parameter("Bug ID", "BUG-" + String.format("%03d", bugCounter));
        Allure.parameter("Severity", "HIGH");
        Allure.parameter("Test Name", testName);
        Allure.parameter("Description", description);
        Allure.parameter("Expected Result", expectedResult);
        Allure.parameter("Actual Result", actualResult);
        Allure.parameter("Timestamp", getCurrentTimestamp());
        
        Allure.addAttachment("Steps to Reproduce", "text/plain", stepsToReproduce);
        
        if (driver != null) {
            Allure.parameter("Current URL", driver.getCurrentUrl());
            Allure.parameter("Browser", driver.getClass().getSimpleName());
            attachScreenshot(driver, testName);
        }
        
        String bugDescription = String.format(
            "🟠 **HIGH PRIORITY BUG**\n\n" +
            "**Description:** %s\n\n" +
            "**Expected:** %s\n\n" +
            "**Actual:** %s\n\n" +
            "**Impact:** Major functionality affected, requires immediate attention",
            description, expectedResult, actualResult
        );
        
        Allure.description(bugDescription);
        Allure.issue("HIGH-" + bugCounter, testName);
        
        System.out.println("🟠 HIGH PRIORITY BUG REPORTED: " + testName);
    }
    
    /**
     * Report a medium priority bug with Allure integration
     */
    @Step("🟡 MEDIUM PRIORITY BUG: {testName}")
    @Severity(SeverityLevel.MINOR)
    public static void reportMediumBug(String testName, String description, 
                                     String expectedResult, String actualResult, 
                                     String stepsToReproduce, WebDriver driver) {
        bugCounter++;
        
        Allure.parameter("Bug ID", "BUG-" + String.format("%03d", bugCounter));
        Allure.parameter("Severity", "MEDIUM");
        Allure.parameter("Test Name", testName);
        Allure.parameter("Description", description);
        Allure.parameter("Expected Result", expectedResult);
        Allure.parameter("Actual Result", actualResult);
        Allure.parameter("Timestamp", getCurrentTimestamp());
        
        Allure.addAttachment("Steps to Reproduce", "text/plain", stepsToReproduce);
        
        if (driver != null) {
            Allure.parameter("Current URL", driver.getCurrentUrl());
            Allure.parameter("Browser", driver.getClass().getSimpleName());
            attachScreenshot(driver, testName);
        }
        
        String bugDescription = String.format(
            "🟡 **MEDIUM PRIORITY BUG**\n\n" +
            "**Description:** %s\n\n" +
            "**Expected:** %s\n\n" +
            "**Actual:** %s\n\n" +
            "**Impact:** Minor functionality affected, can be addressed in next release",
            description, expectedResult, actualResult
        );
        
        Allure.description(bugDescription);
        Allure.issue("MEDIUM-" + bugCounter, testName);
        
        System.out.println("🟡 MEDIUM PRIORITY BUG REPORTED: " + testName);
    }
    
    /**
     * Report a low priority bug with Allure integration
     */
    @Step("🟢 LOW PRIORITY BUG: {testName}")
    @Severity(SeverityLevel.TRIVIAL)
    public static void reportLowBug(String testName, String description, 
                                  String expectedResult, String actualResult, 
                                  String stepsToReproduce, WebDriver driver) {
        bugCounter++;
        
        Allure.parameter("Bug ID", "BUG-" + String.format("%03d", bugCounter));
        Allure.parameter("Severity", "LOW");
        Allure.parameter("Test Name", testName);
        Allure.parameter("Description", description);
        Allure.parameter("Expected Result", expectedResult);
        Allure.parameter("Actual Result", actualResult);
        Allure.parameter("Timestamp", getCurrentTimestamp());
        
        Allure.addAttachment("Steps to Reproduce", "text/plain", stepsToReproduce);
        
        if (driver != null) {
            Allure.parameter("Current URL", driver.getCurrentUrl());
            Allure.parameter("Browser", driver.getClass().getSimpleName());
            attachScreenshot(driver, testName);
        }
        
        String bugDescription = String.format(
            "🟢 **LOW PRIORITY BUG**\n\n" +
            "**Description:** %s\n\n" +
            "**Expected:** %s\n\n" +
            "**Actual:** %s\n\n" +
            "**Impact:** Cosmetic issue, low priority for fixing",
            description, expectedResult, actualResult
        );
        
        Allure.description(bugDescription);
        Allure.issue("LOW-" + bugCounter, testName);
        
        System.out.println("🟢 LOW PRIORITY BUG REPORTED: " + testName);
    }
    
    /**
     * Report a performance issue with Allure integration
     */
    @Step("⚡ PERFORMANCE ISSUE: {testName}")
    @Severity(SeverityLevel.NORMAL)
    public static void reportPerformanceIssue(String testName, String description, 
                                            String expectedPerformance, String actualPerformance, 
                                            String measurementDetails, WebDriver driver) {
        bugCounter++;
        
        Allure.parameter("Bug ID", "PERF-" + String.format("%03d", bugCounter));
        Allure.parameter("Issue Type", "PERFORMANCE");
        Allure.parameter("Test Name", testName);
        Allure.parameter("Description", description);
        Allure.parameter("Expected Performance", expectedPerformance);
        Allure.parameter("Actual Performance", actualPerformance);
        Allure.parameter("Timestamp", getCurrentTimestamp());
        
        Allure.addAttachment("Performance Measurement Details", "text/plain", measurementDetails);
        
        if (driver != null) {
            Allure.parameter("Current URL", driver.getCurrentUrl());
            Allure.parameter("Browser", driver.getClass().getSimpleName());
            attachScreenshot(driver, testName);
        }
        
        String bugDescription = String.format(
            "⚡ **PERFORMANCE ISSUE**\n\n" +
            "**Description:** %s\n\n" +
            "**Expected Performance:** %s\n\n" +
            "**Actual Performance:** %s\n\n" +
            "**Impact:** Performance degradation detected",
            description, expectedPerformance, actualPerformance
        );
        
        Allure.description(bugDescription);
        Allure.issue("PERF-" + bugCounter, testName);
        
        System.out.println("⚡ PERFORMANCE ISSUE REPORTED: " + testName);
    }
    
    /**
     * Attach screenshot to Allure report
     */
    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] attachScreenshot(WebDriver driver, String testName) {
        if (driver != null) {
            try {
                TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
                byte[] screenshot = takesScreenshot.getScreenshotAs(OutputType.BYTES);
                
                // Also save to file system for backup
                ScreenshotUtils.takeScreenshotOnFailure(driver, testName + "_BUG");
                
                return screenshot;
            } catch (Exception e) {
                System.err.println("Failed to take screenshot: " + e.getMessage());
                return new byte[0];
            }
        }
        return new byte[0];
    }
    
    /**
     * Add custom attachment to Allure report
     */
    @Step("📎 Adding attachment: {attachmentName}")
    public static void addAttachment(String attachmentName, String content) {
        Allure.addAttachment(attachmentName, "text/plain", content);
    }
    
    /**
     * Add JSON attachment to Allure report
     */
    @Step("📋 Adding JSON data: {attachmentName}")
    public static void addJsonAttachment(String attachmentName, String jsonContent) {
        Allure.addAttachment(attachmentName, "application/json", jsonContent);
    }
    
    /**
     * Mark test as known issue
     */
    @Step("⚠️ Marking as known issue: {issueId}")
    public static void markAsKnownIssue(String issueId, String description) {
        Allure.issue(issueId, description);
        Allure.parameter("Known Issue", issueId);
        System.out.println("⚠️ MARKED AS KNOWN ISSUE: " + issueId);
    }
    
    /**
     * Add environment information to Allure report
     */
    @Step("🌐 Adding environment info")
    public static void addEnvironmentInfo(WebDriver driver) {
        if (driver != null) {
            Allure.parameter("Browser", driver.getClass().getSimpleName());
            Allure.parameter("Current URL", driver.getCurrentUrl());
            Allure.parameter("Page Title", driver.getTitle());
            Allure.parameter("Window Size", driver.manage().window().getSize().toString());
        }
        
        Allure.parameter("OS", System.getProperty("os.name"));
        Allure.parameter("Java Version", System.getProperty("java.version"));
        Allure.parameter("Test Environment", "The Internet Herokuapp");
        Allure.parameter("Timestamp", getCurrentTimestamp());
    }
    
    /**
     * Get current timestamp
     */
    private static String getCurrentTimestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
    
    /**
     * Get total bug count
     */
    public static int getBugCount() {
        return bugCounter;
    }
    
    /**
     * Reset bug counter (for new test sessions)
     */
    public static void resetBugCounter() {
        bugCounter = 0;
        System.out.println("🔄 Bug counter reset");
    }
    
    /**
     * Create a comprehensive bug report step
     */
    @Step("📋 Creating comprehensive bug report for: {testName}")
    public static void createComprehensiveBugReport(String testName, String severity, 
                                                  String description, String expectedResult, 
                                                  String actualResult, String stepsToReproduce, 
                                                  WebDriver driver) {
        switch (severity.toUpperCase()) {
            case "CRITICAL":
                reportCriticalBug(testName, description, expectedResult, actualResult, stepsToReproduce, driver);
                break;
            case "HIGH":
                reportHighBug(testName, description, expectedResult, actualResult, stepsToReproduce, driver);
                break;
            case "MEDIUM":
                reportMediumBug(testName, description, expectedResult, actualResult, stepsToReproduce, driver);
                break;
            case "LOW":
                reportLowBug(testName, description, expectedResult, actualResult, stepsToReproduce, driver);
                break;
            default:
                reportMediumBug(testName, description, expectedResult, actualResult, stepsToReproduce, driver);
        }
    }
}
