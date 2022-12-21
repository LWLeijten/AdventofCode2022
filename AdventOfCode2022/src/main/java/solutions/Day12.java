package solutions;

import utils.Coordinate;
import utils.Day;

import java.io.File;
import java.util.*;

import static utils.InputReadingUtils.readListOfStrings;

public class Day12 implements Day<Integer> {

    int[][] map;
    Coordinate lowPoint;
    Coordinate highPoint;

    public Day12(File file) {
        List<String> input = readListOfStrings(file);
        map = new int[input.size()][input.get(0).length()];
        for (int y = 0; y < input.size(); y++) {
            for (int x = 0; x < input.get(0).length(); x++) {
                char c = input.get(y).charAt(x);
                if (c == 'S') {
                    map[y][x] = 0;
                    lowPoint = new Coordinate(x, y);
                } else if (c == 'E') {
                    map[y][x] = 27;
                    highPoint = new Coordinate(x, y);
                } else {
                    map[y][x] = c - 'a' + 1;
                }
            }
        }
    }

    public Integer bfs(Coordinate startPos, boolean ascending, int target) {
        Queue<Coordinate> queue = new ArrayDeque<>();
        HashSet<Coordinate> explored = new HashSet<>();
        HashMap<Coordinate, Coordinate> parentMappings = new HashMap<>();
        queue.add(startPos);
        while (queue.size() > 0) {
            Coordinate loc = queue.poll();
            explored.add(loc);
            if (map[loc.elementTwo][loc.elementOne] == target) {
                int steps = 0;
                while (loc != startPos) {
                    loc = parentMappings.get(loc);
                    steps++;
                }
                return steps;
            }
            for (Coordinate neighbour : getValidNeighbours(loc, ascending)) {
                if (!explored.contains(neighbour) && !queue.contains(neighbour)) {
                    queue.add(neighbour);
                    parentMappings.put(neighbour, loc);
                }
            }
        }
        return null;
    }

    private List<Coordinate> getValidNeighbours(Coordinate loc, boolean ascending) {
        List<Coordinate> validNeighbours = new ArrayList<>();
        for (Coordinate dir : loc.getNeighbours(false)) {
            int nX = dir.elementOne;
            int nY = dir.elementTwo;
            if (nX >= 0 && nY >= 0 && nX < map[0].length && nY < map.length) {
                if (ascending && (map[nY][nX] <= map[loc.elementTwo][loc.elementOne] + 1)) {
                    validNeighbours.add(new Coordinate(nX, nY));
                } else if (!ascending && map[nY][nX] >= map[loc.elementTwo][loc.elementOne] - 1) {
                    validNeighbours.add(new Coordinate(nX, nY));
                }
            }
        }
        return validNeighbours;
    }

    @Override
    public Integer runPartOne() {
        return bfs(lowPoint, true, 27);
    }

    @Override
    public Integer runPartTwo() {
        return bfs(highPoint, false, 1);
    }

    public static void main(String[] args) {
        Day12 day12 = new Day12(new File(
                "D:\\programmeer projecten\\AdventofCode\\AdventofCode2022\\AdventOfCode2022\\inputs\\Day12.txt"));
        long partOne = day12.runPartOne();
        long partTwo = day12.runPartTwo();
        System.out.println("Part one: " + partOne);
        System.out.println("Part two: " + partTwo);
    }
}
