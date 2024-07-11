package VM;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

public class VmRuntime {
    private final List<Integer> stack;
    private final List<Pair<String, Integer>> callStack;
    private final int endInstruction = Integer.MAX_VALUE;
    private final HashMap<String, List<Instruction>> instructions = new HashMap<>();
    private List<Instruction> currentInstructions = new ArrayList<Instruction>();
    private String currentFunctionName = "main";

    private int stackBase;
    private int currentInstruction = 0;

    VmRuntime(InputReader reader, int size) {
        this.stack = new ArrayList<>(size + 1);
        this.callStack = new ArrayList<>(size + 1);

        for (int i : IntStream.range(0, size + 1).toArray()) {
            this.stack.add(0);
        }

        this.stack.set(size, this.endInstruction);
        this.stackBase = size - 1;

        while (reader.hasNext()) {
            Pair<String, ArrayList<Instruction>> pair = reader.nextFunction();
            pair.get1().iterator().forEachRemaining(Instruction::println);
            this.instructions.put(pair.get0(), pair.get1());
        }

        this.callStack.add(new Pair<>(currentFunctionName, this.stackBase));
        this.currentInstructions = this.instructions.get(currentFunctionName);
    }

    public void run() {
        while (this.currentInstructions.size() > this.currentInstruction) {
            this.runNext();
            assert Integer.signum(this.currentInstruction) == 1;
        }

        for (int i : IntStream.range(0, this.stack.size()).toArray()) {
            System.out.println(this.stack.get(i));
        }
    }

    private void runNext() {
        this.currentInstructions.get(this.currentInstruction).execute(this);
        this.currentInstruction++;
    }

    public int stackAt(int offset) {
        return this.stack.get(this.stackBase + offset);
    }

    public void setStackAt(int offset, int value) {
        int stackField = this.stackBase + offset;

        this.stack.set(stackField, value);
    }

    public void enterNewFunction(String functionLabel, int offset) {
        this.callStack.getLast().set1(this.currentInstruction + 1);

        this.currentInstructions = this.instructions.get(functionLabel);

        assert Integer.signum(offset) == -1;

        this.stackBase += offset;
        this.currentInstruction = 0;

        this.currentFunctionName = functionLabel;
        this.callStack.add(new Pair<>(this.currentFunctionName, this.stackBase));
    }

    public void returnWith(int obj) {
        if (this.stackAt(1) == this.endInstruction) {
            this.currentInstruction = this.currentInstructions.size();
            this.stackBase = this.stack.size() - 1;

            System.out.println(obj);
        } else {
            this.currentInstruction = this.callStack.removeLast().get1();
            int base = this.stackAt(1);
            this.setStackAt(1, obj);
            this.stackBase = base;
        }
    }

    public void jumpBy(int offset) {
        this.currentInstruction += offset;

        assert Integer.signum(this.currentInstruction) == 1 && this.currentInstruction < this.currentInstructions.size();
    }
}