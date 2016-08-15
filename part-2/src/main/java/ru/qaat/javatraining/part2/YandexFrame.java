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

public class YandexFrame {
    public static final int DRIVER_PAGE_LOAD_TIMEOUT_SECONDS = 20;
    public static final int DRIVE_IMPLICIT_WAIT_TIMEOUT_SECONDS = 5;
    final By LOGIN_LOCATOR = By.name("login");
    final By PASSWORD_LOCATOR = By.name("passwd");
    final By SUBMIT_LOCATOR = By.xpath("//span[text()='Войти']/ancestor::button");
    final By WRIGHT_MAIL_LOCATOR = By.xpath("//a[@href='#compose']");
    final By ADDRESS_LOCATOR = By.xpath("//*[@data-params='field=to']//ancestor::tr//input[@type='text']");
    final By SUBJECT_LOCATOR = By.name("subj");
    final By POST_LOCATOR = By.cssSelector("body#tinymce");
    final By POST_SUBMIT_LOCATOR = By.cssSelector("#compose-submit");
    @Test
    public void frameInYandexMail(){
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("marionette", true);
        WebDriver driver = new FirefoxDriver(capabilities);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(DRIVER_PAGE_LOAD_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(DRIVE_IMPLICIT_WAIT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        driver.get("http://yandex.ru/");
        WebElement loginInput = driver.findElement(LOGIN_LOCATOR);
        WebElement passwordInput = driver.findElement(PASSWORD_LOCATOR);
        WebElement submitButton = driver.findElement(SUBMIT_LOCATOR);
        new WebDriverWait(driver, 5000).until(ExpectedConditions.elementToBeClickable(loginInput));
        new WebDriverWait(driver, 5000).until(ExpectedConditions.elementToBeClickable(passwordInput));
        new WebDriverWait(driver, 5000).until(ExpectedConditions.elementToBeClickable(submitButton));
        loginInput.sendKeys("mailfortraining");
        passwordInput.sendKeys("passwordfortraining");
        submitButton.click();
        WebElement wrightMailButton = driver.findElement(WRIGHT_MAIL_LOCATOR);
        new WebDriverWait(driver, 5000).until(ExpectedConditions.elementToBeClickable(wrightMailButton));
        wrightMailButton.click();
        WebElement addressInput = driver.findElement(ADDRESS_LOCATOR);
        WebElement subjectInput = driver.findElement(SUBJECT_LOCATOR);
        new WebDriverWait(driver, 5000).until(ExpectedConditions.visibilityOf(addressInput));
        new WebDriverWait(driver, 5000).until(ExpectedConditions.visibilityOf(subjectInput));
        addressInput.sendKeys("mailfortraining@yandex.ru");
        subjectInput.sendKeys("Письмо из автотеста");
        driver.switchTo().frame(driver.findElement(By.id("compose-send_ifr")));
        WebElement postInput = driver.findElement(POST_LOCATOR);
        new WebDriverWait(driver, 5000).until(ExpectedConditions.visibilityOf(postInput));
        postInput.sendKeys("Это сообщение отправлено с помощью автотеста!");
        driver.switchTo().defaultContent();
        WebElement postSubmitLocator = driver.findElement(POST_SUBMIT_LOCATOR);
        postSubmitLocator.click();
        driver.close();
    }
}
