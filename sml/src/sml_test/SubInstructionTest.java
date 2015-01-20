package sml_test;

import org.junit.Before;
import org.junit.Test;
import sml.Machine;
import sml.SubInstruction;

import static org.junit.Assert.assertEquals;

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
}
