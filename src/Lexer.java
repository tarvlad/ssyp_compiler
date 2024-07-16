import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Lexer {
    public static List<String> tokenize(String input) {
        List <String> tokens = new ArrayList<>();
        List<String> splitSLines = getSplitBySpc(input);

        for (String elem : splitSLines) {
            boolean startsWSpec = elem.charAt(0) == '#' || elem.charAt(0) == '@';
            int lastIdx = elem.length() - 1;

            if (startsWSpec && elem.charAt(lastIdx) == ';') {
                tokens.add(String.valueOf(elem.charAt(0)));
                tokens.add(elem.substring(1, lastIdx));
                tokens.add(String.valueOf(elem.charAt(lastIdx)));

            } else if (startsWSpec) {
                tokens.add(String.valueOf(elem.charAt(0)));
                tokens.add(elem.substring(1));

            } else if (elem.charAt(lastIdx) == ';') {
                tokens.add(elem.substring(0, lastIdx));
                tokens.add(String.valueOf(elem.charAt(lastIdx)));

            } else {
                tokens.add(elem);
            }
        }
        return tokens;
    }

    private static List<String> getSplitBySpc(String input) {
        assert !input.contains("\t");

        String splitBySemicolon = input
                .replace("\r", "")
                .replace("\n", "")
                .replace(";", "; ");
        List <String> splitSLines = new ArrayList<>(List.of(splitBySemicolon.split(" ")));

        int size = splitSLines.size();
        for (int i = 0; i < size; ++i) {
            if (splitSLines.get(i).isEmpty()) {
                splitSLines.remove(i);
                --i;
            }
            size = splitSLines.size();
        }
        return splitSLines;
    }

    public static List<String> tokenizeFromFile(String filename) {
        try {
            return tokenize(Files.readString(Path.of(filename), Charset.defaultCharset()));
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }
}





