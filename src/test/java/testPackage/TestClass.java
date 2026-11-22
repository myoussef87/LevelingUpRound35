package testPackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestClass {
    WebDriver driver;


    @BeforeMethod
    public void beforeMethod()
    {
        driver  = new ChromeDriver();
    }

    @Test
    public void test1()
    {
        driver.get("https://duckduckgo.com/");

    }

    @AfterMethod
    public void afterMethod()
    {
        driver.quit();
    }


}
