package VM;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class ConservativeGC {
    public void cleanupHeap(VmRuntime runtime, int extraKey) {
        ArrayList<Integer> seenArrays = new ArrayList<>(4);
        seenArrays.add(extraKey);

        for (int slotValue : runtime.getRawStackView()) {
            Integer[] array = runtime.getArray(slotValue);
            if (array != null && !seenArrays.contains(slotValue)) {
                seenArrays.add(slotValue);

                for (int i: IntStream.range(0, array.length).toArray()) {
                    if ( array[i] != null && runtime.getArray(array[i]) != null && !seenArrays.contains(array[i])) {
                        seenArrays.add(array[i]);
                    }
                }
            }
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
