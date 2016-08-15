package ru.qaat.javatraining.part2;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class FastPicAlert {
    public static final int DRIVER_PAGE_LOAD_TIMEOUT_SECONDS = 20;
    public static final int DRIVE_IMPLICIT_WAIT_TIMEOUT_SECONDS = 5;

    @Test
    public void downloadAlert(){
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("marionette", true);
        WebDriver driver = new FirefoxDriver(capabilities);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(DRIVER_PAGE_LOAD_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(DRIVE_IMPLICIT_WAIT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        driver.get("http://fastpic.ru/");
        final By DOWNLOAD_LOCATOR = By.xpath(".//*[@id='uploadButton']");
        WebElement downloadButton = driver.findElement(DOWNLOAD_LOCATOR);
        new WebDriverWait(driver, 5000).until(ExpectedConditions.elementToBeClickable(DOWNLOAD_LOCATOR));
        downloadButton.click();
        Alert alert = driver.switchTo().alert();
        String alertMessage = alert.getText();
        Assert.assertEquals(alertMessage, "Вы не выбрали изображение для загрузки");
        alert.accept();
        driver.close();
    }
}
