package sazka.ios.test.data.lotteries;

import java.util.List;

public class TicketBox {
    private String name;
    private List<String> numbers;
    private List<String> additionalNumbers;
    private List<String> figures;
    private String deposit = "0";

    public List<String> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<String> numbers) {
        this.numbers = numbers;
    }

    public List<String> getAdditionalNumbers() {
        return additionalNumbers;
    }

    public void setAdditionalNumbers(List<String> additionalNumbers) {
        this.additionalNumbers = additionalNumbers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public List<String> getFigures() {
        return figures;
    }

    public void setFigures(List<String> figures) {
        this.figures = figures;
    }
}
