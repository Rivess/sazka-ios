package sazka.ios.test.objects;

import sazka.ios.test.base.PageObject;
import sazka.ios.test.base.annotations.Locator;
import sazka.ios.test.base.driver.Driver;
import sazka.ios.test.components.RandomComponent;

public class HomePage extends PageObject {

    @Locator(value = "unlogged user", type = Locator.LocatorType.ID)
    private RandomComponent loginButton;

    public HomePage(Driver driver) {
        super(driver);
    }

    public void clickOnLogin() {
        if (loginButton.verifyMyText("LALA")) {
            System.out.println("ANO");
        } else {
            System.out.println("NE");
        }
        loginButton.click();
    }
}
