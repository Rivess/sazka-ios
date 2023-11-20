package sazka.ios.test.objects.lotteries;

import sazka.ios.test.base.PageObject;
import sazka.ios.test.base.annotations.Locator;
import sazka.ios.test.base.driver.Driver;
import sazka.ios.test.components.AnyComponent;

public class MyBetsPage extends PageObject {

    @Locator(value = "Eurojackpot")
    private AnyComponent eurojackpotBet;

    public MyBetsPage(Driver driver) {
        super(driver);
    }

    public BetDetailPage openFirstEurojackpotBet() {
        eurojackpotBet.click();
        return new BetDetailPage(driver);
    }
}
