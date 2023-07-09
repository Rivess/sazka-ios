package sazka.ios.test;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest extends IOSTest {

    @Test
    public void login() {
        driver.activateApp();
        new WebDriverWait(driver.getDriver(), Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("cz.sazka.loterie.uat:id/btnItem")))
                .click();
    }
}
