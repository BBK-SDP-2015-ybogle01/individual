package sml_test;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.fail;

public class BnzInstructionTest {
    MachineMock m;

    @Before
    public void setUp() {
        m = new MachineMock();
    }

    @Test
    public void testExecuteIsNotZero() {
        // check reg-10 which isn't 0
        m.getRegisters().setRegister(10, 10);
        new BnzInstructionTest("L1", 10, "L50");

    }

    @Test
    public void testToString() {
        fail();
    }
}