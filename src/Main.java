//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//
//public class Main {
//    public static void main(String[] args) throws IOException {
//          if (args.length < 1)  {
//              System.out.println("Provide file argument");
//          } else {
//              BufferedReader reader = new BufferedReader(new FileReader("file.txt"));
//              String line;
//              StringBuilder token = new StringBuilder();
//              while ((line = reader.readLine()) != null) {
//                  token.append(line);
//              }
//              Lexer.tokenize(String.valueOf(token)).iterator().forEachRemaining(t -> System.out.print("," + t.value()));
//              reader.close();
//          }
//    }
//}
public class Main {
    public static void main(String[] args) {
        Lexer.tokenize("#F_ARGS;\n@war a = 15;\n@war b = a + 2\n#+ x #42 #2\n@war b;\n@war a;\nreturn 0;").iterator().forEachRemaining(t -> System.out.print("," + t.value()));

    }
}