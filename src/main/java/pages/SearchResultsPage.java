package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;


public class SearchResultsPage {
    private final WebDriver driver;
    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    // The locators for these elements should only be defined once.
    By linkOfFirstResult= new By.ByXPath("//li[@data-layout='organic']/article[@id='r1-0']//a[@data-testid='result-extras-url-link']");



    //Actions


    //Assertions
    public void checkLink(String link)
    {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement message = wait.until(ExpectedConditions.presenceOfElementLocated(linkOfFirstResult));
        Assert.assertTrue(driver.findElement(linkOfFirstResult).getAttribute("href").equals(link));
    }

}
