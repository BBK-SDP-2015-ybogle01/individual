package sml;

/**
 * Created by byakov on 20/01/2015.
 */
public class MulInstruction extends Instruction {
    private int result;
    private int op1;
    private int op2;

    public MulInstruction(String l, String op) {
        super(l, op);
    }

    public MulInstruction(String label, int result, int op1, int op2) {
        this(label, "mul");
        this.result = result;
        this.op1 = op1;
        this.op2 = op2;
    }

    @Override
    public void execute(Machine m) {

    }
}
