package Translator;

public class WriteStr implements BytecodeInstruction {
    private final int stringOffset;
    private final String string;

    public WriteStr(int stringOffset, String string) {
        this.stringOffset = stringOffset;
        this.string = string;
    }

    @Override
    public BytecodeType kind() {
        return BytecodeType.WRITE_STR;
    }

    @Override
    public String toString() {
        return String.format("WRITE_STR %d %s", stringOffset, string);
    }
}
