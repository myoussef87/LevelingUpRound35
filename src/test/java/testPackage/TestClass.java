package testPackage;

import jdk.jfr.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;

public class TestClass {
    WebDriver driver;
    HomePage homePage;

    @BeforeMethod
    public void beforeMethod()
    {
        driver  = new ChromeDriver();
        homePage= new HomePage(driver);
    }

    @Test
    @Description("Assert Home Page Title")
    public void test1()
    {
        driver.get("https://duckduckgo.com/");
        homePage.checkTitle("Google");
    }

    @Test
    @Description("Assert Logo on HomePage")
    public void test2()
    {
        driver.get("https://duckduckgo.com/");
        homePage.checkPresenceOfLogo();
    }

    @Test
    @Description("Search for Selenium on HomePage")
    public void test3()
    {
        driver.get("https://duckduckgo.com/");
        homePage.search("Selenium WebDriver").checkLink("https://www.selenium.dev/documentation/webdriver/");
    }

    @AfterMethod
    public void afterMethod()
    {
        driver.quit();
    }


}
