package solutions;

import utils.Day;
import utils.Pair;

import java.io.File;
import java.util.List;

import static utils.InputReadingUtils.readListOfRangePairs;

public class Day04 implements Day<Integer> {
    List<Pair<Pair<Integer>>> rangePairs;

    public Day04(File file) {
        rangePairs = readListOfRangePairs(file);
    }

    // Move this method to Pair<T> or Pair<Integer> if it gets used again after Day04.
    private boolean rangesFullyOverlap(Pair<Integer> rangeOne, Pair<Integer> rangeTwo) {
        return (rangeOne.elementOne() >= rangeTwo.elementOne() && rangeOne.elementTwo() <= rangeTwo.elementTwo())
                || (rangeTwo.elementOne() >= rangeOne.elementOne() && rangeTwo.elementTwo() <= rangeOne.elementTwo());
    }

    // Move this method to Pair<T> or Pair<Integer> if it gets used again after Day04.
    private boolean rangesOverlap(Pair<Integer> rangeOne, Pair<Integer> rangeTwo) {
        return (rangeOne.elementOne() >= rangeTwo.elementOne() && rangeOne.elementOne() <= rangeTwo.elementTwo())
                || (rangeTwo.elementOne() >= rangeOne.elementOne() && rangeTwo.elementOne() <= rangeOne.elementTwo());
    }

    @Override
    public Integer runPartOne() {
        return rangePairs.stream()
                .map(rangePair -> rangesFullyOverlap(rangePair.elementOne(), rangePair.elementTwo()) ? 1 : 0)
                .mapToInt(Integer::intValue)
                .sum();
    }

    @Override
    public Integer runPartTwo() {
        return rangePairs.stream()
                .map(rangePair -> rangesOverlap(rangePair.elementOne(), rangePair.elementTwo()) ? 1 : 0)
                .mapToInt(Integer::intValue)
                .sum();
    }

    public static void main(String[] args) {
        Day04 day04 = new Day04(new File(
                "D:\\programmeer projecten\\AdventofCode\\AdventofCode2022\\AdventOfCode2022\\inputs\\Day04.txt"));
        int partOne = day04.runPartOne();
        int partTwo = day04.runPartTwo();
        System.out.println("Part one: " + partOne);
        System.out.println("Part two: " + partTwo);
    }
}
