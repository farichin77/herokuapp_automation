package tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import pages.HoverPage;
import static org.junit.jupiter.api.Assertions.*;

public class HoverTest extends BaseTest {

    @Test
    @DisplayName("Test Hover Effects on Images")
    public void testHoverEffects() {
        HoverPage hoverPage = new HoverPage(driver);
        hoverPage.navigateToHovers();
        
        // Verify total images
        int totalImages = hoverPage.getTotalImages();
        assertEquals(3, totalImages, "Should have 3 images for hover testing");
        
        // Test hover on first image (index 0)
        assertTrue(hoverPage.isCaptionVisible(0), "Caption should be visible when hovering over first image");
        String caption1 = hoverPage.getCaptionText(0);
        assertTrue(caption1.contains("user1"), "First image caption should contain 'user1'");
        
        // Test hover on second image (index 1)
        assertTrue(hoverPage.isCaptionVisible(1), "Caption should be visible when hovering over second image");
        String caption2 = hoverPage.getCaptionText(1);
        assertTrue(caption2.contains("user2"), "Second image caption should contain 'user2'");
        
        // Test hover on third image (index 2)
        assertTrue(hoverPage.isCaptionVisible(2), "Caption should be visible when hovering over third image");
        String caption3 = hoverPage.getCaptionText(2);
        assertTrue(caption3.contains("user3"), "Third image caption should contain 'user3'");
        
        System.out.println("✅ Hover effects test passed!");
        System.out.println("Image 1 caption: " + caption1);
        System.out.println("Image 2 caption: " + caption2);
        System.out.println("Image 3 caption: " + caption3);
    }

    @Test
    @DisplayName("Test Click User Profile Links")
    public void testUserProfileLinks() {
        HoverPage hoverPage = new HoverPage(driver);
        hoverPage.navigateToHovers();
        
        // Test clicking on first user profile link
        hoverPage.clickUserLink(0);
        
        // Verify navigation to user profile page
        assertTrue(hoverPage.isUserProfilePageLoaded(), "Should navigate to user profile page");
        assertTrue(driver.getCurrentUrl().contains("/users/1"), "URL should contain '/users/1'");
        
        System.out.println("✅ User profile link test passed!");
        System.out.println("Current URL: " + driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Test Multiple Hover Interactions")
    public void testMultipleHoverInteractions() {
        HoverPage hoverPage = new HoverPage(driver);
        hoverPage.navigateToHovers();
        
        // Test hovering over multiple images in sequence
        for (int i = 0; i < hoverPage.getTotalImages(); i++) {
            assertTrue(hoverPage.isCaptionVisible(i), 
                      "Caption should be visible for image " + (i + 1));
            
            String caption = hoverPage.getCaptionText(i);
            assertFalse(caption.isEmpty(), "Caption should not be empty for image " + (i + 1));
            assertTrue(caption.contains("user" + (i + 1)), 
                      "Caption should contain correct user info for image " + (i + 1));
        }
        
        System.out.println("✅ Multiple hover interactions test passed!");
    }

    @Test
    @DisplayName("Test Hover Caption Content")
    public void testHoverCaptionContent() {
        HoverPage hoverPage = new HoverPage(driver);
        hoverPage.navigateToHovers();
        
        // Test detailed caption content for each image
        String[] expectedUsers = {"user1", "user2", "user3"};
        
        for (int i = 0; i < expectedUsers.length; i++) {
            String caption = hoverPage.getCaptionText(i);
            
            // Verify caption contains expected user name
            assertTrue(caption.contains(expectedUsers[i]), 
                      "Caption should contain " + expectedUsers[i]);
            
            // Verify caption contains "View profile" text
            assertTrue(caption.contains("View profile"), 
                      "Caption should contain 'View profile' link");
        }
        
        System.out.println("✅ Hover caption content test passed!");
    }
}
