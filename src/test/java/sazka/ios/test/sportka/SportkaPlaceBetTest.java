package sazka.ios.test.sportka;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import sazka.ios.test.IOSTest;
import sazka.ios.test.data.User;
import sazka.ios.test.data.lotteries.Sportka;
import sazka.ios.test.objects.HomePage;
import sazka.ios.test.utils.TestDataUtils;
import sazka.ios.test.utils.UserUtils;

public class SportkaPlaceBetTest extends IOSTest {

    @DataProvider(name = "sportka")
    public Object[][] dataProvider() {
        return TestDataUtils.loadSportkaTestData(TestDataUtils.SportkaFiles.SPORTKA_PLACE_BET_CSV);
    }

    @Test(dataProvider = "sportka")
    public void sportkaPlaceBet(Sportka sportka) {
        User user = UserUtils.createUser(UserUtils.UserFile.WALLERT);
        HomePage homePage = new HomePage(driver);
        homePage.clickOnLogin()
                .login(user)
                .clickOnSportka()
                .openBettingPage()
                .placeBetNumbers(sportka)
                .selectDrawCountAndDrawDate(sportka)
                .verifyBetSummary(sportka)
                .placeBet()
                .openMyBets()
                .openFirstEurojackpotBet()
                .saveBetId(sportka)
                .verifyDrawCount(sportka)
                .verifyBetOnDate(sportka)
                .verifyBetNumbers(sportka);
    }
}
