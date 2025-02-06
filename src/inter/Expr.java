package inter;

import lexer.Token;
import lexer.Type;

public class Expr extends Node {
    public Token op;
    public Type type;

    public Expr(Token tok, Type p) {
        op = tok;
        type = p;
    }
    public Expr gen() {
        return this;
    }
    public Expr reduce() {
        return this;
    }
    public void jumping(int t, int f) {
        emitJumps(toString(), t, f);
    }
    public void emitJumps(String text, int t, int f) {
        if (t != 0 && f != 0) {
            emit("if " + text + " goto L" + t);
            emit("goto L" + f);
        } else if (t != 0) {
            emit("if " + text + " goto L"+ t);
        } else if (f != 0) {
            emit("ifFalse " + text + " goto L" + f);
        }
    }
    public String toString() {
        return op.toString();
    }
}
