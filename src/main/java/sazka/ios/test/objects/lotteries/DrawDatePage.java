package sazka.ios.test.objects.lotteries;

import org.apache.logging.log4j.Level;
import sazka.ios.test.base.PageObject;
import sazka.ios.test.base.annotations.Locator;
import sazka.ios.test.base.driver.Driver;
import sazka.ios.test.base.errors.IOSTestException;
import sazka.ios.test.components.AnyComponent;
import sazka.ios.test.data.lotteries.Lottery;
import sazka.ios.test.utils.TestDataUtils;

import java.util.ArrayList;
import java.util.List;

public class DrawDatePage extends PageObject {

    @Locator(value = "JEDNORÁZOVĚ")
    private AnyComponent single;

    @Locator(value = "PŘEDPLATNÉ")
    private AnyComponent multiDraw;

    @Locator(value = "id_radioTitleLabel")
    private AnyComponent drawDay;

    @Locator(value = "Můžete vybrat jednu možnost")
    private AnyComponent singleChoiceTitle;

    @Locator(value = "POKRAČOVAT")
    private AnyComponent continueButton;

    @Locator(value = "id_numberLabel")
    private AnyComponent drawCount;

    public DrawDatePage(Driver driver) {
        super(driver);
    }

    public BetSummaryPage selectDrawCountAndDrawDate(Lottery lottery) {
        this.selectDrawCount(lottery);
        this.selectDrawDate(lottery);
        this.clickContinueButton();
        return new BetSummaryPage(driver);
    }

    protected void selectDrawDate(Lottery lottery) {
        List<String> drawDaysNames = lottery.getDrawDaysNames();
        List<AnyComponent> drawDays = drawDay.waitUntilPresent().findPageComponents(drawDay);
        log(Level.INFO, "Selecting draw days: %s", drawDaysNames);
        if (singleChoiceTitle.isDisplayed(3)) {
            drawDays.stream()
                    .filter(day -> TestDataUtils.containsAllWords(day.getText().toLowerCase(), drawDaysNames))
                    .findFirst()
                    .orElseThrow(() -> new IOSTestException("Could not find draw date with values: %s", drawDaysNames))
                    .click();
        } else {
            ArrayList<String> mutableDrawDaysNames = new ArrayList<>(drawDaysNames);
            drawDays.forEach(day -> {
                if (mutableDrawDaysNames.contains(day.getText().toLowerCase())) {
                    day.click();
                    mutableDrawDaysNames.remove(day.getText().toLowerCase());
                }
            });
        }
    }

    protected void selectDrawCount(Lottery lottery) {
        if (lottery.getDrawCountInt() > 1) {
            multiDraw.click();
            driver.sleep(2000);
            List<AnyComponent> drawCounts = drawCount.waitUntilPresent().findPageComponents(drawCount);
            drawCounts.stream()
                      .filter(count -> count.getText().equals(lottery.getDrawCount()))
                      .findFirst()
                      .orElseThrow(() -> new IOSTestException("Could not find draw count with value: %s", lottery.getDrawCount()))
                      .click();
        }
    }

    protected void clickContinueButton() {
        continueButton.click();
    }
}
