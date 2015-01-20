package sml_test;

import org.junit.Before;
import org.junit.Test;
import sml.LinInstruction;

import static org.junit.Assert.assertEquals;

public class LinInstructionTest {
    MachineMock m;

    @Before
    public void setUp() {
        m = new MachineMock();
    }

    @Test
    public void testExecute() {
        // 500 into reg-30
        new LinInstruction("L1", 30, 500).execute(m);
        assertEquals(500, m.getRegisters().getRegister(30));
    }

    @Test
    public void testToString() throws Exception {

    }
}