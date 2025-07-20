package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUploadPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Selectors untuk File Upload page
    private By fileInput = By.id("file-upload");
    private By uploadButton = By.id("file-submit");
    private By uploadedFiles = By.id("uploaded-files");
    private By dragDropArea = By.id("drag-drop-upload");

    public FileUploadPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateToFileUpload() {
        driver.get("https://the-internet.herokuapp.com/upload");
    }

    public void selectFile(String filePath) {
        WebElement fileInputElement = wait.until(ExpectedConditions.presenceOfElementLocated(fileInput));
        fileInputElement.sendKeys(filePath);
    }

    public void clickUploadButton() {
        WebElement uploadBtn = wait.until(ExpectedConditions.elementToBeClickable(uploadButton));
        uploadBtn.click();
    }

    public String getUploadedFileName() {
        WebElement uploadedFile = wait.until(ExpectedConditions.visibilityOfElementLocated(uploadedFiles));
        return uploadedFile.getText();
    }

    public boolean isFileUploaded(String expectedFileName) {
        try {
            String uploadedFileName = getUploadedFileName();
            return uploadedFileName.contains(expectedFileName);
        } catch (Exception e) {
            return false;
        }
    }

    public void uploadFile(String filePath) {
        selectFile(filePath);
        clickUploadButton();
    }

    // Helper method untuk membuat test file
    public String createTestFile(String fileName, String content) {
        try {
            Path testFilePath = Paths.get(System.getProperty("java.io.tmpdir"), fileName);
            Files.write(testFilePath, content.getBytes());
            return testFilePath.toAbsolutePath().toString();
        } catch (IOException e) {
            throw new RuntimeException("Failed to create test file: " + e.getMessage());
        }
    }

    // Helper method untuk membuat test file dengan ukuran tertentu
    public String createTestFileWithSize(String fileName, int sizeInBytes) {
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < sizeInBytes; i++) {
            content.append("a");
        }
        return createTestFile(fileName, content.toString());
    }

    public boolean isUploadSuccessful() {
        try {
            // Check if we're redirected to upload success page
            return driver.getCurrentUrl().contains("upload") && 
                   driver.findElements(uploadedFiles).size() > 0;
        } catch (Exception e) {
            return false;
        }
    }
}
