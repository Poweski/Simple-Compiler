package inter;

import lexer.*;

public class Node {
    int lexLine = 0;
    static int labels = 0;

    Node() {
        lexLine = Lexer.line;
    }
    public void error(String s) {
        throw new Error("near line " + lexLine + ": " + s);
    }
    public int newLabel() {
        return ++labels;
    }
    public void emitLabel(int i) {
        System.out.print("L" + i + ":");
    }
    public void emit(String s) {
        System.out.println("\t" + s);
    }
}
