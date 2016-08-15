package ru.qaat.javatraining.part2;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.ActionChainExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static ru.qaat.javatraining.part2.AmazonSearch.DRIVER_PAGE_LOAD_TIMEOUT_SECONDS;
import static ru.qaat.javatraining.part2.AmazonSearch.DRIVE_IMPLICIT_WAIT_TIMEOUT_SECONDS;

public class AmazonActionExample {
    @Test
    public void actionExample()
    {
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("marionette", true);
        WebDriver driver = new FirefoxDriver(capabilities);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(DRIVER_PAGE_LOAD_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(DRIVE_IMPLICIT_WAIT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        driver.get("http://amazon.com");
        final By SUBMIT_BUTTON_LOCATOR = By.className("nav-input");
        WebElement submit = driver.findElement(SUBMIT_BUTTON_LOCATOR);
        new WebDriverWait(driver, 5000).until(ExpectedConditions.elementToBeClickable(SUBMIT_BUTTON_LOCATOR));
        Actions action = new Actions(driver);
        action.contextClick(submit).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).build().perform();
        action.sendKeys(Keys.ALT).sendKeys(Keys.F4).build().perform();
        driver.quit();
    }
}
