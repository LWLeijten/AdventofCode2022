package solutions;

import utils.DayString;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day05 implements DayString {

    List<Stack<Character>> stacks;
    List<StackInstruction> instructions;

    final int AMOUNT_OF_STACKS = 9;

    public Day05(File file) {
        instructions = new ArrayList<StackInstruction>();
        stacks = new ArrayList<Stack<Character>>();
        parseInput(file);
    }

    private void parseInput(File file) {
        for (int i = 0; i < AMOUNT_OF_STACKS; i++) {
            stacks.add(new Stack<Character>());
        }
        try {
            Scanner scanner = new Scanner(file);
            // Read stacks
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.equals("")) {
                    break;
                } else {
                    for (int i = 0; i < AMOUNT_OF_STACKS; i++) {
                        try {
                            char c = line.charAt(i + 1 + 3 * i);
                            if (Character.isWhitespace(c) || !Character.isAlphabetic(c)) {
                                continue;
                            } else {
                                stacks.get(i).add(0, c);
                            }
                        } catch (Exception e) {
                            continue;
                        }
                    }
                }
            }
            // Continue reading instructions
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                // Remove all non-numericals and map to int array
                int[] numbers = Arrays.stream(
                                line.replaceAll("[^-?0-9]+", " ").trim().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                instructions.add(new StackInstruction(numbers[0], numbers[1] - 1, numbers[2] - 1));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String showTopOfStacks() {
        StringBuilder result = new StringBuilder();
        for (Stack<Character> stack : stacks) {
            result.append(stack.peek());
        }
        return result.toString();
    }

    @Override
    public String runPartOne() {
        for (StackInstruction instruction : instructions) {
            for (int i = 0; i < instruction.amount(); i++) {
                char popped = stacks.get(instruction.from()).pop();
                stacks.get(instruction.to()).push(popped);
            }
        }
        return showTopOfStacks();
    }

    @Override
    public String runPartTwo() {
        for (StackInstruction instruction : instructions) {
            for (int i = instruction.amount(); i > 0; i--) {
                Stack<Character> source = stacks.get(instruction.from());
                char popped = source.remove(source.size() - i);
                stacks.get(instruction.to()).push(popped);
            }
        }
        return showTopOfStacks();
    }

    public static void main(String[] args) {
        Day05 day05 = new Day05(new File(
                "D:\\programmeer projecten\\AdventofCode\\AdventofCode2022\\AdventOfCode2022\\inputs\\Day05.txt"));
        String partOne = day05.runPartOne();
        // Reset stack states
        day05 = new Day05(new File(
                "D:\\programmeer projecten\\AdventofCode\\AdventofCode2022\\AdventOfCode2022\\inputs\\Day05.txt"));
        String partTwo = day05.runPartTwo();
        System.out.println("Part one: " + partOne);
        System.out.println("Part two: " + partTwo);
    }
}

record StackInstruction(Integer amount, Integer from, Integer to) {}
