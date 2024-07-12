package Parsing;

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
}
