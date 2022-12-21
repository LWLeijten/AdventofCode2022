package solutions;

import utils.Coordinate3D;
import utils.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day18 implements Day<Integer> {
    List<Coordinate3D> cubes;

    public Day18(File file) {
        cubes = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                int[] coords = Arrays.stream(line.split(","))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                cubes.add(new Coordinate3D(coords[0], coords[1], coords[2]));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
    Return whether the coordinate falls in our problem space, to prevent infinite exploration.
     */
    public boolean cubeInTargetArea(Coordinate3D cube, int maxX, int maxY, int maxZ) {
        return cube.x >= -1 && cube.x <= maxX && cube.y >= -1 && cube.y <= maxY && cube.z >= -1 && cube.z <= maxZ;
    }

    /*
    BFS search the problem space for all areas reachable by water. Return list of floodable coordinates.
     */
    public List<Coordinate3D> getFloodableCubes(int maxX, int maxY, int maxZ) {
        Queue<Coordinate3D> queue = new ArrayDeque<>();
        HashSet<Coordinate3D> explored = new HashSet<>();
        HashSet<Coordinate3D> floodable = new HashSet<>();
        queue.add(new Coordinate3D(maxX, maxY, maxZ));
        while (queue.size() > 0) {
            Coordinate3D loc = queue.poll();
            explored.add(loc);
            for (Coordinate3D nb : loc.getNeighbours()) {
                if (!explored.contains(nb) && !queue.contains(nb) && cubeInTargetArea(nb, maxX, maxY, maxZ)) {
                    if (!cubes.contains(nb)) {
                        floodable.add(nb);
                        queue.add(nb);
                    }
                }
            }
        }
        return floodable.stream().toList();
    }

    @Override
    public Integer runPartOne() {
        return cubes.stream()
                .map(c -> c.exposedSides(cubes, new ArrayList<>()))
                .mapToInt(Integer::intValue)
                .sum();
    }

    @Override
    public Integer runPartTwo() {
        // get a rim around the droplets for BFS
        int maxX = cubes.stream().mapToInt(c -> c.x + 1).max().getAsInt();
        int maxY = cubes.stream().mapToInt(c -> c.y + 1).max().getAsInt();
        int maxZ = cubes.stream().mapToInt(c -> c.z + 1).max().getAsInt();
        List<Coordinate3D> floodableCubes = getFloodableCubes(maxX, maxY, maxZ);
        return cubes.stream()
                .map(c -> c.exposedSides(cubes, floodableCubes))
                .mapToInt(Integer::intValue)
                .sum();
    }

    public static void main(String[] args) {
        Day18 day18 = new Day18(new File(
                "D:\\programmeer projecten\\AdventofCode\\AdventofCode2022\\AdventOfCode2022\\inputs\\Day18.txt"));
        int partOne = day18.runPartOne();
        int partTwo = day18.runPartTwo();
        System.out.println("Part one: " + partOne);
        System.out.println("Part two: " + partTwo);
    }
}
