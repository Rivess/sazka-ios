package sazka.ios.test;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import sazka.ios.test.base.ObjectFactory;
import sazka.ios.test.objects.HomePage;

import java.time.Duration;

public class LoginTest extends IOSTest {

    @Test
    public void login() {
        HomePage homePage = ObjectFactory.createObject(driver, HomePage.class);
        homePage.clickOnLogin();
    }
}
