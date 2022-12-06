package solutions;

import utils.Day;

import java.io.File;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;

import static utils.InputReadingUtils.readString;

public class Day06 implements Day<Integer> {
    private final String datastream;

    public Day06(File file) {
        datastream = readString(file);
    }

    private Integer findMarker(int slidingWindowSize) {
        // Prepare first window
        Queue<Character> slidingWindow = new ArrayDeque<>();
        for (int i = 0; i < slidingWindowSize; i++) {
            slidingWindow.offer(datastream.charAt(i));
        }
        // Slide
        for (int i = slidingWindowSize; i < datastream.length(); i++) {
            if (new HashSet<Character>(slidingWindow).size() == slidingWindowSize) {
                return i;
            }
            slidingWindow.poll();
            slidingWindow.offer(datastream.charAt(i));
        }
        return null;
    }

    @Override
    public Integer runPartOne() {
        return findMarker(4);
    }

    @Override
    public Integer runPartTwo() {
        return findMarker(14);
    }

    public static void main(String[] args) {
        Day06 day06 = new Day06(new File(
                "D:\\programmeer projecten\\AdventofCode\\AdventofCode2022\\AdventOfCode2022\\inputs\\Day06.txt"));
        int partOne = day06.runPartOne();
        int partTwo = day06.runPartTwo();
        System.out.println("Part one: " + partOne);
        System.out.println("Part two: " + partTwo);
    }
}
