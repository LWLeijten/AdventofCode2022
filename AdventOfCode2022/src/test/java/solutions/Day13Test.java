package solutions;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static junit.framework.TestCase.assertEquals;

public class Day13Test {
    Day13 day13;

    @Before
    public void setUp() {
        day13 = new Day13(new File(
                "D:\\programmeer projecten\\AdventofCode\\AdventofCode2022\\AdventOfCode2022\\inputs\\Day13.txt"));
    }

    @Test
    public void testDay13PartOne() {
        assertEquals(Integer.valueOf(5588), day13.runPartOne());
    }

    @Test
    public void testDay13PartTwo() {
        assertEquals(Integer.valueOf(23958), day13.runPartTwo());
    }
}
