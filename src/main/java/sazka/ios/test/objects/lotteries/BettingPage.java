package sazka.ios.test.objects.lotteries;

import org.apache.logging.log4j.Level;
import sazka.ios.test.base.PageObject;
import sazka.ios.test.base.annotations.Locator;
import sazka.ios.test.base.driver.Driver;
import sazka.ios.test.components.AnyComponent;
import sazka.ios.test.data.lotteries.Lottery;
import sazka.ios.test.data.lotteries.TicketBox;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BettingPage extends PageObject {

    @Locator(value = "id_numberLabel")
    private AnyComponent number;

    @Locator(value = "//*[@name=\"2. Osudí Vyberte 2 čísla\"]//following::*[@name=\"id_numberLabel\"]", type = Locator.LocatorType.XPATH)
    private AnyComponent additionalNumber;

    @Locator(value = "HOTOVO")
    private AnyComponent doneButton;

    @Locator(value = "DALŠÍ SLOUPEC")
    private AnyComponent nextTicketButton;

    public BettingPage(Driver driver) {
        super(driver);
    }

    protected void betAllTicketBoxesNumbers(Lottery lottery) {
        Iterator<TicketBox> ticketBoxes = lottery.getTicketBoxes().iterator();
        while (ticketBoxes.hasNext()) {
            TicketBox ticketBox = ticketBoxes.next();
            this.betNumbers(ticketBox.getNumbers(), ticketBox.getAdditionalNumbers());
            if (ticketBox.getDeposit() != null && !ticketBox.getDeposit().equals("0")) {
                this.selectDeposit(ticketBox.getDeposit());
            }

            if (ticketBoxes.hasNext()) {
                nextTicketButton.click();
                driver.sleep(1000);
            } else {
                doneButton.click();
            }
        }
    }

    private void betNumbers(List<String> lotteryNumbers, List<String> additionalNumbers) {
        ArrayList<String> betNumbers = new ArrayList<>(lotteryNumbers);
        log(Level.INFO, "Betting numbers: %s", lotteryNumbers);
        for (AnyComponent num : number.findPageComponents(number)) {
            if (betNumbers.contains(num.getText())) {
                num.click();
                betNumbers.remove(num.getText());
            }
        }
        if (additionalNumbers != null && !additionalNumbers.isEmpty()) {
            log(Level.INFO, "Betting additional numbers: %s", additionalNumbers);
            ArrayList<String> betAdditionalNumbers = new ArrayList<>(additionalNumbers);
            for (AnyComponent num : additionalNumber.findPageComponents(additionalNumber)) {
                if (betAdditionalNumbers.contains(num.getText())) {
                    num.click();
                    betAdditionalNumbers.remove(num.getText());
                }
            }
        }
    }

    private void selectDeposit(String deposit) {

    }
}
