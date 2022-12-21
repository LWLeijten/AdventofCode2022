package solutions;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static junit.framework.TestCase.assertEquals;

public class Day18Test {
    Day18 day18;

    @Before
    public void setUp() {
        day18 = new Day18(new File(
                "D:\\programmeer projecten\\AdventofCode\\AdventofCode2022\\AdventOfCode2022\\inputs\\Day18.txt"));
    }

    @Test
    public void testDay18PartOne() {
        assertEquals(Integer.valueOf(3564), day18.runPartOne());
    }

    @Test
    public void testDay18PartTwo() {
        assertEquals(Integer.valueOf(2106), day18.runPartTwo());
    }
}
