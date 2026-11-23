package testPackage;

import jdk.jfr.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CheckBoxesPageHerokuapp;
import pages.HomePageDuckGo;
import pages.TablesPageWc3schools;
import pages.UploadPageHerokuapp;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestClass {
    WebDriver driver;
    HomePageDuckGo homePageDuckGo;
    CheckBoxesPageHerokuapp checkBoxesPageHerokuapp;
    TablesPageWc3schools tablesPageWc3schools;
    UploadPageHerokuapp uploadPageHerokuapp;


    @BeforeMethod
    public void beforeMethod() throws IOException {
        Properties properties= new Properties();
        FileInputStream fis = new FileInputStream("src/main/resources/environment.properties");
        properties.load(fis);
        if(properties.getProperty("browser").equalsIgnoreCase("Chrome"))
        {
            driver  = new ChromeDriver();
        }
        else if(properties.getProperty("browser").equalsIgnoreCase("Firefox"))
        {
            driver  = new FirefoxDriver();
        }

        homePageDuckGo = new HomePageDuckGo(driver);
        checkBoxesPageHerokuapp= new CheckBoxesPageHerokuapp(driver);
        tablesPageWc3schools = new TablesPageWc3schools(driver);
        uploadPageHerokuapp = new UploadPageHerokuapp(driver);
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
        homePageDuckGo.search("Selenium WebDriver").checkLink("https://www.selenium.dev/documentation/webdriver/",1);
    }

    @Test
    @Description("Search for TestNG on HomePage using FireFox")
    public void test4()
    {
        driver.get("https://duckduckgo.com/");
        homePageDuckGo.search("TestNG").checkText("TestNG Tutorial");
    }

    @Test
    @Description("Search for Cucumber IO on HomePage")
    public void test5()
    {
        driver.get("https://duckduckgo.com/");
        homePageDuckGo.search("Cucumber IO").checkLink("https://www.linkedin.com",2);
    }

    @Test
    @Description("Check Checkbox 1 in the-internet.herokuapp.com/checkboxes website")
    public void test6()
    {
        driver.get("http://the-internet.herokuapp.com/checkboxes");
        checkBoxesPageHerokuapp.clickCheckbox(1).checkCheckBoxStatus(1,"checked").checkCheckBoxStatus(2,"checked");
    }

    @Test
    @Description("Assert that the Country for the Company [Ernst Handel] is [Austria]")
    public void test7()
    {
        driver.get("https://www.w3schools.com/html/html_tables.asp");
        tablesPageWc3schools.clickDeclineCookies().assertCompanyText("Ernst Handel").assertCountryText("Austria");
    }

    @Test
    @Description("Assert that the file was uploaded successfully")
    public void test8()
    {
        driver.get("http://the-internet.herokuapp.com/upload");
        uploadPageHerokuapp.chooseFile("eso1907a.jpg").upload().assertUploadSuccess("eso1907a.jpg");
    }

    @AfterMethod
    public void afterMethod()
    {
        driver.quit();
    }

}
