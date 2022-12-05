package solutions;

import utils.Day;

import java.io.File;
import java.util.List;
import java.util.Map;

import static utils.InputReadingUtils.readListOfStrings;

public class Day02 implements Day<Integer> {

    List<String> strategies;
    Map<String, Integer> scoresStrategy1 = Map.ofEntries(
            Map.entry("A X", 4),
            Map.entry("A Y", 8),
            Map.entry("A Z", 3),
            Map.entry("B X", 1),
            Map.entry("B Y", 5),
            Map.entry("B Z", 9),
            Map.entry("C X", 7),
            Map.entry("C Y", 2),
            Map.entry("C Z", 6));
    Map<String, Integer> scoresStrategy2 = Map.ofEntries(
            Map.entry("A X", 3),
            Map.entry("A Y", 4),
            Map.entry("A Z", 8),
            Map.entry("B X", 1),
            Map.entry("B Y", 5),
            Map.entry("B Z", 9),
            Map.entry("C X", 2),
            Map.entry("C Y", 6),
            Map.entry("C Z", 7));

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
    public Integer runPartOne() {
        return runProblemWithStrategy(scoresStrategy1);
    }

    @Override
    public Integer runPartTwo() {
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
