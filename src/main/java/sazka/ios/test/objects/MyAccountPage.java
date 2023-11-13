package sazka.ios.test.objects;

import sazka.ios.test.base.PageObject;
import sazka.ios.test.base.annotations.Locator;
import sazka.ios.test.base.driver.Driver;
import sazka.ios.test.components.AnyComponent;
import sazka.ios.test.objects.HomePage;

public class MyAccountPage extends PageObject {

    @Locator(value = "ODHLÁSIT SE")
    private AnyComponent logoutButton;

    public MyAccountPage(Driver driver) {
        super(driver);
    }

    public HomePage logoutUser() {
        logoutButton.click();
        return new HomePage(driver);
    }
}
