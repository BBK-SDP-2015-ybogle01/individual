package sml_test;

import org.junit.Before;
import org.junit.Test;
import sml.MulInstruction;

import static org.junit.Assert.assertEquals;

public class MulInstructionTest {
    MachineMock m;

    @Before
    public void setUp() throws Exception {
        m = new MachineMock();
    }

    @Test
    public void testMultiplyTwoNumber() {
        // 5 * 3 into reg-5
        m.getRegisters().setRegister(10,5);
        m.getRegisters().setRegister(11,3);
        new MulInstruction("L1", 5, 10, 11).execute(m);
        assertEquals(15, m.getRegisters().getRegister(5));
    }
}