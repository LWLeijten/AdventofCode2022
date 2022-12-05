package solutions;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static junit.framework.TestCase.assertEquals;

public class Day05Test {
    Day05 day05;

    @Before
    public void setUp() {
        day05 = new Day05(new File(
                "D:\\programmeer projecten\\AdventofCode\\AdventofCode2022\\AdventOfCode2022\\inputs\\Day05.txt"));
    }

    @Test
    public void testDay05PartOne() {
        assertEquals("ZRLJGSCTR", day05.runPartOne());
    }

    @Test
    public void testDay05PartTwo() {
        assertEquals("PRTTGRFPB", day05.runPartTwo());
    }
}
