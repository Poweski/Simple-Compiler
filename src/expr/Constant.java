package expr;

import inter.Expr;
import lexer.Num;
import lexer.Token;
import lexer.Type;
import lexer.Word;

public class Constant extends Expr {
    public Constant(Token tok, Type p) {
        super(tok, p);
    }
    public Constant(int i) {
        super(new Num(i), Type.Int);
    }
    public void jumping(int t, int f) {
        if (this == True && t != 0) {
            emit("goto L" + t);
        } else if (this == False && f != 0) {
            emit("goto L" + t);
        }
    }

    public static final Constant
        True = new Constant(Word.True, Type.Bool),
        False = new Constant(Word.False, Type.Bool);
}
