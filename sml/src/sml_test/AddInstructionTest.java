package sml_test;

import org.junit.Before;
import org.junit.Test;
import sml.AddInstruction;
import sml.Machine;

import static org.junit.Assert.assertEquals;

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
    public void testToString() {

    }
}