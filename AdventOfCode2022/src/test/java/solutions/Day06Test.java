package solutions;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static junit.framework.TestCase.assertEquals;

public class Day06Test {
    Day06 day06;

    @Before
    public void setUp() {
        day06 = new Day06(new File(
                "D:\\programmeer projecten\\AdventofCode\\AdventofCode2022\\AdventOfCode2022\\inputs\\Day06.txt"));
    }

    @Test
    public void testDay06PartOne() {
        assertEquals(Integer.valueOf(1655), day06.runPartOne());
    }

    @Test
    public void testDay06PartTwo() {
        assertEquals(Integer.valueOf(2665), day06.runPartTwo());
    }
}
