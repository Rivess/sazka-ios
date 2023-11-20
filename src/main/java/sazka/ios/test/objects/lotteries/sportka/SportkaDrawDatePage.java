package sazka.ios.test.objects.lotteries.sportka;

import sazka.ios.test.base.driver.Driver;
import sazka.ios.test.data.lotteries.Sportka;
import sazka.ios.test.objects.lotteries.DrawDatePage;

public class SportkaDrawDatePage extends DrawDatePage {
    public SportkaDrawDatePage(Driver driver) {
        super(driver);
    }

    public SportkaBetSummaryPage selectDrawDate(Sportka sportka) {
        super.selectDrawDate(sportka);
        super.clickContinueButton();
        return new SportkaBetSummaryPage(driver);
    }

    public SportkaDrawDatePage selectDrawCount(Sportka sportka) {
        super.selectDrawCount(sportka);
        return this;
    }
}
