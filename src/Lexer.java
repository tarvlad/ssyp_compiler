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
        for (String elsSplitSpcLine : splitSpcLine) {
            boolean isStartsWithSpecialSymbol = (elsSplitSpcLine.charAt(0) == '#' || elsSplitSpcLine.charAt(0) == '@');
            if (isStartsWithSpecialSymbol && elsSplitSpcLine.charAt(elsSplitSpcLine.length() - 1) == ';') {
                tokens.add(new Token(String.valueOf(elsSplitSpcLine.charAt(0))));
                tokens.add(new Token(elsSplitSpcLine.substring(1, elsSplitSpcLine.length() - 1)));
                tokens.add(new Token(elsSplitSpcLine.substring(elsSplitSpcLine.length() - 1)));

            } else if (isStartsWithSpecialSymbol) {
                tokens.add(new Token(String.valueOf(elsSplitSpcLine.charAt(0))));
                tokens.add(new Token(elsSplitSpcLine.substring(1)));

            } else if (elsSplitSpcLine.charAt(elsSplitSpcLine.length() - 1) == ';') {
                tokens.add(new Token(elsSplitSpcLine.substring(0, elsSplitSpcLine.length() - 1)));
                tokens.add(new Token(elsSplitSpcLine.substring(elsSplitSpcLine.length() - 1)));

            } else {
                tokens.add(new Token(elsSplitSpcLine));
            }
        }
        return tokens;
    }
}
