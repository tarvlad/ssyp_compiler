import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lexer {

    public static List<String> tokenize(String input) {
        List<String> keywords = Arrays.stream(new String[]{"#", "@", "\"", ";"}).toList();
        List<String> space = Arrays.stream(new String[]{" ", "\n", "\r"}).toList();
        List<String> tokens = new ArrayList<>();

        LexerState state = LexerState.LEXING;
        int currentChar = 0;
        StringBuilder builder = new StringBuilder();
        while (currentChar < input.length()) {
            switch (state) {
                case STRING -> {
                    builder = new StringBuilder();

                    while (currentChar < input.length()) {
                        if (input.startsWith("\\\"", currentChar)) {
                            currentChar += 2;
                            builder.append("\\\"");
                        } else if (input.startsWith("\"", currentChar)) {
                            currentChar++;
                            break;
                        } else {
                            builder.append(input.charAt(currentChar));
                            currentChar++;
                        }
                    }

                    tokens.add("\"");
                    tokens.add(builder.toString());
                    tokens.add("\"");

                    state = LexerState.LEXING;
                }
                case LEXING -> {
                    builder = new StringBuilder();
                    while (currentChar < input.length()) {
                        if (input.startsWith("//", currentChar)) {
                            tokens.add(builder.toString());

                            currentChar += 2;
                            state = LexerState.COMMENT;
                            break;
                        } else if (space.contains(STR."\{input.charAt(currentChar)}")) {
                            if (!builder.isEmpty()) {
                                tokens.add(builder.toString());
                            }

                            currentChar++;
                            break;
                        } else if (keywords.contains(STR."\{input.charAt(currentChar)}")) {
                            if (input.charAt(currentChar) == '\"') {
                                state = LexerState.STRING;
                            }

                            if (!builder.isEmpty()) {
                                tokens.add(builder.toString());
                            }
                            tokens.add(STR."\{input.charAt(currentChar)}");

                            currentChar++;
                            break;
                        } else {
                            builder.append(input.charAt(currentChar++));
                        }
                    }

                }
                case COMMENT -> {
                    for (; input.charAt(currentChar) != '\n'; currentChar++) {
                    }
                    state = LexerState.LEXING;
                }
            }
        }

        return tokens;
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


enum LexerState {
    STRING,
    LEXING,
    COMMENT,
}
