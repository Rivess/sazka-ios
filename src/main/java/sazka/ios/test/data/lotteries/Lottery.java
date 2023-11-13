package sazka.ios.test.data.lotteries;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public abstract class Lottery {
    private String testName;
    private String id;
    private int drawCount;
    private List<DayOfWeek> drawDays;
    private String chanceFormat;
    private String chanceNumber;
    private String betPrice;

    private List<TicketBox> ticketBoxes = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<TicketBox> getTicketBoxes() {
        return ticketBoxes;
    }

    public void setTicketBoxes(List<TicketBox> ticketBoxes) {
        this.ticketBoxes = ticketBoxes;
    }

    public String getChanceFormat() {
        return chanceFormat;
    }

    public void setChanceFormat(String chanceFormat) {
        this.chanceFormat = chanceFormat;
    }

    public String getChanceNumber() {
        return chanceNumber;
    }

    public void setChanceNumber(String chanceNumber) {
        this.chanceNumber = chanceNumber;
    }

    public String getDrawCount() {
        return String.valueOf(drawCount);
    }

    public int getDrawCountInt() {
        return drawCount;
    }

    public void setDrawCount(String drawCount) {
        this.drawCount = Integer.parseInt(drawCount);
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public void addTicketBox(int index, TicketBox ticketBox) {
        this.ticketBoxes.add(index, ticketBox);
    }

    public abstract int getCountOfNumbersInTicketBox();

    public List<DayOfWeek> getDrawDays() {
        return drawDays;
    }

    public List<String> getDrawDaysNames() {
        List<String> names = new ArrayList<>();
        drawDays.forEach(day -> names.add(day.getDisplayName(TextStyle.FULL, Locale.forLanguageTag("cs-CZ"))));
        return names;
    }

    public void setDrawDays(String drawDay) {
        if (drawDay.contains("_")) {
            ArrayList<DayOfWeek> daysArray = new ArrayList<>();
            String[] days = drawDay.split("_");
            for (String day : days) {
                daysArray.add(DayOfWeek.valueOf(day));
            }
            this.drawDays = daysArray;
        } else {
            this.drawDays = Collections.singletonList(DayOfWeek.valueOf(drawDay));
        }
    }

    public String getBetPrice() {
        return betPrice;
    }

    public void setBetPrice(String betPrice) {
        this.betPrice = betPrice;
    }
}
