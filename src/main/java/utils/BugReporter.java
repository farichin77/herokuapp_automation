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
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BugReporter {
    private static final String BUG_REPORT_DIR = "bug-reports";
    private static final String BUG_REPORT_FILE = "bug-report.html";
    private static List<BugReport> bugReports = new ArrayList<>();
    
    public static class BugReport {
        private String testName;
        private String severity;
        private String description;
        private String expectedResult;
        private String actualResult;
        private String steps;
        private String screenshotPath;
        private String timestamp;
        private String url;
        private String browserInfo;
        private String environment;
        
        public BugReport(String testName, String severity, String description, 
                        String expectedResult, String actualResult, String steps) {
            this.testName = testName;
            this.severity = severity;
            this.description = description;
            this.expectedResult = expectedResult;
            this.actualResult = actualResult;
            this.steps = steps;
            this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            this.environment = "The Internet Herokuapp - Chrome Browser";
        }
        
        // Getters and Setters
        public String getTestName() { return testName; }
        public String getSeverity() { return severity; }
        public String getDescription() { return description; }
        public String getExpectedResult() { return expectedResult; }
        public String getActualResult() { return actualResult; }
        public String getSteps() { return steps; }
        public String getScreenshotPath() { return screenshotPath; }
        public String getTimestamp() { return timestamp; }
        public String getUrl() { return url; }
        public String getBrowserInfo() { return browserInfo; }
        public String getEnvironment() { return environment; }
        
        public void setScreenshotPath(String screenshotPath) { this.screenshotPath = screenshotPath; }
        public void setUrl(String url) { this.url = url; }
        public void setBrowserInfo(String browserInfo) { this.browserInfo = browserInfo; }
    }
    
    public static void reportBug(String testName, String severity, String description,
                               String expectedResult, String actualResult, String steps,
                               WebDriver driver) {
        BugReport bug = new BugReport(testName, severity, description, expectedResult, actualResult, steps);
        
        if (driver != null) {
            // Capture additional info
            bug.setUrl(driver.getCurrentUrl());
            bug.setBrowserInfo(driver.getClass().getSimpleName());
            
            // Take screenshot
            String screenshotPath = ScreenshotUtils.takeScreenshotOnFailure(driver, testName + "_BUG");
            bug.setScreenshotPath(screenshotPath);
        }
        
        bugReports.add(bug);
        System.out.println("🐛 BUG REPORTED: " + testName + " - " + severity);
        System.out.println("Description: " + description);
    }
    
    public static void reportCriticalBug(String testName, String description,
                                       String expectedResult, String actualResult, 
                                       String steps, WebDriver driver) {
        reportBug(testName, "CRITICAL", description, expectedResult, actualResult, steps, driver);
    }
    
    public static void reportHighBug(String testName, String description,
                                   String expectedResult, String actualResult, 
                                   String steps, WebDriver driver) {
        reportBug(testName, "HIGH", description, expectedResult, actualResult, steps, driver);
    }
    
    public static void reportMediumBug(String testName, String description,
                                     String expectedResult, String actualResult, 
                                     String steps, WebDriver driver) {
        reportBug(testName, "MEDIUM", description, expectedResult, actualResult, steps, driver);
    }
    
    public static void reportLowBug(String testName, String description,
                                  String expectedResult, String actualResult, 
                                  String steps, WebDriver driver) {
        reportBug(testName, "LOW", description, expectedResult, actualResult, steps, driver);
    }
    
    public static void generateBugReport() {
        try {
            // Create bug reports directory
            java.io.File dir = new java.io.File(BUG_REPORT_DIR);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            
            String reportPath = BUG_REPORT_DIR + "/" + BUG_REPORT_FILE;
            FileWriter writer = new FileWriter(reportPath);
            
            // Generate HTML report
            writer.write(generateHTMLReport());
            writer.close();
            
            System.out.println("📋 Bug report generated: " + reportPath);
            System.out.println("Total bugs reported: " + bugReports.size());
            
        } catch (IOException e) {
            System.err.println("Failed to generate bug report: " + e.getMessage());
        }
    }
    
    private static String generateHTMLReport() {
        StringBuilder html = new StringBuilder();
        
        html.append("<!DOCTYPE html>\n");
        html.append("<html>\n<head>\n");
        html.append("<title>Automation Testing Bug Report</title>\n");
        html.append("<style>\n");
        html.append("body { font-family: Arial, sans-serif; margin: 20px; }\n");
        html.append("h1 { color: #d32f2f; }\n");
        html.append("h2 { color: #1976d2; }\n");
        html.append(".bug-item { border: 1px solid #ddd; margin: 10px 0; padding: 15px; border-radius: 5px; }\n");
        html.append(".critical { border-left: 5px solid #f44336; }\n");
        html.append(".high { border-left: 5px solid #ff9800; }\n");
        html.append(".medium { border-left: 5px solid #ffeb3b; }\n");
        html.append(".low { border-left: 5px solid #4caf50; }\n");
        html.append(".severity { font-weight: bold; padding: 2px 8px; border-radius: 3px; color: white; }\n");
        html.append(".critical-severity { background-color: #f44336; }\n");
        html.append(".high-severity { background-color: #ff9800; }\n");
        html.append(".medium-severity { background-color: #ffeb3b; color: black; }\n");
        html.append(".low-severity { background-color: #4caf50; }\n");
        html.append(".summary { background-color: #f5f5f5; padding: 15px; border-radius: 5px; margin-bottom: 20px; }\n");
        html.append("</style>\n");
        html.append("</head>\n<body>\n");
        
        // Header
        html.append("<h1>🐛 Automation Testing Bug Report</h1>\n");
        html.append("<p><strong>Generated:</strong> ").append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).append("</p>\n");
        html.append("<p><strong>Project:</strong> The Internet Herokuapp Automation Testing</p>\n");
        
        // Summary
        html.append("<div class='summary'>\n");
        html.append("<h2>📊 Summary</h2>\n");
        html.append("<p><strong>Total Bugs:</strong> ").append(bugReports.size()).append("</p>\n");
        
        long criticalCount = bugReports.stream().filter(b -> "CRITICAL".equals(b.getSeverity())).count();
        long highCount = bugReports.stream().filter(b -> "HIGH".equals(b.getSeverity())).count();
        long mediumCount = bugReports.stream().filter(b -> "MEDIUM".equals(b.getSeverity())).count();
        long lowCount = bugReports.stream().filter(b -> "LOW".equals(b.getSeverity())).count();
        
        html.append("<p><strong>Critical:</strong> ").append(criticalCount).append("</p>\n");
        html.append("<p><strong>High:</strong> ").append(highCount).append("</p>\n");
        html.append("<p><strong>Medium:</strong> ").append(mediumCount).append("</p>\n");
        html.append("<p><strong>Low:</strong> ").append(lowCount).append("</p>\n");
        html.append("</div>\n");
        
        // Bug Details
        html.append("<h2>🔍 Bug Details</h2>\n");
        
        if (bugReports.isEmpty()) {
            html.append("<p style='color: green; font-size: 18px;'>🎉 <strong>No bugs found! All tests passed successfully.</strong></p>\n");
        } else {
            for (int i = 0; i < bugReports.size(); i++) {
                BugReport bug = bugReports.get(i);
                String severityClass = bug.getSeverity().toLowerCase();
                
                html.append("<div class='bug-item ").append(severityClass).append("'>\n");
                html.append("<h3>Bug #").append(i + 1).append(": ").append(bug.getTestName()).append("</h3>\n");
                html.append("<p><span class='severity ").append(severityClass).append("-severity'>").append(bug.getSeverity()).append("</span></p>\n");
                html.append("<p><strong>Description:</strong> ").append(bug.getDescription()).append("</p>\n");
                html.append("<p><strong>Expected Result:</strong> ").append(bug.getExpectedResult()).append("</p>\n");
                html.append("<p><strong>Actual Result:</strong> ").append(bug.getActualResult()).append("</p>\n");
                html.append("<p><strong>Steps to Reproduce:</strong></p>\n");
                html.append("<pre>").append(bug.getSteps()).append("</pre>\n");
                html.append("<p><strong>Timestamp:</strong> ").append(bug.getTimestamp()).append("</p>\n");
                html.append("<p><strong>Environment:</strong> ").append(bug.getEnvironment()).append("</p>\n");
                
                if (bug.getUrl() != null) {
                    html.append("<p><strong>URL:</strong> ").append(bug.getUrl()).append("</p>\n");
                }
                
                if (bug.getScreenshotPath() != null) {
                    html.append("<p><strong>Screenshot:</strong> ").append(bug.getScreenshotPath()).append("</p>\n");
                }
                
                html.append("</div>\n");
            }
        }
        
        html.append("</body>\n</html>");
        return html.toString();
    }
    
    public static int getBugCount() {
        return bugReports.size();
    }
    
    public static void clearBugReports() {
        bugReports.clear();
    }
}
