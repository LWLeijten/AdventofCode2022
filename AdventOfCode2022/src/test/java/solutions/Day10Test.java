package solutions;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

public class Day10Test {
    Day10 day10;

    @Before
    public void setUp() {
        day10 = new Day10(new File(
                "D:\\programmeer projecten\\AdventofCode\\AdventofCode2022\\AdventOfCode2022\\inputs\\Day10.txt"));
    }

    @Test
    public void testDay10PartOne() {
        assertEquals(Integer.valueOf(15680), day10.runPartOne());
    }

    @Test
    public void testDay10PartTwo() {
        assertNull(day10.runPartTwo());
    }
}
