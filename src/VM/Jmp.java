package VM;

public class Jmp implements Instruction {
    private final int cmpType;
    private final int offsetLeft;
    private final int offsetRight;
    private final int destination;

    Jmp(int cmpType, int offsetLeft, int offsetRight, int destination) {
        this.cmpType = cmpType;
        this.offsetLeft = offsetLeft;
        this.offsetRight = offsetRight;
        this.destination = destination;
    }

    @Override
    public void execute(VmRuntime runtime) {
        runtime.jumpBy(this.destination);
    }

    @Override
    public void println() {
        System.out.println("JMP");
    }
}
