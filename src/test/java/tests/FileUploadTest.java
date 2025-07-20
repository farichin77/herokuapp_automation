package tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import pages.FileUploadPage;
import static org.junit.jupiter.api.Assertions.*;

public class FileUploadTest extends BaseTest {

    @Test
    @DisplayName("Test File Upload with Text File")
    public void testTextFileUpload() {
        FileUploadPage fileUploadPage = new FileUploadPage(driver);
        fileUploadPage.navigateToFileUpload();
        
        // Create a test text file
        String testContent = "This is a test file for automation testing.";
        String testFilePath = fileUploadPage.createTestFile("test-upload.txt", testContent);
        
        // Upload the file
        fileUploadPage.uploadFile(testFilePath);
        
        // Verify upload success
        assertTrue(fileUploadPage.isUploadSuccessful(), "File upload should be successful");
        assertTrue(fileUploadPage.isFileUploaded("test-upload.txt"), "Uploaded file should be 'test-upload.txt'");
        
        String uploadedFileName = fileUploadPage.getUploadedFileName();
        assertEquals("test-upload.txt", uploadedFileName, "Uploaded file name should match");
        
        System.out.println("✅ Text file upload test passed!");
        System.out.println("Uploaded file: " + uploadedFileName);
    }

    @Test
    @DisplayName("Test File Upload with Different File Types")
    public void testDifferentFileTypesUpload() {
        FileUploadPage fileUploadPage = new FileUploadPage(driver);
        
        // Test CSV file upload
        fileUploadPage.navigateToFileUpload();
        String csvContent = "Name,Age,City\nJohn,25,Jakarta\nJane,30,Bandung";
        String csvFilePath = fileUploadPage.createTestFile("test-data.csv", csvContent);
        
        fileUploadPage.uploadFile(csvFilePath);
        assertTrue(fileUploadPage.isFileUploaded("test-data.csv"), "CSV file should be uploaded successfully");
        
        System.out.println("✅ CSV file upload test passed!");
        
        // Test JSON file upload
        fileUploadPage.navigateToFileUpload();
        String jsonContent = "{\"name\":\"Test User\",\"age\":25,\"city\":\"Jakarta\"}";
        String jsonFilePath = fileUploadPage.createTestFile("test-data.json", jsonContent);
        
        fileUploadPage.uploadFile(jsonFilePath);
        assertTrue(fileUploadPage.isFileUploaded("test-data.json"), "JSON file should be uploaded successfully");
        
        System.out.println("✅ JSON file upload test passed!");
    }

    @Test
    @DisplayName("Test File Upload with Large File")
    public void testLargeFileUpload() {
        FileUploadPage fileUploadPage = new FileUploadPage(driver);
        fileUploadPage.navigateToFileUpload();
        
        // Create a larger test file (10KB)
        String largeFilePath = fileUploadPage.createTestFileWithSize("large-test-file.txt", 10240);
        
        // Upload the large file
        fileUploadPage.uploadFile(largeFilePath);
        
        // Verify upload success
        assertTrue(fileUploadPage.isUploadSuccessful(), "Large file upload should be successful");
        assertTrue(fileUploadPage.isFileUploaded("large-test-file.txt"), "Large file should be uploaded");
        
        System.out.println("✅ Large file upload test passed!");
    }

    @Test
    @DisplayName("Test File Selection Without Upload")
    public void testFileSelectionOnly() {
        FileUploadPage fileUploadPage = new FileUploadPage(driver);
        fileUploadPage.navigateToFileUpload();
        
        // Create and select file but don't upload
        String testFilePath = fileUploadPage.createTestFile("selection-test.txt", "Test content");
        fileUploadPage.selectFile(testFilePath);
        
        // Verify we're still on upload page (not uploaded yet)
        assertTrue(driver.getCurrentUrl().contains("/upload"), "Should still be on upload page");
        
        // Now click upload
        fileUploadPage.clickUploadButton();
        
        // Verify upload success
        assertTrue(fileUploadPage.isUploadSuccessful(), "File should be uploaded after clicking upload button");
        
        System.out.println("✅ File selection test passed!");
    }
}
