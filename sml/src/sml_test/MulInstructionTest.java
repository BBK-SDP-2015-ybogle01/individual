package sml_test;

import org.junit.Before;
import org.junit.Test;
import sml.MulInstruction;

import static org.junit.Assert.*;

public class MulInstructionTest {
    MachineMock m;

    @Before
    public void setUp() throws Exception {
        m = new MachineMock();
    }

    @Test
    public void testExecuteMultiplyTwoNumber() {
        // 5 * 3 into reg-5
        m.getRegisters().setRegister(10,5);
        m.getRegisters().setRegister(11,3);
        new MulInstruction("L1", 5, 10, 11).execute(m);
        assertEquals(15, m.getRegisters().getRegister(5));
    }

    @Test
    public void testToString() {
        m.getRegisters().setRegister(0,10);
        m.getRegisters().setRegister(30,20);
        String s = new MulInstruction("L3", 20, 0, 30).toString();
        assertTrue("result was " + s, "L3: mul 0 * 30 to 20".equals(s));
    }
}