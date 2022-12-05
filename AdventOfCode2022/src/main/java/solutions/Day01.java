package solutions;

import utils.Day;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static utils.InputReadingUtils.readListOfListOfInts;

public class Day01 implements Day<Integer> {
    List<List<Integer>> elves;

    public Day01(File input) {
        elves = readListOfListOfInts(input);
    }

    public Integer runPartOne() {
        List<Integer> sums = elves.stream()
                .map(elfList -> elfList.stream().mapToInt(Integer::intValue).sum())
                .toList();
        return Collections.max(sums);
    }

    public Integer runPartTwo() {
        List<Integer> sums = elves.stream()
                .map(elfList -> elfList.stream().mapToInt(Integer::intValue).sum())
                .sorted(Comparator.reverseOrder())
                .toList();
        return sums.stream().limit(3).mapToInt(Integer::intValue).sum();
    }

    public static void main(String[] args) {
        Day01 day01 = new Day01(new File(
                "D:\\programmeer projecten\\AdventofCode\\AdventofCode2022\\AdventOfCode2022\\inputs\\Day01.txt"));
        int partOne = day01.runPartOne();
        int partTwo = day01.runPartTwo();
        System.out.println("Part one: " + partOne);
        System.out.println("Part two: " + partTwo);
    }
}
