package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;


public class CheckBoxesPageHerokuapp {
    private final WebDriver driver;
    public CheckBoxesPageHerokuapp(WebDriver driver) {
        this.driver = driver;
    }

    // The locators for these elements should only be defined once.
    private final By chkbox1 = new By.ByXPath("//form[@id='checkboxes']/input[1]");
    private final By chkbox2 = new By.ByXPath("//form[@id='checkboxes']/input[2]");


    //Actions
    public CheckBoxesPageHerokuapp clickCheckbox(int checkBoxNumber){
        if (checkBoxNumber == 1) {
            driver.findElement(chkbox1).click();
        }
        else if (checkBoxNumber == 2) {driver.findElement(chkbox2).click();}
        return this;
    }


    //Assertions
    public CheckBoxesPageHerokuapp checkCheckBoxStatus(int CheckBoxNumber , String status)
    {
        if(status.equals("checked" )&& CheckBoxNumber==1){
        Assert.assertTrue(driver.findElement(chkbox1).isSelected());}
        else if(status.equals("checked" )&& CheckBoxNumber==2){
            Assert.assertTrue(driver.findElement(chkbox2).isSelected());}
        return this;
    }

}
