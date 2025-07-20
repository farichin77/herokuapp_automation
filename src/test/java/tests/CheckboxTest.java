package tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import pages.CheckboxPage;
import static org.junit.jupiter.api.Assertions.*;

public class CheckboxTest extends BaseTest {

    @Test
    @DisplayName("Test Checkbox Functionality - Select and Deselect")
    public void testCheckboxSelectDeselect() {
        CheckboxPage checkboxPage = new CheckboxPage(driver);
        checkboxPage.navigateToCheckboxes();
        
        // Verify initial state
        assertEquals(2, checkboxPage.getTotalCheckboxes(), "Should have 2 checkboxes");
        
        // Test checkbox 1 (initially unchecked)
        assertFalse(checkboxPage.isCheckbox1Selected(), "Checkbox 1 should be initially unchecked");
        checkboxPage.clickCheckbox1();
        assertTrue(checkboxPage.isCheckbox1Selected(), "Checkbox 1 should be selected after click");
        
        // Test checkbox 2 (initially checked)
        assertTrue(checkboxPage.isCheckbox2Selected(), "Checkbox 2 should be initially checked");
        checkboxPage.clickCheckbox2();
        assertFalse(checkboxPage.isCheckbox2Selected(), "Checkbox 2 should be unchecked after click");
        
        System.out.println("✅ Checkbox select/deselect test passed!");
    }

    @Test
    @DisplayName("Test Select All Checkboxes")
    public void testSelectAllCheckboxes() {
        CheckboxPage checkboxPage = new CheckboxPage(driver);
        checkboxPage.navigateToCheckboxes();
        
        // Select all checkboxes
        checkboxPage.selectAllCheckboxes();
        assertTrue(checkboxPage.areAllCheckboxesSelected(), "All checkboxes should be selected");
        
        System.out.println("✅ Select all checkboxes test passed!");
    }

    @Test
    @DisplayName("Test Unselect All Checkboxes")
    public void testUnselectAllCheckboxes() {
        CheckboxPage checkboxPage = new CheckboxPage(driver);
        checkboxPage.navigateToCheckboxes();
        
        // First select all, then unselect all
        checkboxPage.selectAllCheckboxes();
        assertTrue(checkboxPage.areAllCheckboxesSelected(), "All checkboxes should be selected first");
        
        checkboxPage.unselectAllCheckboxes();
        assertFalse(checkboxPage.areAllCheckboxesSelected(), "All checkboxes should be unselected");
        assertFalse(checkboxPage.isCheckbox1Selected(), "Checkbox 1 should be unselected");
        assertFalse(checkboxPage.isCheckbox2Selected(), "Checkbox 2 should be unselected");
        
        System.out.println("✅ Unselect all checkboxes test passed!");
    }

    @Test
    @DisplayName("Test Checkbox Toggle Multiple Times")
    public void testCheckboxToggle() {
        CheckboxPage checkboxPage = new CheckboxPage(driver);
        checkboxPage.navigateToCheckboxes();
        
        // Toggle checkbox 1 multiple times
        boolean initialState = checkboxPage.isCheckbox1Selected();
        
        checkboxPage.clickCheckbox1();
        assertEquals(!initialState, checkboxPage.isCheckbox1Selected(), "Checkbox 1 state should toggle");
        
        checkboxPage.clickCheckbox1();
        assertEquals(initialState, checkboxPage.isCheckbox1Selected(), "Checkbox 1 should return to initial state");
        
        checkboxPage.clickCheckbox1();
        assertEquals(!initialState, checkboxPage.isCheckbox1Selected(), "Checkbox 1 should toggle again");
        
        System.out.println("✅ Checkbox toggle test passed!");
    }
}
