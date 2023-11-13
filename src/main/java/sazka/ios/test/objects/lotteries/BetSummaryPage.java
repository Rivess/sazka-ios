package sazka.ios.test.objects.lotteries;

import org.apache.logging.log4j.Level;
import sazka.ios.test.base.PageObject;
import sazka.ios.test.base.annotations.Locator;
import sazka.ios.test.base.driver.Driver;
import sazka.ios.test.base.errors.IOSTestException;
import sazka.ios.test.components.AnyComponent;
import sazka.ios.test.data.lotteries.Lottery;
import sazka.ios.test.data.lotteries.TicketBox;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BetSummaryPage extends PageObject {

    @Locator(value = "id_Počet slosování_valueTextLabel")
    private AnyComponent drawCount;

    @Locator(value = "id_Vsadit na_valueTextLabel")
    private AnyComponent drawDate;

    @Locator(value = "//*[contains(@name, \"id_Sloupec\")]", type = Locator.LocatorType.XPATH)
    private AnyComponent numbers;

    @Locator(value = "//*[contains(@name, \"id_checkBox\")]", type = Locator.LocatorType.XPATH)
    private AnyComponent chanceCheckbox;

    @Locator(value = "//*[contains(@name, \"id_textCaptionAndValue\")]", type = Locator.LocatorType.XPATH)
    private AnyComponent chanceNumbers;

    @Locator(value = "id_priceBetValue")
    private AnyComponent betPrice;

    @Locator(value = "VSADIT TEĎ")
    private AnyComponent betButton;

    @Locator(value = "ANO, CHCI VSADIT")
    private AnyComponent confirmPlaceBet;

    public BetSummaryPage(Driver driver) {
        super(driver);
    }

    public PlacedBetPage placeBet() {
        betButton.click();
        confirmPlaceBet.click();
        return new PlacedBetPage(driver);
    }

    protected void verifyDrawCount(Lottery lottery) {
        String acutal = drawCount.waitUntilPresent().getNumbersFromText();
        if (!acutal.equals(lottery.getDrawCount())) {
            throw new IOSTestException("Actual draw count value: %s does not equal to expected one: %s", acutal, lottery.getDrawCount());
        }
    }

    protected void verifyDrawDate(Lottery lottery) {
        List<String> drawDates = lottery.getDrawDaysNames();
        drawDates.forEach(date -> drawDate.verifyContainsText(date, true));
    }

    protected void processChanceNumbers(Lottery lottery) {
        String format = lottery.getChanceFormat();
        if (format.equals("N")) {
            if (chanceCheckbox.waitUntilPresent().getAttribute("value").equals("true")) {
                chanceCheckbox.click();
            }
        } else {
            if (chanceCheckbox.waitUntilPresent().getAttribute("value").equals("false")) {
                chanceCheckbox.click();
            }
            int tries = 0;
            int xOffset = -55;
            while (this.getLastTwoFormatted(lottery.getChanceFormat()).equals(this.getLastTwoFormatted(this.getChanceNumber()))) {
                String oldChanceNumbers = this.getChanceNumber();
                chanceNumbers.clickWithOffset(xOffset, 0);
                if (this.getChanceNumber().equals(oldChanceNumbers)) {
                    xOffset += 2;
                    log(Level.INFO, "Chance numbers did not change after clicking, increasing x offset by 2 to: %s", xOffset);
                }

                if (tries++ > 100) {
                    throw new IOSTestException("Could not set correct chance number for format: %s after 100 tries!", lottery.getChanceFormat());
                }
            }

            lottery.setChanceNumber(this.getChanceNumber());
        }
    }

    protected String getChanceNumber() {
        String[] nameAttributes = chanceNumbers.getAttribute("name").split("_");
        return nameAttributes[nameAttributes.length - 1];
    }

    private List<String> getLastTwoFormatted(String chanceNumber) {
        List<String> chanceNumbers = List.of(chanceNumber.split(""));
        chanceNumbers = chanceNumbers.subList(chanceNumbers.size() - 2, chanceNumbers.size());
        List<String> formattedNumbers = new ArrayList<>();
        for (String number : chanceNumbers) {
            if (!number.equals("9") && !number.equals("0"))
                formattedNumbers.add("X");
        }

        return formattedNumbers;
    }

    protected void saveBetPrice(Lottery lottery) {
        lottery.setBetPrice(betPrice.waitUntilPresent().getNumbersFromText());
    }

    protected void verifyBetNumbers(Lottery lottery) {
        List<AnyComponent> allBetNumbersComponents = numbers.waitUntilPresent().findPageComponents(numbers);
        List<TicketBox> ticketBoxes = lottery.getTicketBoxes();
        int index = 0;
        for (AnyComponent betNumbersComponent : allBetNumbersComponents) {
            List<String> expectedAdditionalNumbers = ticketBoxes.get(index).getAdditionalNumbers();
            List<String> expectedNumbers = ticketBoxes.get(index).getNumbers();
            String betNumbersText = betNumbersComponent.getText();
            if (expectedAdditionalNumbers != null && !expectedAdditionalNumbers.isEmpty()) {
                List<String> betAdditionalNumbers = Stream.of(betNumbersText.split("-")[1].split(",")).map(String::trim).collect(Collectors.toList());
                List<String> betNumbers = Stream.of(betNumbersText.split("-")[0].split(",")).map(String::trim).collect(Collectors.toList());
                if (!betNumbers.equals(expectedNumbers)) {
                    throw new IOSTestException("Expected ticket box numbers: %s are not equal to actual ticket box numbers: %s", expectedNumbers, betNumbers);
                }
                if (!betAdditionalNumbers.equals(expectedAdditionalNumbers)) {
                    throw new IOSTestException("Expected ticket box numbers: %s are not equal to actual ticket box numbers: %s",
                                               expectedAdditionalNumbers,
                                               betAdditionalNumbers);
                }
            } else {
                List<String> betNumbers = Stream.of(betNumbersText.split(",")).map(String::trim).collect(Collectors.toList());
                if (!betNumbers.equals(expectedNumbers)) {
                    throw new IOSTestException("Expected ticket box numbers: %s are not equal to actual ticket box numbers: %s", expectedNumbers, betNumbers);
                }
            }
        }
    }
}
