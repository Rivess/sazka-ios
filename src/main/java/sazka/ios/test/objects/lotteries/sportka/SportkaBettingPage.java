package sazka.ios.test.objects.lotteries.sportka;

import sazka.ios.test.base.driver.Driver;
import sazka.ios.test.data.lotteries.Sportka;
import sazka.ios.test.objects.lotteries.BettingPage;

public class SportkaBettingPage extends BettingPage {
    public SportkaBettingPage(Driver driver) {
        super(driver);
    }

    public SportkaDrawDatePage betSportkaNumbers(Sportka sportka) {
        super.betAllTicketBoxesNumbers(sportka);
        return new SportkaDrawDatePage(driver);
    }
}
