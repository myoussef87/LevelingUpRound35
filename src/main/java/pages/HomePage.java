package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;


public class HomePage {
    private final WebDriver driver;
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // The locators for these elements should only be defined once.

    public void checkTitle(String title)
    {
        Assert.assertTrue(driver.getTitle().equals(title));
    }

    public void checkPresenceOfLogo()
    {

    }
}
