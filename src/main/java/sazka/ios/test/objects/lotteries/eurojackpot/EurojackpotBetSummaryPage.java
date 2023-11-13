package sazka.ios.test.objects.lotteries.eurojackpot;

import sazka.ios.test.base.driver.Driver;
import sazka.ios.test.data.lotteries.Eurojackpot;
import sazka.ios.test.objects.lotteries.BetSummaryPage;

public class EurojackpotBetSummaryPage extends BetSummaryPage {
    public EurojackpotBetSummaryPage(Driver driver) {
        super(driver);
    }

    public EurojackpotBetSummaryPage processEurojackpotChance(Eurojackpot eurojackpot) {
        super.processChanceNumbers(eurojackpot);
        return this;
    }

    public EurojackpotBetSummaryPage verifyEurojackpotSummary(Eurojackpot eurojackpot) {
        super.verifyDrawCount(eurojackpot);
        super.verifyDrawDate(eurojackpot);
        super.verifyBetNumbers(eurojackpot);
        super.saveBetPrice(eurojackpot);
        return this;
    }
}
