package lexer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

public class Lexer {
    private BufferedReader reader;
    public static int line = 1;
    char peek = ' ';
    Hashtable words = new Hashtable();

    public Lexer(String fileName) throws IOException {
        reserve(new Word("if", Tag.IF));
        reserve(new Word("else", Tag.ELSE));
        reserve(new Word("while", Tag.WHILE));
        reserve(new Word("do", Tag.DO));
        reserve(new Word("break", Tag.BREAK));
        reserve(Word.True);
        reserve(Word.False);
        reserve(Type.Int);
        reserve(Type.Char);
        reserve(Type.Bool);
        reserve(Type.Float);
        reader = new BufferedReader(new FileReader(fileName));
        readCh();
    }
    void reserve(Word w) {
        words.put(w.lexeme, w);
    }
    void readCh() throws IOException {
        int data = reader.read();
        if (data == -1) {
            peek = '\0';
            reader.close();
        } else {
            peek = (char) data;
        }
    }
    boolean readCh(char c) throws IOException {
        readCh();
        if (peek != c) {
            return false;
        }
        peek = ' ';
        return true;
    }
    public Token scan() throws IOException {
        while (peek != '\0') {
            if (peek == ' ' || peek == '\t' || peek == '\r') {
                readCh();
                continue;
            }
            if (peek == '\n') {
                readCh();
                line++;
            }
            else {
                break;
            }
        }
        switch (peek) {
            case '&':
                if (readCh('&')) {
                    return Word.and;
                } else {
                    return new Token('&');
                }
            case '|':
                if (readCh('|')) {
                    return Word.or;
                } else {
                    return new Token('|');
                }
            case '=':
                if (readCh('=')) {
                    return Word.eq;
                } else {
                    return new Token('=');
                }
            case '!':
                if (readCh('=')) {
                    return Word.ne;
                } else {
                    return new Token('!');
                }
            case '<':
                if (readCh('=')) {
                    return Word.le;
                } else {
                    return new Token('<');
                }
            case '>':
                if (readCh('=')) {
                    return Word.ge;
                } else {
                    return new Token('>');
                }
        }
        if (Character.isDigit(peek)) {
            int v = 0;
            do {
                v = 10 * v + Character.digit(peek, 10);
                readCh();
            } while (Character.isDigit(peek));
            if (peek != '.') {
                return new Num(v);
            }
            float x = v;
            float d = 10;
            for (;;) {
                readCh();
                if (!Character.isDigit(peek)) {
                    break;
                }
                x = x + Character.digit(peek, 10) / d;
                d *= 10;
            }
            return new Real(x);
        }
        if (Character.isLetter(peek)) {
            StringBuilder b = new StringBuilder();
            do {
                b.append(peek);
                readCh();
            } while (Character.isLetterOrDigit(peek));
            String s = b.toString();
            Word w = (Word)words.get(s);
            if (w != null) {
                return w;
            }
            w = new Word(s, Tag.ID);
            words.put(s, w);
            return w;
        }
        Token tok = new Token(peek);
        peek = ' ';
        return tok;
    }
}
