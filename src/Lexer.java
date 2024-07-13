import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class Lexer {
    public static List<Token> tokenize(String input) {
        List<Token> tokens = new ArrayList<>();
        String[] splitByLine = input.split("\n");
        List<String> splitSpcLine = new ArrayList<>();
        for (String elementOfSplitByLine : splitByLine) {
            String[] splitBySpc = elementOfSplitByLine.split(" ");
            splitSpcLine.addAll(Arrays.asList(splitBySpc));
        }
        for (String elementOfSplitSpcLine : splitSpcLine) {
            boolean isStartsWithSpecialSymbol = (elementOfSplitSpcLine.charAt(0) == '#' || elementOfSplitSpcLine.charAt(0) == '@');
            if (isStartsWithSpecialSymbol && elementOfSplitSpcLine.charAt(elementOfSplitSpcLine.length() - 1) == ';') {
                tokens.add(new Token(String.valueOf(elementOfSplitSpcLine.charAt(0))));
                tokens.add(new Token(elementOfSplitSpcLine.substring(1, elementOfSplitSpcLine.length() - 1)));
                tokens.add(new Token(elementOfSplitSpcLine.substring(elementOfSplitSpcLine.length() - 1)));

            } else if (isStartsWithSpecialSymbol) {
                tokens.add(new Token(String.valueOf(elementOfSplitSpcLine.charAt(0))));
                tokens.add(new Token(elementOfSplitSpcLine.substring(1)));

            } else if (elementOfSplitSpcLine.charAt(elementOfSplitSpcLine.length() - 1) == ';') {
                tokens.add(new Token(elementOfSplitSpcLine.substring(0, elementOfSplitSpcLine.length() - 1)));
                tokens.add(new Token(elementOfSplitSpcLine.substring(elementOfSplitSpcLine.length() - 1)));

            } else {
                tokens.add(new Token(elementOfSplitSpcLine));
            }
        }
        return tokens;
    }
}
