package utils;

import org.openqa.selenium.WebDriver;

public class ManualBugReportGenerator {
    
    public static void main(String[] args) {
        System.out.println("🐛 Manual Bug Report Generator");
        System.out.println("===============================");
        
        // Clear any existing bug reports
        BugReporter.clearBugReports();
        
        // Generate sample bug reports for demonstration
        generateSampleBugReports();
        
        // Generate the HTML report
        BugReporter.generateBugReport();
        
        System.out.println("\n✅ Manual bug report generation completed!");
        System.out.println("📋 Check the 'bug-reports' folder for the generated HTML report.");
    }
    
    private static void generateSampleBugReports() {
        // Critical Bug Example
        BugReporter.reportCriticalBug(
            "System Crash on Login",
            "Application crashes completely when user enters username longer than 50 characters",
            "System should handle long usernames gracefully with proper validation",
            "Application throws OutOfMemoryException and crashes",
            "1. Navigate to login page\n2. Enter username with 51+ characters\n3. Enter any password\n4. Click login button\n5. Observe system crash",
            null
        );
        
        // High Priority Bug Example
        BugReporter.reportHighBug(
            "Payment Processing Failure",
            "Payment transactions fail silently without error notification to user",
            "Failed payments should show clear error message and retry option",
            "Payment fails but user sees 'Processing...' indefinitely",
            "1. Add items to cart\n2. Proceed to checkout\n3. Enter invalid credit card\n4. Submit payment\n5. Observe endless processing state",
            null
        );
        
        // Medium Priority Bug Example
        BugReporter.reportMediumBug(
            "Search Results Pagination Issue",
            "Search results pagination shows incorrect page numbers",
            "Pagination should show correct current page and total pages",
            "Shows 'Page 1 of 0' when there are actually 5 pages of results",
            "1. Search for 'automation'\n2. Observe search results\n3. Check pagination at bottom\n4. Note incorrect page count display",
            null
        );
        
        // Low Priority Bug Example
        BugReporter.reportLowBug(
            "Footer Copyright Year Outdated",
            "Footer shows copyright year 2022 instead of current year",
            "Copyright year should automatically update to current year",
            "Footer displays '© 2022 Company Name' instead of '© 2025 Company Name'",
            "1. Scroll to bottom of any page\n2. Observe footer copyright year\n3. Note it shows 2022 instead of 2025",
            null
        );
        
        // UI/UX Bug Example
        BugReporter.reportMediumBug(
            "Mobile Responsive Design Issue",
            "Navigation menu overlaps with content on mobile devices",
            "Navigation should be properly positioned and not overlap content",
            "Menu items cover the main content area on screens smaller than 768px",
            "1. Open application on mobile device or resize browser to <768px\n2. Open navigation menu\n3. Observe menu overlapping main content",
            null
        );
        
        // Performance Bug Example
        BugReporter.reportHighBug(
            "Page Load Performance Degradation",
            "Homepage takes 15+ seconds to load completely",
            "Homepage should load within 3 seconds for optimal user experience",
            "Homepage consistently takes 15-20 seconds to fully load all elements",
            "1. Clear browser cache\n2. Navigate to homepage\n3. Measure load time using browser dev tools\n4. Observe slow loading of images and scripts",
            null
        );
        
        // Security Bug Example
        BugReporter.reportCriticalBug(
            "SQL Injection Vulnerability",
            "Search functionality is vulnerable to SQL injection attacks",
            "All user inputs should be properly sanitized to prevent SQL injection",
            "Entering SQL commands in search box executes them against database",
            "1. Navigate to search page\n2. Enter: ' OR '1'='1' --\n3. Submit search\n4. Observe unauthorized data access",
            null
        );
        
        // Data Integrity Bug Example
        BugReporter.reportHighBug(
            "User Profile Data Loss",
            "User profile information is lost after session timeout",
            "User data should persist across sessions and be recoverable",
            "All profile changes are lost when session expires, requiring re-entry",
            "1. Login to user account\n2. Update profile information\n3. Wait for session timeout (30 minutes)\n4. Login again\n5. Observe profile data is reset to previous state",
            null
        );
        
        System.out.println("📝 Generated " + BugReporter.getBugCount() + " sample bug reports");
    }
    
    // Helper method to create bug report with custom details
    public static void createCustomBugReport(String testName, String severity, 
                                           String description, String expectedResult, 
                                           String actualResult, String steps) {
        BugReporter.reportBug(testName, severity, description, expectedResult, actualResult, steps, null);
    }
    
    // Helper method to create bug report from test failure
    public static void createBugReportFromTestFailure(String testName, Exception exception, WebDriver driver) {
        String errorMessage = exception.getMessage();
        String stackTrace = java.util.Arrays.toString(exception.getStackTrace());
        
        BugReporter.reportHighBug(
            testName,
            "Automated test failure: " + errorMessage,
            "Test should pass without errors",
            "Test failed with exception: " + errorMessage,
            "1. Run automated test: " + testName + "\n2. Exception details:\n" + stackTrace,
            driver
        );
    }
}
