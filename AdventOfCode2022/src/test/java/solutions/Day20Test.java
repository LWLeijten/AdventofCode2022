package solutions;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static junit.framework.TestCase.assertEquals;

public class Day20Test {
    Day20 day20;

    @Before
    public void setUp() {
        day20 = new Day20(new File(
                "D:\\programmeer projecten\\AdventofCode\\AdventofCode2022\\AdventOfCode2022\\inputs\\Day20.txt"));
    }

    @Test
    public void testDay20PartOne() {
        assertEquals(Long.valueOf(14888L), day20.runPartOne());
    }

    @Test
    public void testDay20PartTwo() {
        assertEquals(Long.valueOf(3760092545849L), day20.runPartTwo());
    }
}
