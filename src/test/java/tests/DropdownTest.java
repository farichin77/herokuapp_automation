package tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import pages.DropdownPage;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class DropdownTest extends BaseTest {

    @Test
    @DisplayName("Test Dropdown Selection by Value")
    public void testDropdownSelectionByValue() {
        DropdownPage dropdownPage = new DropdownPage(driver);
        dropdownPage.navigateToDropdown();
        
        // Test selecting Option 1
        dropdownPage.selectByValue("1");
        assertEquals("1", dropdownPage.getSelectedOptionValue(), "Selected value should be '1'");
        assertEquals("Option 1", dropdownPage.getSelectedOptionText(), "Selected text should be 'Option 1'");
        
        // Test selecting Option 2
        dropdownPage.selectByValue("2");
        assertEquals("2", dropdownPage.getSelectedOptionValue(), "Selected value should be '2'");
        assertEquals("Option 2", dropdownPage.getSelectedOptionText(), "Selected text should be 'Option 2'");
        
        System.out.println("✅ Dropdown selection by value test passed!");
    }

    @Test
    @DisplayName("Test Dropdown Selection by Visible Text")
    public void testDropdownSelectionByText() {
        DropdownPage dropdownPage = new DropdownPage(driver);
        dropdownPage.navigateToDropdown();
        
        // Test selecting by visible text
        dropdownPage.selectByVisibleText("Option 1");
        assertEquals("Option 1", dropdownPage.getSelectedOptionText(), "Selected text should be 'Option 1'");
        
        dropdownPage.selectByVisibleText("Option 2");
        assertEquals("Option 2", dropdownPage.getSelectedOptionText(), "Selected text should be 'Option 2'");
        
        System.out.println("✅ Dropdown selection by text test passed!");
    }

    @Test
    @DisplayName("Test Dropdown Selection by Index")
    public void testDropdownSelectionByIndex() {
        DropdownPage dropdownPage = new DropdownPage(driver);
        dropdownPage.navigateToDropdown();
        
        // Test selecting by index (index 1 = Option 1, index 2 = Option 2)
        dropdownPage.selectByIndex(1);
        assertEquals("Option 1", dropdownPage.getSelectedOptionText(), "Selected text should be 'Option 1'");
        
        dropdownPage.selectByIndex(2);
        assertEquals("Option 2", dropdownPage.getSelectedOptionText(), "Selected text should be 'Option 2'");
        
        System.out.println("✅ Dropdown selection by index test passed!");
    }

    @Test
    @DisplayName("Test Dropdown Options Availability")
    public void testDropdownOptionsAvailability() {
        DropdownPage dropdownPage = new DropdownPage(driver);
        dropdownPage.navigateToDropdown();
        
        // Verify total options (should be 3: default + Option 1 + Option 2)
        assertEquals(3, dropdownPage.getTotalOptions(), "Should have 3 total options");
        
        // Verify specific options are available
        assertTrue(dropdownPage.isOptionAvailable("Option 1"), "Option 1 should be available");
        assertTrue(dropdownPage.isOptionAvailable("Option 2"), "Option 2 should be available");
        assertFalse(dropdownPage.isOptionAvailable("Option 3"), "Option 3 should not be available");
        
        // Get all option texts
        List<String> allOptions = dropdownPage.getAllOptionTexts();
        assertTrue(allOptions.contains("Option 1"), "All options should contain 'Option 1'");
        assertTrue(allOptions.contains("Option 2"), "All options should contain 'Option 2'");
        
        System.out.println("✅ Dropdown options availability test passed!");
        System.out.println("Available options: " + allOptions);
    }
}
