package sazka.ios.test.objects;

import sazka.ios.test.base.PageObject;
import sazka.ios.test.base.annotations.Locator;
import sazka.ios.test.base.driver.Driver;
import sazka.ios.test.components.AnyComponent;

public class HomePage extends PageObject {

    @Locator(value = "unlogged user", type = Locator.LocatorType.ID)
    private AnyComponent loginButton;

    public HomePage(Driver driver) {
        super(driver);
    }

    public LoginPage clickOnLogin() {
        loginButton.click();
        return new LoginPage(driver);
    }
}
