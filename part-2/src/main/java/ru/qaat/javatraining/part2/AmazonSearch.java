package ru.qaat.javatraining.part2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class AmazonSearch {
    public static final int DRIVER_PAGE_LOAD_TIMEOUT_SECONDS = 20;
    public static final int DRIVE_IMPLICIT_WAIT_TIMEOUT_SECONDS = 5;

    @Test
    public void searchInAmazon(){
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("marionette", true);
        WebDriver driver = new FirefoxDriver(capabilities);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(DRIVER_PAGE_LOAD_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(DRIVE_IMPLICIT_WAIT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        driver.get("http://amazon.com");

        final By SEARCH_INPUT_LOCATOR = By.id("twotabsearchtextbox");
        final By SUBMIT_BUTTON_LOCATOR = By.className("nav-input");
        WebElement input = driver.findElement(SEARCH_INPUT_LOCATOR);
        WebElement submit = driver.findElement(SUBMIT_BUTTON_LOCATOR);
        new WebDriverWait(driver, 5000).until(ExpectedConditions.elementToBeClickable(SUBMIT_BUTTON_LOCATOR));
        input.sendKeys("Book");
        submit.click();
        driver.close();
    }
}
