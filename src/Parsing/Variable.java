package Parsing;

public class Variable {
    public final String name;
    public final String type;
    public Variable(String name, String type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        return name + ": " + type;
    }
}
