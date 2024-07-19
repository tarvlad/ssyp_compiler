package VM;

public class WriteStr implements Instruction{
    private final int offset;
    private final String string;
    WriteStr(int offset, String string) {
        this.offset = offset;
        this.string = string;
    }

    @Override
    public void execute(VmRuntime runtime) {
        runtime.setStackAt(offset, runtime.writeStr(string));
    }

    @Override
    public void println(VmRuntime runtime) {
        System.out.printf("%d: WRITE_STR: '%s' to address %d\n", runtime.getInstructionNumber(), string, offset);
    }
}
