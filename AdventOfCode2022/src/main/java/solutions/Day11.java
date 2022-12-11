package solutions;

import utils.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Day11 implements Day<Long> {
    List<Monkey> monkeys;

    public Day11(File file) {
        monkeys = new ArrayList<>();
        int lineNr = 0;
        List<Long> curItems = new ArrayList<>();
        String curOperator = "";
        String curOperatorValue = "";
        int curDivisionTest = -1;
        int curTrueMonkey = -1;
        int curFalseMonkey = -1;
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                switch (lineNr % 7) {
                    case 0 -> {
                        break;
                    }
                    case 1 -> {
                        line = line.replaceFirst("  Starting items: ", "");
                        curItems = Arrays.stream(line.split(", "))
                                .mapToLong(Integer::valueOf)
                                .boxed()
                                .collect(Collectors.toList());
                    }
                    case 2 -> {
                        String[] split = line.split(" ");
                        curOperator = split[6];
                        curOperatorValue = split[7];
                    }
                    case 3 -> {
                        curDivisionTest = Integer.parseInt(line.replaceFirst("  Test: divisible by ", ""));
                    }
                    case 4 -> {
                        curTrueMonkey = Integer.parseInt(line.replaceFirst("    If true: throw to monkey ", ""));
                    }
                    case 5 -> {
                        curFalseMonkey = Integer.parseInt(line.replaceFirst("    If false: throw to monkey ", ""));
                    }
                    case 6 -> {
                        monkeys.add(new Monkey(
                                curItems,
                                curOperator,
                                curOperatorValue,
                                curDivisionTest,
                                curTrueMonkey,
                                curFalseMonkey));
                    }
                }
                lineNr++;
            }
            monkeys.add(new Monkey(
                    curItems, curOperator, curOperatorValue, curDivisionTest, curTrueMonkey, curFalseMonkey));
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Long doMonkeyBusiness(int rounds, boolean bigWorries) {
        int commonModulo = 1;
        for (Monkey monkey : monkeys) {
            commonModulo *= monkey.divisionTest;
        }
        for (int i = 0; i < rounds; i++) {
            for (Monkey monkey : monkeys) {
                for (Long item : monkey.items) {
                    monkey.inspections++;
                    long other = monkey.operatorValue.equals("old") ? item : Long.parseLong(monkey.operatorValue);
                    item = monkey.operator.equals("+") ? item + other : item * other;
                    item = bigWorries ? item % commonModulo : item / 3;
                    if (item % monkey.divisionTest == 0) {
                        monkeys.get(monkey.trueMonkey).items.add(item);
                    } else {
                        monkeys.get(monkey.falseMonkey).items.add(item);
                    }
                }
                monkey.items = new ArrayList<>();
            }
        }
        List<Long> inspections = monkeys.stream()
                .map(monkey -> monkey.inspections)
                .sorted(Comparator.reverseOrder())
                .toList();
        return inspections.stream().limit(2).mapToLong(Long::longValue).reduce(1, (a, b) -> a * b);
    }

    @Override
    public Long runPartOne() {
        return doMonkeyBusiness(20, false);
    }

    @Override
    public Long runPartTwo() {
        return doMonkeyBusiness(10000, true);
    }

    public static void main(String[] args) {
        Day11 day11 = new Day11(new File(
                "D:\\programmeer projecten\\AdventofCode\\AdventofCode2022\\AdventOfCode2022\\inputs\\Day11.txt"));
        long partOne = day11.runPartOne();
        day11 = new Day11(new File(
                "D:\\programmeer projecten\\AdventofCode\\AdventofCode2022\\AdventOfCode2022\\inputs\\Day11.txt"));
        long partTwo = day11.runPartTwo();
        System.out.println("Part one: " + partOne);
        System.out.println("Part two: " + partTwo);
    }

    static class Monkey {
        List<Long> items;
        String operator;
        String operatorValue;
        Integer divisionTest;
        Integer trueMonkey;
        Integer falseMonkey;
        Long inspections;

        public Monkey(
                List<Long> items,
                String operator,
                String operatorValue,
                Integer divisionTest,
                Integer trueMonkey,
                Integer falseMonkey) {
            this.items = items;
            this.operator = operator;
            this.operatorValue = operatorValue;
            this.divisionTest = divisionTest;
            this.trueMonkey = trueMonkey;
            this.falseMonkey = falseMonkey;
            this.inspections = 0L;
        }
    }
}
