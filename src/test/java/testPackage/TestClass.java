package testPackage;

import jdk.jfr.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.Properties;

public class TestClass {
    WebDriver driver;
    HomePageDuckGo homePageDuckGo;
    CheckBoxesPageHerokuapp checkBoxesPageHerokuapp;
    TablesPageWc3schools tablesPageWc3schools;
    UploadPageHerokuapp uploadPageHerokuapp;
    DropabblePageJquery dropabblePageJquery;
    Properties properties= new Properties();
    FileInputStream fis;

    {
        try {
            fis = new FileInputStream("src/main/resources/environment.properties");
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @BeforeMethod
    public void beforeMethod() throws MalformedURLException {
        if(properties.getProperty("TargetBrowser").equalsIgnoreCase("Chrome"))
        {
            //driver  = new ChromeDriver();

            ChromeOptions options = new ChromeOptions();
            driver = new RemoteWebDriver(
                    URI.create("http://localhost:4444").toURL(),
                    options
            );
        }
        else if(properties.getProperty("TargetBrowser").equalsIgnoreCase("Firefox"))
        {
            driver  = new FirefoxDriver();

            FirefoxOptions options = new FirefoxOptions();
            driver = new RemoteWebDriver(
                    URI.create("http://localhost:4444").toURL(),
                    options
            );
        }
        homePageDuckGo = new HomePageDuckGo(driver);
        checkBoxesPageHerokuapp= new CheckBoxesPageHerokuapp(driver);
        tablesPageWc3schools = new TablesPageWc3schools(driver);
        uploadPageHerokuapp = new UploadPageHerokuapp(driver);
        dropabblePageJquery = new DropabblePageJquery(driver);
    }

    @Test
    @Description("Assert Home Page Title")
    public void test1()
    {
        driver.get("https://duckduckgo.com/");
        homePageDuckGo.checkTitle("Google");
    }

    @Test
    @Description("Assert Logo on HomePage")
    public void test2()
    {
        driver.get("https://duckduckgo.com/");
        homePageDuckGo.checkPresenceOfLogo();
    }

    @Test
    @Description("Search for Selenium on HomePage")
    public void test3()
    {
        driver.get("https://duckduckgo.com/");
        homePageDuckGo.search("Selenium WebDriver")
                .checkLink("https://www.selenium.dev/documentation/webdriver/",1);
    }

    @Test
    @Description("Search for TestNG on HomePage using FireFox")
    public void test4()
    {
        driver.get("https://duckduckgo.com/");
        homePageDuckGo.search("TestNG")
                .checkText("TestNG Tutorial");
    }

    @Test
    @Description("Search for Cucumber IO on HomePage")
    public void test5()
    {
        driver.get("https://duckduckgo.com/");
        homePageDuckGo.search("Cucumber IO")
                .checkLink("https://www.linkedin.com",2);
    }

    @Test
    @Description("Check Checkbox 1 in the-internet.herokuapp.com/checkboxes website")
    public void test6()
    {
        driver.get("http://the-internet.herokuapp.com/checkboxes");
        checkBoxesPageHerokuapp.clickCheckbox(1)
                .checkCheckBoxStatus(1,"checked")
                .checkCheckBoxStatus(2,"checked");
    }

    @Test
    @Description("Assert that the Country for the Company [Ernst Handel] is [Austria]")
    public void test7()
    {
        driver.get("https://www.w3schools.com/html/html_tables.asp");
        tablesPageWc3schools.clickDeclineCookies()
                .assertCompanyText("Ernst Handel")
                .assertCountryText("Austria");
    }

    @Test
    @Description("Assert that the file was uploaded successfully")
    public void test8()
    {
        driver.get("http://the-internet.herokuapp.com/upload");
        uploadPageHerokuapp.chooseFile("eso1907a.jpg")
                .upload()
                .assertUploadSuccess("eso1907a.jpg");
    }

    @Test
    @Description("Assert that the text has been changed to [Dropped!]")
    public void test9()
    {
        driver.get("https://jqueryui.com/resources/demos/droppable/default.html");
        dropabblePageJquery.dragsToElement()
                .assertThatElementIsDropped();
    }

    @Test
    @Description("Search for Selenium on HomePage")
    public void test3again()
    {
        driver.get(properties.getProperty("DuckGoURL"));
        homePageDuckGo.search(properties.getProperty("SearchText"))
                .checkLink(properties.getProperty("ResultLink"),1);
    }
    @AfterMethod
    public void afterMethod()
    {
        driver.quit();
    }

}
