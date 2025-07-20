# 🐛 Bug Reporting System Guide

## Overview
Sistem bug reporting otomatis dan manual yang terintegrasi dengan automation testing framework. Sistem ini dapat:
- Generate bug reports otomatis saat test gagal
- Membuat bug reports manual dengan berbagai tingkat severity
- Menghasilkan HTML report yang profesional
- Mengintegrasikan screenshot dan detail teknis

## Fitur Utama

### 1. Automatic Bug Reporting
- **Auto-trigger**: Bug report otomatis dibuat saat test gagal
- **Screenshot**: Otomatis capture screenshot saat bug terjadi
- **Context**: Include URL, browser info, dan timestamp
- **Integration**: Terintegrasi dengan TestNG Listener

### 2. Manual Bug Reporting
- **Severity Levels**: Critical, High, Medium, Low
- **Detailed Info**: Description, expected vs actual results, steps to reproduce
- **Custom Reports**: Buat bug report sesuai kebutuhan
- **Flexible**: Dapat digunakan di dalam atau luar test execution

### 3. HTML Report Generation
- **Professional Layout**: HTML report dengan styling yang menarik
- **Color Coding**: Berbeda warna untuk setiap severity level
- **Summary**: Overview dengan statistik bug
- **Detailed View**: Detail lengkap setiap bug dengan screenshot path

## Cara Penggunaan

### A. Manual Bug Reporting

#### 1. Critical Bug (Sistem crash, data loss, security issues)
```java
BugReporter.reportCriticalBug(
    "System Crash on Login",
    "Application crashes completely when user enters special characters",
    "System should handle special characters gracefully",
    "Application throws OutOfMemoryException and crashes",
    "1. Navigate to login page\n2. Enter username with special chars\n3. Click login\n4. Observe crash",
    driver
);
```

#### 2. High Priority Bug (Major functionality broken)
```java
BugReporter.reportHighBug(
    "Payment Processing Failure",
    "Payment transactions fail without error notification",
    "Failed payments should show clear error message",
    "Payment fails silently, user sees endless processing",
    "1. Add items to cart\n2. Proceed to checkout\n3. Enter invalid card\n4. Submit payment",
    driver
);
```

#### 3. Medium Priority Bug (Minor functionality issues)
```java
BugReporter.reportMediumBug(
    "Search Results Pagination",
    "Pagination shows incorrect page numbers",
    "Should show correct current page and total pages",
    "Shows 'Page 1 of 0' when there are actually 5 pages",
    "1. Search for items\n2. Check pagination\n3. Note incorrect count",
    driver
);
```

#### 4. Low Priority Bug (Cosmetic issues, typos)
```java
BugReporter.reportLowBug(
    "Footer Copyright Year",
    "Footer shows outdated copyright year",
    "Copyright should show current year automatically",
    "Shows '© 2022' instead of '© 2025'",
    "1. Scroll to footer\n2. Observe copyright year",
    driver
);
```

### B. Generate Bug Report
```java
// Generate HTML bug report file
BugReporter.generateBugReport();

// Check total bugs reported
int totalBugs = BugReporter.getBugCount();
System.out.println("Total bugs reported: " + totalBugs);

// Clear all bug reports (for new test session)
BugReporter.clearBugReports();
```

### C. Integration dengan Test Cases

#### JUnit Integration
```java
@Test
public void testLoginFunctionality() {
    try {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("user", "pass");
        
        // Test assertions here
        assertTrue(loginPage.isLoginSuccessful());
        
    } catch (Exception e) {
        // Manual bug report on exception
        BugReporter.reportHighBug(
            "Login Test Failure",
            "Login functionality test failed with exception",
            "Login should work without errors",
            "Exception: " + e.getMessage(),
            "1. Run login test\n2. Observe exception",
            driver
        );
        throw e; // Re-throw to fail the test
    }
}
```

#### TestNG Integration (Automatic)
```java
@Listeners(TestListener.class)
public class MyTestNG extends TestNGBase {
    
    @Test
    public void testSomeFunctionality() {
        // Test code here
        // If test fails, TestListener will automatically create bug report
    }
}
```

## Output Examples

### Console Output
```
🐛 BUG REPORTED: Login System Crash - CRITICAL
Description: Application crashes when user login with special characters

🐛 BUG REPORTED: Payment Processing - HIGH
Description: Payment transactions fail without error notification

📋 Bug report generated: bug-reports/bug-report.html
Total bugs reported: 5
```

### HTML Report Structure
```
bug-reports/
└── bug-report.html    # Main HTML report file
```

### HTML Report Content
- **Header**: Project info, generation timestamp
- **Summary**: Total bugs by severity level
- **Bug Details**: Complete information for each bug
  - Bug ID and test name
  - Severity level (color-coded)
  - Description and expected vs actual results
  - Steps to reproduce
  - Screenshot path (if available)
  - Environment and timestamp info

## Best Practices

### 1. When to Use Manual Bug Reporting
- **During Exploratory Testing**: Manual testing scenarios
- **Custom Validation**: Business logic validation
- **Integration Issues**: Cross-system integration problems
- **Performance Issues**: Load time, memory usage problems

### 2. Bug Report Quality
- **Clear Description**: Describe the issue clearly and concisely
- **Reproducible Steps**: Provide step-by-step reproduction guide
- **Expected vs Actual**: Clearly state what should happen vs what actually happens
- **Appropriate Severity**: Use correct severity level based on impact

### 3. Severity Guidelines
- **Critical**: System crashes, data loss, security vulnerabilities
- **High**: Major features broken, blocking user workflows
- **Medium**: Minor features affected, workarounds available
- **Low**: Cosmetic issues, typos, minor UI problems

## Integration dengan CI/CD

### Jenkins Integration
```bash
# In Jenkins pipeline
mvn clean test
# Bug reports will be generated automatically
# Archive bug-reports/ folder as build artifacts
```

### GitHub Actions Integration
```yaml
- name: Run Tests and Generate Bug Reports
  run: mvn clean test
  
- name: Upload Bug Reports
  uses: actions/upload-artifact@v2
  with:
    name: bug-reports
    path: bug-reports/
```

## Troubleshooting

### Common Issues
1. **Bug report file not generated**: Check write permissions for bug-reports/ folder
2. **Screenshots not included**: Ensure WebDriver is properly initialized
3. **HTML formatting issues**: Check for special characters in bug descriptions

### Debug Mode
```java
// Enable debug logging
System.setProperty("bug.reporter.debug", "true");
```

---

**Sistem bug reporting ini siap digunakan untuk meningkatkan kualitas automation testing dan mempermudah bug tracking!** 🚀
