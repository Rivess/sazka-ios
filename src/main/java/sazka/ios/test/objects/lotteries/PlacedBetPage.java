package sazka.ios.test.objects.lotteries;

import sazka.ios.test.base.PageObject;
import sazka.ios.test.base.annotations.Locator;
import sazka.ios.test.base.driver.Driver;
import sazka.ios.test.components.AnyComponent;

public class PlacedBetPage extends PageObject {

    @Locator(value = "MOJE SÁZKY")
    private AnyComponent myBetsButton;

    public PlacedBetPage(Driver driver) {
        super(driver);
    }

    public MyBetsPage openMyBets() {
        myBetsButton.clickWhilePresent();
        return new MyBetsPage(driver);
    }
}
