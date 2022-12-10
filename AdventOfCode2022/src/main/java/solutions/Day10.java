package solutions;

import utils.Day;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static utils.InputReadingUtils.readListOfStrings;

public class Day10 implements Day<Integer> {
    List<CPU_Instruction> instructions;

    public Day10(File file) {
        instructions = new ArrayList<>();
        for (String s : readListOfStrings(file)) {
            String operation = s.split(" ")[0];
            if (operation.equals("noop")) {
                instructions.add(new CPU_Instruction(s.split(" ")[0], null));
            } else {
                instructions.add(new CPU_Instruction(s.split(" ")[0], Integer.parseInt(s.split(" ")[1])));
            }
        }
    }

    private char[][] initScreen(int width, int height) {
        char[][] screen = new char[height][width];
        for (int y = 0; y < 6; y++) {
            for (int x = 0; x < 40; x++) {
                screen[y][x] = ' ';
            }
        }
        return screen;
    }

    @Override
    public Integer runPartOne() {
        List<Integer> checkpoints = List.of(20, 60, 100, 140, 180, 220);
        int register = 1, result = 0, tick = 0;
        for (CPU_Instruction cur : instructions) {
            int opTicks = cur.operation.equals("addx") ? 2 : 1;
            for (int i = 0; i < opTicks; i++) {
                tick++;
                if (checkpoints.contains(tick)) {
                    result += tick * register;
                }
            }
            register += cur.operation.equals("addx") ? cur.value : 0;
        }
        return result;
    }

    @Override
    public Integer runPartTwo() {
        int register = 1, tick = 0;
        char[][] screen = initScreen(40, 6);
        for (CPU_Instruction cur : instructions) {
            int opTicks = cur.operation.equals("addx") ? 2 : 1;
            for (int i = 0; i < opTicks; i++) {
                if (Math.abs((tick) % 40 - register) <= 1) {
                    int row = (tick) / 40;
                    screen[row][(tick) % 40] = '#';
                }
                tick++;
            }
            register += cur.operation.equals("addx") ? cur.value : 0;
        }
        for (char[] row : screen) {
            System.out.println(new String(row));
        }
        return null;
    }

    public static void main(String[] args) {
        Day10 day10 = new Day10(new File(
                "D:\\programmeer projecten\\AdventofCode\\AdventofCode2022\\AdventOfCode2022\\inputs\\Day10.txt"));
        int partOne = day10.runPartOne();
        System.out.println("Part one: " + partOne);
        day10.runPartTwo();
        // #### #### ###  #### #  #  ##  #  # ###
        //   # #    #  # #    #  # #  # #  # #  #
        //  #  ###  ###  ###  #### #    #  # #  #
        // #   #    #  # #    #  # # ## #  # ###
        // #    #    #  # #    #  # #  # #  # #
        // #### #    ###  #    #  #  ###  ##  #
    }

    record CPU_Instruction(String operation, Integer value) {}
}
