package solutions;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static junit.framework.TestCase.assertEquals;

public class DayO1Test {
    Day01 day01;

    @Before
    public void setUp() {
        day01 = new Day01(new File("D:\\programmeer projecten\\AdventofCode\\AdventofCode2022\\AdventOfCode2022\\inputs\\Day01.txt"));
    }

    @Test
    public void testDay01PartOne() {
        assertEquals(68802, day01.RunPartOne());
    }

    @Test
    public void testDay01PartTwo() {
        assertEquals(205370, day01.RunPartTwo());
    }
}
