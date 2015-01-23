package sml;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Created by byakov on 19/01/2015.
 */
public class Translator {

    // word + line is the part of the current line that's not yet processed
    // word has no whitespace
    // If word and line are not empty, line begins with whitespace
    private String line = "";
    private Labels labels; // The labels of the program being translated
    private ArrayList<Instruction> program; // The program to be created
    private String fileName; // source file of SML code

    private static final String SRC = "C:\\src\\individual\\sml\\src\\sml_test"; //TODO return SRC to original consstant val.

    public Translator(String fileName) {
        this.fileName = SRC + "\\" + fileName;
    }

    // translate the small program in the file into lab (the labels) and
    // prog (the program)
    // return "no errors were detected"
    public boolean readAndTranslate(Labels lab, ArrayList<Instruction> prog) {

        try (Scanner sc = new Scanner(new File(fileName))) {
            // Scanner attached to the file chosen by the user
            labels = lab;
            labels.reset();
            program = prog;
            program.clear();

            try {
                line = sc.nextLine();
            } catch (NoSuchElementException ioE) {
                return false;
            }

            // Each iteration processes line and reads the next line into line
            while (line != null) {
                // Store the label in label
                String label = scan();

                if (label.length() > 0) {
                    Instruction ins = getInstruction(label);
                    if (ins != null) {
                        labels.addLabel(label);
                        program.add(ins);
                    }
                }

                try {
                    line = sc.nextLine();
                } catch (NoSuchElementException ioE) {
                    return false;
                }
            }
        } catch (IOException ioE) {
            System.out.println("File: IO error " + ioE.getMessage());
            return false;
        }
        return true;
    }

    // line should consist of an MML instruction, with its label already
    // removed. Translate line into an instruction with label label
    // and return the instruction
    public Instruction getInstruction(String label) {
        int s1; // Possible operands of the instruction
        int s2;
        int r;
        String x;

        if (line.equals(""))
            return null;

        // Get the instruction class's name
        String ins = scan().toLowerCase();
        char first = Character.toUpperCase(ins.charAt(0));
        String insName = "sml." +
                first +
                ins.substring(1) +
                "Instruction";

        // Load the class object and obtain the correct constructor
        Constructor<?> cstr = null;
        Class<?>[] cstrParams = null;
        try {
            Class<?> instrClass = Class.forName(insName);
            Constructor<?>[] cstrArr = instrClass.getConstructors();
            for (Constructor<?> aCstr : cstrArr) {
                if (Arrays.asList(aCstr.getParameterTypes()).contains(int.class)) {
                    cstr = aCstr;
                    cstrParams = cstr.getParameterTypes();
                    break;
                }
            }
        } catch (ClassNotFoundException | NullPointerException e) {
            e.printStackTrace();
        }
        // Read the correct params from file and pass them to the correct Instruction constructor + return new instance
        try {
            return (Instruction) cstr.newInstance(getParamObjectArray(cstrParams, label));
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Takes parameter classes, instantiates them, and returns the resulting object array
     * @param paramClasses array of parameter classes used by the constructor
     * @param label the first parameter is always the instruction label, which was read earlier
     * @return object array of parameters to the constructor, in declaration order
     */
    private Object[] getParamObjectArray(Class<?>[] paramClasses, String label) {
        Object[] params = new Object[paramClasses.length];
        params[0] = label;
        for (int i = 1; i < paramClasses.length; i++) {
            Class<?> aClass = paramClasses[i];
            if (aClass.getSimpleName().equals("String")) params[i] = scan();
            else params[i] = scanInt();
        }
        return params;
    }

    /*
     * Return the first word of line and remove it from line. If there is no
     * word, return ""
     */
    private String scan() {
        line = line.trim();
        if (line.length() == 0)
            return "";

        int i = 0;
        while (i < line.length() && line.charAt(i) != ' ' && line.charAt(i) != '\t') {
            i = i + 1;
        }
        String word = line.substring(0, i);
        line = line.substring(i);
        return word;
    }

    // Return the first word of line as an integer. If there is
    // any error, return the maximum int
    private int scanInt() {
        String word = scan();
        if (word.length() == 0) {
            return Integer.MAX_VALUE;
        }

        try {
            return Integer.parseInt(word);
        } catch (NumberFormatException e) {
            return Integer.MAX_VALUE;
        }
    }
}
