package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;


public class DropabblePageJquery {
    private final WebDriver driver;
    public DropabblePageJquery(WebDriver driver) {
        this.driver = driver;
    }

    // The locators for these elements should only be defined once.
    private final By draggable = new By.ById("draggable");
    private final By droppable = new By.ById("droppable");
    private final By txtDropped = new By.ByXPath("//div[@id='droppable']/p");


    //Actions
    public DropabblePageJquery dragsToElement(){
        new Actions(driver)
                .dragAndDrop(driver.findElement(draggable), driver.findElement(droppable))
                .perform();
        return this;
    }


    //Assertions
    public DropabblePageJquery assertThatElementIsDropped()
    {
        Assert.assertTrue(driver.findElement(txtDropped).getText().equalsIgnoreCase("Dropped!"));
        return this;

    }

}
