package solutions;

import utils.Day;
import utils.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Day09 implements Day<Integer> {

    List<RopeMotion> ropeMotionList = new ArrayList<>();

    public Day09(File file) {
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                ropeMotionList.add(new RopeMotion(line.charAt(0), Integer.parseInt(line.split(" ")[1])));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private boolean pairsAreTouching(Pair<Integer> pairOne, Pair<Integer> pairTwo) {
        return Math.abs(pairOne.elementOne - pairTwo.elementOne) <= 1
                && Math.abs(pairOne.elementTwo - pairTwo.elementTwo) <= 1;
    }

    private int simulateKnots(int knotCount) {
        HashSet<Pair<Integer>> visited = new HashSet<>();
        List<Pair<Integer>> knots = new ArrayList<>();
        for (int i = 0; i < knotCount; i++) {
            knots.add(new Pair<Integer>(0, 0));
        }
        Pair<Integer> head = knots.get(0);
        visited.add(head.clone());
        for (RopeMotion rm : ropeMotionList) {
            for (int i = 0; i < rm.amount(); i++) {
                for (int k = 0; k < knotCount; k++) {
                    // lead
                    if (k == 0) {
                        switch (rm.direction) {
                            case 'U' -> head.elementTwo += 1;
                            case 'D' -> head.elementTwo -= 1;
                            case 'L' -> head.elementOne -= 1;
                            case 'R' -> head.elementOne += 1;
                        }
                    }
                    // follow
                    else {
                        Pair<Integer> leader = knots.get(k - 1);
                        Pair<Integer> follower = knots.get(k);
                        // rows
                        if (leader.elementTwo.equals(follower.elementTwo)
                                && Math.abs(leader.elementOne - follower.elementOne) == 2) {
                            follower.elementOne += leader.elementOne > follower.elementOne ? 1 : -1;
                        }
                        // columns
                        else if (leader.elementOne.equals(follower.elementOne)
                                && Math.abs(leader.elementTwo - follower.elementTwo) == 2) {
                            follower.elementTwo += leader.elementTwo > follower.elementTwo ? 1 : -1;
                        }
                        // diagonal move
                        else if (!pairsAreTouching(leader, follower)) {
                            follower.elementOne += leader.elementOne > follower.elementOne ? 1 : -1;
                            follower.elementTwo += leader.elementTwo > follower.elementTwo ? 1 : -1;
                        }
                    }
                }
                visited.add(knots.get(knotCount - 1).clone());
            }
        }
        return visited.size();
    }

    @Override
    public Integer runPartOne() {
        return simulateKnots(2);
    }

    @Override
    public Integer runPartTwo() {
        return simulateKnots(10);
    }

    public static void main(String[] args) {
        Day09 day09 = new Day09(new File(
                "D:\\programmeer projecten\\AdventofCode\\AdventofCode2022\\AdventOfCode2022\\inputs\\Day09.txt"));
        int partOne = day09.runPartOne();
        int partTwo = day09.runPartTwo();
        System.out.println("Part one: " + partOne);
        System.out.println("Part two: " + partTwo);
    }

    record RopeMotion(Character direction, Integer amount) {}
}
