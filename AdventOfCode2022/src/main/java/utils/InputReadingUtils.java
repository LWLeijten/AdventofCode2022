package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputReadingUtils {
    public static List<List<Integer>> readListOfListOfInts(File input) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> currentList = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(input);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.equals("")) {
                    list.add(currentList);
                    currentList = new ArrayList<>();
                } else {
                    currentList.add(Integer.parseInt(line));
                }
            }
            list.add(currentList);
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<String> readListOfStrings(File input) {
        List<String> list = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(input);
            while (scanner.hasNextLine()) {
                list.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<Pair<Pair<Integer>>> readListOfRangePairs(File input) {
        List<Pair<Pair<Integer>>> list = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(input);
            while (scanner.hasNextLine()) {
                String[] ranges = scanner.nextLine().split(",");
                Pair<Integer> pairOne = new Pair<Integer>(
                        Integer.parseInt(ranges[0].split("-")[0]),
                        Integer.parseInt(ranges[0].split("-")[1]));
                Pair<Integer> pairTwo = new Pair<Integer>(
                        Integer.parseInt(ranges[1].split("-")[0]),
                        Integer.parseInt(ranges[1].split("-")[1]));
                list.add(new Pair<Pair<Integer>>(pairOne, pairTwo));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
}
