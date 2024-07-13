package Parsing;

import java.util.Arrays;

public class Function {
    public final String name;
    public final Variable[] arguments;
    public final Variable[] locals;
    public final Instruction[] instructions;

    public Function(String name, Variable[] arguments, Variable[] locals, Instruction[] instructions) {
        this.name = name;
        this.arguments = arguments;
        this.locals = locals;
        this.instructions = instructions;
    }

    @Override
    public String toString() {
        String[] args = new String[arguments.length];
        String[] vars = new String[locals.length];
        String[] instructs = new String[instructions.length];
        for (int k = 0; k < args.length; k++) {
            args[k] = arguments[k].toString();
        }
        for (int k = 0; k < vars.length; k++) {
            vars[k] = locals[k].toString();
        }
        for (int k = 0; k < instructs.length; k++) {
            instructs[k] = instructions[k].toString();
        }
        return String.format("""
                Function %s:
                    ARGS: %s
                    VARS: %s
                    INSTRUCTS: %s
                """, String.join(", ", args),
                String.join(", ", vars),
                String.join(", ", instructs));
    }
}
