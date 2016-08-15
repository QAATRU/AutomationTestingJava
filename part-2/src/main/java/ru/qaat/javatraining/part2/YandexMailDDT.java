package ru.qaat.javatraining.part2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class YandexMailDDT {
    public static final int DRIVER_PAGE_LOAD_TIMEOUT_SECONDS = 20;
    public static final int DRIVE_IMPLICIT_WAIT_TIMEOUT_SECONDS = 5;
    final By LOGIN_LOCATOR = By.name("login");
    final By PASSWORD_LOCATOR = By.name("passwd");
    final By SUBMIT_LOCATOR = By.xpath("//span[text()='Войти']/ancestor::button");
    final By ERROR_MESSAGE_LOCATOR = By.className("error-msg");
    final By ERROR_TYPE_LOCATOR = By.className("error-hint");

    @Test(dataProvider = "passwordsForDDT")
    public void yandexMailNegativeTest(String password) {
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
        passwordInput.sendKeys(password);
        submitButton.click();
        WebElement errorMessage = driver.findElement(ERROR_MESSAGE_LOCATOR);
        WebElement errorType = driver.findElement(ERROR_TYPE_LOCATOR);
        new WebDriverWait(driver, 5000).until(ExpectedConditions.elementToBeClickable(errorMessage));
        new WebDriverWait(driver, 5000).until(ExpectedConditions.elementToBeClickable(errorType));
        Assert.assertEquals(errorMessage.getText(), "Неправильный логин или пароль.");
        Assert.assertEquals(errorType.getText(), "Возможно у вас выбрана другая раскладка клавиатуры или нажата клавиша \"Caps Lock\".");
        driver.close();
    }

    @DataProvider(name = "passwordsForDDT")
    public static Object[][] valuesForCheck() {
        return new Object[][]{
                {"1234567890"},
                {"qwertyuiop"},
                {"1234qwer"},
                {"1q2e3r4t"}
        };
    }
}
