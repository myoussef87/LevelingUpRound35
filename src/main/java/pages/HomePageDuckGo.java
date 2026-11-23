package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;


public class HomePageDuckGo {
    private final WebDriver driver;
    public HomePageDuckGo(WebDriver driver) {
        this.driver = driver;
    }

    // The locators for these elements should only be defined once.
    By imglogo = new By.ByXPath("//picture//img[@alt='DuckDuckGo']");
    By txtSearchBox = new By.ByXPath("//textarea[@name='q']");
    By btnSearch = new By.ByXPath("//button[@type='submit' and @data-react-aria-pressable='true']");



    //Actions
    public SearchResultsPageDuckGo search(String searchText){
        driver.findElement(txtSearchBox).sendKeys(searchText);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(btnSearch));
        driver.findElement(btnSearch).click();
        return new SearchResultsPageDuckGo(driver);
    }


    //Assertions
    public void checkTitle(String title)
    {
        Assert.assertTrue(driver.getTitle().equals(title));
    }

    public void checkPresenceOfLogo()
    {
        Assert.assertTrue(driver.findElement(imglogo).isDisplayed());
    }
}
