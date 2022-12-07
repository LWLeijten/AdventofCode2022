package solutions;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static junit.framework.TestCase.assertEquals;

public class Day07Test {
    Day07 day07;

    @Before
    public void setUp() {
        day07 = new Day07(new File(
                "D:\\programmeer projecten\\AdventofCode\\AdventofCode2022\\AdventOfCode2022\\inputs\\Day07.txt"));
    }

    @Test
    public void testDay07PartOne() {
        assertEquals(Integer.valueOf(1118405), day07.runPartOne());
    }

    @Test
    public void testDay07PartTwo() {
        assertEquals(Integer.valueOf(12545514), day07.runPartTwo());
    }
}
