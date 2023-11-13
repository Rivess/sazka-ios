package sazka.ios.test.objects.lotteries.eurojackpot;

import sazka.ios.test.base.driver.Driver;
import sazka.ios.test.data.lotteries.Eurojackpot;
import sazka.ios.test.objects.lotteries.BettingPage;

public class EurojackpotBettingPage extends BettingPage {
    public EurojackpotBettingPage(Driver driver) {
        super(driver);
    }

    public EurojackpotDrawDatePage betEurojackpotNumbers(Eurojackpot eurojackpot) {
        super.betAllTicketBoxesNumbers(eurojackpot);
        return new EurojackpotDrawDatePage(driver);
    }
}
