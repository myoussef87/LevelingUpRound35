package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;


public class TablesPageWc3schools {
    private final WebDriver driver;
    public TablesPageWc3schools(WebDriver driver) {
        this.driver = driver;
    }

    // The locators for these elements should only be defined once.
    By btnCookieDecline = new By.ByXPath("//button[@class='fast-cmp-button-secondary']");
    By iFrame = new By.ById("fast-cmp-iframe");
    By txtThirdRowFirstColumnInTable = new By.ByXPath("//table[@class='ws-table-all']/tbody/tr[4]/td[1]");
    By txtThirdRowThirdColumnInTable = new By.ByXPath("//table[@class='ws-table-all']/tbody/tr[4]/td[3]");

    //Actions
    public TablesPageWc3schools clickDeclineCookies(){
        WebElement iframe = driver.findElement(iFrame);
        driver.switchTo().frame(iframe);
        driver.findElement(btnCookieDecline).click();
        driver.switchTo().defaultContent();
        return new TablesPageWc3schools(driver);
    }

    //Assertions
    public TablesPageWc3schools assertCompanyText(String text)
    {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(txtThirdRowFirstColumnInTable));
        System.out.println(driver.findElement(txtThirdRowFirstColumnInTable).getText());
        Assert.assertTrue(driver.findElement(txtThirdRowFirstColumnInTable).getText().equals(text));
        return new TablesPageWc3schools(driver);
    }
    public TablesPageWc3schools assertCountryText(String text)
    {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(txtThirdRowThirdColumnInTable));
        System.out.println(driver.findElement(txtThirdRowThirdColumnInTable).getText());
        Assert.assertTrue(driver.findElement(txtThirdRowThirdColumnInTable).getText().equals(text));
        return new TablesPageWc3schools(driver);
    }
}
