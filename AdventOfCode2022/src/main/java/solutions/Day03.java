package solutions;

import utils.Day;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static utils.InputReadingUtils.readListOfStrings;

public class Day03 implements Day<Integer> {
    List<String> rucksacks;

    public Day03(File input) {
        rucksacks = readListOfStrings(input);
    }

    private int charToAlphabeticalIndex(char letter) {
        if (Character.isUpperCase(letter)) {
            return letter - 'A' + 27;
        } else {
            return letter - 'a' + 1;
        }
    }

    private char getCommonCharacter(String stringA, List<String> strings) {
        for (char character : stringA.toCharArray()) {
            if (strings.stream().allMatch(string -> string.indexOf(character) > -1)) {
                return character;
            }
        }
        return '.';
    }

    public Integer runPartOne() {
        int prioritySums = 0;
        for (String rucksack : rucksacks) {
            int halfway = rucksack.length() / 2;
            String compartmentA = rucksack.substring(0, halfway);
            String compartmentB = rucksack.substring(halfway, rucksack.length());
            char commonCharacter = getCommonCharacter(compartmentA, new ArrayList<>(List.of(compartmentB)));
            prioritySums += charToAlphabeticalIndex(commonCharacter);
        }
        return prioritySums;
    }

    public Integer runPartTwo() {
        int prioritySums = 0;
        for (int i = 0; i < rucksacks.size(); i += 3) {
            char commonCharacter = getCommonCharacter(
                    rucksacks.get(i), new ArrayList<>(List.of(rucksacks.get(i + 1), rucksacks.get(i + 2))));
            prioritySums += charToAlphabeticalIndex(commonCharacter);
        }
        return prioritySums;
    }

    public static void main(String[] args) {
        Day03 day03 = new Day03(new File(
                "D:\\programmeer projecten\\AdventofCode\\AdventofCode2022\\AdventOfCode2022\\inputs\\Day03.txt"));
        int partOne = day03.runPartOne();
        int partTwo = day03.runPartTwo();
        System.out.println("Part one: " + partOne);
        System.out.println("Part two: " + partTwo);
    }
}
