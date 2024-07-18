package VM;

import java.util.Arrays;

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
                runtime.getArray(runtime.stackAt(this.arrayOffset))[runtime.stackAt(this.index)]);
    }

    @Override
    public void println(VmRuntime runtime) {
        System.out.printf("ARRAY_OUT: $%d = %s[%d]\n", this.var, Arrays.toString(runtime.getArray(this.arrayOffset)), runtime.stackAt(this.index));
    }
}
