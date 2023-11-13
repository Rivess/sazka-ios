package sazka.ios.test.objects.lotteries.sportka;

import sazka.ios.test.base.driver.Driver;
import sazka.ios.test.objects.lotteries.LotteryPage;

public class SportkaPage extends LotteryPage {

    public SportkaPage(Driver driver) {
        super(driver);
    }

    public SportkaBettingPage openSportkaBetting() {
        super.clickBetButton();
        return new SportkaBettingPage(driver);
    }
}
