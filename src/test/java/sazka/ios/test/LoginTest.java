package sazka.ios.test;

import org.testng.annotations.Test;
import sazka.ios.test.data.User;
import sazka.ios.test.objects.HomePage;
import sazka.ios.test.utils.UserUtils;

public class LoginTest extends IOSTest {

    @Test
    public void login() {
        HomePage homePage = new HomePage(driver);
        User user = UserUtils.createUser(UserUtils.UserFile.WALLERT);
        homePage.clickOnLogin()
                .login(user);
    }
}
