package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.File;


public class UploadPageHerokuapp {
    private final WebDriver driver;
    public UploadPageHerokuapp(WebDriver driver) {
        this.driver = driver;
    }

    // The locators for these elements should only be defined once.
    private final By btnChooseFile = new By.ByXPath("//input[@id='file-upload']");
    private final By btnUpload = new By.ByXPath("//input[@id='file-submit']");
    private final By txtUploadSuccess = new By.ByXPath("//div[@class='example']/h3");
    private final By txtFileName= new By.ByXPath("//div[@class='example']/div");
    File uploadFile ;

    //Actions
    public UploadPageHerokuapp chooseFile(String filePath){
        uploadFile = new File(filePath);
        WebElement fileInput = driver.findElement(btnChooseFile);
        fileInput.sendKeys(uploadFile.getAbsolutePath());
        return this;
    }

    public UploadPageHerokuapp upload(){
        driver.findElement(btnUpload).click();
        return this;
    }


    //Assertions
    public UploadPageHerokuapp assertUploadSuccess(String fileName)
    {
        Assert.assertTrue(driver.findElement(txtUploadSuccess).getText().equals("File Uploaded!"));
        Assert.assertTrue(driver.findElement(txtFileName).getText().equals(fileName));
        return this;
    }

}
