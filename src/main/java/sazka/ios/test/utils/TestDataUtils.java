package sazka.ios.test.utils;

import sazka.ios.test.data.lotteries.Eurojackpot;
import sazka.ios.test.data.lotteries.Sportka;
import sazka.ios.test.data.lotteries.TicketBox;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestDataUtils {
    protected static int TEST_NAME_INDEX = 0;
    protected static int DATE_INDEX = 1;
    protected static int DRAW_COUNT_INDEX = 2;
    protected static int CHANCE_FORMAT_INDEX = 3;
    protected static int BETTING_NUMBERS_START_INDEX = 4;

    private static final String folder = "src/test/resources/lotteries/";

    public static Object[][] loadSportkaTestData(SportkaFiles sportkaFiles) {
        List<Sportka> sportkaList = new ArrayList<>();
        parseCsvData(sportkaFiles.getValue()).forEach(data -> {
            Sportka sportka = new Sportka();
            sportka.setDrawCount(data[DRAW_COUNT_INDEX]);
            sportka.setTestName(data[TEST_NAME_INDEX]);
            sportka.setDrawDays(data[DATE_INDEX]);
            sportka.setChanceFormat(data[CHANCE_FORMAT_INDEX]);
            TicketBox ticketBox;
            int index = 0;
            String[] numbers = getFilteredNumbers(data, BETTING_NUMBERS_START_INDEX, data.length);
            for (int i = 0; i < getCountTicketBoxes(numbers, sportka.getCountOfNumbersInTicketBox()); i++) {
                ticketBox = new TicketBox();
                ticketBox.setNumbers(List.of(Arrays.copyOfRange(numbers, index, index + sportka.getCountOfNumbersInTicketBox())));
                sportka.addTicketBox(i, ticketBox);
                index = index + sportka.getCountOfNumbersInTicketBox();
            }
            sportkaList.add(sportka);
        });
        return sportkaList.stream().map(sportka -> new Object[] {sportka}).toArray(Object[][]::new);
    }

    public static Object[][] loadEurojackpotTestData(EurojackpotFiles eurojackpotFiles) {
        List<Eurojackpot> eurojackpotList = new ArrayList<>();
        parseCsvData(eurojackpotFiles.getValue()).forEach(data -> {
            Eurojackpot eurojackpot = new Eurojackpot();
            eurojackpot.setDrawCount(data[DRAW_COUNT_INDEX]);
            eurojackpot.setTestName(data[TEST_NAME_INDEX]);
            eurojackpot.setDrawDays(data[DATE_INDEX]);
            eurojackpot.setChanceFormat(data[CHANCE_FORMAT_INDEX]);
            TicketBox ticketBox;
            int index = 0;
            String[] numbers = getFilteredNumbers(data, BETTING_NUMBERS_START_INDEX, data.length);
            for (int i = 0; i < getCountTicketBoxes(numbers, eurojackpot.getCountOfNumbersInTicketBox()); i++) {
                ticketBox = new TicketBox();
                ticketBox.setNumbers(List.of(Arrays.copyOfRange(numbers, index, index + eurojackpot.getCountOfNumbersInTicketBox())));
                index = index + eurojackpot.getCountOfNumbersInTicketBox();
                ticketBox.setAdditionalNumbers(List.of(Arrays.copyOfRange(numbers, index, index + eurojackpot.getCountOfAdditionalNumbersInTicketBox())));
                index = index + eurojackpot.getCountOfAdditionalNumbersInTicketBox();
                eurojackpot.addTicketBox(i, ticketBox);
            }
            eurojackpotList.add(eurojackpot);
        });
        return eurojackpotList.stream().map(eurojackpot -> new Object[] {eurojackpot}).toArray(Object[][]::new);
    }

    protected static int getCountTicketBoxes(String[] numbers, int countOfNumbers) {
        return numbers.length / countOfNumbers;
    }

    protected static String[] getFilteredNumbers(String[] data, int startIndex, int endIndex) {
        return Arrays.stream(Arrays.copyOfRange(data, startIndex, endIndex))
                     .filter(s -> (s != null && !s.isEmpty())).toArray(String[]::new);
    }

    private static List<String[]> parseCsvData(String filename) {
        BufferedReader bufferedReader = null;
        List<String[]> data = new ArrayList<>();

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream((folder + filename)), "windows-1250"));
            bufferedReader.lines().skip(1).filter(line -> !line.isEmpty()).forEach(line -> data.add(line.split("[;,]")));
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                //TODO: Add proper logging and exception!
                e.printStackTrace();
            }
        }

        return data;
    }

    public static boolean containsAllWords(String word, List<String> keywords) {
        for (String k : keywords)
            if (!word.contains(k))
                return false;
        return true;
    }

    public enum SportkaFiles {
        SPORTKA_PLACE_BET_CSV("place_sportka_bet.csv");

        private final String file;

        SportkaFiles(String file) {
            this.file = file;
        }

        public final String getValue() {
            return "sportka/" + file;
        }
    }


    public enum EurojackpotFiles {
        EUROJACKPOT_PLACE_BET_CSV("place_eurojackpot_bet.csv");

        private final String file;

        EurojackpotFiles(String file) {
            this.file = file;
        }

        public final String getValue() {
            return "eurojackpot/" + file;
        }
    }
}
