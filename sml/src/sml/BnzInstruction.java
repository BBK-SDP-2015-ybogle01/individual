package sml;

/**
 * Created by byakov on 20/01/2015.
 */
public class BnzInstruction extends Instruction {
    private int register;
    private String branchLabel;

    public BnzInstruction(String label, String op) {
        super(label, op);
    }

    public BnzInstruction(String label, int register, String branchLabel) {
        this(label, "bnz");
        this.register = register;
        this.branchLabel = branchLabel;
    }

    @Override
    public void execute(Machine m) {
        int test = m.getRegisters().getRegister(register);
        if (test != 0) m.setPc(m.getLabels().indexOf(branchLabel));
    }

    @Override
    public String toString() {
        return null;
    }
}
