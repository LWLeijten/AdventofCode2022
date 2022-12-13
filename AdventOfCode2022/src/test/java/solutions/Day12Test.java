package solutions;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static junit.framework.TestCase.assertEquals;

public class Day12Test {
    Day12 day12;

    @Before
    public void setUp() {
        day12 = new Day12(new File(
                "D:\\programmeer projecten\\AdventofCode\\AdventofCode2022\\AdventOfCode2022\\inputs\\Day12.txt"));
    }

    @Test
    public void testDay12PartOne() {
        assertEquals(Integer.valueOf(447), day12.runPartOne());
    }

    @Test
    public void testDay12PartTwo() {
        assertEquals(Integer.valueOf(446), day12.runPartTwo());
    }
}
