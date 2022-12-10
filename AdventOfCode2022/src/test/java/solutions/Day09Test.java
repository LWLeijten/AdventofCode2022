package solutions;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static junit.framework.TestCase.assertEquals;

public class Day09Test {
    Day09 day09;

    @Before
    public void setUp() {
        day09 = new Day09(new File(
                "D:\\programmeer projecten\\AdventofCode\\AdventofCode2022\\AdventOfCode2022\\inputs\\Day09.txt"));
    }

    @Test
    public void testDay09PartOne() {
        assertEquals(Integer.valueOf(6044), day09.runPartOne());
    }

    @Test
    public void testDay09PartTwo() {
        assertEquals(Integer.valueOf(2384), day09.runPartTwo());
    }
}
