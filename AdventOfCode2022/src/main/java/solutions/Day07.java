package solutions;

import utils.Day;
import utils.FileSystem;

import java.io.File;

public class Day07 implements Day<Integer> {
    FileSystem fs;
    int SMALL_DIR_THRESHOLD = 100000;
    int TOTAL_DISK_SPACE = 70000000;
    int NEEDED_DISK_SPACE = 30000000;
    int ROOT_SIZE;

    public Day07(File file) {
        fs = new FileSystem(file);
        ROOT_SIZE = fs.getRoot().getSize();
    }

    private int sumSmallDirs(FileSystem.FileSystemEntry fse) {
        // End case
        if (fse.isFile()) {
            return 0;
        }
        // Recursion
        int size = fse.getSize();
        int isSmall = size < SMALL_DIR_THRESHOLD ? size : 0;
        return isSmall + fse.getChildren().stream().mapToInt(this::sumSmallDirs).sum();
    }

    private int findSmallestDeletableDir(FileSystem.FileSystemEntry fse, int curSmallestDir) {
        // End case
        if (fse.isFile()) {
            return curSmallestDir;
        }
        // Recursion
        int newSmallestDir;
        int size = fse.getSize();
        int leftoverSize = TOTAL_DISK_SPACE - ROOT_SIZE + fse.getSize();
        if (leftoverSize > NEEDED_DISK_SPACE) {
            newSmallestDir = size;
        } else {
            newSmallestDir = curSmallestDir;
        }
        return fse.getChildren().stream()
                .mapToInt(f -> findSmallestDeletableDir(f, newSmallestDir))
                .min()
                .getAsInt();
    }

    @Override
    public Integer runPartOne() {
        return sumSmallDirs(fs.getRoot());
    }

    @Override
    public Integer runPartTwo() {
        return findSmallestDeletableDir(fs.getRoot(), Integer.MAX_VALUE);
    }

    public static void main(String[] args) {
        Day07 day07 = new Day07(new File(
                "D:\\programmeer projecten\\AdventofCode\\AdventofCode2022\\AdventOfCode2022\\inputs\\Day07.txt"));
        int partOne = day07.runPartOne();
        int partTwo = day07.runPartTwo();
        System.out.println("Part one: " + partOne);
        System.out.println("Part two: " + partTwo);
    }
}
