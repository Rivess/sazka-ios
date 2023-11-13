package sazka.ios.test.objects.lotteries.eurojackpot;

import sazka.ios.test.base.driver.Driver;
import sazka.ios.test.data.lotteries.Eurojackpot;
import sazka.ios.test.objects.lotteries.DrawDatePage;

public class EurojackpotDrawDatePage extends DrawDatePage {

    public EurojackpotDrawDatePage(Driver driver) {
        super(driver);
    }

    public EurojackpotBetSummaryPage selectDrawDate(Eurojackpot eurojackpot) {
        super.selectDrawDate(eurojackpot);
        super.clickContinueButton();
        return new EurojackpotBetSummaryPage(driver);
    }

    public EurojackpotDrawDatePage selectDrawCount(Eurojackpot eurojackpot) {
        super.selectDrawCount(eurojackpot);
        return this;
    }
}
