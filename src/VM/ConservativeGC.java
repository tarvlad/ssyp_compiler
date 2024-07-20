package VM;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class ConservativeGC {

    private void AddValidArrays(VmRuntime runtime, ArrayList<Integer> seenArrays, int arrayLink) {
        Integer[] array = runtime.getArray(arrayLink);
        if (array != null && !seenArrays.contains(arrayLink)) {
            seenArrays.add(arrayLink);

            for (int i: IntStream.range(0, array.length).toArray()) {
                if ( array[i] != null) {
                    AddValidArrays(runtime, seenArrays, array[i]);
                }
            }
        }
    }
    public void cleanupHeap(VmRuntime runtime, int extraKey) {
        ArrayList<Integer> seenArrays = new ArrayList<>(4);
        seenArrays.add(extraKey);

        for (int slotValue : runtime.getRawStackView()) {
            AddValidArrays(runtime, seenArrays, slotValue);
        }

        for (int key : runtime.getRawHeapPages().stream().toList()) {
            if (!seenArrays.contains(key)) {
                if (runtime.isDebugging()) {
                    System.out.println(STR."Destroyed key:\{key} len:\{runtime.getArray(key).length}");
                }

                runtime.DestroyArray(key);
            }
        }
    }
}
