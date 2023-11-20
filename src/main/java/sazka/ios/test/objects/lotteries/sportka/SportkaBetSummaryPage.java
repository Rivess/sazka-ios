package sazka.ios.test.objects.lotteries.sportka;

import sazka.ios.test.base.driver.Driver;
import sazka.ios.test.data.lotteries.Sportka;
import sazka.ios.test.objects.lotteries.BetSummaryPage;

public class SportkaBetSummaryPage extends BetSummaryPage {
    public SportkaBetSummaryPage(Driver driver) {
        super(driver);
    }

    public SportkaBetSummaryPage processSportkaChance(Sportka sportka) {
        super.processChanceNumbers(sportka);
        return this;
    }

    public SportkaBetSummaryPage verifySportkaSummary(Sportka sportka) {
        super.verifyDrawCount(sportka);
        super.verifyDrawDate(sportka);
        super.verifyBetNumbers(sportka);
        super.saveBetPrice(sportka);
        return this;
    }
}
