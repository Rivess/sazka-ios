package sazka.ios.test;

import org.testng.annotations.Test;
import sazka.ios.test.base.ObjectFactory;
import sazka.ios.test.objects.HomePage;

public class LoginTest extends IOSTest {

    @Test
    public void login() {
        HomePage homePage = new HomePage(driver);
        homePage.clickOnLogin();
    }
}
