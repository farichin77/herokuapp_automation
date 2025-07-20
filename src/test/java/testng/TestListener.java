package testng;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ScreenshotUtils;
import utils.AllureBugReporter;
import io.qameta.allure.Allure;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Starting test: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("✅ Test passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("❌ TEST FAILED: " + result.getMethod().getMethodName());
        System.out.println("Error: " + result.getThrowable().getMessage());
        
        // Take screenshot on failure
        Object testClass = result.getInstance();
        if (testClass instanceof TestNGBase) {
            TestNGBase base = (TestNGBase) testClass;
            if (base.driver != null) {
                String screenshotPath = ScreenshotUtils.takeScreenshotOnFailure(
                    base.driver, 
                    result.getMethod().getMethodName()
                );
                System.out.println("📸 Screenshot saved: " + screenshotPath);
                
                // Create automatic bug report with Allure
                String errorMessage = result.getThrowable().getMessage();
                String stackTrace = result.getThrowable().toString();
                
                // Add environment info to Allure
                AllureBugReporter.addEnvironmentInfo(base.driver);
                
                // Report bug with appropriate severity based on exception type
                String severity = determineSeverity(result.getThrowable());
                
                AllureBugReporter.createComprehensiveBugReport(
                    result.getMethod().getMethodName(),
                    severity,
                    "Automated test failure: " + errorMessage,
                    "Test should pass without errors",
                    "Test failed with exception: " + errorMessage,
                    "1. Run test: " + result.getMethod().getMethodName() + "\n2. Exception details:\n" + stackTrace,
                    base.driver
                );
                
                // Add stack trace as attachment
                AllureBugReporter.addAttachment("Stack Trace", stackTrace);
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("⏭️ Test skipped: " + result.getMethod().getMethodName());
    }
    
    @Override
    public void onFinish(ITestContext context) {
        System.out.println("🏁 All tests completed!");
        
        // Add final summary to Allure
        int totalBugs = AllureBugReporter.getBugCount();
        if (totalBugs > 0) {
            System.out.println("📋 Total bugs reported to Allure: " + totalBugs);
        } else {
            System.out.println("✅ No bugs reported - All tests passed!");
        }
    }
    
    /**
     * Determine bug severity based on exception type
     */
    private String determineSeverity(Throwable throwable) {
        if (throwable == null) {
            return "MEDIUM";
        }
        
        String exceptionName = throwable.getClass().getSimpleName().toLowerCase();
        String message = throwable.getMessage() != null ? throwable.getMessage().toLowerCase() : "";
        
        // Critical issues
        if (exceptionName.contains("nullpointer") || 
            exceptionName.contains("outofmemory") ||
            exceptionName.contains("security") ||
            message.contains("crash") ||
            message.contains("system")) {
            return "CRITICAL";
        }
        
        // High priority issues
        if (exceptionName.contains("assertion") ||
            exceptionName.contains("timeout") ||
            exceptionName.contains("nosuchelement") ||
            message.contains("login") ||
            message.contains("payment")) {
            return "HIGH";
        }
        
        // Low priority issues
        if (exceptionName.contains("element") ||
            message.contains("display") ||
            message.contains("text") ||
            message.contains("color")) {
            return "LOW";
        }
        
        // Default to medium
        return "MEDIUM";
    }
}
