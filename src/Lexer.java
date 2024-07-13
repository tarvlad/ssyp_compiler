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
        for (String elementsOfSplitSpcLine : splitSpcLine) {
            boolean isStartsWithSpecialSymbol = (elementsOfSplitSpcLine.charAt(0) == '#' || elementsOfSplitSpcLine.charAt(0) == '@');
            if (isStartsWithSpecialSymbol && elementsOfSplitSpcLine.charAt(elementsOfSplitSpcLine.length() - 1) == ';') {
                tokens.add(new Token(String.valueOf(elementsOfSplitSpcLine.charAt(0))));
                tokens.add(new Token(elementsOfSplitSpcLine.substring(1, elementsOfSplitSpcLine.length() - 1)));
                tokens.add(new Token(elementsOfSplitSpcLine.substring(elementsOfSplitSpcLine.length() - 1)));

            } else if (isStartsWithSpecialSymbol) {
                tokens.add(new Token(String.valueOf(elementsOfSplitSpcLine.charAt(0))));
                tokens.add(new Token(elementsOfSplitSpcLine.substring(1)));

            } else if (elementsOfSplitSpcLine.charAt(elementsOfSplitSpcLine.length() - 1) == ';') {
                tokens.add(new Token(elementsOfSplitSpcLine.substring(0, elementsOfSplitSpcLine.length() - 1)));
                tokens.add(new Token(elementsOfSplitSpcLine.substring(elementsOfSplitSpcLine.length() - 1)));

            } else {
                tokens.add(new Token(elementsOfSplitSpcLine));
            }
        }
        return tokens;
    }
}
