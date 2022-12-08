package solutions;

import utils.Day;
import utils.Matrix;

import java.io.File;

import static utils.InputReadingUtils.readNumberMatrix;

public class Day08 implements Day<Integer> {

    Matrix map;

    public Day08(File file) {
        map = readNumberMatrix(file);
    }

    @Override
    public Integer runPartOne() {
        return map.visibleCoordinates();
    }

    @Override
    public Integer runPartTwo() {
        return map.highestScenicScore();
    }

    public static void main(String[] args) {
        Day08 day08 = new Day08(new File(
                "D:\\programmeer projecten\\AdventofCode\\AdventofCode2022\\AdventOfCode2022\\inputs\\Day08.txt"));
        int partOne = day08.runPartOne();
        int partTwo = day08.runPartTwo();
        System.out.println("Part one: " + partOne);
        System.out.println("Part two: " + partTwo);
    }
}
