package solutions;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static junit.framework.TestCase.assertEquals;

public class Day08Test {
    Day08 day08;

    @Before
    public void setUp() {
        day08 = new Day08(new File(
                "D:\\programmeer projecten\\AdventofCode\\AdventofCode2022\\AdventOfCode2022\\inputs\\Day08.txt"));
    }

    @Test
    public void testDay08PartOne() {
        assertEquals(Integer.valueOf(1546), day08.runPartOne());
    }

    @Test
    public void testDay08PartTwo() {
        assertEquals(Integer.valueOf(519064), day08.runPartTwo());
    }
}
