package sml_test;

import org.junit.Before;
import org.junit.Test;
import sml.BnzInstruction;

import static org.junit.Assert.assertEquals;
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
        m.getLabels().addLabel("Null-0");
        m.getLabels().addLabel("Null-1");
        m.getLabels().addLabel("Null-2");
        m.getLabels().addLabel("BranchTarget");
        new BnzInstruction("L1", 10, "BranchTarget").execute(m);
        assertEquals(3, m.getPc());
    }

    @Test
    public void testExecuteIsZero() {
        // check reg-5 which is 0
        m.getRegisters().setRegister(5, 0);
        m.getLabels().addLabel("Null-0");
        m.getLabels().addLabel("Null-1");
        m.getLabels().addLabel("Null-2");
        m.getLabels().addLabel("BranchTarget");
        new BnzInstruction("L1", 5, "BranchTarget").execute(m);
        assertEquals(0, m.getPc());
    }

    @Test
    public void testToString() {
        fail();
    }
}