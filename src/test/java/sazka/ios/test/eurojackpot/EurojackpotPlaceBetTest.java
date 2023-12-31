package sazka.ios.test.eurojackpot;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import sazka.ios.test.IOSTest;
import sazka.ios.test.data.User;
import sazka.ios.test.data.lotteries.Eurojackpot;
import sazka.ios.test.objects.HomePage;
import sazka.ios.test.utils.TestDataUtils;
import sazka.ios.test.utils.UserUtils;

public class EurojackpotPlaceBetTest extends IOSTest {

    @DataProvider(name = "eurojackpot")
    public Object[][] dataProvider() {
        return TestDataUtils.loadEurojackpotTestData(TestDataUtils.EurojackpotFiles.EUROJACKPOT_PLACE_BET_CSV);
    }

    @Test(dataProvider = "eurojackpot")
    public void eurojackpotPlaceBet(Eurojackpot eurojackpot) {
        User user = UserUtils.createUser(UserUtils.UserFile.WALLERT);
        HomePage homePage = new HomePage(driver);
        homePage.clickOnLogin()
                .login(user)
                .clickOnEurojackpot()
                .openBettingPage()
                .placeBetNumbers(eurojackpot)
                .selectDrawCountAndDrawDate(eurojackpot)
                .setChanceNumbers(eurojackpot)
                .verifyBetSummary(eurojackpot)
                .placeBet()
                .openMyBets()
                .openFirstEurojackpotBet()
                .saveBetId(eurojackpot)
                .verifyDrawCount(eurojackpot)
                .verifyBetOnDate(eurojackpot)
                .verifyBetNumbers(eurojackpot);
    }
}
