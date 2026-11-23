package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;


public class SearchResultsPageDuckGo {
    private final WebDriver driver;
    public SearchResultsPageDuckGo(WebDriver driver) {
        this.driver = driver;
    }

    // The locators for these elements should only be defined once.
    By txtOfFourthResult= new By.ByXPath("//li[@data-layout='organic']/article[@id='r1-3']//a[@data-testid='result-title-a']/span");
    By lnkResults;
    //Actions

    //Assertions
    public void checkLink(String link, int lnkOrder)
    {
        lnkResults = By.xpath("//li[@data-layout='organic']/article[@id='r1-"+(lnkOrder-1)+"']//a[@data-testid='result-extras-url-link']");
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(lnkResults));
        System.out.println(driver.findElement(lnkResults).getText());
        Assert.assertTrue(driver.findElement(lnkResults).getAttribute("href").contains(link));
    }
    public void checkText(String text)
    {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(txtOfFourthResult));
        System.out.println(driver.findElement(txtOfFourthResult).getText());
        Assert.assertTrue(driver.findElement(txtOfFourthResult).getText().equals(text));
    }

}
