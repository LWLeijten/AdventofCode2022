package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputReadingUtils {
    public static List<List<Integer>> ReadListOfListOfInts(File input) {
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
}
