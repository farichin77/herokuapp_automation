package tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import pages.AlertsPage;
import static org.junit.jupiter.api.Assertions.*;

public class AlertsTest extends BaseTest {

    @Test
    @DisplayName("Test JavaScript Alert - Accept")
    public void testJavaScriptAlert() {
        AlertsPage alertsPage = new AlertsPage(driver);
        alertsPage.navigateToAlerts();
        
        // Click JS Alert button
        alertsPage.clickJSAlert();
        
        // Verify alert is present and get text
        assertTrue(alertsPage.isAlertPresent(), "Alert should be present");
        String alertText = alertsPage.getAlertText();
        assertEquals("I am a JS Alert", alertText, "Alert text should match expected");
        
        // Accept the alert
        alertsPage.acceptAlert();
        
        // Verify result text
        String resultText = alertsPage.getResultText();
        assertEquals("You successfully clicked an alert", resultText, "Result text should indicate success");
        
        System.out.println("✅ JavaScript Alert test passed!");
        System.out.println("Alert text: " + alertText);
        System.out.println("Result: " + resultText);
    }

    @Test
    @DisplayName("Test JavaScript Confirm - Accept")
    public void testJavaScriptConfirmAccept() {
        AlertsPage alertsPage = new AlertsPage(driver);
        alertsPage.navigateToAlerts();
        
        // Click JS Confirm button
        alertsPage.clickJSConfirm();
        
        // Verify alert is present and get text
        assertTrue(alertsPage.isAlertPresent(), "Confirm dialog should be present");
        String alertText = alertsPage.getAlertText();
        assertEquals("I am a JS Confirm", alertText, "Confirm text should match expected");
        
        // Accept the confirm
        alertsPage.acceptAlert();
        
        // Verify result text
        String resultText = alertsPage.getResultText();
        assertEquals("You clicked: Ok", resultText, "Result should indicate OK was clicked");
        
        System.out.println("✅ JavaScript Confirm Accept test passed!");
        System.out.println("Confirm text: " + alertText);
        System.out.println("Result: " + resultText);
    }

    @Test
    @DisplayName("Test JavaScript Confirm - Dismiss")
    public void testJavaScriptConfirmDismiss() {
        AlertsPage alertsPage = new AlertsPage(driver);
        alertsPage.navigateToAlerts();
        
        // Click JS Confirm button
        alertsPage.clickJSConfirm();
        
        // Verify alert is present
        assertTrue(alertsPage.isAlertPresent(), "Confirm dialog should be present");
        
        // Dismiss the confirm
        alertsPage.dismissAlert();
        
        // Verify result text
        String resultText = alertsPage.getResultText();
        assertEquals("You clicked: Cancel", resultText, "Result should indicate Cancel was clicked");
        
        System.out.println("✅ JavaScript Confirm Dismiss test passed!");
        System.out.println("Result: " + resultText);
    }

    @Test
    @DisplayName("Test JavaScript Prompt with Text Input")
    public void testJavaScriptPromptWithText() {
        AlertsPage alertsPage = new AlertsPage(driver);
        alertsPage.navigateToAlerts();
        
        // Click JS Prompt button
        alertsPage.clickJSPrompt();
        
        // Verify alert is present and get text
        assertTrue(alertsPage.isAlertPresent(), "Prompt dialog should be present");
        String alertText = alertsPage.getAlertText();
        assertEquals("I am a JS prompt", alertText, "Prompt text should match expected");
        
        // Send text to prompt and accept
        String inputText = "Hello Automation Testing!";
        alertsPage.sendTextToAlert(inputText);
        
        // Verify result text contains our input
        String resultText = alertsPage.getResultText();
        assertEquals("You entered: " + inputText, resultText, "Result should contain entered text");
        
        System.out.println("✅ JavaScript Prompt with text test passed!");
        System.out.println("Prompt text: " + alertText);
        System.out.println("Input text: " + inputText);
        System.out.println("Result: " + resultText);
    }

    @Test
    @DisplayName("Test JavaScript Prompt - Dismiss")
    public void testJavaScriptPromptDismiss() {
        AlertsPage alertsPage = new AlertsPage(driver);
        alertsPage.navigateToAlerts();
        
        // Click JS Prompt button
        alertsPage.clickJSPrompt();
        
        // Verify alert is present
        assertTrue(alertsPage.isAlertPresent(), "Prompt dialog should be present");
        
        // Dismiss the prompt
        alertsPage.dismissAlert();
        
        // Verify result text
        String resultText = alertsPage.getResultText();
        assertEquals("You entered: null", resultText, "Result should indicate null when dismissed");
        
        System.out.println("✅ JavaScript Prompt Dismiss test passed!");
        System.out.println("Result: " + resultText);
    }
}
