package sml_test;

import org.junit.Before;
import org.junit.Test;
import sml.AddInstruction;
import sml.Machine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AddInstructionTest {
    Machine m;

    @Before
    public void setUp() {
        m = new MachineMock();
    }

    @Test
    public void testExecuteAddIntoFirstRegister() {
        // 1 + 2 with result in reg-0
        m.getRegisters().setRegister(1, 0);
        m.getRegisters().setRegister(2, 1);
        new AddInstruction("L1", 0, 1, 2).execute(m);
        assertEquals(1, m.getRegisters().getRegister(0));
    }

    @Test
    public void testExecuteAddIntoLastRegister() {
        // 3 + 5 with result into reg-31
        m.getRegisters().setRegister(10,3);
        m.getRegisters().setRegister(20,5);
        new AddInstruction("L2", 31, 20, 10).execute(m);
        assertEquals(8, m.getRegisters().getRegister(31));
    }

    @Test
    public void testToString() {
        m.getRegisters().setRegister(3,33);
        m.getRegisters().setRegister(6,66);
        String s = new AddInstruction("L3", 0, 3, 6).toString();
        assertTrue("result was " + s, "L3: add 3 + 6 to 0".equals(s));
    }
}