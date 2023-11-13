package sazka.ios.test.data.lotteries;

public class Eurojackpot extends Lottery {
    private static final int NUMBERS_IN_TICKET_BOX = 5;
    private static final int ADDITIONAL_NUMBERS_IN_TICKET_BOX = 2;


    public int getCountOfNumbersInTicketBox() {
        return NUMBERS_IN_TICKET_BOX;
    }

    public int getCountOfAdditionalNumbersInTicketBox() {
        return ADDITIONAL_NUMBERS_IN_TICKET_BOX;
    }
}
