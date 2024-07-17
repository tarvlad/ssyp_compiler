package VM;

public class ArrayOut implements Instruction {
    private final int arrayOffset;
    private final int index;
    private final int var;
    ArrayOut(int arrayOffset, int index, int var) {
        this.arrayOffset = arrayOffset;
        this.index = index;
        this.var = var;
    }

    @Override
    public void execute(VmRuntime runtime) {
        runtime.setStackAt(
                this.var,
                runtime.getArray(this.arrayOffset)[runtime.stackAt(this.index)]);
    }

    @Override
    public void println() {
        System.out.println("ARRAY_OUT");
    }
}
