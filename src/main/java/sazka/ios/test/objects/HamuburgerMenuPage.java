package sazka.ios.test.objects;

import sazka.ios.test.base.PageObject;
import sazka.ios.test.base.annotations.Locator;
import sazka.ios.test.base.driver.Driver;
import sazka.ios.test.components.AnyComponent;

public class HamuburgerMenuPage extends PageObject {

    @Locator(value = "DOMŮ")
    private AnyComponent homeButton;

    public HamuburgerMenuPage(Driver driver) {
        super(driver);
    }

    public HomePage home() {
        homeButton.click();
        return new HomePage(driver);
    }
}
