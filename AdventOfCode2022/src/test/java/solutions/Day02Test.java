package solutions;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static junit.framework.TestCase.assertEquals;

public class Day02Test {
    Day02 day02;

    @Before
    public void setUp() {
        day02 = new Day02(new File(
                "D:\\programmeer projecten\\AdventofCode\\AdventofCode2022\\AdventOfCode2022\\inputs\\Day02.txt"));
    }

    @Test
    public void testDay02PartOne() {
        assertEquals(Integer.valueOf(12772), day02.runPartOne());
    }

    @Test
    public void testDay02PartTwo() {
        assertEquals(Integer.valueOf(11618), day02.runPartTwo());
    }
}
