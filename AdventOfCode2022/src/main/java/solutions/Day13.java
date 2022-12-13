package solutions;

import utils.Day;
import utils.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Day13 implements Day<Integer> {
    List<Pair<Packet>> packetPairs;
    PacketComparator packetComparator = new PacketComparator();

    public Day13(File file) {
        packetPairs = new ArrayList<>();
        Packet packet1 = null;
        Packet packet2 = null;
        try {
            Scanner scanner = new Scanner(file);
            int lineNr = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                switch (lineNr % 3) {
                    case 0 -> packet1 = new Packet(line);
                    case 1 -> packet2 = new Packet(line);
                    case 2 -> packetPairs.add(new Pair<>(packet1, packet2));
                }
                lineNr++;
            }
            packetPairs.add(new Pair<>(packet1, packet2));
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Integer runPartOne() {
        int result = 0;
        for (int i = 0; i < packetPairs.size(); i++) {
            if (packetComparator.compare(packetPairs.get(i).elementOne, packetPairs.get(i).elementTwo) < 0) {
                result += (i + 1);
            }
        }
        return result;
    }

    @Override
    public Integer runPartTwo() {
        List<Packet> packets = new ArrayList<>(List.of(new Packet("[[2]]"), new Packet("[[6]]")));
        for (Pair<Packet> pair : packetPairs) {
            packets.addAll(List.of(pair.elementOne, pair.elementTwo));
        }
        packets.sort(packetComparator);
        List<String> contents = packets.stream().map(p -> p.contents).toList();
        return (contents.indexOf("[[2]]") + 1) * (contents.indexOf("[[6]]") + 1);
    }

    public static void main(String[] args) {
        Day13 day13 = new Day13(new File(
                "D:\\programmeer projecten\\AdventofCode\\AdventofCode2022\\AdventOfCode2022\\inputs\\Day13.txt"));
        long partOne = day13.runPartOne();
        long partTwo = day13.runPartTwo();
        System.out.println("Part one: " + partOne);
        System.out.println("Part two: " + partTwo);
    }

    static class Packet {
        String contents;
        boolean nested;

        public Packet(String contents) {
            this.contents = contents;
            this.nested = contents.charAt(0) == '[';
        }

        public List<Packet> parsePacketContent() {
            // Single number, base case
            if (!nested) {
                return List.of(new Packet(contents));
            }
            // Parse nested packets
            List<Packet> innerContent = new ArrayList<>();
            int nestingLevel = 0;
            StringBuilder newPacketContent = new StringBuilder();
            for (int i = 1; i < contents.length() - 1; i++) {
                char c = contents.charAt(i);
                // End of current sub element
                if (nestingLevel == 0 && c == ',') {
                    Packet newPacket = new Packet(newPacketContent.toString());
                    newPacketContent = new StringBuilder();
                    innerContent.add(newPacket);
                } else {
                    switch (c) {
                        case '[' -> nestingLevel++;
                        case ']' -> nestingLevel--;
                    }
                    newPacketContent.append(c);
                }
            }
            if (!newPacketContent.isEmpty()) {
                innerContent.add(new Packet(newPacketContent.toString()));
            }
            return innerContent;
        }
    }

    /**
     * Recursive Comparator for Packets based on the specs defined in Day13.
     */
    static class PacketComparator implements Comparator<Packet> {
        @Override
        public int compare(Packet p1, Packet p2) {
            // Comparing two ints
            if (!p1.nested && !p2.nested) {
                int intP1 = Integer.parseInt(p1.contents);
                int intP2 = Integer.parseInt(p2.contents);
                return Integer.compare(intP1, intP2);
            }
            // Nested
            List<Packet> p1List = p1.parsePacketContent();
            List<Packet> p2List = p2.parsePacketContent();
            for (int i = 0; i < Math.min(p1List.size(), p2List.size()); i++) {
                int ordering = compare(p1List.get(i), p2List.get(i));
                if (ordering == 0) {
                    continue;
                }
                return ordering;
            }
            return Integer.compare(p1List.size(), p2List.size());
        }
    }
}
