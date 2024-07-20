package VM;

import java.util.ArrayList;

public class WriteStr implements Instruction{
    private final int offset;
    private final String string;
    WriteStr(int offset, String string) {
        this.offset = offset;
        this.string = string;
    }

    private static String parse_string(String twoChar) {
        return switch (twoChar) {
            case "\\\\" -> "\\";
            case "\\n" -> "\n";
            case "\\t" -> "\t";
            case "\\r" -> "\r";
            case "\\s" -> " ";
            case "\\f" -> "\f";
            case "\\b" -> "\b";
            case "\\\"" -> "\"";
            case "\\'" -> "'";
            default -> twoChar;
        };
    }

    @Override
    public void execute(VmRuntime runtime) {
        ArrayList<String> readyString = new ArrayList<>();
        for (int k = 0; k < string.length() - 1; k++) {
            if (string.charAt(k) == '\\') {
                readyString.add(parse_string(string.substring(k, k + 2)));
                k++;
            } else {
                readyString.add(string.substring(k, k + 1));
            }
        }
        runtime.setStackAt(offset, runtime.writeStr(String.join("", readyString)));
    }

    @Override
    public void println(VmRuntime runtime) {
        System.out.printf("%d: WRITE_STR: '%s' to address %d\n", runtime.getInstructionNumber(), string, offset);
    }
}
