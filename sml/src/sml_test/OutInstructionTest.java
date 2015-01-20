package sml_test;

import org.junit.Before;
import org.junit.Test;
import sml.OutInstruction;

import static org.junit.Assert.*;

public class OutInstructionTest {
    MachineMock m;

    @Before
    public void setUp() {
        m = new MachineMock();
    }

    @Test
    public void testExecute() {
        // print contents of reg-31 to console. Should be 0
        new OutInstruction("L1", 31).execute(m);
    }

    @Test
    public void testToString() {
        String s = new OutInstruction("L50", 20).toString();
        assertTrue("result was " + s, "L50: out printed contents of register 20".equals(s));
    }
}