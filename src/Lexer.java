import java.util.ArrayList;
import java.util.List;

public class Lexer {
    public static Token[] tokenize(String input) {
        List<Token> tokens = new ArrayList<Token>();
        String[] splitByLinebreak = input.split("\n");
        List<String> splitByLinebreakAndSpace = new ArrayList<String>();
        for (String s : splitByLinebreak) {
            String[] splitBySpace = s.split(" ");
            for (String elsOfSplitBySpace : splitBySpace) {
                splitByLinebreakAndSpace.add(elsOfSplitBySpace);
            }
        }
        List<String> splitByFunctions = new ArrayList<String>();
        for (String elsOfSplitByLinebreakAndSpase : splitByLinebreakAndSpace) {
            if ((elsOfSplitByLinebreakAndSpase.toCharArray()[0] == '#' || elsOfSplitByLinebreakAndSpase.toCharArray()[0] == '@') && elsOfSplitByLinebreakAndSpase.toCharArray()[elsOfSplitByLinebreakAndSpase.toCharArray().length - 1] == ';') {
                tokens.add(new Token(elsOfSplitByLinebreakAndSpase.substring(0, 1)));
                tokens.add(new Token(elsOfSplitByLinebreakAndSpase.substring(1, elsOfSplitByLinebreakAndSpase.toCharArray().length - 1)));
                tokens.add(new Token(elsOfSplitByLinebreakAndSpase.substring(elsOfSplitByLinebreakAndSpase.toCharArray().length - 1, elsOfSplitByLinebreakAndSpase.toCharArray().length)));
            } else if (elsOfSplitByLinebreakAndSpase.toCharArray()[0] == '#' || elsOfSplitByLinebreakAndSpase.toCharArray()[0] == '@') {
                tokens.add(new Token(elsOfSplitByLinebreakAndSpase.substring(0, 1)));
                tokens.add(new Token(elsOfSplitByLinebreakAndSpase.substring(1, elsOfSplitByLinebreakAndSpase.toCharArray().length)));
            } else if (elsOfSplitByLinebreakAndSpase.toCharArray()[elsOfSplitByLinebreakAndSpase.toCharArray().length - 1] == ';') {
                tokens.add(new Token(elsOfSplitByLinebreakAndSpase.substring(0, elsOfSplitByLinebreakAndSpase.toCharArray().length - 1)));
                tokens.add(new Token(elsOfSplitByLinebreakAndSpase.substring(elsOfSplitByLinebreakAndSpase.toCharArray().length - 1, elsOfSplitByLinebreakAndSpase.toCharArray().length)));
            } else {
                tokens.add(new Token(elsOfSplitByLinebreakAndSpase));
            }
        }
        return null;
    }
}
