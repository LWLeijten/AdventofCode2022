package solutions;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static junit.framework.TestCase.assertEquals;

public class Day03Test {
    Day03 day03;

    @Before
    public void setUp() {
        day03 = new Day03(new File(
                "D:\\programmeer projecten\\AdventofCode\\AdventofCode2022\\AdventOfCode2022\\inputs\\Day03.txt"));
    }

    @Test
    public void testDay02PartOne() {
        assertEquals(8153, day03.runPartOne());
    }

    @Test
    public void testDay02PartTwo() {
        assertEquals(2342, day03.runPartTwo());
    }
}
