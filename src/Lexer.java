import java.util.ArrayList;
import java.util.List;

public class Lexer {
    public static List<Token> tokenize(String input) {
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
            char[] toCharElsOfSplitEnd = elsOfSplitByLinebreakAndSpase.toCharArray();
            if ((toCharElsOfSplitEnd[0] == '#' || toCharElsOfSplitEnd[0] == '@') && toCharElsOfSplitEnd[toCharElsOfSplitEnd.length - 1] == ';') {
                tokens.add(new Token(elsOfSplitByLinebreakAndSpase.substring(0, 1)));
                tokens.add(new Token(elsOfSplitByLinebreakAndSpase.substring(1, toCharElsOfSplitEnd.length - 1)));
                tokens.add(new Token(elsOfSplitByLinebreakAndSpase.substring(toCharElsOfSplitEnd.length - 1, elsOfSplitByLinebreakAndSpase.toCharArray().length)));
            } else if (toCharElsOfSplitEnd[0] == '#' || toCharElsOfSplitEnd[0] == '@') {
                tokens.add(new Token(elsOfSplitByLinebreakAndSpase.substring(0, 1)));
                tokens.add(new Token(elsOfSplitByLinebreakAndSpase.substring(1, toCharElsOfSplitEnd.length)));
            } else if (toCharElsOfSplitEnd[elsOfSplitByLinebreakAndSpase.toCharArray().length - 1] == ';') {
                tokens.add(new Token(elsOfSplitByLinebreakAndSpase.substring(0, toCharElsOfSplitEnd.length - 1)));
                tokens.add(new Token(elsOfSplitByLinebreakAndSpase.substring(toCharElsOfSplitEnd.length - 1, elsOfSplitByLinebreakAndSpase.toCharArray().length)));
            } else {
                tokens.add(new Token(elsOfSplitByLinebreakAndSpase));
            }
        }
        return tokens;
    }
}
