import java.io.IOException;
import lexer.Lexer;
import parser.Parser;

public class Main {
    public static void main(String[] args) throws IOException {
        Lexer lex = new Lexer("test.txt");
        Parser parse = new Parser(lex);
        try {
            parse.program();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}