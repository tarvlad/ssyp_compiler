import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Lexer {
    public static List<String> tokenize(String inputStr) {
        assert !inputStr.contains("\t");
        List<Integer> quotesBegIdxs = new ArrayList<>();
        List<Integer> quotesEndIdxs = new ArrayList<>();
        char[] input = inputStr.toCharArray();

        boolean inComment = true;
        assert input[0] != '"';
        for (int i = 1; i < inputStr.length(); ++i) {
            // "\\""
            if (input[i] == '"') {
                int countOfBackSlach = 0;
                for (int j = i; j >= 0; --j) {
                    if (input[i] == '\\') {
                        countOfBackSlach += 1;
                    } else {
                        break;
                    }
                }
                if (countOfBackSlach % 2 == 0) {
                    if (inComment) {
                        quotesBegIdxs.add(i);
                        inComment = false;
                    } else {
                        quotesEndIdxs.add(i);
                        inComment = true;
                    }
                }
            }
        }
        for (int i = 0; i < input.length - 1; ++i) {
            if (input[i] == '/' && input[i + 1] == '/')  {
                boolean invertInQuotes = true;
                for (int j = 0; j < quotesEndIdxs.size(); ++j) {
                    if (i > quotesBegIdxs.get(j) && i < quotesEndIdxs.get(j)) {
                        invertInQuotes = false;
                        break;
                    }
                }

                if (invertInQuotes) {
                    int erasureLimiter = input.length - 1;
                    for (int j = i; j < input.length; ++j) {
                        if (input[j] == '\n') {
                            erasureLimiter = j;
                            break;
                        }
                    }
                    for (int j = i; j <= erasureLimiter; ++j) {
                        input[j] = ' ';
                    }
                }
            }
        }

        List<Integer> started = new ArrayList<>();
        List<Integer> ended = new ArrayList<>();
        boolean inverseInQuotes = true;
        assert input[0] != '"';

        for (int i = 1; i < input.length; ++i) {
            if (input[i] == '"' && input[i - 1] != '\\') {
                if (inverseInQuotes) {
                    started.add(i);
                    inverseInQuotes = false;
                } else {
                    ended.add(i);
                    inverseInQuotes = true;
                }
            }
        }
        assert started.size() == ended.size();

        for (int i = 0; i < started.size(); ++i) {
            for (int j = started.get(i) + 1; j < ended.get(i); ++j) {
                input[j] = '.'; // TODO strange place, think about it (in future)
            }
        }

        List<String> tokens = getSplitBySpc(String.valueOf(input));

        int ind = 0;
        for (int i = 1; i < tokens.size() - 1; ++i) {
            if (tokens.get(i - 1).equals("\"") && tokens.get(i + 1).equals("\"")) {
                tokens.set(i, inputStr.substring(started.get(ind) + 1, ended.get(ind)));
                ind += 1;
            }

        }
        return tokens;
    }

    private static List<String> getSplitBySpc(String input) {
        assert !input.contains("\t");

        String splitBySpecialSymbol = input
                .replace("\r", "")
                .replace("\n", "")
                .replace(";", " ; ")
                .replace("#", " # ")
                .replace("@", " @ ")
                .replace("\"", " \" ");
        List <String> splitSpace = new ArrayList<>(List.of(splitBySpecialSymbol.split(" ")));

        int size = splitSpace.size();
        for (int i = 0; i < size; ++i) {
            if (splitSpace.get(i).isEmpty()) {
                splitSpace.remove(i);
                --i;
            }
            size = splitSpace.size();
        }
        return splitSpace;
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