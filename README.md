# 🌟 **The Internet Herokuapp - Automation Testing Framework**

[![Java](https://img.shields.io/badge/Java-11+-orange.svg)](https://www.oracle.com/java/)
[![Selenium](https://img.shields.io/badge/Selenium-4.19.1-green.svg)](https://selenium.dev/)
[![TestNG](https://img.shields.io/badge/TestNG-7.9.0-red.svg)](https://testng.org/)
[![JUnit](https://img.shields.io/badge/JUnit-5.10.2-blue.svg)](https://junit.org/junit5/)
[![Maven](https://img.shields.io/badge/Maven-3.6+-purple.svg)](https://maven.apache.org/)
[![Allure](https://img.shields.io/badge/Allure-2.24.0-yellow.svg)](https://docs.qameta.io/allure/)

## 🌟 **Ready to Start My QA Automation Career!**

This framework represents my **learning journey in automation testing** and demonstrates the foundational skills I've developed for **junior QA automation engineer positions**. The clean test structure, basic reporting systems, and organized code show my commitment to learning best practices and growing in the QA automation field.

**Perfect for:**
- 🎯 **Junior QA Automation Engineer** positions
- 🎯 **QA Automation Trainee** roles
- 🎯 **Entry-level Test Automation** opportunities
- 🎯 **Learning-focused** QA environments

---

*Built with 🌟 as part of my automation testing learning journey* [The Internet Herokuapp](https://the-internet.herokuapp.com) - a popular testing practice site. The framework demonstrates **core automation testing skills** including basic test architecture, Page Object Model implementation, test reporting, and clean code organization.

**🎯 Learning Highlights:**
- ✅ **Foundational** test framework setup and structure
- ✅ **Clean** Page Object Model implementation
- ✅ **Comprehensive** test coverage (15+ test scenarios)
- ✅ **Basic** bug reporting with screenshot capture
- ✅ **Modern** Allure integration for test reports
- ✅ **Professional** code organization and documentation

## 📊 **Advanced Reporting & Analytics**
- **Allure Reporting**: Interactive test reports with rich analytics
- **Screenshot Automation**: Auto-capture on test failures
- **Bug Reporting System**: Automated bug report generation
- **Test Categorization**: Epic, Feature, Story annotations
- **Severity Levels**: Critical, High, Medium, Low priority classification

## 📁 Struktur Project

```
the-internet-automation/
├── 📄 pom.xml                           # Maven configuration
├── 📄 README.md                         # Project documentation
├── 📄 BUG_REPORTING_GUIDE.md            # Bug reporting guidelines
├── 📁 src/
│   ├── 📁 main/java/
│   │   ├── 📁 pages/                    # Page Object Model classes
│   │   │   ├── 🔐 LoginPage.java        # Login functionality
│   │   │   ├── 📋 DropdownPage.java     # Dropdown interactions
│   │   │   ├── ☑️ CheckboxPage.java      # Checkbox operations
│   │   │   ├── 📁 FileUploadPage.java   # File upload handling
│   │   │   ├── ⚠️ AlertsPage.java        # JavaScript alerts
│   │   │   └── 🖱️ HoverPage.java         # Mouse hover actions
│   │   └── 📁 utils/                    # Utility classes
│   │       ├── 📸 ScreenshotUtils.java  # Screenshot automation
│   │       ├── 🐛 AllureBugReporter.java # Bug reporting system
│   │       ├── 🔧 SimpleBugDemo.java    # Bug demo utilities
│   │       └── 📝 ManualBugReportGenerator.java
│   ├── 📁 test/java/
│   │   ├── 📁 tests/                    # JUnit 5 test classes
│   │   │   ├── 🔐 LoginTest.java        # Login test scenarios
│   │   │   └── 🎯 ComprehensiveTest.java # Comprehensive UI tests
│   │   └── 📁 testng/                   # TestNG test classes
│   │       ├── 🔐 LoginTestNG.java      # Login test scenarios
│   │       ├── 🎯 ComprehensiveTestNG.java # Comprehensive UI tests
│   │       ├── 🐛 BugDemoTestNG.java    # Bug reporting demos
│   │       └── 👂 TestListener.java     # TestNG event listener
│   └── 📁 test/resources/
│       └── 📄 comprehensive-suite.xml   # TestNG suite configuration
├── 📁 screenshots/                      # Auto-generated screenshots
└── 📁 target/                          # Maven build output
    ├── 📁 allure-results/              # Allure test results
    └── 📁 site/allure-maven-plugin/    # Generated Allure reports
```

## 🚀 Cara Menjalankan

### 📋 Prerequisites
1. **Java 11+** - [Download Oracle JDK](https://www.oracle.com/java/technologies/downloads/)
2. **Maven 3.6+** - [Download Apache Maven](https://maven.apache.org/download.cgi)
3. **Chrome Browser** - WebDriverManager akan auto-download ChromeDriver

### 🧪 Menjalankan Tests

#### 1. **JUnit 5 Tests**
```bash
# Jalankan semua JUnit tests
mvn test -Dtest=tests.*

# Jalankan login tests
mvn test -Dtest=tests.LoginTest

# Jalankan comprehensive UI tests
mvn test -Dtest=tests.ComprehensiveTest
```

#### 2. **TestNG Tests**
```bash
# Jalankan semua TestNG tests
mvn test -Dtest=testng.*

# Jalankan login tests
mvn test -Dtest=testng.LoginTestNG

# Jalankan comprehensive UI tests
mvn test -Dtest=testng.ComprehensiveTestNG

# Jalankan bug demo tests
mvn test -Dtest=testng.BugDemoTestNG
```

#### 3. **Test Suites**
```bash
# Jalankan comprehensive test suite
mvn test -Dtest="testng.ComprehensiveTestNG,tests.ComprehensiveTest"

# Jalankan semua tests
mvn clean test
```

#### 4. **Parallel Execution**
```bash
# Jalankan tests secara parallel (TestNG)
mvn test -Dparallel=methods -DthreadCount=3
```

### 📊 Melihat Hasil Test

#### **Allure Reports (Recommended)**
```bash
# Generate dan serve Allure report
mvn clean test
mvn io.qameta.allure:allure-maven:report
mvn io.qameta.allure:allure-maven:serve
```

#### **Standard Reports**
- **Surefire HTML Reports**: `target/surefire-reports/index.html`
- **TestNG Reports**: `target/surefire-reports/testng-results.xml`
- **Screenshots**: `screenshots/` folder (auto-generated on failures)
- **Bug Reports**: Generated HTML reports in project root

## 🎯 Test Coverage Overview

### 📊 **Test Statistics**
- **Total Test Scenarios**: 15+ comprehensive test cases
- **UI Components Covered**: 6 different components
- **Framework Support**: TestNG + JUnit 5
- **Test Categories**: Login, Dropdown, Checkbox, File Upload, Alerts, Hover Actions

### 🔐 **Authentication Tests**
| Test Class | Framework | Test Cases | Description |
|------------|-----------|------------|-------------|
| `LoginTest` | JUnit 5 | 4 scenarios | Login functionality validation |
| `LoginTestNG` | TestNG | 4 scenarios | Login functionality validation |

**Test Scenarios:**
1. ✅ **testValidLogin**: Login dengan credentials valid
2. ✅ **testInvalidUsername**: Login dengan username invalid  
3. ✅ **testInvalidPassword**: Login dengan password invalid
4. ✅ **testLoginAndLogout**: Login dan logout flow lengkap

### 🎯 **Comprehensive UI Tests**
| Test Class | Framework | Test Cases | Description |
|------------|-----------|------------|-------------|
| `ComprehensiveTest` | JUnit 5 | 11 scenarios | Multi-component UI testing |
| `ComprehensiveTestNG` | TestNG | 11 scenarios | Multi-component UI testing |

#### 📋 **Dropdown Testing**
1. ✅ **testDropdownSelection**: Dropdown selection functionality
2. ✅ **testDropdownOptions**: Dropdown options validation

#### ☑️ **Checkbox Testing**  
3. ✅ **testCheckboxInteraction**: Individual checkbox operations
4. ✅ **testMultipleCheckboxSelection**: Multiple checkbox handling

#### 📁 **File Upload Testing**
5. ✅ **testFileUpload**: Basic file upload functionality
6. ✅ **testFileUploadValidation**: File type validation

#### ⚠️ **JavaScript Alerts Testing**
7. ✅ **testJavaScriptAlert**: Alert dialog handling
8. ✅ **testJavaScriptConfirm**: Confirm dialog (Accept/Dismiss)
9. ✅ **testJavaScriptPrompt**: Prompt dialog with input

#### 🖱️ **Hover Actions Testing**
10. ✅ **testHoverActions**: Mouse hover functionality
11. ✅ **testHoverProfileLinks**: Hover profile navigation

### 🐛 **Bug Reporting Tests**
| Test Class | Framework | Test Cases | Description |
|------------|-----------|------------|-------------|
| `BugDemoTestNG` | TestNG | Multiple scenarios | Bug reporting system demo |

## 🚀 Advanced Features

### 📸 **Screenshot Automation**
- **Auto-Capture**: Screenshots diambil otomatis saat test failure
- **Timestamped Files**: Disimpan dengan timestamp untuk tracking
- **Integration**: Terintegrasi dengan Allure reports
- **Storage**: Organized dalam folder `screenshots/`
- **Utility Class**: `ScreenshotUtils` untuk screenshot management

### 🐛 **Comprehensive Bug Reporting System**

#### **Automatic Bug Reports**
- Auto-generate bug reports saat test failure
- Integration dengan TestNG listeners
- Include screenshot dan error details
- Categorized berdasarkan severity level

#### **Manual Bug Reports**
```java
// Critical bug reporting
AllureBugReporter.reportCriticalBug(
    "Login System Crash",
    "Application crashes when user login with special characters",
    "System should handle special characters gracefully",
    "Application throws NullPointerException",
    "1. Navigate to login\n2. Enter special chars\n3. Observe crash",
    driver
);
```

#### **Bug Report Features**
- **Severity Levels**: Critical, High, Medium, Low priority classification
- **HTML Reports**: Professional HTML bug report generation
- **Screenshot Integration**: Auto-attach screenshots to bug reports
- **Detailed Logging**: Comprehensive error tracking dan context
- **Export Options**: Multiple format support untuk bug reports

#### Cara Menggunakan Bug Reporting:

**1. Manual Bug Report:**
```java
// Critical bug
BugReporter.reportCriticalBug(testName, description, expected, actual, steps, driver);

// High priority bug
BugReporter.reportHighBug(testName, description, expected, actual, steps, driver);

// Medium priority bug
BugReporter.reportMediumBug(testName, description, expected, actual, steps, driver);

// Low priority bug
BugReporter.reportLowBug(testName, description, expected, actual, steps, driver);
```

**2. Generate Bug Report:**
```java
// Generate HTML bug report
BugReporter.generateBugReport();
```

**3. Auto Bug Reports:**
- TestNG Listener otomatis membuat bug report saat test gagal
- Include screenshot, error details, dan test context

### Allure Reporting
```bash
# Generate Allure report setelah test
mvn clean test
mvn allure:report
mvn allure:serve
```

### TestNG Listener
- `TestListener` class untuk handle test events
- Screenshot otomatis saat test failure
- Auto bug reporting saat test gagal
- Logging detail untuk setiap test

## Contoh Output Test
```
✅ Valid login test passed!
Flash message: You logged into a secure area!

✅ Invalid username test passed!
Flash message: Your username is invalid!

✅ Invalid password test passed!
Flash message: Your password is invalid!

✅ Login and logout test passed!
Flash message: You logged out of the secure area!
```

## Pengembangan Lanjutan

### Saran Improvement
- **Data Driven Testing**: Tambahkan CSV/JSON untuk test data
- **Parallel Execution**: Konfigurasi TestNG untuk parallel testing
- **Cross Browser Testing**: Tambahkan Firefox, Edge support
- **CI/CD Integration**: GitHub Actions, Jenkins pipeline
- **Docker Support**: Containerized testing environment
- **API Testing**: Tambahkan REST API automation
- **Database Testing**: Validasi data di database
- **Performance Testing**: Load testing dengan JMeter integration

### Struktur untuk Test Tambahan
```java
// Contoh test tambahan yang bisa ditambahkan
@Test
public void testFormAuthentication() { ... }

@Test  
public void testCheckboxes() { ... }

@Test
public void testDropdown() { ... }

@Test
public void testFileUpload() { ... }
```

## Troubleshooting

### Common Issues
1. **ChromeDriver Issues**: WebDriverManager akan auto-download driver yang sesuai
2. **Element Not Found**: Gunakan WebDriverWait untuk element yang lambat load
3. **Test Flaky**: Tambahkan implicit/explicit waits
4. **Screenshot Gagal**: Pastikan folder `screenshots/` bisa ditulis

### Debug Mode
Untuk debugging, tambahkan:
```java
System.setProperty("webdriver.chrome.verboseLogging", "true");
```

---

## 🏆 **Portfolio Highlights**

**Project ini adalah comprehensive automation testing framework yang siap untuk Junior QA Automation Engineer positions!**

### ✨ **Technical Excellence**
- ✅ **Page Object Model**: Clean, maintainable architecture
- ✅ **Multi-Framework Support**: TestNG + JUnit 5 integration
- ✅ **Advanced Reporting**: Allure reports dengan rich analytics
- ✅ **Screenshot Automation**: Auto-capture pada failures
- ✅ **Bug Reporting System**: Automated bug tracking
- ✅ **Comprehensive Coverage**: 15+ test scenarios across 6 UI components
- ✅ **Professional Structure**: Enterprise-ready codebase
- ✅ **CI/CD Ready**: Maven-based build system

### 🎯 **Industry Best Practices**
- ✅ **Clean Code**: Well-structured, readable, maintainable
- ✅ **Error Handling**: Robust exception management
- ✅ **Test Organization**: Logical grouping dan categorization
- ✅ **Documentation**: Comprehensive README dan inline comments
- ✅ **Scalability**: Easily extensible framework
- ✅ **Cross-Component Testing**: Multiple UI interaction types

### 📊 **Portfolio Impact**

| **Metric** | **Value** | **Impact** |
|------------|-----------|------------|
| **Test Coverage** | 15+ scenarios | 275% increase from basic login tests |
| **UI Components** | 6 components | Comprehensive UI interaction coverage |
| **Framework Support** | 2 frameworks | TestNG + JUnit 5 flexibility |
| **Reporting** | Advanced | Allure + Bug reporting integration |
| **Architecture** | Enterprise | Production-ready structure |

### 🚀 **Career Readiness**

**Suitable for positions:**
- 🎯 **Junior QA Automation Engineer**
- 🎯 **Automation Tester**
- 🎯 **SDET (Software Development Engineer in Test)**
- 🎯 **QA Engineer dengan automation expertise**

**Demonstrates expertise in:**
- Basic test automation frameworks
- Multiple testing paradigms
- Professional reporting solutions
- Enterprise-grade code quality
- Comprehensive UI testing strategies

---

## 📞 **Contact & Contribution**

**Ready for technical interviews and code reviews!**

This framework showcases foundational automation testing skills suitable for junior-level positions in quality assurance and test automation.

**Key Differentiators:**
- Comprehensive test coverage across multiple UI components
- Advanced reporting and bug tracking integration
- Professional code structure and documentation
- Multi-framework support demonstrating technical versatility
- Industry best practices implementation

---

*Built with ❤️ for comprehensive UI automation testing*
