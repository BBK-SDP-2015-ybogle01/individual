package sml_test;

import sml.Machine;
import sml.Registers;

/**
 * Created by byakov on 20/01/2015.
 */

public class MachineMock extends Machine {
    public MachineMock() {
        setRegisters(new Registers());
    }
}
