package sml;

/**
 * Created by byakov on 20/01/2015.
 */
public class DivInstruction extends Instruction {
    private int result;
    private int op1;
    private int op2;

    public DivInstruction(String l, String op) {
        super(l, op);
    }

    public DivInstruction(String label, int result, int op1, int op2) {
        this(label, "div");
        this.result = result;
        this.op1 = op1;
        this.op2 = op2;
    }

    @Override
    public void execute(Machine m) {

    }

    @Override
    public String toString() {
        return null;
    }
}
