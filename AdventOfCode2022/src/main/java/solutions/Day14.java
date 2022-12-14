package solutions;

import utils.Day;
import utils.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class Day14 implements Day<Integer> {
    HashSet<Pair<Integer>> tiles;
    Pair<Integer> origin;
    Integer maxHeight;

    public Day14(File file) {
        origin = new Pair<>(500, 0);
        maxHeight = Integer.MIN_VALUE;
        try {
            Scanner scanner = new Scanner(file);
            tiles = new HashSet<>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] corners = line.split(" -> ");
                for (int i = 1; i < corners.length; i++) {
                    Pair<Integer> cornerA = parsePairFromString(corners[i - 1]);
                    Pair<Integer> cornerB = parsePairFromString(corners[i]);
                    // Horizontal
                    if (cornerA.elementTwo.equals(cornerB.elementTwo)) {
                        for (int x = Math.min(cornerA.elementOne, cornerB.elementOne);
                                x <= Math.max(cornerA.elementOne, cornerB.elementOne);
                                x++) {
                            addPairToTileMap(x, cornerA.elementTwo);
                        }
                    } // vertical
                    else {
                        for (int y = Math.min(cornerA.elementTwo, cornerB.elementTwo);
                                y <= Math.max(cornerA.elementTwo, cornerB.elementTwo);
                                y++) {
                            addPairToTileMap(cornerA.elementOne, y);
                        }
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    Pair<Integer> parsePairFromString(String coordinate) {
        String[] split = coordinate.split(",");
        return new Pair<Integer>(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
    }

    void addPairToTileMap(int x, int y) {
        tiles.add(new Pair<>(x, y));
        if (y > maxHeight) {
            maxHeight = y;
        }
    }

    int runSimulation(boolean abyss) {
        int sandUnits = 0;
        // Drop sand units whilst we can
        while (true) {
            Pair<Integer> sandUnit = origin.clone();
            // Drop the single sand unit
            while (true) {
                // (abyss/floor edge cases respectively)
                if (abyss && sandUnit.elementTwo > maxHeight) {
                    return sandUnits;
                } else if (!abyss && sandUnit.elementTwo == maxHeight + 2) {
                    tiles.add(sandUnit);
                    break;
                }
                // Drop one step
                if (!tiles.contains(new Pair<>(sandUnit.elementOne, sandUnit.elementTwo + 1))) {
                    sandUnit.elementTwo++;
                    continue;
                } else if (!tiles.contains(new Pair<>(sandUnit.elementOne - 1, sandUnit.elementTwo + 1))) {
                    sandUnit.elementOne--;
                    sandUnit.elementTwo++;
                    continue;
                } else if (!tiles.contains(new Pair<>(sandUnit.elementOne + 1, sandUnit.elementTwo + 1))) {
                    sandUnit.elementOne++;
                    sandUnit.elementTwo++;
                    continue;
                }
                // Dropped
                tiles.add(sandUnit);
                sandUnits++;
                if (sandUnit.equals(origin)) {
                    return sandUnits;
                }
                break;
            }
        }
    }

    @Override
    public Integer runPartOne() {
        return runSimulation(true);
    }

    @Override
    public Integer runPartTwo() {
        return runSimulation(false);
    }

    public static void main(String[] args) {
        Day14 day14 = new Day14(new File(
                "D:\\programmeer projecten\\AdventofCode\\AdventofCode2022\\AdventOfCode2022\\inputs\\Day14.txt"));
        long partOne = day14.runPartOne();
        day14 = new Day14(new File(
                "D:\\programmeer projecten\\AdventofCode\\AdventofCode2022\\AdventOfCode2022\\inputs\\Day14.txt"));
        long partTwo = day14.runPartTwo();
        System.out.println("Part one: " + partOne);
        System.out.println("Part two: " + partTwo);
    }
}
