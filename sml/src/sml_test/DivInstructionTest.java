package sml_test;

import org.junit.Before;
import org.junit.Test;
import sml.DivInstruction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class DivInstructionTest {
    MachineMock m;

    @Before
    public void setUp() throws Exception {
        m = new MachineMock();
    }

    @Test
    public void testExecuteDivide() throws Exception {
        // 15 / 3 into reg-5
        m.getRegisters().setRegister(10,15);
        m.getRegisters().setRegister(11,3);
        new DivInstruction("L1", 5, 10, 11).execute(m);
        assertEquals(5, m.getRegisters().getRegister(5));
    }

    @Test (expected = ArithmeticException.class)
    public void testExecuteDivideByZero() throws Exception {
        // 15 / 0 into reg-5
        m.getRegisters().setRegister(10,15);
        m.getRegisters().setRegister(11,0);
        new DivInstruction("L1", 5, 10, 11).execute(m);
    }

    @Test
    public void testToString() throws Exception {
        fail();
    }
}