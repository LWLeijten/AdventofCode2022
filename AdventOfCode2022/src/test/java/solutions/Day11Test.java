package solutions;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static junit.framework.TestCase.assertEquals;

public class Day11Test {
    Day11 day11;

    @Before
    public void setUp() {
        day11 = new Day11(new File(
                "D:\\programmeer projecten\\AdventofCode\\AdventofCode2022\\AdventOfCode2022\\inputs\\Day11.txt"));
    }

    @Test
    public void testDay11PartOne() {
        assertEquals(Long.valueOf(66124), day11.runPartOne());
    }

    @Test
    public void testDay11PartTwo() {
        assertEquals(Long.valueOf(19309892877L), day11.runPartTwo());
    }
}
