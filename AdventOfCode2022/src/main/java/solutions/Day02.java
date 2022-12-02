package solutions;

import utils.Day;

import java.io.File;
import java.util.List;
import java.util.Map;

import static utils.InputReadingUtils.readListOfStrings;

public class Day02 implements Day {

    List<String> strategies;
    Map<String, Integer> scoresStrategy1 = Map.of(
            "A X", 4,
            "A Y", 8,
            "A Z", 3,
            "B X", 1,
            "B Y", 5,
            "B Z", 9,
            "C X", 7,
            "C Y", 2,
            "C Z", 6);
    Map<String, Integer> scoresStrategy2 = Map.of(
            "A X", 3,
            "A Y", 4,
            "A Z", 8,
            "B X", 1,
            "B Y", 5,
            "B Z", 9,
            "C X", 2,
            "C Y", 6,
            "C Z", 7);

    public Day02(File file) {
        strategies = readListOfStrings(file);
    }

    private int runProblemWithStrategy(Map<String, Integer> scoreStrategy) {
        return strategies.stream()
                .map(scoreStrategy::get)
                .mapToInt(Integer::intValue)
                .sum();
    }

    @Override
    public int runPartOne() {
        return runProblemWithStrategy(scoresStrategy1);
    }

    @Override
    public int runPartTwo() {
        return runProblemWithStrategy(scoresStrategy2);
    }

    public static void main(String[] args) {
        Day02 day02 = new Day02(new File(
                "D:\\programmeer projecten\\AdventofCode\\AdventofCode2022\\AdventOfCode2022\\inputs\\Day02.txt"));
        int partOne = day02.runPartOne();
        int partTwo = day02.runPartTwo();
        System.out.println("Part one: " + partOne);
        System.out.println("Part two: " + partTwo);
    }
}
