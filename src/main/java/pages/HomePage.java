package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;


public class HomePage {
    private final WebDriver driver;
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // The locators for these elements should only be defined once.
    By logo= new By.ByXPath("//picture//img[@alt='DuckDuckGo']");
    By seachTextBox= new By.ByXPath("//textarea[@name='q']");
    By seachBtn= new By.ByXPath("//button[@type='submit' and @data-react-aria-pressable='true']");



    //Actions
    public SearchResultsPage search(String searchText){
        driver.findElement(seachTextBox).sendKeys(searchText);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement message = wait.until(ExpectedConditions.presenceOfElementLocated(seachBtn));
        driver.findElement(seachBtn).click();
        return new SearchResultsPage(driver);
    }


    //Assertions
    public void checkTitle(String title)
    {
        Assert.assertTrue(driver.getTitle().equals(title));
    }

    public void checkPresenceOfLogo()
    {
        Assert.assertTrue(driver.findElement(logo).isDisplayed());
    }
}
