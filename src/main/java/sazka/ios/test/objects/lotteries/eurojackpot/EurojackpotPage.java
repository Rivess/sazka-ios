package sazka.ios.test.objects.lotteries.eurojackpot;

import sazka.ios.test.base.driver.Driver;
import sazka.ios.test.objects.lotteries.LotteryPage;

public class EurojackpotPage extends LotteryPage {
    public EurojackpotPage(Driver driver) {
        super(driver);
    }

    public EurojackpotBettingPage openEurojackpotBetting() {
        super.clickBetButton();
        return new EurojackpotBettingPage(driver);
    }
}
