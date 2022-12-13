package solutions;

import utils.Day;
import utils.Pair;

import java.io.File;
import java.util.*;

import static utils.InputReadingUtils.readListOfStrings;

public class Day12 implements Day<Integer> {

    int[][] map;
    Pair<Integer> lowPoint;
    Pair<Integer> highPoint;

    public Day12(File file) {
        List<String> input = readListOfStrings(file);
        map = new int[input.size()][input.get(0).length()];
        for (int y = 0; y < input.size(); y++) {
            for (int x = 0; x < input.get(0).length(); x++) {
                char c = input.get(y).charAt(x);
                if (c == 'S') {
                    map[y][x] = 0;
                    lowPoint = new Pair<>(x, y);
                } else if (c == 'E') {
                    map[y][x] = 27;
                    highPoint = new Pair<>(x, y);
                } else {
                    map[y][x] = c - 'a' + 1;
                }
            }
        }
    }

    public Integer bfs(Pair<Integer> startPos, boolean ascending, int target) {
        Queue<Pair<Integer>> queue = new ArrayDeque<>();
        HashSet<Pair<Integer>> explored = new HashSet<>();
        HashMap<Pair<Integer>, Pair<Integer>> parentMappings = new HashMap<>();
        queue.add(startPos);
        while (queue.size() > 0) {
            Pair<Integer> loc = queue.poll();
            explored.add(loc);
            if (map[loc.elementTwo][loc.elementOne] == target) {
                int steps = 0;
                while (loc != startPos) {
                    loc = parentMappings.get(loc);
                    steps++;
                }
                return steps;
            }
            for (Pair<Integer> neighbour : getNeighbours(loc, ascending)) {
                if (!explored.contains(neighbour) && !queue.contains(neighbour)) {
                    queue.add(neighbour);
                    parentMappings.put(neighbour, loc);
                }
            }
        }
        return null;
    }

    private List<Pair<Integer>> getNeighbours(Pair<Integer> loc, boolean ascending) {
        List<Pair<Integer>> neighbours = new ArrayList<>();
        List<Pair<Integer>> directions =
                List.of(new Pair<>(-1, 0), new Pair<>(1, 0), new Pair<>(0, -1), new Pair<>(0, 1));
        for (Pair<Integer> dir : directions) {
            int nX = loc.elementOne + dir.elementOne;
            int nY = loc.elementTwo + dir.elementTwo;
            if (nX >= 0 && nY >= 0 && nX < map[0].length && nY < map.length) {
                if (ascending && (map[nY][nX] <= map[loc.elementTwo][loc.elementOne] + 1)) {
                    neighbours.add(new Pair<>(nX, nY));
                } else if (!ascending && map[nY][nX] >= map[loc.elementTwo][loc.elementOne] - 1) {
                    neighbours.add(new Pair<>(nX, nY));
                }
            }
        }
        return neighbours;
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
