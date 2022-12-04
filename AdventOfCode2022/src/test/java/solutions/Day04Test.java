package solutions;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static junit.framework.TestCase.assertEquals;

public class Day04Test {
    Day04 day04;

    @Before
    public void setUp() {
        day04 = new Day04(new File(
                "D:\\programmeer projecten\\AdventofCode\\AdventofCode2022\\AdventOfCode2022\\inputs\\Day04.txt"));
    }

    @Test
    public void testDay04PartOne() {
        assertEquals(487, day04.runPartOne());
    }

    @Test
    public void testDay04PartTwo() {
        assertEquals(849, day04.runPartTwo());
    }
}
