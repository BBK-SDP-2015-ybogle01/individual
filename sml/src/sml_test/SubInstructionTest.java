package sml_test;

import org.junit.Before;
import org.junit.Test;
import sml.Machine;
import sml.SubInstruction;

import static org.junit.Assert.*;

/**
 * Created by byakov on 20/01/2015.
 */
public class SubInstructionTest {
    Machine m;

    @Before
    public void setUp() {
        m = new MachineMock();
    }

    @Test
    public void testExecuteWithPositiveResult() {
        // 5 - 3 into reg-5
        m.getRegisters().setRegister(10,5);
        m.getRegisters().setRegister(11,3);
        new SubInstruction("L1", 5, 10, 11).execute(m);
        assertEquals(2, m.getRegisters().getRegister(5));
    }

    @Test
    public void testExecuteWithNegativeResult() {
        // 10 - 300 into reg-27
        m.getRegisters().setRegister(1,10);
        m.getRegisters().setRegister(21,300);
        new SubInstruction("L2", 27, 1, 21).execute(m);
        assertEquals(-290, m.getRegisters().getRegister(27));
    }

    @Test
    public void testToString() {
        m.getRegisters().setRegister(0,10);
        m.getRegisters().setRegister(30,20);
        String s = new SubInstruction("L3", 20, 0, 30).toString();
        assertTrue("result was " + s, "L3: sub 0 - 30 to 20".equals(s));
    }
}
