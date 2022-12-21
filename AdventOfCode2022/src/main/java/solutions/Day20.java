package solutions;

import utils.CircularLinkedList;
import utils.Day;

import java.io.File;
import java.util.List;

import static utils.InputReadingUtils.readListOfInts;

public class Day20 implements Day<Long> {
    CircularLinkedList cll;

    public Day20(File file) {
        cll = new CircularLinkedList();
        List<Integer> numbers = readListOfInts(file);
        for (int i = 0; i < numbers.size(); i++) {
            cll.addItem(Long.valueOf(numbers.get(i)), i);
        }
    }

    public Long mix(int times, long decryptionKey) {
        long result = 0;
        // Apply key
        for (CircularLinkedList.LinkedListItem item : cll.lookupTable.values()) {
            item.setValue(item.getValue() * decryptionKey);
        }
        // Performing mixing i times.
        for (int i = 0; i < times; i++) {
            for (int j = 0; j < cll.lookupTable.size(); j++) {
                CircularLinkedList.LinkedListItem cur = cll.lookupTable.get(j);
                cur.move(cur.getValue(), cll.lookupTable.size());
            }
        }
        // Find value 0
        CircularLinkedList.LinkedListItem value0 = cll.head;
        while (!value0.getValue().equals(0L)) {
            value0 = value0.getNext();
        }
        // Find elements 1000,2000 and 3000
        CircularLinkedList.LinkedListItem cur = value0;
        for (int i = 0; i < 3000; i++) {
            cur = cur.getNext();
            if ((i + 1) % 1000 == 0) {
                result += cur.getValue();
            }
        }
        return result;
    }

    @Override
    public Long runPartOne() {
        return mix(1, 1);
    }

    @Override
    public Long runPartTwo() {
        return mix(10, 811589153L);
    }

    public static void main(String[] args) {
        Day20 day20 = new Day20(new File(
                "D:\\programmeer projecten\\AdventofCode\\AdventofCode2022\\AdventOfCode2022\\inputs\\Day20.txt"));
        long partOne = day20.runPartOne();
        day20 = new Day20(new File(
                "D:\\programmeer projecten\\AdventofCode\\AdventofCode2022\\AdventOfCode2022\\inputs\\Day20.txt"));
        long partTwo = day20.runPartTwo();
        System.out.println("Part one: " + partOne);
        System.out.println("Part two: " + partTwo);
    }
}
