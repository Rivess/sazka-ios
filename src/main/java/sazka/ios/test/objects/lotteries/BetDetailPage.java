package sazka.ios.test.objects.lotteries;

import sazka.ios.test.base.PageObject;
import sazka.ios.test.base.annotations.Locator;
import sazka.ios.test.base.driver.Driver;
import sazka.ios.test.base.errors.IOSTestException;
import sazka.ios.test.components.AnyComponent;
import sazka.ios.test.data.lotteries.Lottery;
import sazka.ios.test.data.lotteries.TicketBox;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BetDetailPage extends PageObject {

    @Locator(value = "id_Datum slosování_valueTextLabel")
    private AnyComponent drawDate;

    @Locator(value = "id_Počet slosování_valueTextLabel")
    private AnyComponent drawCount;

    @Locator(value = "id_Vsazeno na_valueTextLabel")
    private AnyComponent betOnDate;

    @Locator(value = "id_Číslo sázky_valueTextLabel")
    private AnyComponent betId;

    @Locator(value = "id_Cena sázky_valueTextLabel")
    private AnyComponent betPrice;

    @Locator(value = "//*[contains(@name, \"id_Sloupec\")]", type = Locator.LocatorType.XPATH)
    private AnyComponent ticketBoxNumbers;

    @Locator(value = "id_Extra 6_valueTextLabel")
    private AnyComponent chanceNumbers;

    @Locator(value = "VSADIT ZNOVU")
    private AnyComponent betAgainButton;

    @Locator(value = "PŘIDAT K OBLÍBENÝM")
    private AnyComponent addToFavoriteButton;

    @Locator(value = "ZRUŠIT SÁZKU")
    private AnyComponent cancelBetButton;

    public BetDetailPage(Driver driver) {
        super(driver);
    }

    public BetDetailPage verifyDrawCount(Lottery lottery) {
        drawCount.verifyContainsText(lottery.getDrawCount());
        return this;
    }

    public BetDetailPage verifyBetOnDate(Lottery lottery) {
        for (String dayName : lottery.getDrawDaysNames()) {
            betOnDate.verifyContainsText(dayName, true);
        }
        return this;
    }

    public BetDetailPage saveBetId(Lottery lottery) {
        lottery.setId(betId.waitUntilPresent().getText());
        return this;
    }

    public BetDetailPage verifyBetNumbers(Lottery lottery) {
        List<AnyComponent> allNumbers = ticketBoxNumbers.findPageComponents(ticketBoxNumbers);
        List<TicketBox> ticketBoxes = lottery.getTicketBoxes();
        for (int i = 0; i < allNumbers.size(); i++) {
            this.compareNumbers(ticketBoxes.get(i), allNumbers.get(i));
        }
        return this;
    }

    private void compareNumbers(TicketBox ticketBox, AnyComponent numbersComponent) {
        String numbersString = numbersComponent.getText().replace("Číslo", "").split("(\\r?\\n|V|K)")[0];
        List<String> actualNumbers = Stream.of(numbersString).map(String::trim).collect(Collectors.toList());
        if (!ticketBox.getAdditionalNumbers().isEmpty()) {
            actualNumbers = Stream.of(numbersString.split("-")[0]).map(String::trim).collect(Collectors.toList());
            List<String> actualAdditionalNumbers = Stream.of(numbersString.split("-")[1]).map(String::trim).collect(Collectors.toList());
            if (!ticketBox.getAdditionalNumbers().equals(actualAdditionalNumbers)) {
                throw new IOSTestException("Expected additional numbers: %s does not equal to actual additional numbers: %s",
                                           ticketBox.getAdditionalNumbers(),
                                           actualAdditionalNumbers);
            }
        }
        if (!ticketBox.getNumbers().equals(actualNumbers)) {
            throw new IOSTestException("Expected numbers: %s does not equal to actual numbers: %s", ticketBox.getNumbers(), actualNumbers);
        }
    }
}
