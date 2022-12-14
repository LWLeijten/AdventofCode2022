package solutions;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static junit.framework.TestCase.assertEquals;

public class Day14Test {
    Day14 day14;

    @Before
    public void setUp() {
        day14 = new Day14(new File(
                "D:\\programmeer projecten\\AdventofCode\\AdventofCode2022\\AdventOfCode2022\\inputs\\Day14.txt"));
    }

    @Test
    public void testDay14PartOne() {
        assertEquals(Integer.valueOf(779), day14.runPartOne());
    }

    @Test
    public void testDay14PartTwo() {
        assertEquals(Integer.valueOf(27426), day14.runPartTwo());
    }
}
