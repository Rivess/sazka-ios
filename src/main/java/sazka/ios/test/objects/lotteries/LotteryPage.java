package sazka.ios.test.objects.lotteries;

import sazka.ios.test.base.PageObject;
import sazka.ios.test.base.annotations.Locator;
import sazka.ios.test.base.driver.Driver;
import sazka.ios.test.components.AnyComponent;

public class LotteryPage extends PageObject {

    @Locator(value = "VSAĎTE SI")
    private AnyComponent betButton;

    public LotteryPage(Driver driver) {
        super(driver);
    }

    protected void clickBetButton() {
        betButton.click();
    }
}
