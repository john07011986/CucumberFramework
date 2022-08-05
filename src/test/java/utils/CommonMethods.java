package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import steps.PageInitializers;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CommonMethods extends PageInitializers {

    public static WebDriver driver;

    public void openBrowserAndLaunchApplication(){
        //first we need to read the data from properties file
        ConfigReader.readProperties(Constants.CONFIGURATION_FILEPATH);

// then we can retrieve values using keys
        //we will use switch function to initialize browser based on the value in the properties file
        switch (ConfigReader.getProperties("browser")){
            case "chrome":
                //set headless browser
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setHeadless(true);
                WebDriverManager.chromedriver().setup();
                driver=new ChromeDriver(chromeOptions);
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver=new FirefoxDriver();
                break;
            default:
                throw new RuntimeException("Invalid browser name");

        }


        driver.get(ConfigReader.getProperties("url"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
        initializePageObject();

    }

    public static void sendText(WebElement element, String text){
        element.clear();
        element.sendKeys(text);
    }

    // this method will use wait from get wair and will execute explicit wait
    public static  void waitForClickability(WebElement element){
        getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

// we have WebDriverWait as a class that can be returned
public static WebDriverWait getWait (){
        WebDriverWait wait = new WebDriverWait(driver, Constants.EXPLICIT_WAIT);
        return wait;
}

public static void click(WebElement element){
        waitForClickability(element);
        element.click();
}

public static JavascriptExecutor getJSExecutor(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js;
}

public static void jsClick(WebElement element){
        getJSExecutor().executeScript("arguments[0].click();", element);

}

public static void tearDown(){
        driver.quit();
}
    //to store screenshots we will create directory at the project level, not just src leve
//return is the arey of bytes
    public static byte[] takeScreenshot(String fileName){
        TakesScreenshot ts = (TakesScreenshot) driver;
        byte[] picBytes = ts.getScreenshotAs(OutputType.BYTES);
        File sourceFile = ts.getScreenshotAs(OutputType.FILE);

        try {

            //we use file utils to copy dile from source file to new file
            //to avoid file being overriden we will add time by creating get time stamp method
            FileUtils.copyFile(sourceFile, new File(Constants.SCREENSHOT_FILEPATH + fileName
                    //this date format is preddefined
                    + " " + getTimeStamp("yyyy-MM-dd-HH-mm-ss")+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return picBytes;
    }

    public static String getTimeStamp(String pattern){
        Date date = new Date();
        //to format the date according to our choice we want to implement in this function
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }



}
